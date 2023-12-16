package com.example.redbus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.redbus.entity.Bus;
import com.example.redbus.entity.Seat;

public interface SeatRepositoy extends JpaRepository<Seat, Integer>{

	@Query("select count(*) from Seat s where s.bus=?1")
	public int countofSeats(Bus bus);
	
	@Query("select s from Seat s where s.seatNo=?1 and s.bus=?2")
	public Optional<Seat> getSeat(int seatNo, Bus bus);
}
