package com.crusaders.jobKey.controller;


import com.crusaders.jobKey.DTO.empresas.EmpresasRequest;
import com.crusaders.jobKey.DTO.empresas.EmpresasResponse;
import com.crusaders.jobKey.service.services.EmpresasService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresasController {

    private final EmpresasService empresasService;

    public EmpresasController(EmpresasService empresasService) {
        this.empresasService = empresasService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmpresasResponse> obtenerEmpresa(
            @PathVariable Integer id) {

        return ResponseEntity.ok(empresasService.obtenerEmpresa(id));
    }

    @GetMapping
    public ResponseEntity<List<EmpresasResponse>> listarEmpresas() {

        return ResponseEntity.ok(empresasService.listarEmpresas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresasResponse> actualizarEmpresa(
            @PathVariable Integer id,
            @RequestBody EmpresasRequest request) {

        return ResponseEntity.ok(empresasService.actualizarEmpresa(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(
            @PathVariable Integer id) {

        empresasService.eliminarEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}