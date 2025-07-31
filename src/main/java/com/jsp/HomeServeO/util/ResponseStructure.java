package com.jsp.HomeServeO.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private LocalDateTime time = LocalDateTime.now();
	private String message;
	private int status;
	private T data;
}
