package com.study.colegio.calificaciones.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.study.colegio.calificaciones.entity.MateriaEntity;

public interface MateriaRepository extends JpaRepository<MateriaEntity,Long>{
    
    /*@Query("SELECT m.notasTotal FROM MateriaEntity m WHERE m.materia = :materia")
    Optional<Long> findByMateria(String materia);*/
}
