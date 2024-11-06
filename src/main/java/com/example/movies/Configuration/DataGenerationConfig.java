package com.example.movies.Configuration;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.movies.Entity.Actor;
import com.example.movies.Entity.Address;
import com.example.movies.Entity.Gender;
import com.example.movies.Repository.ActorRepository;

@Configuration
public class DataGenerationConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(
			ActorRepository actorRepository
			) {
		return args -> {
			actorRepository.saveAll(generateActor());
		};
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
