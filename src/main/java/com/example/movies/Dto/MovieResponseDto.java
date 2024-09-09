package com.example.movies.Dto;

import java.time.LocalTime;

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
}
