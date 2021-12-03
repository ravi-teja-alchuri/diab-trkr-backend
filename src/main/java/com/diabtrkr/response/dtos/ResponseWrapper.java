package com.diabtrkr.response.dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diabtrkr.controllers.utils.AppConstants.StatusCodes;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ResponseWrapper {

	/***
	 * <li>Creates {@code DataResult} object by wrapping parameters into it.</li>
	 * <li>Creates {@code ResponseEntity} object by wrapping {@code DataResult},
	 * {@code HttpStatus} into it.</li>
	 * <li>sets the {@code HttpStatus} value as customStatusCode</li>
	 * 
	 * @param successful
	 * @param result
	 * @param httpStatus
	 * @param entity
	 * @return
	 */
	public static ResponseEntity<DataResult> wrapResponse(boolean successful, String message, HttpStatus httpStatus,
			Object entity) {
		return new ResponseEntity<DataResult>(new DataResult(successful, message, httpStatus.value(), entity),
				httpStatus);
	}

	/***
	 * <li>Creates {@code DataResult} object by wrapping parameters into it.</li>
	 * <li>Creates {@code ResponseEntity} object by wrapping {@code DataResult},
	 * {@code HttpStatus} into it.</li>
	 * 
	 * @param successful
	 * @param result
	 * @param custom     StatusCode
	 * @param httpStatus
	 * @return
	 */
	public static ResponseEntity<DataResult> wrapResponse(boolean successful, String message, int customStatusCode,
			HttpStatus httpStatus) {
		return new ResponseEntity<DataResult>(new DataResult(successful, message, customStatusCode), httpStatus);
	}

	/**
	 * <li>Invalid input response</li>
	 * <li>Creates {@code DataResult} object by wrapping parameters into it.</li>
	 * <li>Creates {@code ResponseEntity} object by wrapping {@code DataResult},
	 * {@code HttpStatus} into it.</li>
	 * <li>sets the {@code HttpStatus} value as customStatusCode</li>
	 * 
	 * @param successful
	 * @param result
	 * @param httpStatus
	 * @return
	 * @return ResponseEntity<DataResult>
	 */
	public static ResponseEntity<DataResult> wrapInvalidInputResponse() {
		return new ResponseEntity<DataResult>(
				new DataResult(false, "Invalid input, Mandatory fields are missing", StatusCodes.BAD_REQUEST.get()),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * Success response
	 * <li>Creates {@code DataResult} object by wrapping parameters into it.</li>
	 * <li>Creates {@code ResponseEntity} object by wrapping {@code DataResult},
	 * {@code HttpStatus} into it.</li>
	 * <li>sets the {@code HttpStatus} value as customStatusCode</li>
	 * 
	 * @param <T>
	 * 
	 * @param entity
	 * 
	 * @return ResponseEntity<DataResult>
	 */
	public static <T> ResponseEntity<DataResult> wrapSuccessResponse(T entity) {
		return new ResponseEntity<DataResult>(new DataResult(true, "Success", StatusCodes.SUCCESS.get(), entity),
				HttpStatus.OK);

	}

	/**
	 * 
	 * Success response
	 * <li>Creates {@code DataResult} object by wrapping parameters into it.</li>
	 * <li>Creates {@code ResponseEntity} object by wrapping {@code DataResult},
	 * {@code HttpStatus} into it.</li>
	 * <li>sets the {@code HttpStatus} value as customStatusCode</li>
	 * 
	 * @param <T>
	 * 
	 * @param entities
	 * 
	 * @return ResponseEntity<DataResult>
	 */
	public static <T> ResponseEntity<DataResult> wrapSuccessResponse(List<T> entities) {
		return new ResponseEntity<DataResult>(new DataResult(true, "Success", StatusCodes.SUCCESS.get(), entities),
				HttpStatus.OK);
	}

	/**
	 * Success response
	 * <li>Creates {@code DataResult} object by wrapping parameters into it.</li>
	 * <li>Creates {@code ResponseEntity} object by wrapping {@code DataResult},
	 * {@code HttpStatus} into it.</li>
	 * <li>sets the {@code HttpStatus} value as customStatusCode</li>
	 * 
	 * @param message
	 * 
	 * @return ResponseEntity<DataResult>
	 */
	public static ResponseEntity<DataResult> wrapSuccessResponse(String message) {
		return new ResponseEntity<DataResult>(new DataResult(true, message, StatusCodes.SUCCESS.get()), HttpStatus.OK);
	}

	/**
	 * No content response
	 * <li>Creates {@code DataResult} object by wrapping parameters into it.</li>
	 * <li>Creates {@code ResponseEntity} object by wrapping {@code DataResult},
	 * {@code HttpStatus} into it.</li>
	 * <li>sets the {@code HttpStatus} value as customStatusCode</li>
	 * 
	 * @param entities
	 * 
	 * @return ResponseEntity<DataResult>
	 */
	public static ResponseEntity<DataResult> wrapNoContentResponse() {
		return new ResponseEntity<DataResult>(
				new DataResult(true, "No content", StatusCodes.NO_CONTENT.get(), new ArrayList<Object>(1)),
				HttpStatus.OK);
	}

	/**
	 * un authorized response
	 * <li>Creates {@code DataResult} object by wrapping parameters into it.</li>
	 * <li>Creates {@code ResponseEntity} object by wrapping {@code DataResult},
	 * {@code HttpStatus} into it.</li>
	 * <li>sets the {@code HttpStatus} value as customStatusCode</li>
	 * 
	 * @param entities
	 * 
	 * @return ResponseEntity<DataResult>
	 */
	public static ResponseEntity<DataResult> wrapUnauthorizedResponse() {
		return new ResponseEntity<DataResult>(
				new DataResult(false, "Unauthorized request", StatusCodes.UNAUTHORIZED.get()), HttpStatus.UNAUTHORIZED);
	}
}
