package com.javanfood.javanfood.domain.exception;

public class NegocioException extends RuntimeException {
	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(String msg, Throwable causa) {
		super(msg, causa);
	}
}
