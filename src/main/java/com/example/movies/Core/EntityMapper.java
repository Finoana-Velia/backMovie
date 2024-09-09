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
				.firstName(actor.getFirstName())
				.lastName(actor.getLastName())
				.build();
	}
	
	public Actor toEntityActor(ActorDto actorDto) {
		return Actor.builder()
				.id(actorDto.getId())
				.profile(actorDto.getProfile())
				.firstName(actorDto.getFirstName())
				.lastName(actorDto.getLastName())
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
