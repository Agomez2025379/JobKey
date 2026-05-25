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
    public CandidatosResponse getCandidate(Integer id) {
        Candidatos candidato = candidatosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        return mapToResponse(candidato);
    }

    @Override
    public List<CandidatosResponse> listCandidates() {
        return candidatosRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidatosResponse> listarTodos() {
        return listCandidates();
    }

    @Override
    public CandidatosResponse updateCandidate(Integer id, CandidatosRequest request) {
        Candidatos candidato = candidatosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

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
    public void deleteCandidate(Integer id) {
        if (!candidatosRepository.existsById(id)) {
            throw new RuntimeException("Candidate not found");
        }
        candidatosRepository.deleteById(id);
    }

    private CandidatosResponse mapToResponse(Candidatos candidato) {
        Integer usuarioId = null;
        if (candidato.getUsuario() != null) {
            usuarioId = candidato.getUsuario().getIdUsuario();
        }

        Integer departamentoId = null;
        if (candidato.getDepartamento() != null) {
            departamentoId = candidato.getDepartamento().getIdDepartamento();
        }

        return new CandidatosResponse(
                candidato.getIdCandidato(),
                usuarioId,
                candidato.getNombre(),
                candidato.getApellido(),
                candidato.getTelefono(),
                candidato.getProfesion(),
                candidato.getExperiencia(),
                candidato.getEducacion(),
                candidato.getHabilidades(),
                candidato.getCurriculumUrl(),
                departamentoId
        );
    }

    @Override
    public CandidatosResponse findByUsuarioId(Integer idUsuario) {
        Candidatos candidato = candidatosRepository.findByUsuario_IdUsuario(idUsuario);
        if (candidato == null) {
            return null;
        }
        return mapToResponse(candidato);
    }

    @Override
    public CandidatosResponse createCandidate(CandidatosRequest request, Integer idUsuario) {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Candidatos candidato = new Candidatos();
        candidato.setUsuario(usuario);
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
}