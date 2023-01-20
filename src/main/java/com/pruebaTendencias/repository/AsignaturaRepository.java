package com.pruebaTendencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebaTendencias.entity.Asignatura;

@Repository
public interface AsignaturaRepository extends JpaRepository <Asignatura,Long>{

}
