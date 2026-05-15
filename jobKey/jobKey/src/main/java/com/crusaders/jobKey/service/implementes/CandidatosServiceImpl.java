package com.crusaders.jobKey.service.implementes;


import com.crusaders.jobKey.dto.candidatos.*;
import com.crusaders.jobKey.entity.*;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.*;
import com.crusaders.jobKey.service.services.CandidatosService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatosServiceImpl implements CandidatosService {

    private final CandidatosRepository candidatosRepository;
    private final UsuariosRepository usuariosRepository;
    private final DepartamentosRepository departamentosRepository;

    public CandidatosServiceImpl(
            CandidatosRepository candidatosRepository,
            UsuariosRepository usuariosRepository,
            DepartamentosRepository departamentosRepository
    ) {
        this.candidatosRepository = candidatosRepository;
        this.usuariosRepository = usuariosRepository;
        this.departamentosRepository = departamentosRepository;
    }


    @Override
    public CandidatosResponse obtenerCandidato(Integer id) {

        Candidatos candidato = candidatosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato no encontrado"));

        return mapToResponse(candidato);
    }

    @Override
    public List<CandidatosResponse> listarCandidatos() {

        return candidatosRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CandidatosResponse actualizarCandidato(Integer id, CandidatosRequest request) {

        Candidatos candidato = candidatosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        candidato.setNombre(request.getNombre());
        candidato.setApellido(request.getApellido());
        candidato.setTelefono(request.getTelefono());
        candidato.setProfesion(request.getProfesion());
        candidato.setExperiencia(request.getExperiencia());
        candidato.setEducacion(request.getEducacion());
        candidato.setHabilidades(request.getHabilidades());
        candidato.setCurriculumUrl(request.getCurriculumUrl());
        candidato.setDepartamento(departamento);

        candidatosRepository.save(candidato);

        return mapToResponse(candidato);
    }

    @Override
    public void eliminarCandidato(Integer id) {

        if (!candidatosRepository.existsById(id)) {
            throw new RuntimeException("Candidato no encontrado");
        }

        candidatosRepository.deleteById(id);
    }

    private CandidatosResponse mapToResponse(Candidatos candidato) {

        return new CandidatosResponse(
                candidato.getIdCandidato(),
                candidato.getUsuario().getIdUsuario(),
                candidato.getNombre(),
                candidato.getApellido(),
                candidato.getTelefono(),
                candidato.getProfesion(),
                candidato.getExperiencia(),
                candidato.getEducacion(),
                candidato.getHabilidades(),
                candidato.getCurriculumUrl(),
                candidato.getDepartamento().getIdDepartamento()
        );
    }
}