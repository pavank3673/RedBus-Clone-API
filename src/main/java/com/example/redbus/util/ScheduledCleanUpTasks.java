package com.example.redbus.util;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.redbus.entity.Ride;
import com.example.redbus.entity.Ticket;
import com.example.redbus.entity.User;
import com.example.redbus.enums.RideStatus;
import com.example.redbus.enums.TicketStatus;
import com.example.redbus.repository.RideRepository;
import com.example.redbus.repository.TicketRepository;
import com.example.redbus.repository.UserRepository;


@Component
public class ScheduledCleanUpTasks {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RideRepository rideRepo;

	@Autowired
	private TicketRepository ticketRepo;

	@Scheduled(cron = "0 * * * * *")
	public void deleteUsers() {
		Date date = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 90L));
		Optional<List<User>> optional = userRepo.findDeletedUser(date);
		if(optional.isPresent()) {
			List<User> users = optional.get();
			users.forEach(System.out::print);
			if(!users.isEmpty()) {
				userRepo.deleteAll(users);
			}
		}

	}

	@Scheduled(fixedDelay = (1000*60))
	public void updateStatusOfStartedRides() {
			System.err.println("Ride Status updating to Started");
		List<Ride> rides = rideRepo.findRideByStartTime(LocalDateTime.now());
		for(Ride ride: rides) {
			List<Ticket> tickets = ticketRepo.getTickets(ride);
			for(Ticket ticket:tickets) {
				ticket.setTicketStatus(TicketStatus.ACTIVE);
				ticketRepo.save(ticket);
			}
			ride.setRideStatus(RideStatus.STARTED);
			rideRepo.save(ride);
		}
	}

	@Scheduled(fixedDelay = (1000*60))
	public void updateStatusOfCompletedRides() {
			System.err.println("Ride status updating to Completed");
		List<Ride> rides = rideRepo.findRideByEndTime(LocalDateTime.now());
		for (Ride ride : rides) {
			List<Ticket> tickets = ticketRepo.getTickets(ride);
			for(Ticket ticket: tickets) {
				ticket.setTicketStatus(TicketStatus.EXPIRED);
				ticketRepo.save(ticket);
			}
			ride.setRideStatus(RideStatus.COMPLETED);
			rideRepo.save(ride);
		}
	}

}
