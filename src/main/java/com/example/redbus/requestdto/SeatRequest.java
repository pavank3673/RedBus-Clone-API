package com.example.redbus.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SeatRequest {

	@NotNull(message = "Seat No cannot be null")
	@NotBlank(message = "Seat No cannot be blank")
	@Pattern(regexp = "^[0-9]*$", message = "Seat No should contain only numbers")
	private int seatNo;

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	
}
