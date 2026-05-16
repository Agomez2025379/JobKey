package com.crusaders.jobKey.service.services;

import com.crusaders.jobKey.dto.instituciones.InstitucionResponse;
import com.crusaders.jobKey.dto.instituciones.InstitucionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface InstitucionService {

    InstitucionResponse obtenerPorId(Integer id);

    List<InstitucionResponse> listarInstituciones();

    InstitucionResponse actualizarInstitucion(Integer id, InstitucionRequest request);

    void eliminarInstitucion(Integer id);

    InstitucionResponse crearInstitucion(InstitucionRequest request);

    Page<InstitucionResponse> listarInstitucionesPaginadas(Pageable pageable);
}