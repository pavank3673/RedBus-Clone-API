package com.example.redbus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.redbus.entity.Traveller;

public interface TravellerRepository extends JpaRepository<Traveller, Integer>{

}
