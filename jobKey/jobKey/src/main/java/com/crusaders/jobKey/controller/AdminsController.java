// AdminsController.java
package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.admins.*;
import com.crusaders.jobKey.service.services.AdminsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public List<AdminResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public AdminResponse obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdminResponse crear(@Valid @RequestBody AdminRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public AdminResponse actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody AdminUpdateRequest request
    ) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}