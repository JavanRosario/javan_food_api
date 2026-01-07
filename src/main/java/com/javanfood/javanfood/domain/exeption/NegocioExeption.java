package com.javanfood.javanfood.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegocioExeption extends RuntimeException {
	public NegocioExeption(String message) {
		super(message);
	}

	public NegocioExeption(String msg, Throwable causa) {
		super(msg, causa);
	}
}
