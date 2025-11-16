package com.study.colegio.calificaciones.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.study.colegio.calificaciones.controller.dto.NotasDTO;
import com.study.colegio.calificaciones.controller.dto.QualificationsDTO;
import com.study.colegio.calificaciones.controller.dto.RequestDTO;
import com.study.colegio.calificaciones.repository.entity.QualificationsEntity;
import com.study.colegio.calificaciones.service.QualificationsService;





@RestController
@RequestMapping("/qualifications")
public class QualificationsController {
    
    private final QualificationsService qualificationsService;

    
    public QualificationsController(QualificationsService qualificationsService){
        this.qualificationsService = qualificationsService;
    }

    @GetMapping
    public List<QualificationsDTO> getQualifications() {
        return qualificationsService.getAllQualificationes();
    }

    @PostMapping
    public RequestDTO createQualifications(@RequestBody RequestDTO dto) {
    
     return qualificationsService.saveQualifications(dto);

    }



        

   
    
    

}
