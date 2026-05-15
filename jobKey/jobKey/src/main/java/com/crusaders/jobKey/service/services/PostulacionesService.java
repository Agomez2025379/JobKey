package com.crusaders.jobKey.service.services;

import com.crusaders.jobKey.dto.postulaciones.PostulacionesRequest;
import com.crusaders.jobKey.dto.postulaciones.PostulacionesResponse;

import java.util.List;

public interface PostulacionesService {

    PostulacionesResponse crearPostulacion(PostulacionesRequest request);

    List<PostulacionesResponse> listarPostulaciones();

    PostulacionesResponse obtenerPostulacion(Integer id);

    void eliminarPostulacion(Integer id);


}