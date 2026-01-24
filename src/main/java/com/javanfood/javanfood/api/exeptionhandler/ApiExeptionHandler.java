package com.javanfood.javanfood.api.exeptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.exeption.NegocioExeption;
import com.javanfood.javanfood.domain.exeption.ValidacaoExeption;
import com.javanfood.javanfood.domain.service.CadastroRestauranteService;

@ControllerAdvice
public class ApiExeptionHandler extends ResponseEntityExceptionHandler {

	private final CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private MessageSource messageSource;

	private static final String MSG_TIPO_INVALIDO_CLIENTE_FINAL = "A propiedade '%s' recebeu o valor '%s',que é de um tipo inválido. Corrija e informe um valor compatível com o tipo inteiro.";
	private static final String MSG_ERRO_GENERICA = "Ocorreu um erro interno inesperado no sistema. "
			+ "Tente novamente e se o problema persistir, entre em contato"
			+ " com o administrador do sistema";


	ApiExeptionHandler(CadastroRestauranteService cadastroRestauranteService) {
		this.cadastroRestauranteService = cadastroRestauranteService;
	}


	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleException(RuntimeException e, WebRequest webRequest) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		ProblemType problemType = ProblemType.ERRO_NO_SISTEMA;
		Problem problem = createProblemBuilder(httpStatus, problemType, MSG_ERRO_GENERICA)
				.userMessage(MSG_ERRO_GENERICA).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), httpStatus, webRequest);
	}


	@ExceptionHandler(EntidadeNaoEncontradaExeption.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradoException(EntidadeNaoEncontradaExeption e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeEmUsoExeption.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoExeption e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();


		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}


	@ExceptionHandler(ValidacaoExeption.class)
	protected ResponseEntity<Object> handleValidacaoExeption(
			ValidacaoExeption ex,
			WebRequest request) {

		return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(NegocioExeption.class)
	public ResponseEntity<?> handleNegocioException(NegocioExeption e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

	}


	@Override
	protected ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {

		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status,
					request);
		}

		return super.handleTypeMismatch(ex, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

		String userMessage = String.format(MSG_TIPO_INVALIDO_CLIENTE_FINAL, ex.getName(), ex.getValue(),
				ex.getRequiredType());

		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é um tipo inválido. "
				+ "Corrija e informe o valor compatível com o tipo %s", ex.getName(), ex.getValue(),
				ex.getRequiredType().getSimpleName());

		Problem problem = createProblemBuilder(httpStatus, problemType, detail).userMessage(userMessage).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
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
					.timeStamp(LocalDateTime.now())
					.title(ex.getMessage())
					.status(statusCode.value())
					.userMessage(MSG_ERRO_GENERICA)
					.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.timeStamp(LocalDateTime.now())
					.title((String) body)
					.status(statusCode.value())
					.userMessage(MSG_ERRO_GENERICA)
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}


	@Override
	protected ResponseEntity<Object> handleNoResourceFoundException(
			NoResourceFoundException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;

		String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente.",
				ex.getResourcePath());

		Problem problem = createProblemBuilder(httpStatus, problemType, detail).userMessage(detail).build();

		return handleExceptionInternal(ex, problem, headers, httpStatus, request);
	}

	private ResponseEntity<Object> handleValidationInternal(
			Exception ex,
			BindingResult bindingResult,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

		List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream().<Problem.Object>map(objectError -> {
			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			String name = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				name = ((FieldError) objectError).getField();
			}

			return Problem.Object.builder()
					.name(name)
					.userMessage(message)
					.build();
		})
				.collect(Collectors.toList());

		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.objects(problemObjects)
				.build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
		return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
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

		if (causaRaiz instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) causaRaiz, headers, status, request);
		}

		String userMessage = "Um ou mais campos estão incorretos. Verifique e tente novamente.";
		String detail = "O corpo da requisição está inválido. Verifique erro na sintaxe";
		ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;
		HttpStatus statusHttp = HttpStatus.BAD_REQUEST;
		Problem problem = createProblemBuilder(statusHttp, problemType, detail).userMessage(userMessage).build();

		return handleExceptionInternal(ex, problem, headers, statusHttp, request);
	}

	private ResponseEntity<Object> handlePropertyBindingException(
			PropertyBindingException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {

		String path = joinPath(ex.getPath());

		ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		String detail = String.format(
				"A propiedade %s não existe. Corrija ou remova essa propiedade e tente novamente",
				path);
		Problem problem = createProblemBuilder(httpStatus, problemType, detail)
				.userMessage(detail).build();


		return handleExceptionInternal(ex, problem, headers, status, request);

	}


	private String joinPath(List<Reference> references) {
		return references.stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
	}


	private ResponseEntity<Object> handleInvalidFormatException(
			InvalidFormatException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {

		String path = joinPath(ex.getPath());

		ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		String detail = String.format("A propiedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s",
				path, ex.getValue(), ex.getTargetType().getSimpleName());

		String userMessage = String.format(MSG_TIPO_INVALIDO_CLIENTE_FINAL,
				path, ex.getValue(), ex.getTargetType().getSimpleName());


		Problem problem = createProblemBuilder(httpStatus, problemType, detail).userMessage(userMessage).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(
			HttpStatus status,
			ProblemType problemType,
			String detail) {
		return Problem.builder()
				.timeStamp(LocalDateTime.now())
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);

	}


}
