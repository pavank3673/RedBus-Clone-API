package com.example.redbus.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.redbus.entity.Ticket;
import com.example.redbus.entity.User;
import com.example.redbus.exception.UserNotFoundByIdException;
import com.example.redbus.repository.RideRepository;
import com.example.redbus.repository.SeatRepositoy;
import com.example.redbus.repository.TicketRepository;
import com.example.redbus.repository.UserRepository;
import com.example.redbus.requestdto.TicketRequest;
import com.example.redbus.responsedto.TicketResponse;
import com.example.redbus.util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private ResponseStructure<TicketResponse> responseStructure;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired
	private RideRepository rideRepo;
	
	@Autowired
	private SeatRepositoy seatRepo;
	
	
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<TicketResponse>> saveTicket(int userId, int rideId, int seatId, @Valid TicketRequest ticketRequest) {

		Ticket ticket = mapper.map(ticketRequest, Ticket.class);
		
		User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundByIdException("Failed to create a bus data."));
		
		ticket.setUser(user);
		
		ticketRepo.save(ticket);
		
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Ticket Added Successfully");
		responseStructure.setData(mapper.map(ticket, TicketResponse.class));
		return new ResponseEntity<ResponseStructure<TicketResponse>>(responseStructure, HttpStatus.CREATED);
		
	}
}
