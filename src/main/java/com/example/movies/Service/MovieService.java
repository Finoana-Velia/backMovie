package com.example.movies.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.movies.Dto.MovieRequestDto;
import com.example.movies.Dto.MovieResponseDto;
import com.example.movies.Entity.Movie;

public interface MovieService {
	List<MovieResponseDto> findAll();
	List<MovieResponseDto> findByTitle(String title);
	Page<MovieResponseDto> searchByTitl(String title,Pageable pageable);
	MovieResponseDto findById(Long id);
	MovieResponseDto create(MovieRequestDto movie);
	MovieResponseDto update(Long id,MovieRequestDto movie);
	void delete(Long id);
}
