package com.example.movies.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.movies.Dto.ActorDto;
import com.example.movies.Entity.Actor;

public interface ActorService {
	List<ActorDto> findAll();
	Page<ActorDto> searchByName(String name,Pageable pageable);
	ActorDto findById(Long id);
	ActorDto save(ActorDto actor);
	ActorDto update(Long id,ActorDto actor);
	void delete(Long id);
}
