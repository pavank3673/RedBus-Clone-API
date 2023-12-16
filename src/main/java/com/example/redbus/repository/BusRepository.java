package com.example.redbus.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.redbus.entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer>{

	@Query("from Bus where isDeleted = true and deletedDateTime <?1")
	public List<Optional<Bus>> findDeletedBus(Date dateTime);
	
}
