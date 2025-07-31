package com.jsp.HomeServeO.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class NoSuchListOfElementFoundByCustomerException extends RuntimeException {
	private String message = " No such element found!";

	public NoSuchListOfElementFoundByCustomerException(String message) {
		super();
		this.message = message;
	}

	public NoSuchListOfElementFoundByCustomerException() {
		super();
	}
}
