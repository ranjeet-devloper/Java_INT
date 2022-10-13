package com.jwt.model;

public class SaveUserDetailResponse {
	
	private String responseCode;
	private String responseMessage;
	public SaveUserDetailResponse(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	@Override
	public String toString() {
		return "SaveUserDetailResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage + "]";
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	

}
