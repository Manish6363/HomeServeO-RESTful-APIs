package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class CustomerEmailNotMatchException extends RuntimeException {
	private String message = "Incorrect Email Exception";

	public CustomerEmailNotMatchException(String message) {
		super();
		this.message = message;
	}

	public CustomerEmailNotMatchException() {
		super();
	}
}
