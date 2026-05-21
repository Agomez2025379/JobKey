package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.ofertas.OfertasTrabajoRequest;
import com.crusaders.jobKey.dto.ofertas.OfertasTrabajoResponse;
import com.crusaders.jobKey.service.services.OfertasTrabajoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
@RequiredArgsConstructor
public class OfertasTrabajoController {

    private final OfertasTrabajoService service;

    @PostMapping("/create")
    public ResponseEntity<OfertasTrabajoResponse> crear(@Valid @RequestBody OfertasTrabajoRequest request) {

        return ResponseEntity.ok(service.crear(request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<OfertasTrabajoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertasTrabajoResponse> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<OfertasTrabajoResponse>
    actualizar(@PathVariable Integer id,
               @Valid @RequestBody OfertasTrabajoRequest request)
    {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}