package com.example.redbus.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {

	@NotNull(message = "username cannot be null")
	@NotBlank(message = "username cannot be blank")
	private String userName;
	@NotNull(message = "phone number cannot be null")
	@Min(value = 6000000000l, message = "Phone number is not valid")
	@Max(value = 9999999999l, message = "Phone number is not valid")
	private Long userPhNo;
	@NotNull(message = "email cannot be null")
	@NotBlank(message = "email cannot be blank")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String userEmail;
	@NotNull(message = "password cannot be null")
	@NotBlank(message = "password cannot be blank")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	private String userPassword;

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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
