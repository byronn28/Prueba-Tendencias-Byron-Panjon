package com.pruebaTendencias.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaTendencias.entity.Asignatura;
import com.pruebaTendencias.entity.Docente;
import com.pruebaTendencias.service.AsignaturaService;
import com.pruebaTendencias.service.DocenteService;


@RestController
@RequestMapping("/docente")
public class DocenteController {
	@Autowired
	private DocenteService docenteService;
	
	//Metodo Crear
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody Docente docente){
		return ResponseEntity.status(HttpStatus.CREATED).body(docenteService.save(docente));
	}
	
	//Leer docente por id
	@GetMapping("/{id_docente}")
	public ResponseEntity<?> read(@PathVariable Long id_docente){
		Optional<Docente> oAsignatura = docenteService.findById(id_docente);
		
		if(!oAsignatura.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oAsignatura);
	}
	
	
	//Metodo editar
	@PutMapping("/actualizar/{id_docente}")
	public ResponseEntity<?> update(@RequestBody Docente docenteDetails, @PathVariable Long id_docente){
		Optional<Docente> docente = docenteService.findById(id_docente);
		
		if(!docente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		docente.get().setApellido(docenteDetails.getApellido());
		docente.get().setCelular(docenteDetails.getCelular());
		docente.get().setEmail(docenteDetails.getEmail());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(docenteService.save(docente.get()));
	}
	
	
	//Metodo eliminar
	@DeleteMapping("/eliminar/{id_docente}")
	public ResponseEntity<?> delete(@PathVariable Long id_docente){
		
		if(!docenteService.findById(id_docente).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		docenteService.deleteById(id_docente);
		return ResponseEntity.ok().build();
	}
	
	//Leer todos las asignaturas
	@GetMapping("/list")
	public List<Docente> readAll(){
		
		List<Docente> users = StreamSupport
				.stream(docenteService.findAll().spliterator(), false).collect(Collectors.toList());
		
		return users;
	}
}
