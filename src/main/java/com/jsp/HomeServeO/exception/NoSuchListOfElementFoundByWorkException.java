package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class NoSuchListOfElementFoundByWorkException extends RuntimeException {
	private String message = "Work List not Found";

	public NoSuchListOfElementFoundByWorkException(String message) {
		super();
		this.message = message;
	}

	public NoSuchListOfElementFoundByWorkException() {
		super();
	}
}
