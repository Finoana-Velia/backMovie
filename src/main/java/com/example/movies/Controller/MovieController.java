package com.example.movies.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movies.Entity.Movie;
import com.example.movies.Service.Impl.MovieServiceImpl;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
	
	private final MovieServiceImpl movieService;
	
	public MovieController(MovieServiceImpl movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<Movie>> searchByTitle(
			@RequestParam(defaultValue = "")String title,
			@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="10")int size
			){
		PageRequest request = PageRequest.of(page, size);
		Page<Movie> movies = this.movieService.searchByTitl(title, request);
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}
	
	@PostMapping
	public ResponseEntity<Movie> create(Movie movie) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(this.movieService.create(movie));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Movie> update(@PathVariable Long id,Movie movie) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(this.movieService.update(id, movie));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
