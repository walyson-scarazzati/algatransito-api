package com.algaworks.algatransito.api.exceptionhandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algatransito.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	private final MessageSource messageSource;
	
	@ExceptionHandler(NegocioException.class)
	public ProblemDetail handleNegocio(NegocioException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setDetail(e.getMessage());
		problemDetail.setType(URI.create("https://algatransito.com/erros/regra-de-negocio"));
		
		return problemDetail;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(status);
		problemDetail.setTitle("Um ou mais campos estão inválidos");
		problemDetail.setType(URI.create("https://algatransito.com/erros/campos-invalidos"));

		Map<String, String> fields = ex.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                		objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));

        problemDetail.setProperty("fields", fields);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}
	

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ProblemDetail handleDataIntegrity(DataIntegrityViolationException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
		problemDetail.setTitle("Recurso está em uso");
		problemDetail.setType(URI.create("https://algatransito.com/erros/recurso-em-uso"));
		return problemDetail;
	}
	
}
