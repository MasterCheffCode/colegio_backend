package com.study.colegio.calificaciones.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.study.colegio.calificaciones.entity.QualificationsEntity;

public interface QualificationsRepository extends JpaRepository<QualificationsEntity,Long> {

    @Query("SELECT q FROM QualificationsEntity q WHERE q.estudiante.documentoIdentidad = :documento")
    List<QualificationsEntity> qualificacionsByDocumento(String documento);

    @Query("SELECT q FROM QualificationsEntity q WHERE q.estudiante.documentoIdentidad = :documentoIdentidad AND q.periodo = :periodo AND q.numeroNota = :numeroNota AND q.materia.materia = :materia")
Optional<QualificationsEntity> findByEstudianteDocumentoIdentidadAndPeriodoAndNumeroNotaAndMateria(String documentoIdentidad, Integer periodo, Integer numeroNota, String materia);

    @Query("SELECT MAX(q.numeroNota) FROM QualificationsEntity q WHERE q.estudiante.documentoIdentidad = :documentoIdentidad AND q.periodo = :periodo")
    Integer findMaxNumeroNotaByDocumentoAndPeriodo(String documentoIdentidad, Integer periodo);

    @Modifying
@Query("DELETE FROM QualificationsEntity q WHERE q.estudiante.documentoIdentidad = :documentoIdentidad AND q.periodo = :periodo AND q.numeroNota = :numeroNota AND q.materia.materia = :materia")
void deleteByEstudianteDocumentoIdentidadAndPeriodoAndNumeroNotaAndMateria(String documentoIdentidad, Integer periodo, Integer numeroNota, String materia);

}
