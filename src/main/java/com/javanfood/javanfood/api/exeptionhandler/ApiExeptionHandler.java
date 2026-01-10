package com.javanfood.javanfood.api.exeptionhandler;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
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

	@ExceptionHandler(EntidadeEmUsoExeption.class)
	public ResponseEntity<?> handleEntidadeEmUsoExeption(EntidadeEmUsoExeption e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;

		Problem problem = createProblemBuilder(status, problemType, detail).build();


		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}


	@ExceptionHandler(NegocioExeption.class)
	public ResponseEntity<?> handleNegocioException(NegocioExeption e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;

		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

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


	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {


		Throwable causaRaiz = ex.getCause();

		if (causaRaiz instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) causaRaiz, headers, status, request);
		}

		String detail = "O corpo da requisição está inválido. Verifique erro na sintaxe";
		ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;
		HttpStatus statusHttp = HttpStatus.BAD_REQUEST;
		Problem problem = createProblemBuilder(statusHttp, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}


	private ResponseEntity<Object> handleInvalidFormatException(
			InvalidFormatException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {

		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));

		ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		String detail = String.format("A propiedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s",
				path, ex.getValue(), ex.getTargetType().getSimpleName());

		Problem problem = createProblemBuilder(httpStatus, problemType, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
	}

}
