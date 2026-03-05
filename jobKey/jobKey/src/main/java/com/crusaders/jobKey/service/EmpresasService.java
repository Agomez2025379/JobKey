package com.crusaders.jobKey.service;

import com.crusaders.jobKey.dto.empresas.EmpresasRequest;
import com.crusaders.jobKey.entity.Empresas;

import java.util.List;

public interface EmpresasService {
    List<Empresas> listarTodas();
    Empresas crear(EmpresasRequest request);
    Empresas actualizar(Integer id, EmpresasRequest request);
    Empresas buscarPorId(Integer id);
    void eliminar(Integer id);
}