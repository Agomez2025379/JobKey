package com.crusaders.jobKey.service;

import com.crusaders.jobKey.entity.Resenas;
import com.crusaders.jobKey.entity.ResenaTipo;

import java.util.List;

public interface ResenasService {
    List<Resenas> listar();
    Resenas obtenerPorId(Integer id);
    List<Resenas> obtenerPorTipo(ResenaTipo tipo);
    List<Resenas> obtenerPorEmpresaId(Integer empresaId);
}