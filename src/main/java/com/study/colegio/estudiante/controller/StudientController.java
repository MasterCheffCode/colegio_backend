package com.study.colegio.estudiante.controller;

import org.springframework.web.bind.annotation.*;

import com.study.colegio.estudiante.controller.dto.StudientDTO;
import com.study.colegio.estudiante.repository.entity.StudientEntity;
import com.study.colegio.estudiante.service.StudientService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/studients")
public class StudientController {

    private final StudientService studientService;

    public StudientController(StudientService studientService){
        this.studientService = studientService;
    }

    @GetMapping
    public List<StudientDTO> getStudients() {
        return studientService.getAllStudients();
    }
    
    @GetMapping("/{documentoIdentidad}")
    public StudientDTO getStudientByDocumento(@PathVariable Long documentoIdentidad) {
        return studientService.findStudientByDocumento(documentoIdentidad);
    }
    

    @PostMapping
    public StudientDTO createStudient(@RequestBody StudientDTO studient) {
        
        return studientService.saveStudient(studient);
    }

  
    
    
}
