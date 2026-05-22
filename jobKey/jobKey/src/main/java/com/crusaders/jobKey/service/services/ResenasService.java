package com.crusaders.jobKey.service.services;

import com.crusaders.jobKey.entity.Resenas;
import com.crusaders.jobKey.enums.ResenaTipo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResenasService {
    List<Resenas> listar();
    Resenas obtenerPorId(Integer id);

    @Transactional(readOnly = true)
    List<Resenas> obtenerPorTipo(ResenaTipo tipo);

    List<Resenas> obtenerPorEmpresaId(Integer empresaId);
    Resenas guardar(Resenas resena);
}