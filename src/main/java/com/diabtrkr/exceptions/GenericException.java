package com.diabtrkr.exceptions;

import com.diabtrkr.controllers.utils.AppConstants.StatusCodes;

public class GenericException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	protected int statusCode = StatusCodes.BAD_REQUEST.get();

	public GenericException() {
		super("invalid arguments");
	}

	public GenericException(String message) {
		super(message);
	}

	public GenericException(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
