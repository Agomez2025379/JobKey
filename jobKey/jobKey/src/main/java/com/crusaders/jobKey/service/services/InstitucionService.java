package com.crusaders.jobKey.service.services;


import com.crusaders.jobKey.DTO.instituciones.InstitucionResponse;
import com.crusaders.jobKey.DTO.instituciones.InstitucionRequest;

import java.util.List;

public interface InstitucionService {

    InstitucionResponse obtenerPorId(Integer id);

    List<InstitucionResponse> listarInstituciones();

    InstitucionResponse actualizarInstitucion(Integer id, InstitucionRequest request);

    void eliminarInstitucion(Integer id);
}