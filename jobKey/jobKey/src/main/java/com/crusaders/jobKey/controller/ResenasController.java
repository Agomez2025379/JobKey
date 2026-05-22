package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Resenas;
import com.crusaders.jobKey.enums.ResenaTipo;
import com.crusaders.jobKey.service.services.ResenasService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

    @GetMapping("/tipo/{tipo}")
    public List<Resenas> obtenerPorTipo(@PathVariable String tipo) {
        try {
            ResenaTipo t = ResenaTipo.valueOf(tipo.trim());
            return service.obtenerPorTipo(t);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de reseña inválido: " + tipo);
        }
    }

    @GetMapping("/empresa/{empresaId}")
    public List<Resenas> obtenerPorEmpresa(@PathVariable Integer empresaId) {
        return service.obtenerPorEmpresaId(empresaId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Resenas guardar(@RequestBody Resenas resena) {
        return service.guardar(resena);
    }
}