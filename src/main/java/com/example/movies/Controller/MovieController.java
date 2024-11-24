package com.example.movies.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.movies.Core.FileUploader;
import com.example.movies.Dto.MovieRequestDto;
import com.example.movies.Dto.MovieResponseDto;
import com.example.movies.Entity.Movie;
import com.example.movies.Repository.MovieRepository;
import com.example.movies.Service.Impl.MovieServiceImpl;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
	
	private final MovieServiceImpl movieService;
	private final FileUploader fileUploader;
	private final MovieRepository movieRepository;
	
	public MovieController(
			MovieRepository movieRepository,
			MovieServiceImpl movieService,
			FileUploader fileUploader) {
		this.movieRepository = movieRepository;
		this.movieService = movieService;
		this.fileUploader = fileUploader;
	}
	
	@GetMapping
	public ResponseEntity<List<MovieResponseDto>> findAll() {
		List<MovieResponseDto> movies = this.movieService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}
	
	@GetMapping("/actor/{id}")
	public ResponseEntity<List<MovieResponseDto>> findByActorId(@PathVariable Long id) {
		List<MovieResponseDto> movies = this.movieService.findByActorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<MovieResponseDto>> searchByTitle(
			@RequestParam(defaultValue = "")String title,
			@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="10")int size
			){
		PageRequest request = PageRequest.of(page, size);
		Page<MovieResponseDto> movies = this.movieService.searchByTitl(title, request);
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}
	
	@GetMapping("/jacket")
	@ResponseBody
	public byte[] getJacket(Long id) throws Exception {
		File file = this.fileUploader.getFile(id,"movie");
		return IOUtils.toByteArray(new FileInputStream(file));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		MovieResponseDto movie = this.movieService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(movie);
	}
	
	@PostMapping
	public ResponseEntity<MovieResponseDto> create(
			MovieRequestDto movie,
			@RequestParam MultipartFile file
			)  throws Exception{
		MovieResponseDto movieResponse;
		if(!file.isEmpty()) {
			movie.setJacket(file.getOriginalFilename());
			movieResponse = this.movieService.create(movie);
			this.fileUploader.registerFile(file, "movie", movieResponse.getId());
		}else {
			movieResponse = this.movieService.create(movie);
		}
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(movieResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovieResponseDto> update(
			@PathVariable Long id,
			MovieRequestDto movie,
			@RequestParam(required=false) MultipartFile file
			) throws Exception{
		MovieResponseDto movieResponse;
		if(file != null) {
			movie.setJacket(file.getOriginalFilename());
			movieResponse = this.movieService.update(id,movie);
			this.fileUploader.updateFile(file, "movie", id);
		}else {
			movieResponse = this.movieService.update(id, movie);
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(movieResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.movieService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
