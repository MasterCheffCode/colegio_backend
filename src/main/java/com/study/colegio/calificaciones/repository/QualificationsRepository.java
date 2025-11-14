package com.study.colegio.calificaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.colegio.calificaciones.repository.entity.QualificationsEntity;

public interface QualificationsRepository extends JpaRepository<QualificationsEntity,Long> {

    List<QualificationsEntity> findByDocumentoIdentidad(Long documentoIdentidad);

}
