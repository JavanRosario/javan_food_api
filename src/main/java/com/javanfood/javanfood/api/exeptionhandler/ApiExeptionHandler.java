package com.javanfood.javanfood.api.exeptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.exeption.NegocioExeption;

@ControllerAdvice
public class ApiExeptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaExeption.class)
	public ResponseEntity<?> tratarEstadoNaoEncontradoExeption(EntidadeNaoEncontradaExeption e) {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.menssagem(e.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
	}

	@ExceptionHandler(NegocioExeption.class)
	public ResponseEntity<?> tratarNegocio(NegocioExeption e) {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.menssagem(e.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}

	@ExceptionHandler(HttpMediaTypeException.class)
	public ResponseEntity<?> tratarHttpMediaTypeException() {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.menssagem("O tipo de mídia não é aceito")
				.build();
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problema);
	}

	@ExceptionHandler(EntidadeEmUsoExeption.class)
	public ResponseEntity<?> tratarEntidadeEmUsoExeption(EntidadeEmUsoExeption e) {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.menssagem(e.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
	}


}
