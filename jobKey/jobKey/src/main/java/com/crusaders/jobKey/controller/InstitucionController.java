package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.DTO.instituciones.InstitucionRequest;
import com.crusaders.jobKey.DTO.instituciones.InstitucionResponse;
import com.crusaders.jobKey.service.services.InstitucionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instituciones")
public class InstitucionController {

    private final InstitucionService institucionService;

    public InstitucionController(InstitucionService institucionService) {
        this.institucionService = institucionService;
    }


    /* Obtener institución por id */
    @GetMapping("/{id}")
    public ResponseEntity<InstitucionResponse> obtenerInstitucion(
            @PathVariable Integer id) {

        InstitucionResponse response = institucionService.obtenerPorId(id);
        return ResponseEntity.ok(response);
    }

    /* Listar todas las instituciones */
    @GetMapping
    public ResponseEntity<List<InstitucionResponse>> listarInstituciones() {

        List<InstitucionResponse> response = institucionService.listarInstituciones();
        return ResponseEntity.ok(response);
    }

    /* Actualizar institución */
    @PutMapping("/{id}")
    public ResponseEntity<InstitucionResponse> actualizarInstitucion(
            @PathVariable Integer id,
            @RequestBody InstitucionRequest request) {

        InstitucionResponse response = institucionService.actualizarInstitucion(id, request);
        return ResponseEntity.ok(response);
    }

    /* Eliminar institución */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInstitucion(
            @PathVariable Integer id) {

        institucionService.eliminarInstitucion(id);
        return ResponseEntity.noContent().build();
    }
}