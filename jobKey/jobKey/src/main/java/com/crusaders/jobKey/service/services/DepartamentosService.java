package com.crusaders.jobKey.service.services;

import com.crusaders.jobKey.entity.Departamentos;

import java.util.List;

public interface DepartamentosService {
    List<Departamentos> listar();
    Departamentos obtenerPorId(Integer id);
    Departamentos obtenerPorNombre(String nombre);

}