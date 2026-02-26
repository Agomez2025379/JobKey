package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Candidatos;
import com.crusaders.jobKey.service.CandidatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidatos")
@CrossOrigin(origins = "*") // Para que no tengas problemas de CORS con el Frontend
public class CandidatosController {

    @Autowired
    private CandidatosService candidatosService;

    @GetMapping
    public List<Candidatos> listar() {
        return candidatosService.listar();
    }

    @GetMapping("/{id}")
    public Candidatos obtenerPorId(@PathVariable Integer id) {
        return candidatosService.obtenerPorId(id);
    }
}
