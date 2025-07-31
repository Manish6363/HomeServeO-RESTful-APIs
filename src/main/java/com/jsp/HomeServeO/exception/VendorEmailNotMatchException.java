package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class VendorEmailNotMatchException extends RuntimeException {

	private String message = "Incorrect Email Exception";

	public VendorEmailNotMatchException(String message) {
		super();
		this.message = message;
	}

	public VendorEmailNotMatchException() {
		super();
	}

}
