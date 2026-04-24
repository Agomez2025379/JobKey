package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.entity.Admins;

import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.AdminsRepository;
import com.crusaders.jobKey.service.services.AdminsService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AdminsServiceImpl implements AdminsService {

    private final AdminsRepository repository;

    public AdminsServiceImpl(AdminsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Admins> listar() {
        return repository.findAll();
    }

    @Override
    public Admins obtenerPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID, no encontrado: " + id ));
    }

    @Override
    public Admins actualizar(Integer id, Admins usuario) {
        Admins existente = obtenerPorId(id);

        existente.setNombre(usuario.getNombre());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw  new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}