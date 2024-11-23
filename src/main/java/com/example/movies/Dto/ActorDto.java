package com.example.movies.Dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import com.example.movies.Entity.Address;
import com.example.movies.Entity.Gender;
import com.example.movies.Entity.Movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActorDto {

	private Long id;
	private String name;
	private String profile;
	private Date birthDate;
	private Gender gender;
	private Address location;
	private String biography;

}
