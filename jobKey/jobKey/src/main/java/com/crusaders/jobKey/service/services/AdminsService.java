// AdminsService.java
package com.crusaders.jobKey.service.services;

import com.crusaders.jobKey.dto.admins.*;
import com.crusaders.jobKey.entity.Admins;
import java.util.List;

public interface AdminsService {
    List<AdminResponse> listar();
    AdminResponse obtenerPorId(Integer id);
    AdminResponse crear(AdminRequest request);
    AdminResponse actualizar(Integer id, AdminUpdateRequest request);
    void eliminar(Integer id);
}