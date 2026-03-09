package com.crusaders.jobKey.service.services;


import com.crusaders.jobKey.DTO.empresas.EmpresasRequest;
import com.crusaders.jobKey.DTO.empresas.EmpresasResponse;

import java.util.List;

public interface EmpresasService {


    EmpresasResponse obtenerEmpresa(Integer id);

    List<EmpresasResponse> listarEmpresas();

    EmpresasResponse actualizarEmpresa(Integer id, EmpresasRequest request);

    void eliminarEmpresa(Integer id);

}