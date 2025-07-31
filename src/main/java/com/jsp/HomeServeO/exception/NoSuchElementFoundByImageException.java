package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class NoSuchElementFoundByImageException extends RuntimeException {
	private String message = " No such element found!";

	public NoSuchElementFoundByImageException(String message) {
		super();
		this.message = message;
	}

	public NoSuchElementFoundByImageException() {
		super();
	}
}
