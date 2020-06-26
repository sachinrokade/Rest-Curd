package com.example.aop;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.exception.CustomErrorResponse;
import com.example.exception.ListEmptyException;
import com.example.exception.NotFoundStudent;

@RestControllerAdvice
public class ControllerAdivice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ListEmptyException.class)
	public ResponseEntity<CustomErrorResponse> handleListValid(ListEmptyException ex, WebRequest request)
			throws Exception {

		CustomErrorResponse exp = new CustomErrorResponse();
		exp.setError(ex.getMessage());
		exp.setStatus(HttpStatus.NO_CONTENT.value());
		exp.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<CustomErrorResponse>(exp, HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(NotFoundStudent.class)
	public ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundStudent ex, WebRequest request)
			throws Exception {

		CustomErrorResponse exp = new CustomErrorResponse();
		exp.setError(ex.getMessage());
		exp.setStatus(HttpStatus.NOT_FOUND.value());
		exp.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<CustomErrorResponse>(exp, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorResponse exp = new CustomErrorResponse();
		exp.setError(ex.getBindingResult().getFieldError().getDefaultMessage().toString());
		exp.setStatus(HttpStatus.BAD_REQUEST.value());
		exp.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(exp, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		return new ResponseEntity<Object>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
