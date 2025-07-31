package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class NoSuchListOfElementFoundByVendorException extends RuntimeException{
	private String message = "Work List not Found";

	public NoSuchListOfElementFoundByVendorException(String message) {
		super();
		this.message = message;
	}

	public NoSuchListOfElementFoundByVendorException() {
		super();
	}

}
