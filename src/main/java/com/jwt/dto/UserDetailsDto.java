package com.jwt.dto;

public class UserDetailsDto {

	Long id;
	String name;
	String phone;
	String email;
	String linkedIn;

	public UserDetailsDto(Long id, String name, String phone, String email, String linkedIn) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.linkedIn = linkedIn;
	}


	public UserDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
