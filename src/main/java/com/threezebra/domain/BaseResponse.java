package com.threezebra.domain;

public class BaseResponse {

	String response;
	String responseType;
	public BaseResponse(String response, String responseType) {
	  this.response=response;
	  this.responseType=responseType;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

}
