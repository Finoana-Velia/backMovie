package com.example.movies.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.movies.Core.FileUploader;
import com.example.movies.Dto.ActorDto;
import com.example.movies.Entity.Actor;
import com.example.movies.Service.Impl.ActorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/actor")
@AllArgsConstructor
public class ActorController {
	
	private final ActorServiceImpl actorService;
	private final FileUploader fileUploader;
	
	@GetMapping
	public ResponseEntity<List<ActorDto>> findAll() {
		List<ActorDto> actors = this.actorService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(actors);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<ActorDto>> searchByName(
			@RequestParam(defaultValue="")String name,
			@RequestParam(defaultValue="0")int page,
			@RequestParam(defaultValue="10")int size
			){
		PageRequest request = PageRequest.of(page, size);
		Page<ActorDto> actors = this.actorService.searchByName("%" + name + "%",request);
		return ResponseEntity.status(HttpStatus.OK).body(actors);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ActorDto> findById(@PathVariable Long id) {
		ActorDto actor = this.actorService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(actor);
	}
	
	@PostMapping
	public ResponseEntity<ActorDto> create(
			@RequestParam String actorData,
			@RequestParam(required=false) MultipartFile file
			) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		ActorDto actor = objectMapper.readValue(actorData, ActorDto.class);
		ActorDto actorResponse;
		if(file != null) {
			actor.setProfile(file.getOriginalFilename());
			actorResponse = this.actorService.save(actor);
			System.out.println(actorResponse);
			this.fileUploader.registerFile(file, "actor", actorResponse.getId());
			
		}else {
			actorResponse = this.actorService.save(actor);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(actorResponse); 
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ActorDto> update(
			@PathVariable Long id, 
			@RequestParam String actor,
			@RequestParam(required=false) MultipartFile file) 
	throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		ActorDto actorDto = mapper.readValue(actor, ActorDto.class);
		actorDto.setId(id);
		ActorDto actorResponse;
		if(file!=null) {
			actorDto.setProfile(file.getOriginalFilename());
			actorResponse = this.actorService.update(id,actorDto);
			this.fileUploader.updateFile(file, "actor", id);
		}else {
			actorResponse = this.actorService.update(id, actorDto);
		}
		//ActorDto actorUpdated = this.actorService.update(id, actor);
		return ResponseEntity.status(HttpStatus.OK).body(actorResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.actorService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/getImage")
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		File file = this.fileUploader.getFile(id,"actor");
		if(file.exists()) {
			return IOUtils.toByteArray(new FileInputStream(file));
		}else {
			return null;
		}
	}

}
