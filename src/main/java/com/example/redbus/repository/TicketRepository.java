package com.example.redbus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.redbus.entity.Ride;
import com.example.redbus.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

	
	@Query("select t from Ticket t where t.ride =?1")
	public List<Ticket> getTickets(Ride ride);
	
}
