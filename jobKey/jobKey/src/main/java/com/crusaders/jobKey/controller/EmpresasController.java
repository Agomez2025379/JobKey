package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.empresas.EmpresasRequest;
import com.crusaders.jobKey.dto.empresas.EmpresasResponse;
import com.crusaders.jobKey.entity.Empresas;
import com.crusaders.jobKey.service.services.EmpresasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresasController {

    @Autowired
    private EmpresasService empresasService;

    @GetMapping
    public List<Empresas> listar() {
        // igual que arriba: si quieres Response DTO, mapea con toResponse(...)
        return empresasService.listarTodas();
    }

    @PostMapping
    public EmpresasResponse crear(@Valid @RequestBody EmpresasRequest request) {
        Empresas e = empresasService.crear(request);
        return toResponse(e);
    }

    @GetMapping("/{id}")
    public EmpresasResponse obtenerPorId(@PathVariable Integer id) {
        Empresas e = empresasService.buscarPorId(id);
        return toResponse(e);
    }

    @PutMapping("/{id}")
    public EmpresasResponse actualizar(@PathVariable Integer id, @Valid @RequestBody EmpresasRequest request) {
        Empresas e = empresasService.actualizar(id, request);
        return toResponse(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        empresasService.eliminar(id);
    }

    // ---- Conversión manual a DTO (sin mapper externo) ----
    private EmpresasResponse toResponse(Empresas e) {
        EmpresasResponse r = new EmpresasResponse();
        r.setId(e.getIdEmpresa());
        r.setNombreEmpresa(e.getNombreEmpresa());
        r.setEmail(e.getEmail());
        r.setTelefono(e.getTelefono());
        r.setDescripcion(e.getDescripcion());
        r.setSectorEmpresarial(e.getSectorEmpresarial());
        if (e.getDepartamentos() != null) {
            r.setDepartamentoId(e.getDepartamentos().getIdDepartamento());
            r.setDepartamentoNombre(e.getDepartamentos().getDepartamento());
        }
        r.setTieneLogo(e.getLogoUrl() != null && e.getLogoUrl().length > 0);
        r.setFechaRegistro(e.getFechaRegistro());
        return r;
    }
}