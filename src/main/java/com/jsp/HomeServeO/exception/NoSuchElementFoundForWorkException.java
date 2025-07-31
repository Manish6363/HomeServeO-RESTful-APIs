package com.jsp.HomeServeO.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class NoSuchElementFoundForWorkException extends RuntimeException {
	private String message = "No work found...!";

	public NoSuchElementFoundForWorkException(String message) {
		super();
		this.message = message;
	}

	public NoSuchElementFoundForWorkException() {
		super();
	}

}
