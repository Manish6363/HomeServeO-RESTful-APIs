package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class NoSuchElementFoundByVendorException extends RuntimeException {
	private String message = " No such element found!";

	public NoSuchElementFoundByVendorException(String message) {
		super();
		this.message = message;
	}

	public NoSuchElementFoundByVendorException() {
		super();
	}

}
