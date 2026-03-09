package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Admins;
import com.crusaders.jobKey.service.services.AdminsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminsController {

    private final AdminsService service;

    public AdminsController(AdminsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Admins> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Admins obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }


    @PutMapping("/{id}")
    public Admins actualizar(@PathVariable Integer id, @RequestBody Admins admin) {
        return service.actualizar(id, admin);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}