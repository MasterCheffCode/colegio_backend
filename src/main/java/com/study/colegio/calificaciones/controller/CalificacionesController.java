package com.study.colegio.calificaciones.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.study.colegio.calificaciones.controller.dto.CalificacionesDTO;
import com.study.colegio.calificaciones.controller.dto.RequestDTO;
import com.study.colegio.calificaciones.service.CalificacionesServiceImpl;





@RestController
@RequestMapping("/calificaciones")
public class CalificacionesController {
    
    private final CalificacionesServiceImpl qualificationsService;

    
    public CalificacionesController(CalificacionesServiceImpl qualificationsService){
        this.qualificationsService = qualificationsService;
    }

    @GetMapping
    public List<CalificacionesDTO> getQualifications() {
        return qualificationsService.obtenerTodas();
    }

    @PostMapping
    public RequestDTO createQualifications(@RequestBody RequestDTO dto) {
    
     return qualificationsService.crearCalificacion(dto);

    }



        

   
    
    

}
