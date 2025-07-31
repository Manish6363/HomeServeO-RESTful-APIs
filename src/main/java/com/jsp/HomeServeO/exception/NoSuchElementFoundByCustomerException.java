package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class NoSuchElementFoundByCustomerException extends RuntimeException {
	private String message = " No such element found!";

	public NoSuchElementFoundByCustomerException(String message) {
		super();
		this.message = message;
	}

	public NoSuchElementFoundByCustomerException() {
		super();
	}
}
