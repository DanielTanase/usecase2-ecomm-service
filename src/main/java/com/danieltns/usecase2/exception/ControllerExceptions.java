package com.danieltns.usecase2.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;

@RestControllerAdvice
public class ControllerExceptions {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationEx(MethodArgumentNotValidException ex) {
		Map<String, String> responseBody = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(err -> {
			String field = err.getField();
			String message = err.getDefaultMessage();
			responseBody.put(field, message);
			
		});
		
		return responseBody;
	}
	
	@ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleIllegalArgumentEx(Exception ex) {
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("message", ex.getMessage());
		
		return responseBody;
	}
	
	@ExceptionHandler(FeignException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleFeignEx(FeignException ex) {		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
		
		HashMap<String, String> responseBody;
		try {
			responseBody = mapper.readValue(ex.contentUTF8(), typeRef);
		} catch (Exception e) {
			responseBody = new HashMap<>();
			responseBody.put("message", ex.getMessage());			
		}
		
		return responseBody;
	}
}
