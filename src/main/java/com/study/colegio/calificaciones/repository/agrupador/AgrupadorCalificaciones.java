package com.study.colegio.calificaciones.repository.agrupador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.study.colegio.calificaciones.controller.dto.CalificacionesDTO;
import com.study.colegio.calificaciones.controller.dto.CursoDto;
import com.study.colegio.calificaciones.controller.dto.MateriaDTO;
import com.study.colegio.calificaciones.controller.dto.PeriodoDTO;
public class AgrupadorCalificaciones {
    
    public static List<CalificacionesDTO> agrupar(List<CalificacionesDTO> calificacionesEntrada) {
        if (calificacionesEntrada == null || calificacionesEntrada.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Integer, CalificacionesDTO> estudianteMap  = new HashMap<>();

        
        for (CalificacionesDTO calificacionesDTO : calificacionesEntrada) {
            Integer idEstudiante = calificacionesDTO.getIdEstudiante();
            CalificacionesDTO estudianteAgrupado = estudianteMap.computeIfAbsent(idEstudiante, 
            id -> {
                CalificacionesDTO c = new CalificacionesDTO();
                c.setIdEstudiante(idEstudiante);
                c.setCursos(new ArrayList<>());
                return c;
            }
            );

            Map<Integer, CursoDto> cursoMap  = estudianteAgrupado.getCursos()
            .stream()
            .collect(Collectors.toMap(CursoDto::getIdCurso, c -> c, (a,b) -> a));

            for (CursoDto curso : calificacionesDTO.getCursos()) {
                        CursoDto cursoAgrupado = cursoMap.computeIfAbsent(curso.getIdCurso(), id -> {
                            CursoDto c = new CursoDto();
                            c.setIdCurso(id);
                            c.setMaterias(new ArrayList<>());
                            estudianteAgrupado.getCursos().add(c);
                            return c;
                        }
                );
                
            Map<Integer, MateriaDTO> materiasMap = cursoAgrupado.getMaterias()
            .stream()
            .collect(Collectors.toMap(MateriaDTO::getIdMateria,m -> m, (a,b) -> a));
            
            for( MateriaDTO materia : curso.getMaterias()){
                MateriaDTO materiaAgregada = materiasMap.computeIfAbsent(
                    materia.getIdMateria(),
                    id -> {
                        MateriaDTO m = new MateriaDTO();
                        m.setIdMateria(id);
                        m.setPeriodos(new ArrayList<>());
                        cursoAgrupado.getMaterias().add(m);
                        return m;
                    }
                );

                Map<Integer,PeriodoDTO> perioMap = materiaAgregada.getPeriodos()
                .stream()
                .collect(Collectors.toMap(PeriodoDTO::getIdPeriodo, p -> p, (a,b) -> a));

                for (PeriodoDTO periodo : materia.getPeriodos()) {
                    PeriodoDTO perAgregado = perioMap.computeIfAbsent(
                        periodo.getIdPeriodo(), 
                        id -> {
                            PeriodoDTO p = new PeriodoDTO();
                            p.setIdPeriodo(id);
                            p.setCalificaciones(new ArrayList<>());
                            materiaAgregada.getPeriodos().add(p);
                            return p;
                        }
                    );
                    perAgregado.getCalificaciones().addAll(periodo.getCalificaciones());
                }

            }
            }
            
            
        }
        
        
        return new ArrayList<>(estudianteMap.values());
    }
}
