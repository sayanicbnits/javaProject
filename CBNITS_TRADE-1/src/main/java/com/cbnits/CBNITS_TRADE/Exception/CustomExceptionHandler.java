package com.cbnits.CBNITS_TRADE.Exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cbnits.CBNITS_TRADE.SecurityJwt.Models.ResponseMessage;
import com.fasterxml.jackson.core.JsonParseException;

import io.jsonwebtoken.MalformedJwtException;


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

//	
	 @ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeException(RuntimeException ex){ 
		 
//		 return ResponseEntity.badRequest().body("Invalid Details");
//		 , HttpHeaders headers, HttpStatus status, WebRequest request
		  Map<String, Object> errors = new HashMap<>();
		  List<String> message = new ArrayList<String>();
		  message.add("Invalid username");
		  message.add("Invalid password");
		  
		  ErrorDetails errorDetails = new ErrorDetails( "Validation Failed" , message);
		  errors.put("Errors", errorDetails);
		    return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(MalformedJwtException.class)
		public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex){ 
			 
			  
			  ErrorDetails errorDetails = new ErrorDetails();
			  errorDetails.setMessage("Bad Credentials");
			    return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
		 }
	 
	 @ExceptionHandler(MaxUploadSizeExceededException.class)
	  public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
   
	 
	 }
}
  
//  @ExceptionHandler(UsernameNotFoundException.class)
//  @ResponseBody
//  public ResponseEntity<Object> UsernameNotFoundException(UsernameNotFoundException bindException, HttpHeaders headers, HttpStatus status, WebRequest request){ 
//	  List<String> message = new ArrayList();
//	  message.add("Invalid username");
//	  message.add("Invalid password");
//	  ErrorDetails errorDetails = new ErrorDetails( "Validation Failed",message);
//	    return new ResponseEntity<Object>("Invalid User", HttpStatus.BAD_REQUEST);
// }
 
  
