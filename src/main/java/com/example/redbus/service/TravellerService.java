package com.example.redbus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.redbus.repository.TravellerRepository;
import com.example.redbus.responsedto.TravellerResponse;
import com.example.redbus.util.ResponseStructure;

@Service
public class TravellerService {

	@Autowired
	private TravellerRepository travellerRepo;
	
	@Autowired
	private ResponseStructure<TravellerResponse> responseStructure;
}
