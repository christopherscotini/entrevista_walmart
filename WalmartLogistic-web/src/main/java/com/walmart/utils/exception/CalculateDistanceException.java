package com.walmart.utils.exception;

public class CalculateDistanceException extends Exception {

	private static final long serialVersionUID = -4727389602773424782L;

	public CalculateDistanceException(String message) {
		super(message);
	}

	public CalculateDistanceException(Throwable cause) {
		super(cause);
	}

	public CalculateDistanceException(String message, Throwable cause) {
		super(message, cause);
	}

}
