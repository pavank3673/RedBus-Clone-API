package com.example.redbus.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.redbus.entity.Bus;
import com.example.redbus.entity.Ride;
import com.example.redbus.entity.Seat;
import com.example.redbus.entity.Ticket;
import com.example.redbus.entity.User;
import com.example.redbus.enums.RideStatus;
import com.example.redbus.enums.TicketStatus;
import com.example.redbus.exception.BusNotFoundByIdException;
import com.example.redbus.exception.DestinationCannotBeAlteredException;
import com.example.redbus.exception.RideCannotBeCreatedForStartDateTime;
import com.example.redbus.exception.RideDeletedException;
import com.example.redbus.exception.RideNotFoundByIdException;
import com.example.redbus.exception.UserNotFoundByIdException;
import com.example.redbus.repository.BusRepository;
import com.example.redbus.repository.RideRepository;
import com.example.redbus.repository.TicketRepository;
import com.example.redbus.requestdto.RideRequest;
import com.example.redbus.responsedto.RideResponse;
import com.example.redbus.responsedto.UserResponse;
import com.example.redbus.util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class RideService {

	@Autowired
	private RideRepository rideRepo;

	@Autowired
	private BusRepository busRepo;
	
	@Autowired
	private TicketRepository ticketRepo;

	@Autowired
	private ResponseStructure<RideResponse> responseStructure;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<RideResponse>> saveRide(int busId, @Valid RideRequest rideRequest) {
		Ride ride = mapper.map(rideRequest, Ride.class);
		Bus bus = busRepo.findById(busId).orElseThrow(() -> new BusNotFoundByIdException("Failed to save the ride"));
		
		if(rideRequest.getEndDateTime().isBefore(LocalDateTime.now())) {
			throw new RideCannotBeCreatedForStartDateTime("Ride start time is after end time");
		}
		
		 Optional<Ride> optional2 = rideRepo.findRideByBusAndStartTimeBetweenGivenTime(bus,LocalDateTime.now(), LocalDateTime.now().plusHours(24l));
		 if(optional2.isPresent()) {
			 throw new RideCannotBeCreatedForStartDateTime("Ride cannot be created for the requested start time");
		 }
		
		LocalDateTime updatedStartTime = rideRequest.getStartDateTime().minusMinutes(10l);
		LocalDateTime updatedEndTime = rideRequest.getEndDateTime().plusMinutes(10l);
		 Optional<Ride> optional = rideRepo.findRideByBusAndStartTimeAndEndTime(bus, updatedStartTime, updatedEndTime);
		 if(optional.isPresent()) {
			 throw new RideCannotBeCreatedForStartDateTime("Ride cannot be created for the requested start time");
		 }
		
		ride.setBus(bus);
		ride.setAvailableSeats(bus.getNoOfSeats());
		ride.setRideStatus(RideStatus.BOOKED);
		Ride savedRide = rideRepo.save(ride);
		for (int i = 0; i < ride.getNoOfTickets(); i++) {
			Ticket ticket = new Ticket();
			ticket.setTicketStatus(TicketStatus.BOOKED);
			ticket.setRide(ride);
			ticketRepo.save(ticket);
		}
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Ride Added Successfully");
		responseStructure.setData(mapper.map(savedRide, RideResponse.class));
		return new ResponseEntity<ResponseStructure<RideResponse>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<RideResponse>> updateRide(int rideId, @Valid RideRequest rideRequest) {

		Ride ride = rideRepo.findById(rideId)
				.orElseThrow(() -> new RideNotFoundByIdException("Failed to update the ride"));
		if (!ride.getDestination().equals(rideRequest.getDestination())) {
			throw new DestinationCannotBeAlteredException("Failed to update the ride");
		}
//			Ride mappedRide = mapper.map(rideRequest, Ride.class);
		Ride mappedRide = mapToRide(rideRequest, ride);
		mappedRide.setRideId(rideId);
		Ride savedRide = rideRepo.save(mappedRide);
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Ride Updated Successfully");
		responseStructure.setData(mapper.map(savedRide, RideResponse.class));
		return new ResponseEntity<ResponseStructure<RideResponse>>(responseStructure, HttpStatus.OK);
	}

	private Ride mapToRide(RideRequest rideRequest, Ride exRide) {
			exRide.setDistance(rideRequest.getDistance());
			exRide.setEndDateTime(rideRequest.getEndDateTime());
			exRide.setPickupPoint(rideRequest.getPickupPoint());
			exRide.setPrice(rideRequest.getPrice());
			exRide.setStartDateTime(rideRequest.getStartDateTime());
			return exRide;
	}

	public ResponseEntity<ResponseStructure<RideResponse>> findRideById(int rideId) {

		Ride ride = rideRepo.findById(rideId).orElseThrow(() -> new RideNotFoundByIdException("Failed to fetch the ride"));
		if (ride.getRideStatus()!=RideStatus.CANCELLED) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Ride Found Successfully");
			responseStructure.setData(mapper.map(ride, RideResponse.class));
			return new ResponseEntity<ResponseStructure<RideResponse>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new RideDeletedException("Failed to fetch the ride");
		}

	}

	public ResponseEntity<ResponseStructure<RideResponse>> cancelRide(int rideId) {

		Optional<Ride> optional = rideRepo.findById(rideId);
		if (optional.isPresent()) {
			Ride ride = optional.get();

			ride.setRideStatus(RideStatus.CANCELLED);
			rideRepo.save(ride);
			
			for(Ticket ticket: ticketRepo.getTickets(ride)) {
				ticket.setTicketStatus(TicketStatus.CANCELLED);
				ticketRepo.save(ticket);
			}
			
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Ride Cancelled Successfully");
			responseStructure.setData(mapper.map(ride, RideResponse.class));
			return new ResponseEntity<ResponseStructure<RideResponse>>(responseStructure, HttpStatus.OK);
		} else {
			throw new RideNotFoundByIdException("Failed to cancel the ride");
		}
	}

}
