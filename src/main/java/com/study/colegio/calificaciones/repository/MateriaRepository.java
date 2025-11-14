package com.study.colegio.calificaciones.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.study.colegio.calificaciones.repository.entity.MateriaEntity;

public interface MateriaRepository extends JpaRepository<MateriaEntity,Long>{
    
}
