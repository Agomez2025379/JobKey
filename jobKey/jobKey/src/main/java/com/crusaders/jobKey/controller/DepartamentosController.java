package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.service.DepartamentosService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentosController {

    private final DepartamentosService service;

    public DepartamentosController(DepartamentosService service) {
        this.service = service;
    }


    @GetMapping("/get")
    public List<Departamentos> listar() {
        return service.listar();
    }


    @GetMapping("/{id}")
    public Departamentos obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }


    @GetMapping("/nombre/{nombre}")
    public Departamentos obtenerPorNombre(@PathVariable String nombre) {
        return service.obtenerPorNombre(nombre);
    }
}