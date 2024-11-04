package com.example.movies.Core;

import org.springframework.stereotype.Service;

import com.example.movies.Dto.ActorDto;
import com.example.movies.Dto.MovieRequestDto;
import com.example.movies.Dto.MovieResponseDto;
import com.example.movies.Entity.Actor;
import com.example.movies.Entity.Movie;

@Service
public class EntityMapper {
	
	
	public MovieResponseDto toResponseMovie(Movie movieEntity) {
		return MovieResponseDto.builder()
				.id(movieEntity.getId())
				.jacket(movieEntity.getJacket())
				.title(movieEntity.getTitle())
				.duration(movieEntity.getDuration())
				.build();
	}
	
	public ActorDto toDtoActor(Actor actor) {
		return ActorDto.builder()
				.id(actor.getId())
				.profile(actor.getProfile())
				.name(actor.getName())
				.birthDate(actor.getBirthDate())
				.gender(actor.getGender())
				.location(actor.getLocation())
				.biography(actor.getBiography())
				.build();
	}
	
	public Actor toEntityActor(ActorDto actor) {
		return Actor.builder()
				.id(actor.getId())
				.profile(actor.getProfile())
				.name(actor.getName())
				.birthDate(actor.getBirthDate())
				.gender(actor.getGender())
				.location(actor.getLocation())
				.biography(actor.getBiography())
				.build();
	}
	
	public Movie toEntityMovie(MovieRequestDto movieRequest) {
		return Movie.builder()
				.id(movieRequest.getId())
				.jacket(movieRequest.getJacket())
				.title(movieRequest.getTitle())
				.duration(movieRequest.getDuration())
				.build();
	}
	
	

}
