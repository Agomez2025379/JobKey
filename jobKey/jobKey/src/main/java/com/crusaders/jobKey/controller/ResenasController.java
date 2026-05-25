package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Resenas;
import com.crusaders.jobKey.service.services.ResenasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ResenasController {

    private final ResenasService resenasService;

    public ResenasController(ResenasService resenasService) {
        this.resenasService = resenasService;
    }

    @GetMapping("/resenas")
    public String mostrarVistaResenas() {
        return "resenas";
    }

    @GetMapping("/api/resenas/get")
    @ResponseBody
    public ResponseEntity<List<Resenas>> obtenerTodas() {
        List<Resenas> lista = resenasService.listar();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/api/resenas/save")
    @ResponseBody
    public ResponseEntity<?> guardarNueva(@RequestBody Resenas nuevaResena) {
        try {
            nuevaResena.setIdResena(null);
            Resenas resenaGuardada = resenasService.guardar(nuevaResena);
            return ResponseEntity.status(HttpStatus.CREATED).body(resenaGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}