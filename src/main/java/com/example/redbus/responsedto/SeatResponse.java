package com.example.redbus.responsedto;

import org.springframework.stereotype.Component;

@Component
public class SeatResponse {

	private int seatId;
	private int seatNo;
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	
	
}
