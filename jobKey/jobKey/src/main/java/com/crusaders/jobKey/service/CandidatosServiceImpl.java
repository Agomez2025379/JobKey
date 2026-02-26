package com.crusaders.jobKey.service;

import com.crusaders.jobKey.entity.Candidatos;
import com.crusaders.jobKey.entity.Empresas;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.CandidatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatosServiceImpl implements CandidatosService {

    @Autowired
    private CandidatosRepository candidatosRepository;

    @Override
    public List<Candidatos> listar() {
        return candidatosRepository.findAll();
    }

    @Override
    public Candidatos guardar(Candidatos candidatos) {
        return candidatosRepository.save(candidatos);
    }

    @Override
    public Candidatos obtenerPorId(Integer id) {
        // Si no llega a encontrar el id le mostrara null
        return candidatosRepository.findById(id).orElse(null);
    }

    @Override
    public Candidatos obtenerPorNombre(String nombre) {
        return candidatosRepository.findByNombre(nombre);
    }
}