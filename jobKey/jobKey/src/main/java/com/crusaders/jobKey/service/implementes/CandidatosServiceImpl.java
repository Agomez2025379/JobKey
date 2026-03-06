package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.dto.candidatos.CandidatosRequest;
import com.crusaders.jobKey.entity.Candidatos;
import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.CandidatosRepository;
import com.crusaders.jobKey.service.services.CandidatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatosServiceImpl implements CandidatosService {

    @Autowired
    private CandidatosRepository candidatosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Candidatos> listar() {
        return candidatosRepository.findAll();
    }

    @Override
    public Candidatos crear(CandidatosRequest r) {
        if (candidatosRepository.existsByEmail(r.getEmail())) {
            throw new ResourceNotFoundException("El email ya está registrado");
        }
        Candidatos c = new Candidatos();
        c.setNombre(r.getNombre());
        c.setApellido(r.getApellido());
        c.setEmail(r.getEmail());
        c.setTelefono(r.getTelefono());
        c.setPasswordHash(passwordEncoder.encode(r.getPassword()));
        c.setProfesion(r.getProfesion());
        c.setExperiencia(r.getExperiencia());
        c.setEducacion(r.getEducacion());
        c.setHabilidades(r.getHabilidades());
        c.setCurriculumUrl(r.getCurriculumUrl());

        if (r.getDepartamentoId() != null) {
            Departamentos d = new Departamentos();
            d.setIdDepartamento(r.getDepartamentoId()); // referencia por id
            c.setDepartamentos(d);
        }
        return candidatosRepository.save(c);
    }

    @Override
    public Candidatos actualizar(Integer id, CandidatosRequest r) {
        Candidatos c = candidatosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato no encontrado"));

        // Si cambia el email, valida unicidad
        if (!c.getEmail().equals(r.getEmail()) && candidatosRepository.existsByEmail(r.getEmail())) {
            throw new ResourceNotFoundException("El email ya está registrado");
        }

        c.setNombre(r.getNombre());
        c.setApellido(r.getApellido());
        c.setEmail(r.getEmail());
        c.setTelefono(r.getTelefono());
        if (r.getPassword() != null && !r.getPassword().isBlank()) {
            c.setPasswordHash(passwordEncoder.encode(r.getPassword()));
        }
        c.setProfesion(r.getProfesion());
        c.setExperiencia(r.getExperiencia());
        c.setEducacion(r.getEducacion());
        c.setHabilidades(r.getHabilidades());
        c.setCurriculumUrl(r.getCurriculumUrl());

        if (r.getDepartamentoId() != null) {
            Departamentos d = new Departamentos();
            d.setIdDepartamento(r.getDepartamentoId());
            c.setDepartamentos(d);
        } else {
            c.setDepartamentos(null);
        }
        return candidatosRepository.save(c);
    }

    @Override
    public void eliminar(Integer id) {
        Candidatos c = candidatosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato no encontrado"));
        candidatosRepository.delete(c);
    }

    @Override
    public Candidatos obtenerPorId(Integer id) {
        return candidatosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato no encontrado"));
    }

    @Override
    public Candidatos obtenerPorNombre(String nombre) {
        return candidatosRepository.findByNombre(nombre);
    }
}