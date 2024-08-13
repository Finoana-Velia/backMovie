package com.example.movies.Controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movies.Entity.Actor;
import com.example.movies.Service.Impl.ActorServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/actor")
@AllArgsConstructor
public class ActorController {
	
	private final ActorServiceImpl actorService;
	
	@GetMapping
	public ResponseEntity<List<Actor>> findAll() {
		List<Actor> actors = this.actorService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(actors);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<Actor>> searchByName(
			@RequestParam(defaultValue="")String name,
			@RequestParam(defaultValue="0")int page,
			@RequestParam(defaultValue="10")int sizw
			){
		PageRequest request = PageRequest.of(page, page);
		Page<Actor> actors = this.actorService.searchByName("%" + name + "%",request);
		return ResponseEntity.status(HttpStatus.OK).body(actors);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Actor> findById(@PathVariable Long id) {
		Actor actor = this.actorService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(actor);
	}
	
	@PostMapping
	public ResponseEntity<Actor> creaete(Actor actor) {
		Actor actorSaved = this.actorService.save(actor);
		return ResponseEntity.status(HttpStatus.CREATED).body(actorSaved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Actor> update(@PathVariable Long id, Actor actor) {
		Actor actorUpdated = this.actorService.update(id, actor);
		return ResponseEntity.status(HttpStatus.OK).body(actorUpdated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.actorService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
