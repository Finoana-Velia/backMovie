package com.example.movies.Exceptions;

public class NotAuthorizedException extends RuntimeException{

	public NotAuthorizedException(String message) {
		super(message);
	}
}
