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
    private final UsuariosRepository usuarioRepository;

    public InstitucionServiceImpl(
            InstitucionRepository institucionRepository,
            DepartamentosRepository departamentosRepository,
            UsuariosRepository usuarioRepository) {
        this.institucionRepository = institucionRepository;
        this.departamentosRepository = departamentosRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public InstitucionResponse obtenerPorId(Integer id) {
        System.out.println("=== BUSCANDO INSTITUCIÓN POR ID: " + id);
        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institución no encontrada con ID: " + id));
        System.out.println("Institución encontrada: " + institucion.getNombreInstitucion());
        return mapToResponse(institucion);
    }

    @Override
    public List<InstitucionResponse> listarInstituciones() {
        System.out.println("=== LISTANDO TODAS LAS INSTITUCIONES");
        return institucionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InstitucionResponse crearInstitucion(InstitucionRequest request) {
        System.out.println("=== CREANDO NUEVA INSTITUCIÓN");
        Institucion institucion = new Institucion();

        // Buscar departamento por nombre "guatemala"
        Departamentos departamento = departamentosRepository.findAll()
                .stream()
                .filter(d -> d.getDepartamento() != null && d.getDepartamento().equalsIgnoreCase("guatemala"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Departamento 'guatemala' no existe en la base de datos"));

        System.out.println("Departamento encontrado: ID=" + departamento.getIdDepartamento() +
                ", Nombre=" + departamento.getDepartamento());
        institucion.setDepartamento(departamento);

        // Asignar campos básicos
        institucion.setNombreInstitucion(request.getNombreInstitucion());
        institucion.setTelefono(request.getTelefono());
        institucion.setDescripcion(request.getDescripcion());
        institucion.setTipo(request.getTipo());

        if (request.getLogo() != null && request.getLogo().length > 0) {
            institucion.setLogo(request.getLogo());
        }

        // Asignar usuario
        if (request.getUsuarioId() != null) {
            Usuarios usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getUsuarioId()));
            institucion.setUsuario(usuario);
            System.out.println("Usuario encontrado: ID=" + usuario.getIdUsuario() + ", Email=" + usuario.getEmail());
        } else {
            throw new RuntimeException("Se requiere un usuarioId para crear la institucion");
        }

        Institucion saved = institucionRepository.save(institucion);
        System.out.println("Institución guardada con ID: " + saved.getIdInstitucion());
        return mapToResponse(saved);
    }

    @Override
    public InstitucionResponse actualizarInstitucion(Integer id, InstitucionRequest request) {
        System.out.println("=== ACTUALIZANDO INSTITUCIÓN ID: " + id);
        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institución no encontrada con ID: " + id));

        // Actualizar campos
        institucion.setNombreInstitucion(request.getNombreInstitucion());
        institucion.setTelefono(request.getTelefono());
        institucion.setDescripcion(request.getDescripcion());
        institucion.setTipo(request.getTipo());

        if (request.getLogo() != null && request.getLogo().length > 0) {
            institucion.setLogo(request.getLogo());
        }

        // Actualizar departamento si se proporciona
        if (request.getDepartamentoId() != null) {
            Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + request.getDepartamentoId()));
            institucion.setDepartamento(departamento);
            System.out.println("Departamento actualizado: ID=" + departamento.getIdDepartamento());
        }

        // Actualizar email del usuario si se proporciona
        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            Usuarios usuario = institucion.getUsuario();
            if (usuario != null) {
                usuario.setEmail(request.getEmail());
                usuarioRepository.save(usuario);
                System.out.println("Email de usuario actualizado: " + request.getEmail());
            }
        }

        Institucion saved = institucionRepository.save(institucion);
        System.out.println("Institución actualizada correctamente");
        return mapToResponse(saved);
    }

    @Override
    public void eliminarInstitucion(Integer id) {
        System.out.println("=== Eliminando Institucion ID: " + id);
        if (!institucionRepository.existsById(id)) {
            throw new RuntimeException("Institución no encontrada con ID: " + id);
        }
        institucionRepository.deleteById(id);
        System.out.println("Institución eliminada correctamente");
    }

    @Override
    public Page<InstitucionResponse> listarInstitucionesPaginadas(Pageable pageable) {
        return institucionRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    private InstitucionResponse mapToResponse(Institucion institucion) {
        System.out.println("Mapeando institución ID: " + institucion.getIdInstitucion());

        Integer usuarioId = null;
        String email = null;  // ← AGREGAR
        if (institucion.getUsuario() != null) {
            usuarioId = institucion.getUsuario().getIdUsuario();
            email = institucion.getUsuario().getEmail();  // ← AGREGAR
            System.out.println("  - Usuario ID: " + usuarioId + ", Email: " + email);
        } else {
            System.out.println("  - ADVERTENCIA: La institución no tiene usuario asociado");
        }

        Integer departamentoId = null;
        if (institucion.getDepartamento() != null) {
            departamentoId = institucion.getDepartamento().getIdDepartamento();
            System.out.println("  - Departamento ID: " + departamentoId);
        } else {
            System.out.println("  - ADVERTENCIA: La institución no tiene departamento asociado");
        }

        return new InstitucionResponse(
                institucion.getIdInstitucion(),
                usuarioId,
                email,  // ← AGREGAR
                institucion.getNombreInstitucion(),
                institucion.getTelefono(),
                institucion.getDescripcion(),
                institucion.getTipo(),
                departamentoId
        );
    }
}