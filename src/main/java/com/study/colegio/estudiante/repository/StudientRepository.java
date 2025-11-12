package com.study.colegio.estudiante.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.colegio.estudiante.entity.StudientEntity;


@Repository
public interface StudientRepository extends JpaRepository<StudientEntity,Long> {

    Optional<StudientEntity> findByDocumentoIdentidad(String documentoIdentidad);

    StudientEntity deleteByDocumentoIdentidad(String documentoIdentidad);
} 
