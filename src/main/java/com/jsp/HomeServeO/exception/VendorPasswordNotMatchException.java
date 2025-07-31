package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class VendorPasswordNotMatchException extends RuntimeException {
	private String message = "Incorrect Password Exception";

	public VendorPasswordNotMatchException(String message) {
		super();
		this.message = message;
	}

	public VendorPasswordNotMatchException() {
		super();
	}

}
