package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Resenas;
import com.crusaders.jobKey.entity.ResenaTipo;
import com.crusaders.jobKey.service.services.ResenasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ResenasController {

    private final ResenasService service;

    public ResenasController(ResenasService service) {
        this.service = service;
    }

    @GetMapping("/get")
    public List<Resenas> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Resenas obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    // Buscar por tipo: empresa_a_candidato | candidato_a_empresa
    @GetMapping("/tipo/{tipo}")
    public List<Resenas> obtenerPorTipo(@PathVariable String tipo) {
        // convertir String a Enum (mismo nombre exacto del enum)
        ResenaTipo t = ResenaTipo.valueOf(tipo);
        return service.obtenerPorTipo(t);
    }

    // Buscar por empresa_id
    @GetMapping("/empresa/{empresaId}")
    public List<Resenas> obtenerPorEmpresa(@PathVariable Integer empresaId) {
        return service.obtenerPorEmpresaId(empresaId);
    }
}