package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.entity.Institucion;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.enums.ETipoInstitucion;
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
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found with ID: " + id));
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
        return institucionRepository.findByUsuario_IdUsuario(usuarioId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InstitucionResponse crearInstitucion(InstitucionRequest request) {
        List<Institucion> institucionesUsuario = institucionRepository.findByUsuario_IdUsuario(request.getUsuarioId());

        boolean yaTieneUniversidad = institucionesUsuario.stream()
                .anyMatch(i -> i.getTipo() == ETipoInstitucion.UNIVERSIDAD);
        boolean yaTieneInstituto = institucionesUsuario.stream()
                .anyMatch(i -> i.getTipo() == ETipoInstitucion.INSTITUTO);
        boolean yaTieneColegio = institucionesUsuario.stream()
                .anyMatch(i -> i.getTipo() == ETipoInstitucion.COLEGIO);

        if (request.getTipo() == ETipoInstitucion.UNIVERSIDAD && yaTieneUniversidad) {
            throw new RuntimeException("You already have a registered university. You can only have one.");
        }
        if (request.getTipo() == ETipoInstitucion.INSTITUTO && yaTieneInstituto) {
            throw new RuntimeException("You already have a registered institute. You can only have one.");
        }
        if (request.getTipo() == ETipoInstitucion.COLEGIO && yaTieneColegio) {
            throw new RuntimeException("You already have a registered school. You can only have one.");
        }

        Institucion institucion = new Institucion();

        Departamentos departamento = null;
        if (request.getDepartamentoId() != null) {
            departamento = departamentosRepository.findById(request.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Department not found with ID: " + request.getDepartamentoId()));
        } else {
            departamento = departamentosRepository.findAll()
                    .stream()
                    .filter(d -> d.getDepartamento() != null && d.getDepartamento().equalsIgnoreCase("guatemala"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Department 'guatemala' does not exist in the database"));
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
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUsuarioId()));
            institucion.setUsuario(usuario);
        } else {
            throw new RuntimeException("A userId is required to create the institution");
        }

        Institucion saved = institucionRepository.save(institucion);
        return mapToResponse(saved);
    }

    @Override
    public InstitucionResponse actualizarInstitucion(Integer id, InstitucionRequest request) {
        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institution not found with ID: " + id));

        institucion.setNombreInstitucion(request.getNombreInstitucion());
        institucion.setTelefono(request.getTelefono());
        institucion.setDescripcion(request.getDescripcion());
        institucion.setTipo(request.getTipo());

        if (request.getLogoBase64() != null && !request.getLogoBase64().isEmpty()) {
            institucion.setLogo(Base64.getDecoder().decode(request.getLogoBase64()));
        }

        if (request.getDepartamentoId() != null) {
            Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Department not found with ID: " + request.getDepartamentoId()));
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
            throw new RuntimeException("Institution not found with ID: " + id);
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