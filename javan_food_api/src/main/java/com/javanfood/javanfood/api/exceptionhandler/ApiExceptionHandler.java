package com.javanfood.javanfood.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.javanfood.javanfood.domain.exception.EntidadeEmUsoException;
import com.javanfood.javanfood.domain.exception.EntidadeNaoEncontradaException;
import com.javanfood.javanfood.domain.exception.NegocioException;
import com.javanfood.javanfood.domain.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.URI;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    private static final String MSG_TIPO_INVALIDO_CLIENTE_FINAL = "A propiedade '%s' recebeu o valor '%s',que é de um tipo inválido. Corrija e informe um valor compatível com o tipo inteiro.";
    private static final String MSG_ERRO_GENERICA = "Ocorreu um erro interno inesperado no sistema. "
            + "Tente novamente e se o problema persistir, entre em contato"
            + " com o administrador do sistema";

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request) {

        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

        if (body == null) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(statusCode, MSG_ERRO_GENERICA);
            problemDetail.setTitle(ex.getMessage());
            problemDetail.setInstance(URI.create(requestUri));
            problemDetail.setProperty("timestamp", Instant.now());
            body = problemDetail;
        } else if (body instanceof String) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(statusCode, MSG_ERRO_GENERICA);
            problemDetail.setTitle((String) body);
            problemDetail.setInstance(URI.create(requestUri));
            problemDetail.setProperty("timestamp", Instant.now());
            body = problemDetail;
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
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

        String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente.", ex.getResourcePath());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, detail);
        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(URI.create(problemType.getUri()));
        problemDetail.setInstance(URI.create(requestUri));
        problemDetail.setProperty("code", problemType.getCode());
        problemDetail.setProperty("timestamp", Instant.now());

        return handleExceptionInternal(ex, problemDetail, headers, httpStatus, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
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

        HttpStatus statusHttp = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();
        String detail = "O corpo da requisição está inválido. Verifique erro na sintaxe.";

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(statusHttp, detail);
        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(URI.create(problemType.getUri()));
        problemDetail.setInstance(URI.create(requestUri));
        problemDetail.setProperty("code", problemType.getCode());
        problemDetail.setProperty("timestamp", Instant.now());

        return handleExceptionInternal(ex, problemDetail, headers, statusHttp, request);
    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(RuntimeException e, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_NO_SISTEMA;
        String requestUri = ((ServletWebRequest) webRequest).getRequest().getRequestURI();


        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, MSG_ERRO_GENERICA);
        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(URI.create(problemType.getUri()));
        problemDetail.setInstance(URI.create(requestUri));
        problemDetail.setProperty("timestamp", Instant.now());

        return handleExceptionInternal(e, problemDetail, new HttpHeaders(), httpStatus, webRequest);
    }


    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradoException(EntidadeNaoEncontradaException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(URI.create(problemType.getUri()));
        problemDetail.setInstance(URI.create(requestUri));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("code", problemType.getCode());

        return handleExceptionInternal(e, problemDetail, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(URI.create(problemType.getUri()));
        problemDetail.setInstance(URI.create(requestUri));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("code", problemType.getCode());

        return handleExceptionInternal(e, problemDetail, new HttpHeaders(), status, request);


    }


    @ExceptionHandler(ValidacaoException.class)
    protected ResponseEntity<Object> handleValidacaoException(
            ValidacaoException ex,
            WebRequest request) {

        return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(URI.create(problemType.getUri()));
        problemDetail.setInstance(URI.create(requestUri));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("code", problemType.getCode());

        return handleExceptionInternal(e, problemDetail, new HttpHeaders(), status, request);

    }


    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é um tipo inválido. "
                        + "Corrija e informe o valor compatível com o tipo %s",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, detail);

        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(URI.create(problemType.getUri()));
        problemDetail.setInstance(URI.create(requestUri));

        problemDetail.setProperty("code", problemType.getCode());
        problemDetail.setProperty("timestamp", Instant.now());

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }


    public record InvalidField(String name, String userMessage) {}

    private ResponseEntity<Object> handleValidationInternal(
            Exception ex,
            BindingResult bindingResult,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

        List<InvalidField> invalidFields = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return new InvalidField(name, message);
                })
                .collect(Collectors.toList());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);

        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(URI.create(problemType.getUri()));
        problemDetail.setInstance(URI.create(requestUri));

        problemDetail.setProperty("code", problemType.getCode());
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("objects", invalidFields);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(
            PropertyBindingException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

        String path = joinPath(ex.getPath());
        String detail = String.format(
                "A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.",
                path);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, detail);

        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(URI.create(problemType.getUri()));
        problemDetail.setInstance(URI.create(requestUri));

        problemDetail.setProperty("code", problemType.getCode());
        problemDetail.setProperty("timestamp", Instant.now());

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
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

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.CORPO_INCOMPREENSIVEL;
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

        String path = joinPath(ex.getPath());
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, detail);

        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(URI.create(problemType.getUri()));
        problemDetail.setInstance(URI.create(requestUri));

        problemDetail.setProperty("code", problemType.getCode());
        problemDetail.setProperty("timestamp", Instant.now());

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }




}
