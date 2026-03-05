package com.crusaders.jobKey.service;

import com.crusaders.jobKey.dto.empresas.EmpresasRequest;
import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.entity.Empresas;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresasServiceImpl implements EmpresasService {

    @Autowired
    private EmpresasRepository empresasRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Empresas> listarTodas() {
        return empresasRepository.findAll();
    }

    @Override
    public Empresas crear(EmpresasRequest r) {
        if (empresasRepository.existsByEmail(r.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        Empresas e = new Empresas();
        e.setNombreEmpresa(r.getNombreEmpresa());
        e.setEmail(r.getEmail());
        e.setTelefono(r.getTelefono());
        e.setPasswordHash(passwordEncoder.encode(r.getPassword()));
        e.setDescripcion(r.getDescripcion());
        e.setSectorEmpresarial(r.getSectorEmpresarial());
        if (r.getDepartamentoId() != null) {
            Departamentos d = new Departamentos();
            d.setIdDepartamento(r.getDepartamentoId());
            e.setDepartamentos(d);
        }
        return empresasRepository.save(e);
    }

    @Override
    public Empresas actualizar(Integer id, EmpresasRequest r) {
        Empresas e = empresasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada"));

        if (!e.getEmail().equals(r.getEmail()) && empresasRepository.existsByEmail(r.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        e.setNombreEmpresa(r.getNombreEmpresa());
        e.setEmail(r.getEmail());
        e.setTelefono(r.getTelefono());
        if (r.getPassword() != null && !r.getPassword().isBlank()) {
            e.setPasswordHash(passwordEncoder.encode(r.getPassword()));
        }
        e.setDescripcion(r.getDescripcion());
        e.setSectorEmpresarial(r.getSectorEmpresarial());
        if (r.getDepartamentoId() != null) {
            Departamentos d = new Departamentos();
            d.setIdDepartamento(r.getDepartamentoId());
            e.setDepartamentos(d);
        } else {
            e.setDepartamentos(null);
        }
        return empresasRepository.save(e);
    }

    @Override
    public Empresas buscarPorId(Integer id) {
        return empresasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada"));
    }

    @Override
    public void eliminar(Integer id) {
        Empresas e = buscarPorId(id);
        empresasRepository.delete(e);
    }
}