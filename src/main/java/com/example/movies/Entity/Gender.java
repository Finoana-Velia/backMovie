package com.example.movies.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
	MAN("Man"),WOMAN("Woman"),OTHER("Other");
	
	private final String value;
}
