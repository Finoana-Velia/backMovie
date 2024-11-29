package com.example.movies.Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.example.movies.Core.ErrorResponse;
import com.example.movies.Core.ErrorType;
import com.example.movies.Exceptions.DuplicateEntryException;
import com.example.movies.Exceptions.NotAuthorizedException;
import com.example.movies.Exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class ResponseControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> resourceNotFound(ResourceNotFoundException exception) {
		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", exception.getMessage());
		
		ErrorResponse errorResponse = ErrorResponse.builder()
				.statusCode(404)
				.errorType(ErrorType.RESOURCE_NOT_FOUND)
				.details(errorMessage)
				.suggestion("Please make sure that all informations that you have entered are corrects!")
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(errorResponse);
	}
	
	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<ErrorResponse> resourceAlreadyExists(DuplicateEntryException exception) {
		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", exception.getMessage());
		
		ErrorResponse response = ErrorResponse.builder()
				.statusCode(400)
				.errorType(ErrorType.RESOURCE_ALREADY_EXISTS)
				.details(errorMessage)
				.suggestion("Resource already exists, please use other informations")
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(response);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> inputValidationException(ConstraintViolationException exception) {
		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", exception.getMessage());
		
		ErrorResponse response = ErrorResponse.builder()
				.statusCode(400)
				.errorType(ErrorType.INPUT_MISMATCH)
				.details(errorMessage)
				.suggestion("Please make sure that your data are corrects!")
				.timestamp(LocalDateTime.now())
				.build();
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(response);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException exception) {
		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", exception.getMessage());
		
		ErrorResponse response = ErrorResponse.builder()
				.statusCode(400)
				.errorType(ErrorType.INPUT_MISMATCH)
				.suggestion("Please ensure that the given value is correct")
				.timestamp(LocalDateTime.now())
				.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(response);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ErrorResponse> ioException(IOException exception) {
		Map<String,String> errorMessage = new HashMap<>();
		errorMessage.put("error",exception.getMessage());
		
		ErrorResponse response = ErrorResponse.builder()
				.statusCode(400)
				.errorType(ErrorType.FILE_UPLOAD_ERROR)
				.details(errorMessage)
				.suggestion("Please ensure that your file match with requirements!")
				.timestamp(LocalDateTime.now())
				.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(response);
	}
	
	@ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ErrorResponse> resourceNotAuthorized(NotAuthorizedException exception) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("error", exception.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(500)
                .errorType(ErrorType.NOT_AUTHORIZED)
                .details(errorMessage)
                .suggestion("You do not have permission to this resource")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorResponse);
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ErrorResponse> badCredentialsException() {
//        Map<String, String> errorMessage = new HashMap<>();
//        errorMessage.put("error", "Input auth incorrect");
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .statusCode(401)
//                .errorType(ErrorType.INPUT_MISMATCH)
//                .details(errorMessage)
//                .suggestion("Please ensure that the information you have entered are corrects")
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(errorResponse);
//    }
	
}
