package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class UpdationFailureException extends RuntimeException {
	private String message = "Updation Failed!";

	public UpdationFailureException(String message) {
		super();
		this.message = message;
	}

	public UpdationFailureException() {
		super();
	}
}
