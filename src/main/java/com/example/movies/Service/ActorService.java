package com.example.movies.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.movies.Entity.Actor;

public interface ActorService {
	List<Actor> findAll();
	Page<Actor> searchByName(String name,Pageable pageable);
	Actor findById(Long id);
	Actor save(Actor actor);
	Actor update(Long id,Actor actor);
	void delete(Long id);
}
