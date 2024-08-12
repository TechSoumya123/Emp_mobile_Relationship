package com.developer.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> employeeNotFound(EmployeeNotFoundException employeeNotFoundException) {
		Map<String, String> error = new HashMap<>();
		error.put("error", employeeNotFoundException.getMessage());
		return error;
	}
	
	@ExceptionHandler(NoMobileFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> mobileNotFound(NoMobileFoundException noMobileFoundException) {
		Map<String, String> error = new HashMap<>();
		error.put("error", noMobileFoundException.getMessage());
		return error;
	}
}
