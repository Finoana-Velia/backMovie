package com.example.movies.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.movies.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long>{
	
	@Query("select m from Movie m where m.title like :x")
	Page<Movie> searchByTitle(@Param("x")String title,Pageable pageable);
	
	@Query("select m from Movie m where m.title like :x")
	List<Movie> findByTitle(@Param("x")String title);
	
}