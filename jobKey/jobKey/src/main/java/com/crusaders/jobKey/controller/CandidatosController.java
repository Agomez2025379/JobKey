package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.candidatos.CandidatosRequest;
import com.crusaders.jobKey.dto.candidatos.CandidatosResponse;
import com.crusaders.jobKey.entity.Candidatos;
import com.crusaders.jobKey.service.CandidatosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidatos")
@CrossOrigin(origins = "*")
public class CandidatosController {

    @Autowired
    private CandidatosService candidatosService;

    @GetMapping
    public List<Candidatos> listar() {
        // Si prefieres devolver Response DTO, cambia a:
        // return candidatosService.listar().stream().map(this::toResponse).toList();
        return candidatosService.listar();
    }

    @GetMapping("/{id}")
    public CandidatosResponse obtenerPorId(@PathVariable Integer id) {
        Candidatos c = candidatosService.obtenerPorId(id);
        return toResponse(c);
    }

    @PostMapping
    public CandidatosResponse crear(@Valid @RequestBody CandidatosRequest request) {
        Candidatos c = candidatosService.crear(request);
        return toResponse(c);
    }

    @PutMapping("/{id}")
    public CandidatosResponse actualizar(@PathVariable Integer id,
                                         @Valid @RequestBody CandidatosRequest request) {
        Candidatos c = candidatosService.actualizar(id, request);
        return toResponse(c);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        candidatosService.eliminar(id);
    }

    // ---- Conversión manual a DTO (sin mapper externo) ----
    private CandidatosResponse toResponse(Candidatos c) {
        CandidatosResponse r = new CandidatosResponse();
        r.setId(c.getIdCandidato());
        r.setNombre(c.getNombre());
        r.setApellido(c.getApellido());
        r.setEmail(c.getEmail());
        r.setTelefono(c.getTelefono());
        r.setProfesion(c.getProfesion());
        r.setExperiencia(c.getExperiencia());
        r.setEducacion(c.getEducacion());
        r.setHabilidades(c.getHabilidades());
        r.setCurriculumUrl(c.getCurriculumUrl());
        if (c.getDepartamentos() != null) {
            r.setDepartamentoId(c.getDepartamentos().getIdDepartamento());
            r.setDepartamentoNombre(c.getDepartamentos().getDepartamento());
        }
        r.setFechaRegistro(c.getFechaRegistro());
        // updatedAt no existe en tu BD, lo dejamos null
        return r;
    }
}