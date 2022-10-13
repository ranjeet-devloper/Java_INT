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
	
	@Override
	public String toString() {
		return "UserLogin [Id=" + Id + ", user=" + user + ", token=" + token + ", status=" + status + ", createdat="
				+ createdat + "]";
	}

}
