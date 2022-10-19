package com.jwt.model;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
public class UserLogin {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long Id;
	
	@OneToOne
	JwtRequest user;
	String token;
	String status;
	LocalDateTime createdat;
	public UserLogin( JwtRequest user, String token, String status, LocalDateTime createdat) {
		super();
		this.user = user;
		this.token = token;
		this.status = status;
		this.createdat = createdat;
	}
	
	
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public JwtRequest getUser() {
		return user;
	}

	public void setUser(JwtRequest user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}

	@Override
	public String toString() {
		return "UserLogin [Id=" + Id + ", user=" + user + ", token=" + token + ", status=" + status + ", createdat="
				+ createdat + "]";
	}

}
