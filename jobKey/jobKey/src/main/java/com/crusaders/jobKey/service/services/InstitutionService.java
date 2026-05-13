package com.crusaders.jobKey.service.services;


import com.crusaders.jobKey.DTO.institutions.InstitutionResponse;
import com.crusaders.jobKey.DTO.institutions.InstitutionRequest;

import java.util.List;

public interface InstitutionService {

    InstitutionResponse getById(Integer id);

    List<InstitutionResponse> listInstitutions();

    InstitutionResponse updateInstitution(Integer id, InstitutionRequest request);  // ← Cambiar a InstitutionRequest

    void deleteInstitution(Integer id);
}