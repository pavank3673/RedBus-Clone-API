package com.example.redbus.responsedto;

import org.springframework.stereotype.Component;

@Component
public class TravellerResponse {

	private int travellerId;
	private String travellerName;
	private Long aadhaarId;
	public int getTravellerId() {
		return travellerId;
	}
	public void setTravellerId(int travellerId) {
		this.travellerId = travellerId;
	}
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
