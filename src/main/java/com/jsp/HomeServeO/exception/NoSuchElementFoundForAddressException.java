package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class NoSuchElementFoundForAddressException extends RuntimeException {
	private String message = " No such element found!";

	public NoSuchElementFoundForAddressException(String message) {
		super();
		this.message = message;
	}

	public NoSuchElementFoundForAddressException() {
		super();
	}
}
