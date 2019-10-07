package com.learning.service.movieinfo.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.learning.service.movieinfo.model.MovieError;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MovieNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
    public MovieError handleException(Exception ex, WebRequest request) {
		
		MovieError error;
		if (ex instanceof MovieNotFoundException) {
			error = new MovieError("MovieNotFound", ex.getMessage());
			
		} else {
			error = new MovieError("UnknownError", ex.getMessage());
		}
		
		return error;
		
	}
	
}
