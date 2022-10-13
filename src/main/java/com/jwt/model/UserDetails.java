package com.jwt.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	 
	@OneToOne
	JwtRequest user;
	
	String name;
	String phone;
	String email;
	String linkedIn;
	LocalDateTime created_at;
	LocalDateTime updated_at;
	public UserDetails(Long id, JwtRequest user, String name, String phone, String email, String linkedIn,
			LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.linkedIn = linkedIn;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public JwtRequest getUser() {
		return user;
	}
	public void setUser(JwtRequest user) {
		this.user = user;
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
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", user=" + user + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", linkedIn=" + linkedIn + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
}
