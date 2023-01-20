package com.pruebaTendencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebaTendencias.entity.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long>{

}
