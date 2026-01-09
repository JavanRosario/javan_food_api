package com.javanfood.javanfood.api.exeptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.exeption.NegocioExeption;

@ControllerAdvice
public class ApiExeptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaExeption.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradoExeption(EntidadeNaoEncontradaExeption e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;

		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioExeption.class)
	public ResponseEntity<?> handleNegocioException(NegocioExeption e, WebRequest request) {
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}


	@ExceptionHandler(EntidadeEmUsoExeption.class)
	public ResponseEntity<?> handleEntidadeEmUsoExeption(EntidadeEmUsoExeption e, WebRequest request) {
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex,
			Object body,
			HttpHeaders headers,
			HttpStatusCode statusCode,
			WebRequest request) {

		if (body == null) {
			body = Problem.builder()
					.title(ex.getMessage())	
					.status(statusCode.value())
					.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.title((String) body)
					.status(statusCode.value())
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
	}

}
