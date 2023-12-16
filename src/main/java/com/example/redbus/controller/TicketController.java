package com.example.redbus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.redbus.requestdto.TicketRequest;
import com.example.redbus.responsedto.TicketResponse;
import com.example.redbus.service.TicketService;
import com.example.redbus.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping("/users/{userId}/rides/{rideId}/seats/{seatId}/tickets")
	public ResponseEntity<ResponseStructure<TicketResponse>> saveTicket(@PathVariable int userId,
			@PathVariable int rideId, @PathVariable int seatId, @RequestBody @Valid TicketRequest ticketRequest) {
		return ticketService.saveTicket(userId,rideId,seatId, ticketRequest);
	}

}
