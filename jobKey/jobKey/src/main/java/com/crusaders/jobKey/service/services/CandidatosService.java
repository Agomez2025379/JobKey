package com.crusaders.jobKey.service.services;


import com.crusaders.jobKey.dto.candidatos.CandidatosRequest;
import com.crusaders.jobKey.dto.candidatos.CandidatosResponse;

import java.util.List;

public interface CandidatosService {



    CandidatosResponse obtenerCandidato(Integer id);

    List<CandidatosResponse> listarCandidatos();

    CandidatosResponse actualizarCandidato(Integer id, CandidatosRequest request);

    void eliminarCandidato(Integer id);

}