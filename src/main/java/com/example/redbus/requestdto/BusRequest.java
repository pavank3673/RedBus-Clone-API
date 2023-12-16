package com.example.redbus.requestdto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BusRequest {

	@NotNull(message = "Bus No cannot be null")
	@NotBlank(message = "Bus No cannot be blank")
	@Pattern(regexp = "^[A-Za-z0-9]*$", message = "Bus no should contain only alphabets or numbers or both")
	private String busNo;
	@NotNull(message = "Manufacturer cannot be null")
	@NotBlank(message = "Manufacturer cannot be blank")
	private String manufacturer;
	@NotNull(message = "No of seats cannot be null")
	@Max(value = 60)
	@Min(value = 10)
	private int noOfSeats;
	public String getBusNo() {
		return busNo;
	}
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	
	
	
}
