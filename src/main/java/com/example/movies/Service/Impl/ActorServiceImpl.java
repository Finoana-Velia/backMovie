package com.example.movies.Service.Impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.movies.Entity.Actor;
import com.example.movies.Repository.ActorRepository;
import com.example.movies.Service.ActorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService{
	
	private final ActorRepository actorRepository;

	@Override
	public List<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	@Override
	public Page<Actor> searchByName(String name, Pageable pageable) {
		return this.actorRepository.searchByName(name,pageable);
	}

	@Override
	public Actor findById(Long id) {
		return this.actorRepository.findById(id).orElseThrow();
	}

	@Override
	public Actor save(Actor actor) {
		return this.actorRepository.save(actor);
	}

	@Override
	public Actor update(Long id,Actor actor) {
		return this.actorRepository.findById(id).map(
				actorUpdate -> this.actorRepository.save(actor) 
				).orElseThrow();
	}

	@Override
	public void delete(Long id) {
		this.actorRepository.deleteById(id);
	}

}
