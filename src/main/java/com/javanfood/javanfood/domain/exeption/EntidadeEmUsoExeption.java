package com.javanfood.javanfood.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoExeption extends NegocioExeption {
	public EntidadeEmUsoExeption(String msg) {
		super(msg);
	}
}
	