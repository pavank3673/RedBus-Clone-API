package com.example.redbus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.redbus.service.TravellerService;

@RestController
public class TravellerController {

	@Autowired
	private TravellerService travellerService;
}
