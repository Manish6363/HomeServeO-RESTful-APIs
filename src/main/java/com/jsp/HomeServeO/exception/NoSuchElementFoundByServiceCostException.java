package com.jsp.HomeServeO.exception;

import lombok.Getter;

@Getter
public class NoSuchElementFoundByServiceCostException extends RuntimeException {
	private String message = " No such element found!";

	public NoSuchElementFoundByServiceCostException(String message) {
		super();
		this.message = message;
	}

	public NoSuchElementFoundByServiceCostException() {
		super();
	}

}
