package com.crusaders.jobKey.service.services;

import com.crusaders.jobKey.entity.Admins;

import java.util.List;

public interface AdminsService {


    List<Admins> listar();

    Admins obtenerPorId(Integer id);

    Admins actualizar(Integer id, Admins admin);

    void eliminar(Integer id);

}
