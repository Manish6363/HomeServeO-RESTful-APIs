package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class CustomerPasswordNotMatchException extends RuntimeException {
	private String message = "Incorrect Password Exception";

	public CustomerPasswordNotMatchException(String message) {
		super();
		this.message = message;
	}

	public CustomerPasswordNotMatchException() {
		super();
	}

}
