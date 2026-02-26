package com.crusaders.jobKey.service;

import com.crusaders.jobKey.entity.Empresas;
import com.crusaders.jobKey.repository.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresasServiceImpl implements EmpresasService {

    @Autowired
    private EmpresasRepository empresasRepository;

    @Override
    public List<Empresas> listarTodas() {
        return empresasRepository.findAll();
    }

    @Override
    public Empresas guardar(Empresas empresa) {
        return empresasRepository.save(empresa);
    }

    @Override
    public Empresas buscarPorId(Integer id) {
        return empresasRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        empresasRepository.deleteById(id);
    }
}