package com.diabtrkr.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.diabtrkr.controllers.utils.AppConstants.StatusCodes;
import com.diabtrkr.response.dtos.DataResult;
import com.mongodb.MongoException;

/**
 * Different exception handlers
 * 
 * @author Ravi Teja
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LogManager.getLogger();

	private static final int BAD_REQUEST_CODE = StatusCodes.BAD_REQUEST.get();

	/**
	 * Exception handler for Custom exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { GenericException.class })
	public ResponseEntity<DataResult<String>> customExceptionHandler(final GenericException genericException) {
		logger.error(genericException);
		return new ResponseEntity<DataResult<String>>(
				new DataResult<String>(false, genericException.getMessage(), genericException.getStatusCode()),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception handler for variable validations
	 * 
	 * @param manvException
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DataResult<String>> handleValidationExceptions(
			final MethodArgumentNotValidException manvException) {
		logger.error(manvException);
		final String msg = this.getDefaultMessageFromBindingResult(manvException.getBindingResult());
		return new ResponseEntity<DataResult<String>>(new DataResult<String>(false, msg, BAD_REQUEST_CODE),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Global Exception handler for Mongo
	 * 
	 * @param mongoException
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MongoException.class)
	public ResponseEntity<DataResult<String>> handleMongoExceptions(final MongoException mongoException) {
		logger.error(mongoException);
		final String msg = mongoException.getMessage();
		return new ResponseEntity<DataResult<String>>(new DataResult<String>(false, msg, BAD_REQUEST_CODE),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Processor for joining all the validation errors
	 * 
	 * @param bindingResult
	 * @return
	 */
	private String getDefaultMessageFromBindingResult(final BindingResult bindingResult) {
		final List<String> brList = new ArrayList<String>();
		final List<ObjectError> objectErrorList = bindingResult.getAllErrors();
		objectErrorList.forEach((error) -> {
			brList.add(((FieldError) error).getDefaultMessage());
		});
		return String.join(", ", brList);
	}

	/**
	 * Default Exception handler
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<DataResult<String>> sendResponse(final Exception exception) {
		logger.error(exception);
		return new ResponseEntity<DataResult<String>>(
				new DataResult<String>(false, exception.getMessage(), BAD_REQUEST_CODE), HttpStatus.BAD_REQUEST);
	}
}
