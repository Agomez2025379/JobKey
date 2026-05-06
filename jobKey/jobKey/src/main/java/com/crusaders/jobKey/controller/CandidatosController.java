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
    public ResponseEntity<List<CandidatosResponse>> listCandidates() {
        return ResponseEntity.ok(candidatoService.listCandidates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidatosResponse> getCandidate(
            @PathVariable Integer id) {

        return ResponseEntity.ok(candidatoService.getCandidate(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidatosResponse> updateCandidate(
            @PathVariable Integer id,
            @RequestBody CandidatosRequest request) {

        return ResponseEntity.ok(candidatoService.updateCandidate(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Integer id) {

        candidatoService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }
}