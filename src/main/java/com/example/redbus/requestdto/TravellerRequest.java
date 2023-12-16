package com.example.redbus.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class TravellerRequest {

	@NotNull(message = "Traveller Name cannot be null")
	@NotBlank(message = "Traveller Name cannot be blank")
	private String travellerName;
	@NotNull(message = "Aadhaar Id cannot be null")
	@Pattern(regexp = "^[0-9]*$", message = "Aadhaar Id should contain only numbers")
	private Long aadhaarId;
	public String getTravellerName() {
		return travellerName;
	}
	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}
	public Long getAadhaarId() {
		return aadhaarId;
	}
	public void setAadhaarId(Long aadhaarId) {
		this.aadhaarId = aadhaarId;
	}
	
	
	
}
