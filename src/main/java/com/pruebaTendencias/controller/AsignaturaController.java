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
import com.pruebaTendencias.service.AsignaturaService;



@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {
	@Autowired
	private AsignaturaService asignaturaService;
	
	//Metodo Crear
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody Asignatura asignatura){
		return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaService.save(asignatura));
	}
	
	//Leer usuario
	@GetMapping("/{id_asignatura}")
	public ResponseEntity<?> read(@PathVariable Long id_asignatura){
		Optional<Asignatura> oAsignatura = asignaturaService.findById(id_asignatura);
		
		if(!oAsignatura.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oAsignatura);
	}
	
	
	//Metodo editar
	@PutMapping("/actualizar/{id_Asignatura}")
	public ResponseEntity<?> update(@RequestBody Asignatura asignaturaDetails, @PathVariable Long id_Asignatura){
		Optional<Asignatura> asig = asignaturaService.findById(id_Asignatura);
		
		if(!asig.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		asig.get().setNombre(asignaturaDetails.getNombre());
		asig.get().setCarrera(asignaturaDetails.getCarrera());
		asig.get().setHora_inicio(asignaturaDetails.getHora_inicio());
		asig.get().setHora_fin(asignaturaDetails.getHora_fin());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaService.save(asig.get()));
	}
	
	
	//Metodo eliminar
	@DeleteMapping("/eliminar/{id_asignatura}")
	public ResponseEntity<?> delete(@PathVariable Long id_asignatura){
		
		if(!asignaturaService.findById(id_asignatura).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		asignaturaService.deleteById(id_asignatura);
		return ResponseEntity.ok().build();
	}
	
	//Leer todos las asignaturas
	@GetMapping("/list")
	public List<Asignatura> readAll(){
		
		List<Asignatura> users = StreamSupport
				.stream(asignaturaService.findAll().spliterator(), false).collect(Collectors.toList());
		
		return users;
	}
}
