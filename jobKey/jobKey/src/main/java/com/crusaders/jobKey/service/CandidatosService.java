package com.crusaders.jobKey.service;

import com.crusaders.jobKey.entity.Candidatos;
import com.crusaders.jobKey.entity.Empresas;

import java.util.List;

public interface CandidatosService {
    List<Candidatos> listar();
    Candidatos guardar(Candidatos candidatos);
    Candidatos obtenerPorId(Integer id);
    Candidatos obtenerPorNombre(String nombre);

}
