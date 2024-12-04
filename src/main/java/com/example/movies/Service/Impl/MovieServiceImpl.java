package com.example.movies.Service.Impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import com.example.movies.Dto.MovieRequestDto;
import com.example.movies.Dto.MovieResponseDto;
import com.example.movies.Entity.Actor;
import com.example.movies.Entity.Movie;
import com.example.movies.Exceptions.ResourceNotFoundException;
import com.example.movies.Repository.ActorRepository;
import com.example.movies.Repository.MovieRepository;
import com.example.movies.Service.MovieService;
import com.example.movies.Core.EntityMapper;
import com.example.movies.Core.EntityMapper.*;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService{
	
	private final MovieRepository movieRepository;
	private final ActorRepository actorRepository;
	private final EntityMapper entityMapper;

	@Override
	public List<MovieResponseDto> findAll() {
		return this.movieRepository.findAll()
				.stream()
				.map(movie -> entityMapper.toResponseMovie(movie))
				.toList();
	}
	
	@Override
	public List<MovieResponseDto> findByActorId(Long id) {
		return this.movieRepository.findByActorId(id)
				.stream().map(movie -> entityMapper.toResponseMovie(movie))
				.toList();
	}
	
	@Override
	public List<MovieResponseDto> findByTitle(String title) {
		return this.movieRepository.findByTitle("%"+title+"%")
				.stream().map(movie -> entityMapper.toResponseMovie(movie))
				.toList();
	}

	@Override
	public Page<MovieResponseDto> searchByTitl(String title, Pageable pageable) {
		return this.movieRepository.searchByTitle("%"+title+"%", pageable)
				.map(movie -> entityMapper.toResponseMovie(movie));
	}

	@Override
	public MovieResponseDto findById(Long id) {
		return this.movieRepository.findById(id)
				.map(movie -> entityMapper.toResponseMovie(movie))
				.orElseThrow(
						() -> new ResourceNotFoundException("Movie with id " + id + " is not found")
						);
	}

	@Override
	public MovieResponseDto create(MovieRequestDto movie) {
		Movie movieMapper = entityMapper.toEntityMovie(movie);
		Set<Actor> actorList = new HashSet<>();
		movie.getActors().forEach(idActors -> {
			Actor actorFound = this.actorRepository.findById(idActors).orElseThrow();
			actorList.add(actorFound);
		});
		movieMapper.setActors(actorList);
		Movie movieSaved = this.movieRepository.save(movieMapper);
		//Movie movieSaved = this.movieRepository.save(movie);
		return entityMapper.toResponseMovie(movieSaved);
	}

	@Override
	public MovieResponseDto update(Long id, MovieRequestDto movie) {
		Movie movieMapper = entityMapper.toEntityMovie(movie);
		return this.movieRepository.findById(id)
				.map(movieFound -> {
					if(movie.getJacket() == null) {
						movie.setJacket(movieFound.getJacket());
					}
					Set<Actor> actorList = new HashSet<>();
					movie.getActors().forEach(idActor -> {
						Actor actorFound = this.actorRepository.findById(idActor).orElseThrow();
						actorList.add(actorFound);
					});
					movieMapper.setActors(actorList);
					Movie movieSaved = this.movieRepository.save(movieMapper);
					return entityMapper.toResponseMovie(movieSaved);
				})
				.orElseThrow(
						() -> new ResourceNotFoundException("Movie with id " + id + " is not found")
						);
	}

	@Override
	public void delete(Long id) {
		this.movieRepository.deleteById(id);
	}

	@Override
	public List<MovieResponseDto> findMovieByType(String type) {
		return this.movieRepository.findMovieByType(type).stream()
				.map(movie -> entityMapper.toResponseMovie(movie))
				.toList();
	}

	
}
