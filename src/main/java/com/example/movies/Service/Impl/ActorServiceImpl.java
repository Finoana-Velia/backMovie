package com.example.movies.Service.Impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.movies.Core.EntityMapper;
import com.example.movies.Dto.ActorDto;
import com.example.movies.Entity.Actor;
import com.example.movies.Repository.ActorRepository;
import com.example.movies.Service.ActorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService{
	public final ActorRepository actorRepository;
	public final EntityMapper entityMapper;
	
	@Override
	public List<ActorDto> findAll() {
		return this.actorRepository.findAll()
				.stream()
				.map(actors -> entityMapper.toDtoActor(actors))
				.toList();
	}

	@Override
	public Page<ActorDto> searchByName(String name, Pageable pageable) {
		return this.actorRepository.searchByName(name, pageable)
				.map(actors -> entityMapper.toDtoActor(actors));
	}

	@Override
	public ActorDto findById(Long id) {
		return this.actorRepository.findById(id)
				.map(actor -> entityMapper.toDtoActor(actor))
				.orElseThrow();
	}

	@Override
	public ActorDto save(ActorDto actor) {
		Actor actorSaved = entityMapper.toEntityActor(actor);
		this.actorRepository.save(actorSaved);
		return entityMapper.toDtoActor(actorSaved);
	}

	@Override
	public ActorDto update(Long id, ActorDto actor) {
		Actor actorFind = this.actorRepository.findById(id).orElseThrow();
		if(actor.getProfile() == null) {
			actor.setProfile(actorFind.getProfile());
		}
		Actor actorMapper = entityMapper.toEntityActor(actor);
		Actor actorUpdated = this.actorRepository.save(actorMapper);
		return entityMapper.toDtoActor(actorUpdated);
	}

	@Override
	public void delete(Long id) {
		this.actorRepository.deleteById(id);
	}

	
	
	

}
