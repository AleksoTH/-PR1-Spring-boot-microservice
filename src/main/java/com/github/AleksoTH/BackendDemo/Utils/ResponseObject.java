package com.github.AleksoTH.BackendDemo.Utils;

import org.springframework.http.HttpStatus;

public class ResponseObject {
	private String message;
    private int status;
    
	public ResponseObject(String string, HttpStatus status) {
		setMessage(string);
		setStatus(status.value());
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}

