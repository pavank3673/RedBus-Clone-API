package com.example.redbus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Traveller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int travellerId;
	private String travellerName;
	private Long travellerAadhaarId;
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
	public Long getTravellerAadhaarId() {
		return travellerAadhaarId;
	}
	public void setTravellerAadhaarId(Long travellerAadhaarId) {
		this.travellerAadhaarId = travellerAadhaarId;
	}
	
	
	
}
