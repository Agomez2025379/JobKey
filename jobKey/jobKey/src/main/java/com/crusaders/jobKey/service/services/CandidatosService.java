package com.crusaders.jobKey.service.services;

import com.crusaders.jobKey.dto.candidatos.CandidatosRequest;
import com.crusaders.jobKey.entity.Candidatos;

import java.util.List;

public interface CandidatosService {
    List<Candidatos> listar();
    Candidatos crear(CandidatosRequest request);
    Candidatos actualizar(Integer id, CandidatosRequest request);
    void eliminar(Integer id);
    Candidatos obtenerPorId(Integer id);
    Candidatos obtenerPorNombre(String nombre);
}