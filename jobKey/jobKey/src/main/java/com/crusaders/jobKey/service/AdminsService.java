package com.crusaders.jobKey.service;

import com.crusaders.jobKey.entity.Admins;

import java.util.List;

public interface AdminsService {
    List<Admins> listar();
    Admins obtenerPorId(Integer id);
    Admins obtenerPorEmail(String email);
    Admins obtenerPorNombre(String nombre);
}
