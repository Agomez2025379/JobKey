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

import java.util.Base64;
import java.util.List;
import java.util.Optional;
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
        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institución no encontrada con ID: " + id));
        return mapToResponse(institucion);
    }

    @Override
    public List<InstitucionResponse> listarTodas() {
        return institucionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<InstitucionResponse> listarPorUsuario(Integer usuarioId) {
        Optional<Institucion> optInstitucion = institucionRepository.findByUsuario_IdUsuario(usuarioId);
        if (optInstitucion.isPresent()) {
            return List.of(mapToResponse(optInstitucion.get()));
        }
        return List.of();
    }

    @Override
    public InstitucionResponse crearInstitucion(InstitucionRequest request) {
        Institucion institucion = new Institucion();

        Departamentos departamento = null;
        if (request.getDepartamentoId() != null) {
            departamento = departamentosRepository.findById(request.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + request.getDepartamentoId()));
        } else {
            departamento = departamentosRepository.findAll()
                    .stream()
                    .filter(d -> d.getDepartamento() != null && d.getDepartamento().equalsIgnoreCase("guatemala"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Departamento 'guatemala' no existe en la base de datos"));
        }
        institucion.setDepartamento(departamento);

        institucion.setNombreInstitucion(request.getNombreInstitucion());
        institucion.setTelefono(request.getTelefono());
        institucion.setDescripcion(request.getDescripcion());
        institucion.setTipo(request.getTipo());

        if (request.getLogoBase64() != null && !request.getLogoBase64().isEmpty()) {
            institucion.setLogo(Base64.getDecoder().decode(request.getLogoBase64()));
        }

        if (request.getUsuarioId() != null) {
            Usuarios usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getUsuarioId()));
            institucion.setUsuario(usuario);
        } else {
            throw new RuntimeException("Se requiere un usuarioId para crear la institucion");
        }

        Institucion saved = institucionRepository.save(institucion);
        return mapToResponse(saved);
    }

    @Override
    public InstitucionResponse actualizarInstitucion(Integer id, InstitucionRequest request) {
        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institución no encontrada con ID: " + id));

        institucion.setNombreInstitucion(request.getNombreInstitucion());
        institucion.setTelefono(request.getTelefono());
        institucion.setDescripcion(request.getDescripcion());
        institucion.setTipo(request.getTipo());

        if (request.getLogoBase64() != null && !request.getLogoBase64().isEmpty()) {
            institucion.setLogo(Base64.getDecoder().decode(request.getLogoBase64()));
        }

        if (request.getDepartamentoId() != null) {
            Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + request.getDepartamentoId()));
            institucion.setDepartamento(departamento);
        }

        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            Usuarios usuario = institucion.getUsuario();
            if (usuario != null) {
                usuario.setEmail(request.getEmail());
                usuarioRepository.save(usuario);
            }
        }

        Institucion saved = institucionRepository.save(institucion);
        return mapToResponse(saved);
    }

    @Override
    public void eliminarInstitucion(Integer id) {
        if (!institucionRepository.existsById(id)) {
            throw new RuntimeException("Institución no encontrada con ID: " + id);
        }
        institucionRepository.deleteById(id);
    }

    @Override
    public Page<InstitucionResponse> listarInstitucionesPaginadas(Pageable pageable) {
        return institucionRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    private InstitucionResponse mapToResponse(Institucion institucion) {
        Integer usuarioId = null;
        String email = null;
        if (institucion.getUsuario() != null) {
            usuarioId = institucion.getUsuario().getIdUsuario();
            email = institucion.getUsuario().getEmail();
        }

        Integer departamentoId = null;
        String nombreDepartamento = null;
        if (institucion.getDepartamento() != null) {
            departamentoId = institucion.getDepartamento().getIdDepartamento();
            nombreDepartamento = institucion.getDepartamento().getDepartamento();
        }

        String logoBase64 = null;
        if (institucion.getLogo() != null && institucion.getLogo().length > 0) {
            logoBase64 = Base64.getEncoder().encodeToString(institucion.getLogo());
        }

        return new InstitucionResponse(
                institucion.getIdInstitucion(),
                usuarioId,
                email,
                institucion.getNombreInstitucion(),
                institucion.getTelefono(),
                institucion.getDescripcion(),
                institucion.getTipo(),
                departamentoId,
                nombreDepartamento,
                logoBase64
        );
    }
}