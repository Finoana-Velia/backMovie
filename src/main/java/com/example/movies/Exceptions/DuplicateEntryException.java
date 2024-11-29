package com.example.movies.Exceptions;

@SuppressWarnings("serial")
public class DuplicateEntryException extends RuntimeException{

	public DuplicateEntryException(String message) {
		super(message);
	}
}
