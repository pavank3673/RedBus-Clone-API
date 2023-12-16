package com.example.redbus.service;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.redbus.entity.User;
import com.example.redbus.enums.UserRole;
import com.example.redbus.exception.UserNotFoundByIdException;
import com.example.redbus.repository.UserRepository;
import com.example.redbus.requestdto.UserRequest;
import com.example.redbus.responsedto.UserResponse;
import com.example.redbus.util.ResponseStructure;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ResponseStructure<UserResponse> responseStructure;
	
	@Autowired
	private ModelMapper mapper;
	
	
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRole userRole, UserRequest userRequest) {
		
		User user = mapper.map(userRequest, User.class);
		user.setUserRole(userRole);
		user = userRepo.save(user);
		
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User Added Successfully");
		responseStructure.setData(mapper.map(user, UserResponse.class));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, UserRequest userRequest) {
	
		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundByIdException("Failed to update the User"));
		user = mapper.map(userRequest, User.class);
		user.setUserId(userId);
		User savedUser = userRepo.save(user);
			
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("User Updated Successfully");
		responseStructure.setData(mapper.map(savedUser, UserResponse.class));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId){
		
		Optional<User> optional = userRepo.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();

			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("User Found Successfully");
			responseStructure.setData(mapper.map(user, UserResponse.class));
			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new UserNotFoundByIdException("Failed to fetch the user");
		}
		
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId){
		
		Optional<User> optional = userRepo.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			user.setDeleted(true);
			user.setDeletedDateTime(new Date(System.currentTimeMillis()- (1000 * 60 * 60 * 24 * 95L)));
			userRepo.save(user);
			
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("User Deleted Successfully");
			responseStructure.setData(mapper.map(user, UserResponse.class));
			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
		} else {
			throw new UserNotFoundByIdException("Failed To Delete The User");
		}
		
	}
	
	
}
