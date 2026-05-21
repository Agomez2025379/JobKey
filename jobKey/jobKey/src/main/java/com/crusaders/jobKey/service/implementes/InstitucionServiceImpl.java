package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.entity.Institucion;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.InstitucionRepository;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.InstitucionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.crusaders.jobKey.dto.instituciones.InstitucionResponse;
import com.crusaders.jobKey.dto.instituciones.InstitucionRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitucionServiceImpl implements InstitucionService {

    private final InstitucionRepository institucionRepository;
    private final DepartamentosRepository departamentosRepository;
    private final UsuariosRepository usuarioRepository; // ← AGREGAR

    public InstitucionServiceImpl(
            InstitucionRepository institucionRepository,
            DepartamentosRepository departamentosRepository,
            UsuariosRepository usuarioRepository) { // ← AGREGAR
        this.institucionRepository = institucionRepository;
        this.departamentosRepository = departamentosRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public InstitucionResponse obtenerPorId(Integer id) {
        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institucion no encontrada"));
        return mapToResponse(institucion);
    }

    @Override
    public List<InstitucionResponse> listarInstituciones() {
        return institucionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public InstitucionResponse crearInstitucion(InstitucionRequest request) {
        Institucion institucion = new Institucion();

        // Asignar campos básicos
        institucion.setNombreInstitucion(request.getNombreInstitucion());
        institucion.setTelefono(request.getTelefono());
        institucion.setDescripcion(request.getDescripcion());
        institucion.setTipo(request.getTipo());
        institucion.setLogo(request.getLogo());

        // Asignar departamento (si existe)
        if (request.getDepartamentoId() != null) {
            Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
            institucion.setDepartamento(departamento);
        }

        // Asignar usuario (puedes obtener el usuario actual o usar uno por defecto)
        // Temporal: usar el primer usuario o necesitas el ID del usuario logueado
        if (request.getUsuarioId() != null) {
            Usuarios usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            institucion.setUsuario(usuario);
        }

        Institucion saved = institucionRepository.save(institucion);
        return mapToResponse(saved);
    }

    @Override
    public InstitucionResponse actualizarInstitucion(Integer id, InstitucionRequest request) {
        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institucion no encontrada"));

        if (request.getDepartamentoId() != null) {
            Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
            institucion.setDepartamento(departamento);
        }

        institucion.setNombreInstitucion(request.getNombreInstitucion());
        institucion.setTelefono(request.getTelefono());
        institucion.setDescripcion(request.getDescripcion());
        institucion.setTipo(request.getTipo());
        institucion.setLogo(request.getLogo());

        institucionRepository.save(institucion);
        return mapToResponse(institucion);
    }

    @Override
    public void eliminarInstitucion(Integer id) {
        if (!institucionRepository.existsById(id)) {
            throw new RuntimeException("Institucion no encontrada");
        }
        institucionRepository.deleteById(id);
    }


    @Override
    public Page<InstitucionResponse> listarInstitucionesPaginadas(Pageable pageable) {
        return institucionRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    private InstitucionResponse mapToResponse(Institucion institucion) {
        return new InstitucionResponse(
                institucion.getIdInstitucion(),
                institucion.getUsuario() != null ? institucion.getUsuario().getIdUsuario() : null,
                institucion.getNombreInstitucion(),
                institucion.getTelefono(),
                institucion.getDescripcion(),
                institucion.getTipo(),
                institucion.getDepartamento() != null ? institucion.getDepartamento().getIdDepartamento() : null
        );
    }
}