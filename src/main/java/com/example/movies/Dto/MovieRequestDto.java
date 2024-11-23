package com.example.movies.Dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequestDto {
	
	private Long id;
	private String jacket;
	private String title;
	private LocalTime duration;
	private String release;
	private String type;
	private String description;
	private List<Long> actors;

}

