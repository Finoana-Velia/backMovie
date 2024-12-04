package com.example.movies.actor;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.example.movies.Dto.ActorDto;
import com.example.movies.Entity.Actor;
import com.example.movies.Entity.Address;
import com.example.movies.Entity.Gender;
import com.example.movies.Exceptions.ResourceNotFoundException;
import com.example.movies.Repository.ActorRepository;
import com.example.movies.Service.Impl.ActorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ActorTestService {
	
	@Mock
	ActorRepository actorRepository;
	
	@InjectMocks
	ActorServiceImpl actorService;
	
	List<Actor> actorList = new ArrayList<>();
	
	PageRequest request = PageRequest.of(0,10);
	
	@BeforeEach
	void setUp() {
		actorList = generateActor();
	}

	
	@Test
	@DisplayName("Test find all actors")
	void testFindAll() {
		when(actorRepository.findAll()).thenReturn(actorList);
		List<ActorDto> actorListResponse = actorService.findAll();
		assertAll(
				() -> assertNotNull(actorListResponse),
				() -> assertEquals("Chris Evans",actorListResponse.get(0).getName())
		);
	}
	
	@Test
	@DisplayName("Test search actor by his name")
	void testSearchActor() {
		String name = "%Vin%";
		when(actorRepository.searchByName(name, request)).thenReturn(new PageImpl<>(actorList));
		Page<ActorDto> actorPage = actorService.searchByName(name, request);
		assertAll(
				() -> assertNotNull(actorPage.getContent())
				);
	}
	
	@Test
	@DisplayName("Test find actor by his id when it exists")
	void testFindActorByIdIfItExist() {
		long id = 1L;
		when(actorRepository.findById(id))
		.thenReturn(Optional.of(actorList.get(0)));
		ActorDto actorFound = actorService.findById(id);
		assertAll(
				() -> assertNotNull(actorFound),
				() -> assertEquals(Gender.MAN,actorFound.getGender())
				);
	}
	
	@Test
	@DisplayName("Test find by his id when it doesn't exist")
	void testFindActorByIdNotFound() {
		long id = 100L;
		when(actorRepository.findById(id))
		.thenThrow(ResourceNotFoundException.class);
		assertThrows(
				ResourceNotFoundException.class,
				() -> actorService.findById(id)
				);
	}
	
	private List<Actor> generateActor() {
		Actor diesel = Actor.builder()
				.profile(null)
				.name("Vin Diesel")
				.birthDate(new Date())
				.gender(Gender.MAN)
				.location(Address.builder()
						.street("Guantanamo")
						.nation("CUBA")
						.build())
				.build();
		Actor tom = Actor.builder()
				.profile(null)
				.name("Tom Cruse")
				.birthDate(new Date())
				.gender(Gender.MAN)
				.location(Address.builder()
						.street("New York")
						.nation("USA")
						.build())
				.build();
		Actor robert = Actor.builder()
				.profile(null)
				.name("Robert Downey Jr")
				.birthDate(new Date())
				.gender(Gender.MAN)
				.location(Address.builder()
						.street("Los Angeles")
						.nation("USA")
						.build())
				.build();
		Actor olsen = Actor.builder()
				.profile(null)
				.name("Elizabeth Olsen")
				.birthDate(new Date())
				.gender(Gender.WOMAN)
				.location(Address.builder()
						.street("San Francisco")
						.nation("USA")
						.build())
				.build();
		Actor scarlett = Actor.builder()
				.profile(null)
				.name("Scarlett Johanson")
				.birthDate(new Date())
				.gender(Gender.WOMAN)
				.location(Address.builder()
						.street("Boston")
						.nation("USA")
						.build())
				.build();
		Actor chris = Actor.builder()
				.profile(null)
				.name("Chris Evans")
				.birthDate(new Date())
				.gender(Gender.MAN)
				.location(Address.builder()
						.street("Texas")
						.nation("USA")
						.build())
				.build();
	return List.of(chris,olsen,scarlett,robert,diesel,tom);
	}

}
