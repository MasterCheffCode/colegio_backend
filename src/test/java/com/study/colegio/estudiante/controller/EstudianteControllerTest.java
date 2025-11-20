package com.study.colegio.estudiante.controller;

import com.study.colegio.estudiante.controller.dto.EstudianteDTO;
import com.study.colegio.estudiante.repository.EstudianteRepository;
import com.study.colegio.estudiante.service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(EstudianteController.class)
public class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EstudianteRepository estudianteRepository;

    @MockitoBean
    private EstudianteService estudianteService;

    private EstudianteDTO createTestStudentDto(Long id, Long documento, String nombre, String apellido, int curso, int edad) {
    EstudianteDTO dto = new EstudianteDTO();
    dto.setId(id);
    dto.setDocumentoIdentidad(documento);
    dto.setNombre(nombre);
    dto.setApellido(apellido);
    dto.setCurso(curso);
    dto.setEdad(edad);
    return dto;
}

    @Test
    void shouldReturnAllStudentsWhenGetAllStudents() throws Exception {

        EstudianteDTO e1 = new EstudianteDTO();
        e1.setId(1L);
        e1.setDocumentoIdentidad(1004345037L);
        e1.setNombre("Elian");
        e1.setApellido("Ureche");
        e1.setCurso(1);
        e1.setEdad(25);

        EstudianteDTO e2 = new EstudianteDTO();
        e2.setId(2L);
        e2.setDocumentoIdentidad(36544286L);
        e2.setNombre("Farides");
        e2.setApellido("Lopez");
        e2.setCurso(2);
        e2.setEdad(60);

        List<EstudianteDTO> estudiantes = List.of(
                e1,e2
        );

        when(estudianteService.obtenerTodosEstudiantes())
                .thenReturn(estudiantes);

        mockMvc.perform(get("/estudiantes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].documentoIdentidad").value(1004345037))
                .andExpect(jsonPath("$[0].nombre").value("Elian"))
                .andExpect(jsonPath("$[0].apellido").value("Ureche"))
                .andExpect(jsonPath("$[0].curso").value(1))
                .andExpect(jsonPath("$[0].edad").value(25));
        verify(estudianteService, times(1)).obtenerTodosEstudiantes();
    }

    @Test
    void shouldCreateStudentWhenValidDataProvided() throws Exception{
        EstudianteDTO s1 = createTestStudentDto(1L,1004345037L,"Elian","Ureche",2,25);

       when(estudianteService.guardarEstudiante(eq(s1))).thenReturn(s1);

        String json = """
            {
                "id": 1,
                "documentoIdentidad": 1004345037,
                "nombre": "Elian",
                "apellido": "Ureche",
                "curso": 2,
                "edad": 25
            }
            """;

        mockMvc.perform(post("/estudiantes")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Elian"))
                .andExpect(jsonPath("$.documentoIdentidad").value(1004345037));

        verify(estudianteService, times(1)).guardarEstudiante(any(EstudianteDTO.class));
    }

    

    // @Test
    // void putStudient()throws Exception{
    //     EstudianteDTO eExiste = new EstudianteDTO();
    //     eExiste.setId(1L);
    //     eExiste.setDocumentoIdentidad(1004345037L);
    //     eExiste.setNombre("Elian");
    //     eExiste.setApellido("Lopez");
    //     eExiste.setCurso(2);
    //     eExiste.setEdad(25);

    //     EstudianteDTO eActualizar = new EstudianteDTO();
    //     eActualizar.setId(1L);
    //     eActualizar.setNombre("Elian");
    //     eActualizar.setApellido("Ureche");
    //     eActualizar.setCurso(2);
    //     eActualizar.setEdad(25);

    //     when(estudianteService.editarEstudiante(anyString(),any(EstudianteDTO.class))).thenReturn(eActualizar);

    //     String json = """
    //         {
    //             "id": 1,
    //             "documentoIdentidad": "1004345037",
    //             "nombre": "Elian",
    //             "apellido": "Lopez",
    //             "curso": 2,
    //             "edad": 25
    //         }
    //         """;

    //     mockMvc.perform(put("/estudiantes/{documentoIdentidad}","1004345037")
    //             .content(json)
    //             .contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.nombre").value("Elian"))
    //             .andExpect(jsonPath("$.documentoIdentidad").value("1004345037"));
        

    // }

    // @Test
    // void deleteStudient()throws Exception{
    //     String documentoIdentidad = "254585";

    // doNothing().when(studientService).deleteStudient(any(String.class));

    //     mockMvc.perform(delete("/studients/{documentoIdentidad}", documentoIdentidad)
    //         .contentType(MediaType.APPLICATION_JSON))
    //         .andExpect(status().isOk()); 
    
    // verify(studientService, times(1)).deleteStudient(documentoIdentidad);
    // }
}
