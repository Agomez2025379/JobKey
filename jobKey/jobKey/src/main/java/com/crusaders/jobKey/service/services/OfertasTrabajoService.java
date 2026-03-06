package com.crusaders.jobKey.service.services;

import com.crusaders.jobKey.DTO.ofertas.OfertasTrabajoRequest;
import com.crusaders.jobKey.DTO.ofertas.OfertasTrabajoResponse;

import java.util.List;

public interface OfertasTrabajoService {

    OfertasTrabajoResponse crear(OfertasTrabajoRequest request);

    List<OfertasTrabajoResponse> listar();

    OfertasTrabajoResponse obtenerPorId(Integer id);

    OfertasTrabajoResponse actualizar(Integer id, OfertasTrabajoRequest request);

    void eliminar(Integer id);
}