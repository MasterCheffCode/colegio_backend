package com.study.colegio.estudiante.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.colegio.estudiante.repository.entity.EstudianteEntity;


@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity,Long> {

    Optional<EstudianteEntity> findByDocumentoIdentidad(Long documentoIdentidad);

} 
