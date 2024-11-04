package com.example.movies.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.movies.Entity.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long>{
	
	@Query("select a from Actor a where a.name like :x")
	Page<Actor> searchByName(@Param("x")String name,Pageable pageable);

}
