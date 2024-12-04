package com.example.movies;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.example.movies.Dto.MovieResponseDto;
import com.example.movies.Entity.Movie;
import com.example.movies.Exceptions.ResourceNotFoundException;
import com.example.movies.Repository.MovieRepository;
import com.example.movies.Service.Impl.MovieServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MovieTestService {

	@Mock
	MovieRepository movieRepository;
	
	@InjectMocks
	MovieServiceImpl movieService;
	
	List<Movie> movies;
	PageRequest request;
	
	
	@BeforeEach
	void setUp() {
		movies = generateMovies();
		request = PageRequest.of(0, 10);
	}
	
	@Test
	@DisplayName("Test find all movies")
	void testFindAll() {
		when(movieRepository.findAll()).thenReturn(movies);
		List<MovieResponseDto> moviesResponse = movieService.findAll();
		assertAll(
				() -> assertNotNull(moviesResponse),
				() -> assertEquals("Avenger",moviesResponse.get(0).getTitle())
				);
	}
	
	@Test
	@DisplayName("Test search movie by title")
	void testSearchByTitle() {
		String title = "Mission Impossible";
		Movie movie = movies.get(1);
		when(movieRepository.searchByTitle(title, request))
		.thenReturn(new PageImpl<>(List.of(movie)));
		
		Page<MovieResponseDto> moviesResponse = movieService.searchByTitl(title, request); 
		
		assertAll(
				() -> assertNotNull(moviesResponse)
				);
		
		verify(movieRepository).searchByTitle(title,request);
	}
	
	@Test
	@DisplayName("Test search movie by an unknown title")
	void testSearchByUnknownTitle() {
		String title = "Doctor Strange";
		when(movieRepository.searchByTitle(title, request))
		.thenReturn(new PageImpl<>(new ArrayList<>()));
		
		Page<MovieResponseDto> movieResponse = movieService.searchByTitl(title, request);
		
		assertAll(
				() -> assertEquals(true,movieResponse.getContent().isEmpty())
				);
	}
	
	@Test
	@DisplayName("Test find movie by id")
	void testFindById() {
		long id = 1L;
		when(movieRepository.findById(id)).thenReturn(Optional.of(movies.get(0)));
		
		MovieResponseDto response = movieService.findById(id);
		
		assertAll(
				() -> assertNotNull(response),
				() -> assertEquals("Anvenger",response.getTitle())
				);
	}
	
	@Test
	@DisplayName("Test find movie by an unknown id")
	void testFindByUnknownId() {
		long id = 300L;
		when(movieRepository.findById(id))
		.thenReturn(Optional.empty());
		
		assertThrows(
				ResourceNotFoundException.class,
				() -> movieService.findById(id)
				);
		
	}
	
	List<Movie> generateMovies() {
		return List.of(
				Movie.builder()
					.title("Avenger")
					.jacket("image.jpg")
					.release(LocalDate.of(2019, 4, 26))
					.duration(LocalTime.of(2, 30))
					.type("Adventure")
				.build(),
				Movie.builder()
					.title("Mission Impossible")
					.jacket("image.jpg")
					.release(LocalDate.of(2018, 7, 25))
					.duration(LocalTime.of(3, 0))
					.type("Action")
				.build()
				);
	}
}
