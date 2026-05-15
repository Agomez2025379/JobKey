package com.crusaders.jobKey.service.services;


import com.crusaders.jobKey.dto.candidatos.CandidatosRequest;
import com.crusaders.jobKey.dto.candidatos.CandidatosResponse;

import java.util.List;

public interface CandidatosService {



    CandidatosResponse getCandidate(Integer id);

    List<CandidatosResponse> listCandidates();

    CandidatosResponse updateCandidate(Integer id, CandidatosRequest request);

    void deleteCandidate(Integer id);

}