package com.example.movies.Dto;

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
	private String firstName;
	private String lastName;
	private String profile;

}
