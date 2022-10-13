package com.jwt.model;

public class JwtResponse {
	
	int responsecode;
	String responsemessage;
	String token;
	public JwtResponse(int responsecode, String responsemessage, String token) {
		super();
		this.responsecode = responsecode;
		this.responsemessage = responsemessage;
		this.token = token;
	}
	public int getResponsecode() {
		return responsecode;
	}
	public void setResponsecode(int responsecode) {
		this.responsecode = responsecode;
	}
	public String getResponsemessage() {
		return responsemessage;
	}
	public void setResponsemessage(String responsemessage) {
		this.responsemessage = responsemessage;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "JwtResponse [responsecode=" + responsecode + ", responsemessage=" + responsemessage + ", token=" + token
				+ "]";
	}
	
	

}
