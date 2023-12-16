package com.example.redbus.requestdto;

import com.example.redbus.enums.TicketStatus;

import jakarta.validation.constraints.NotNull;

public class TicketRequest {

	@NotNull(message = "TicketStatus cannot be null")
	private TicketStatus ticketStatus;

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	
	
}
