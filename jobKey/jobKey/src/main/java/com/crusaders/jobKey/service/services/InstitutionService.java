package com.crusaders.jobKey.service.services;


import com.crusaders.jobKey.DTO.instituciones.InstitucionResponse;

import java.util.List;

public interface InstitutionService {

    InstitucionResponse getById(Integer id);

    List<InstitucionResponse> listInstitutions();

    InstitucionResponse updateInstitution(Integer id, InstitucionResponse request);

    void deleteInstitution(Integer id);
}