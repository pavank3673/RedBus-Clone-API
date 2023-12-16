package com.example.redbus.responsedto;

import org.springframework.stereotype.Component;

@Component
public class UserResponse {

	private int userId;
	private String userName;
	private Long userPhNo;
	private String userEmail;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserPhNo() {
		return userPhNo;
	}
	public void setUserPhNo(Long userPhNo) {
		this.userPhNo = userPhNo;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	
}
