package com.crusaders.jobKey.service;

import com.crusaders.jobKey.entity.Empresas;

import java.util.List;

public interface EmpresasService {
    List<Empresas> listarTodas(); // Asegúrate que el nombre sea igual al del Impl
    Empresas guardar(Empresas empresa);
    Empresas buscarPorId(Integer id);
    void eliminar(Integer id);
}
