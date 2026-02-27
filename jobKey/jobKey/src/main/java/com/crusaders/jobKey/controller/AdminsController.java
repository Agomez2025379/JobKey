package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Admins;
import com.crusaders.jobKey.service.AdminsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminsController {

    private final AdminsService service;

    public AdminsController(AdminsService service) {
        this.service = service;
    }

    @GetMapping("/get")
    public List<Admins> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Admins obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }


    @GetMapping("/email/{email}")
    public Admins obtenerPorEmail(@PathVariable String email) {
        return service.obtenerPorEmail(email);
    }


    @GetMapping("/nombre/{nombre}")
    public Admins obtenerPorNombre(@PathVariable String nombre) {
        return service.obtenerPorNombre(nombre);
    }
}