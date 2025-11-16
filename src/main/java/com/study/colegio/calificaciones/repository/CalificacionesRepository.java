package com.study.colegio.calificaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.colegio.calificaciones.repository.entity.CalificacionesEntity;

@Repository
public interface CalificacionesRepository extends JpaRepository<CalificacionesEntity,Long> {

    List<CalificacionesEntity> findByDocumentoIdentidad(Long documentoIdentidad);

}
