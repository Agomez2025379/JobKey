package com.crusaders.jobKey.service.services;


import com.crusaders.jobKey.dto.empresas.EmpresasRequest;
import com.crusaders.jobKey.dto.empresas.EmpresasResponse;

import java.util.List;

public interface EmpresasService {


    EmpresasResponse getCompany(Integer id);

    List<EmpresasResponse> listCompanies();

    EmpresasResponse updateCompany(Integer id, EmpresasRequest request);

    void deleteCompany(Integer id);

}