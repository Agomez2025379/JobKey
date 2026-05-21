package com.crusaders.jobKey.service.services;

import com.crusaders.jobKey.dto.usuarios.UsuarioResponse;
import com.crusaders.jobKey.dto.usuarios.UsuarioUpdateRequest;

import java.util.List;

public interface UsuariosService {
    List<UsuarioResponse> listar();
    UsuarioResponse obtenerPorId(Integer id);
    UsuarioResponse actualizar(Integer id, UsuarioUpdateRequest request);
    void eliminar(Integer id);
}
