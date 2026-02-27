package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Empresas;
import com.crusaders.jobKey.service.EmpresasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empresas")
@CrossOrigin(origins = "*")
public class EmpresasController {

    @Autowired
    private EmpresasService empresasService;

    @GetMapping
    public List<Empresas> listar() {
        return empresasService.listarTodas();
    }

    @PostMapping
    public Empresas guardar(@RequestBody Empresas empresa) {
        return empresasService.guardar(empresa);
    }

    @GetMapping("/{id}")
    public Empresas obtenerPorId(@PathVariable Integer id) {
        return empresasService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        empresasService.eliminar(id);
    }
}
