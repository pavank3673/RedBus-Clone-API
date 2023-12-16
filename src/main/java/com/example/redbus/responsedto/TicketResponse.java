package com.example.redbus.responsedto;

import org.springframework.stereotype.Component;

@Component
public class TicketResponse {

	private int ticketId;
	private String ticketStatus;
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	
	
	
}
