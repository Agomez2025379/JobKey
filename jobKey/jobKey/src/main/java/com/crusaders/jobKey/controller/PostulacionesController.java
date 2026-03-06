package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.DTO.postulaciones.PostulacionesRequest;
import com.crusaders.jobKey.DTO.postulaciones.PostulacionesResponse;
import com.crusaders.jobKey.service.services.PostulacionesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postulaciones")
@RequiredArgsConstructor
public class PostulacionesController {

    private final PostulacionesService postulacionesService;

    @PostMapping("/create")
    public PostulacionesResponse crearPostulacion(
            @Valid @RequestBody PostulacionesRequest request
    ) {
        return postulacionesService.crearPostulacion(request);
    }

    @GetMapping("/get")
    public List<PostulacionesResponse> listarPostulaciones() {
        return postulacionesService.listarPostulaciones();
    }

    @GetMapping("/get/{id}")
    public PostulacionesResponse obtenerPostulacion(
            @PathVariable Integer id
    ) {
        return postulacionesService.obtenerPostulacion(id);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarPostulacion(
            @PathVariable Integer id
    ) {
        postulacionesService.eliminarPostulacion(id);
    }

}