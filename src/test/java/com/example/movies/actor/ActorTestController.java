package com.example.movies.actor;

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
public class ActorTestController {
	
	@Autowired
	MockMvc mock;
	
	String url;
	
	@BeforeEach
	void setUp() {
		this.url = "/api/v1/actor";
	}
	
	@Test
	@DisplayName("Test find all actor")
	void findAllActor() throws Exception {
		mock.perform(get(this.url)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Test find actor by his name")
	void findActorByName() throws Exception {
		String name = "Vin";
		mock.perform(get(this.url+"/search?name="+name)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Test search actor by unknown name")
	void findActorByUncknownName() throws Exception {
		String name = "Densel";
		mock.perform(get(this.url+"/search?name="+name))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.content.length()",is(0)));
	}
	
	
	@Test
	@DisplayName("Test find actor by his id")
	void findActorById() throws Exception{
		long id = 1L;
		mock.perform(get("/api/v1/actor/"+id))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id",is(1)));
	}
	
	@Test
	@DisplayName("Test find actor by an unknown id")
	void findActorByUnknownId() throws Exception{
		long id = 100L;
		mock.perform(get("/api/v1/actor/"+id))
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.errorType", is("RESOURCE_NOT_FOUND")));
	}
	
	

}
