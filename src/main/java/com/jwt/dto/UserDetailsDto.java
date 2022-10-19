package com.jwt.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlRegistry;

import org.springframework.lang.NonNull;

public class UserDetailsDto {
	@Size(min=2,max=16, message="name length should be 2 to 16")
	@NotEmpty
	@NotNull
	private String name;
	
	@NotNull
	@Size(min=10,max=10, message="mobile no. should be 10 digit")
    @Pattern(regexp = "^[0-9]{10}$")
	@NotBlank
	private String phone;
	

	@Email(message="email address is not valid!!")
	private String email;
	
	
	@NotEmpty
	@NotNull
	private String linkedIn;

	public UserDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDetailsDto(@Size(min = 2, max = 16, message = "name length should be 2 to 16") String name,
		@Size(min = 10, max = 10, message = "mobile no. should be 10 digit") @Pattern(regexp = "^[0-9]{10}$") String phone,
		@Email String email, String linkedIn) {
	super();
	
	this.name = name;
	this.phone = phone;
	this.email = email;
	this.linkedIn = linkedIn;
}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}	
	
}
