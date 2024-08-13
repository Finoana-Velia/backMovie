package com.example.movies.Service.Impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.movies.Entity.Movie;
import com.example.movies.Repository.MovieRepository;
import com.example.movies.Service.MovieService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService{
	
	private final MovieRepository movieRepository;

	@Override
	public List<Movie> findAll() {
		return this.movieRepository.findAll();
	}

	@Override
	public List<Movie> findByTitle(String title) {
		return this.movieRepository.findByTitle("%"+title+"%");
	}

	@Override
	public Page<Movie> searchByTitl(String title, Pageable pageable) {
		return this.movieRepository.searchByTitle("%"+title+"%", pageable);
	}

	@Override
	public Movie findById(Long id) {
		return this.movieRepository.findById(id).orElseThrow();
	}

	@Override
	public Movie create(Movie movie) {
		return this.movieRepository.save(movie);
	}

	@Override
	public Movie update(Long id, Movie movie) {
		return this.movieRepository.findById(id).map(
				movieUpdate -> this.movieRepository.save(movie)
				).orElseThrow();
	}

	@Override
	public void delete(Long id) {
		this.movieRepository.deleteById(id);
	}

}
