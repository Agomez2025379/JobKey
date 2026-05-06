package com.crusaders.jobKey.service.services;


import com.crusaders.jobKey.DTO.empresas.EmpresasRequest;
import com.crusaders.jobKey.DTO.empresas.EmpresasResponse;

import java.util.List;

public interface EmpresasService {


    EmpresasResponse getCompany(Integer id);

    List<EmpresasResponse> listCompanmies();

    EmpresasResponse updateCompany(Integer id, EmpresasRequest request);

    void deleteCompany(Integer id);

}