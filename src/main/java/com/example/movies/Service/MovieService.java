package com.example.movies.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.movies.Entity.Movie;

public interface MovieService {
	List<Movie> findAll();
	List<Movie> findByTitle(String title);
	Page<Movie> searchByTitl(String title,Pageable pageable);
	Movie findById(Long id);
	Movie create(Movie movie);
	Movie update(Long id,Movie movie);
	void delete(Long id);
}
