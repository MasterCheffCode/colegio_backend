package com.study.colegio.estudiante.controller;

import org.springframework.web.bind.annotation.*;

import com.study.colegio.estudiante.controller.dto.EstudianteDTO;
import com.study.colegio.estudiante.service.EstudianteService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService studientService;

    public EstudianteController(EstudianteService studientService){
        this.studientService = studientService;
    }

    @GetMapping
    public List<EstudianteDTO> getStudients() {
        return studientService.obtenerTodosEstudiantes();
    }

    @PostMapping
    public EstudianteDTO createStudient(@RequestBody EstudianteDTO studient) {
        
        return studientService.guardarEstudiante(studient);
    }

  
    
    
}
