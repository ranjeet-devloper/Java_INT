package com.jwt.model;

import java.util.List;

public class UserDetailsResponse {
	
	private int responseCode;
	private String responseMessage;
	private List<UserDetails> data;
	public UserDetailsResponse(int responseCode, String responseMessage, List<UserDetails> listOfUserDetail) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.data = listOfUserDetail;
	}
	public UserDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public List<UserDetails> getData() {
		return data;
	}
	public void setData(List<UserDetails> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "UserDetailsResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage + ", data="
				+ data + "]";
	}
	
	

}
