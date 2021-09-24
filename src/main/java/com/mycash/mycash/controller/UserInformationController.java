package com.mycash.mycash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycash.mycash.model.UserInformation;
import com.mycash.mycash.repository.UserInformationRepository;

@RestController
@RequestMapping({"/userIformation"})
public class UserInformationController {
	
	@Autowired
	private UserInformationRepository repository;
	
	@GetMapping
	// http://localhost:8095/UserInformation
	public List findAllInvoices() {
		return repository.findAll();
	}
	
	@GetMapping(value = "{id}")
	
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	// http://localhost:8095/despesa/
	public UserInformation create(@RequestBody UserInformation information) {
		return repository.save(information);
	}
	
	@PutMapping
	// http://localhost:8095/UserInformation/{id}
	public ResponseEntity update (@PathVariable long id, @RequestBody UserInformation information) {
		return repository.findById(id)
				.map(record -> {
					record.setNome(information.getNome());
					record.setId_autentication(information.getId_autentication());
					record.setTelefone(information.getTelefone());
				UserInformation update = repository.save(record);
				return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping
	// http://localhost:8095/UserInformation/{id}
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().body("Deletado com sucesso");
				}).orElse(ResponseEntity.notFound().build());
	}
	

	
}
