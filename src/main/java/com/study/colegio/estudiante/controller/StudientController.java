package com.study.colegio.estudiante.controller;

import org.springframework.web.bind.annotation.*;

import com.study.colegio.estudiante.entity.StudientEntity;
import com.study.colegio.estudiante.service.StudientService;

import java.util.List;


@RestController
@RequestMapping("/studients")
public class StudientController {

    private final StudientService studientService;

    public StudientController(StudientService studientService){
        this.studientService = studientService;
    }

    @GetMapping("/get_studients")
    public List<StudientEntity> getStudients() {
        return studientService.getAllStudients();
    }
    

    @PostMapping("/create_studient")
    public StudientEntity createStudient(@RequestBody StudientEntity studient) {
        
        return studientService.saveStudient(studient);
    }

    @PutMapping("/{documentoIdentidad}")
    public StudientEntity updateStudient(@PathVariable String documentoIdentidad,@RequestBody StudientEntity studient) {
        return studientService.editStudient(documentoIdentidad, studient);
    }

    @DeleteMapping("/{documentoIdentidad}")
    public void deleteStudient(@PathVariable String documentoIdentidad) {
        studientService.deleteStudient(documentoIdentidad);
    }
    
    
}
