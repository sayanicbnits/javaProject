package com.cbnits.CBNITS_TRADE.Exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;

import java.net.BindException;
import java.util.*;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.validation.FieldError;
import java.util.stream.Collectors;
import org.springframework.validation.ObjectError;


@ControllerAdvice

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//      HttpHeaders headers, HttpStatus status, WebRequest request) {
//    ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",ex.getBindingResult().toString());
//    System.out.println(errorDetails);
//    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//  }
	
//		@Override
//		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//				HttpHeaders headers, HttpStatus status, WebRequest request) {
//			
//			Map<String, String> errors = new HashMap<>();
//					 
//				ex.getBindingResult().getAllErrors().forEach((error) ->{
//				String fieldName = ((FieldError) error).getField();
//				String message = error.getDefaultMessage();
////				errors.put("error", "true");
//				errors.put(fieldName, message);
//			});
//			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//		}
	
	@Override  
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)   
	{  
//		Map<String, String> body = new HashMap<>();
		Map<String, Object> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError) error).getField();
			List<String> message = ex.getBindingResult()
			        .getFieldErrors()
			        .stream()
			        .map(DefaultMessageSourceResolvable::getDefaultMessage)
			        .collect(Collectors.toList());
//			String mess = error.getDefaultMessage();
			
			ErrorDetails err = new ErrorDetails( "Validation Failed", message ); 
			errors.put("errors", err);
			 errors.put("error", true);
			
			});

	//returning exception structure and specific status  
	return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);  
	}  
	

	public String isValid() {
		return Boolean.TRUE.toString();
	}
	
	
	
  
//  @ExceptionHandler(BindException.class)
//  @ResponseBody
//  public ResponseEntity<Object> bindExceptionHandler(BindException bindException, HttpHeaders headers, HttpStatus status, WebRequest request){         
//	  ErrorDetails errorDetails = new ErrorDetails( new Date(),"Validation Failed",bindException.getMessage());
//	    return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
// }
 
  
}