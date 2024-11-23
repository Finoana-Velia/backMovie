package com.example.movies.Dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import com.example.movies.Entity.Actor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponseDto {
	private Long id;
	private String jacket;
	private String title;
	private LocalTime duration;
	private LocalDate release;
	private String type;
	private String description;
	private Set<Actor> actors;
}
