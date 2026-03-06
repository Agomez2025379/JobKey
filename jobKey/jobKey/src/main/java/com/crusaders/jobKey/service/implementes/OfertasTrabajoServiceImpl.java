package com.crusaders.jobKey.service.implementes;


import com.crusaders.jobKey.DTO.ofertas.OfertasTrabajoRequest;
import com.crusaders.jobKey.DTO.ofertas.OfertasTrabajoResponse;
import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.entity.Empresas;
import com.crusaders.jobKey.entity.OfertasTrabajo;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.EmpresasRepository;
import com.crusaders.jobKey.repository.OfertasTrabajoRepository;
import com.crusaders.jobKey.service.services.OfertasTrabajoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfertasTrabajoServiceImpl implements OfertasTrabajoService {

    private final OfertasTrabajoRepository ofertasRepository;
    private final EmpresasRepository empresasRepository;
    private final DepartamentosRepository departamentosRepository;

    @Override
    public OfertasTrabajoResponse crear(OfertasTrabajoRequest request) {

        Empresas empresa = empresasRepository.findById(request.getEmpresaId())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada"));

        Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado"));

        OfertasTrabajo oferta = new OfertasTrabajo();
        oferta.setTitulo(request.getTitulo());
        oferta.setDescripcion(request.getDescripcion());
        oferta.setRequisitos(request.getRequisitos());
        oferta.setSalario(request.getSalario());
        oferta.setModalidad(request.getModalidad());
        oferta.setTipoJornada(request.getTipoJornada());
        oferta.setNivelRequerido(request.getNivelRequerido());
        oferta.setFechaCierre(request.getFechaCierre());
        oferta.setEmpresa(empresa);
        oferta.setDepartamento(departamento);
        oferta.setActiva(true);

        OfertasTrabajo guardada = ofertasRepository.save(oferta);

        return mapToResponse(guardada);
    }

    @Override
    public List<OfertasTrabajoResponse> listar() {
        return ofertasRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OfertasTrabajoResponse obtenerPorId(Integer id) {
        OfertasTrabajo oferta = ofertasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oferta no encontrada"));

        return mapToResponse(oferta);
    }

    @Override
    public OfertasTrabajoResponse actualizar(Integer id, OfertasTrabajoRequest request) {

        OfertasTrabajo oferta = ofertasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oferta no encontrada"));

        oferta.setTitulo(request.getTitulo());
        oferta.setDescripcion(request.getDescripcion());
        oferta.setRequisitos(request.getRequisitos());
        oferta.setSalario(request.getSalario());
        oferta.setModalidad(null);
        oferta.setModalidad(request.getModalidad());
        oferta.setTipoJornada(null);
        oferta.setTipoJornada(request.getTipoJornada());
        oferta.setNivelRequerido(null);
        oferta.setNivelRequerido(request.getNivelRequerido());
        oferta.setFechaCierre(request.getFechaCierre());

        return mapToResponse(ofertasRepository.save(oferta));
    }

    @Override
    public void eliminar(Integer id) {
        if (!ofertasRepository.existsById(id)) {
            throw new ResourceNotFoundException("Oferta no encontrada");
        }
        ofertasRepository.deleteById(id);
    }

    private OfertasTrabajoResponse mapToResponse(OfertasTrabajo oferta) {

        OfertasTrabajoResponse dto = new OfertasTrabajoResponse();

        dto.setIdOfertaTrabajo(oferta.getIdOfertaTrabajo());
        dto.setTitulo(oferta.getTitulo());
        dto.setDescripcion(oferta.getDescripcion());
        dto.setRequisitos(oferta.getRequisitos());
        dto.setSalario(oferta.getSalario());
        dto.setModalidad(oferta.getModalidad());
        dto.setTipoJornada(oferta.getTipoJornada());
        dto.setNivelRequerido(oferta.getNivelRequerido());
        dto.setFechaPublicacion(oferta.getFechaPublicacion());
        dto.setFechaCierre(oferta.getFechaCierre());
        dto.setActiva(oferta.getActiva());

        dto.setEmpresaId(oferta.getEmpresa().getIdEmpresa());
        dto.setNombreEmpresa(oferta.getEmpresa().getNombreEmpresa());

        dto.setDepartamentoId(oferta.getDepartamento().getIdDepartamento());
        dto.setNombreDepartamento(oferta.getDepartamento().getDepartamento());

        return dto;
    }
}
