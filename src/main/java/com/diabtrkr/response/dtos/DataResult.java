package com.diabtrkr.response.dtos;

import java.util.List;

import com.diabtrkr.controllers.utils.AppConstants.StatusCodes;

public class DataResult<T> {
	public boolean successful = false;
	public String message = "";
	public int statusCode = StatusCodes.BAD_REQUEST.get();
	public T entity;
	public List<T> entities;

	public DataResult(boolean successful, String message, int statusCode, T entity) {
		this.successful = successful;
		this.message = message;
		this.statusCode = statusCode;
		this.entity = entity;
	}

//	public DataResult(boolean successful, String message, int statusCode, List<T> entities) {
//		this.successful = successful;
//		this.message = message;
//		this.statusCode = statusCode;
//		this.entities = entities;
//	}

	public DataResult(boolean successful, String message, int statusCode) {
		this.successful = successful;
		this.message = message;
		this.statusCode = statusCode;
	}

}
