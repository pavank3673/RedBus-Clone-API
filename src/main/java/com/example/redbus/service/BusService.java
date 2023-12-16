package com.example.redbus.service;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.redbus.entity.Bus;
import com.example.redbus.entity.Seat;
import com.example.redbus.entity.User;
import com.example.redbus.enums.UserRole;
import com.example.redbus.exception.BusDeletedException;
import com.example.redbus.exception.BusNotFoundByIdException;
import com.example.redbus.exception.SeatNotFoundBySeatNoAndBus;
import com.example.redbus.exception.UnAuthorizedAccessException;
import com.example.redbus.exception.UserNotFoundByIdException;
import com.example.redbus.repository.BusRepository;
import com.example.redbus.repository.SeatRepositoy;
import com.example.redbus.repository.UserRepository;
import com.example.redbus.requestdto.BusRequest;
import com.example.redbus.responsedto.BusResponse;
import com.example.redbus.util.ResponseStructure;

@Service
public class BusService {

	@Autowired
	private BusRepository busRepo;

	@Autowired
	private SeatRepositoy seatRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ResponseStructure<BusResponse> responseStructure;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<BusResponse>> saveBus( int userId, BusRequest busRequest) {
			Bus bus = mapper.map(busRequest, Bus.class);
			User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundByIdException("Failed to create a bus data."));
			bus.setUser(user);
			busRepo.save(bus);
			for (int i = 0; i < bus.getNoOfSeats(); i++) {
				Seat seat = new Seat();
				seat.setSeatNo(i + 1);
				seat.setBus(bus);
				seatRepo.save(seat);
			}
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Bus Added Successfully");
			responseStructure.setData(mapper.map(bus, BusResponse.class));
			return new ResponseEntity<ResponseStructure<BusResponse>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<BusResponse>> updateBus( int busId, BusRequest busRequest) {
		Bus exBus = busRepo.findById(busId)
				.orElseThrow(() -> new BusNotFoundByIdException("Failed to update the Bus data!"));
		User user = exBus.getUser();
		if (user.getUserRole().equals(UserRole.AGENT)) {
			int countofSeats = seatRepo.countofSeats(exBus);
			int lastSeatNo = countofSeats;
			if (busRequest.getNoOfSeats() > countofSeats) {
				for (int i = lastSeatNo + 1; i <= busRequest.getNoOfSeats(); i++) {
					Seat seat = new Seat();
					seat.setSeatNo(i);
					seat.setBus(exBus);
					seatRepo.save(seat);
				}
			} else if (busRequest.getNoOfSeats() < countofSeats) {
				for (int i = lastSeatNo; i > busRequest.getNoOfSeats(); i--) {
					Optional<Seat> optional = seatRepo.getSeat(i, exBus);
					if (optional.isPresent()) {
						Seat seat = optional.get();
						seatRepo.delete(seat);
						// new Date(System.currentTimeMillis());
					} else {
						throw new SeatNotFoundBySeatNoAndBus("Failed to delete the seat");
					}
				}
			}
			Bus bus = mapper.map(busRequest, Bus.class);
			bus.setBusId(busId);
			bus.setUser(user);
			Bus savedBus = busRepo.save(exBus);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Bus Updated Successfully");
			responseStructure.setData(mapper.map(savedBus, BusResponse.class));
			return new ResponseEntity<ResponseStructure<BusResponse>>(responseStructure, HttpStatus.OK);
		} else {
			throw new UnAuthorizedAccessException("Failed to update the bus data.");
		}

	}

	public ResponseEntity<ResponseStructure<BusResponse>> findBusById(int busId) {
		Bus bus = busRepo.findById(busId).orElseThrow(() -> new BusNotFoundByIdException("Failed to fetch the bus"));
		if (!bus.isDeleted()) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Bus Found Successfully");
			responseStructure.setData(mapper.map(bus, BusResponse.class));
			return new ResponseEntity<ResponseStructure<BusResponse>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new BusDeletedException("Failed to fetch the bus");
		}

	}

	public ResponseEntity<ResponseStructure<BusResponse>> deleteBus(int busId) {

		Bus bus = busRepo.findById(busId).orElseThrow(() -> new BusNotFoundByIdException("Failed to delete the bus"));
		User user = bus.getUser();
		if (user.getUserRole().equals(UserRole.AGENT)) {
			bus.setDeleted(true);
			bus.setDeletedDateTime(new Date(System.currentTimeMillis()));
			Bus savedBus = busRepo.save(bus);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Bus Deleted Successfully");
			responseStructure.setData(mapper.map(savedBus, BusResponse.class));
			return new ResponseEntity<ResponseStructure<BusResponse>>(responseStructure, HttpStatus.OK);
		} else {
			throw new UnAuthorizedAccessException("Failed to delete the bus data.");
		}

	}
}
