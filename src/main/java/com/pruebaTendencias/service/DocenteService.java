package com.pruebaTendencias.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pruebaTendencias.entity.Docente;


public interface DocenteService {
	public Iterable<Docente> findAll();
	
	public Page<Docente> findAll(Pageable pageable);
	
	public Optional<Docente> findById(Long id_docente);
	
	public Docente save(Docente docente);
	
	public void deleteById(Long id_docente);
}
