package com.diabtrkr.controllers.utils;

public class AppConstants {

	/**
	 * GLOBAL
	 */
	public static final String AUTHORIZATION_HEADER = "Authorization";

	/**
	 * APPLICATION SPECIFIC
	 */
	public static final String APPLICATION_BASE_PATH = "/auth";
	public static final String APPLICATION_REST_BASE_PATH = APPLICATION_BASE_PATH + "/rest";
	public static final String APPLICATION_NAME = "DiabTrkr Server";
	public static final String APPLICATION_PACKAGE_CONTROLLERS = "com.diabtrkr.controllers";

	/**
	 * JWT specific
	 */
	public static final long DAY_IN_MILLI_SECONDS = 24 * 60 * 60 * 1000;

	/**
	 * STATUS CODE
	 */
	public enum StatusCodes {
		NO_CONTENT(204), SUCCESS(200), CREATED(201), INVALID_REQUEST(210), UNAUTHORIZED(401), BAD_REQUEST(400),
		INTERNAL_SERVER_ERROR(500);

		private final int code;

		private StatusCodes(int code) {
			this.code = code;
		}

		public int get() {
			return code;
		}
	}
}
