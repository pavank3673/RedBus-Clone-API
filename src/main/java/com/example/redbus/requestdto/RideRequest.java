package com.example.redbus.requestdto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RideRequest {						

	@NotNull(message = "StartDateTime cannot be null")
	private LocalDateTime startDateTime;
	@NotNull(message = "EndDateTime cannot be null")
	private LocalDateTime endDateTime;
	@NotNull(message = "NoofTickets cannot be null")
	private int noOfTickets;
	@NotNull(message = "Destination cannot be null")
	@NotBlank(message = "Destination cannot be blank")
	private String destination;
	@NotNull(message = "Distance cannot be null")
	@NotBlank(message = "Distance cannot be blank")
	private String distance;
	@NotNull(message = "PickupPoint cannot be null")
	@NotBlank(message = "PickupPoint cannot be blank")
	private String pickupPoint;
	@NotNull(message = "Price cannot be null")
	private Double price;

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getPickupPoint() {
		return pickupPoint;
	}
	public void setPickupPoint(String pickupPoint) {
		this.pickupPoint = pickupPoint;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	
	
	
	
}
