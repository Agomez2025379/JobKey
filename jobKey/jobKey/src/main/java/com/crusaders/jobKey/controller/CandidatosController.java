package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.DTO.candidatos.*;
import com.crusaders.jobKey.service.services.CandidatosService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatosController {

    private final CandidatosService candidatoService;

    public CandidatosController(CandidatosService candidatoService) {
        this.candidatoService = candidatoService;
    }



    @GetMapping
    public ResponseEntity<List<CandidatosResponse>> listar() {
        return ResponseEntity.ok(candidatoService.listarCandidatos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidatosResponse> obtener(
            @PathVariable Integer id) {

        return ResponseEntity.ok(candidatoService.obtenerCandidato(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidatosResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody CandidatosRequest request) {

        return ResponseEntity.ok(candidatoService.actualizarCandidato(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        candidatoService.eliminarCandidato(id);
        return ResponseEntity.noContent().build();
    }
}