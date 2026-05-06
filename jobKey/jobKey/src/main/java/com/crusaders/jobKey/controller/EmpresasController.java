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
    public ResponseEntity<EmpresasResponse> getCompany(
            @PathVariable Integer id) {

        return ResponseEntity.ok(empresasService.getCompany(id));
    }

    @GetMapping
    public ResponseEntity<List<EmpresasResponse>> listCompanies() {

        return ResponseEntity.ok(empresasService.listCompanmies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresasResponse> updateCompany(
            @PathVariable Integer id,
            @RequestBody EmpresasRequest request) {

        return ResponseEntity.ok(empresasService.updateCompany(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(
            @PathVariable Integer id) {

        empresasService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}