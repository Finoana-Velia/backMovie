package com.example.movies;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieTestConroller {

	@Autowired
	MockMvc mock;
	
	String url;
	
	@BeforeEach
	void setUp() {
		this.url = "/api/v1/movies";
	}
	
	@Test
	@DisplayName("Test find all movies")
	void testFindAll() throws Exception{
		mock.perform(get(url)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Test search movie by title")
	void testSearchMovieByTitle() throws Exception{
		String title = "Avenger";
		mock.perform(get(url + "/search?title="+title))
		.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Test search movie by an unknown title")
	void testSearchMovieByUnknownTitle() throws Exception{
		String title = "Battle LA";
		mock.perform(get(url + "/search?title="+title))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.content.length()",is(0)));
	}
	
	@Test
	@DisplayName("Test find movie by id")
	void testFindById() throws Exception{
		long id = 1L;
		mock.perform(get(url+"/"+id)).andExpect(status().isOk())
		.andExpect(jsonPath("$.type",is("Adventure")));
	}
	
	@Test
	@DisplayName("Test find movie by an unknown id")
	void testFindByUnknownId() throws Exception{
		long id = 500L;
		mock.perform(get(url + "/" + id))
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.errorType", is("RESOURCE_NOT_FOUND")));
	}
}
