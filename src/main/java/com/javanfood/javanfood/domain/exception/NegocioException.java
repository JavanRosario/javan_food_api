package com.javanfood.javanfood.domain.exception;

import com.javanfood.javanfood.domain.model.StatusPedido;

public class NegocioException extends RuntimeException {
	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(String msg, Throwable causa) {
		super(msg, causa);
	}

	public NegocioException(String format, Long id, StatusPedido statusPedido, StatusPedido statusPedido1) {
	}
}
