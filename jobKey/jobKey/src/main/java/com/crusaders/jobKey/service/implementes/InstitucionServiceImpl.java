package com.crusaders.jobKey.service.implementes;



import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.entity.Institucion;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.InstitucionRepository;
import com.crusaders.jobKey.service.services.InstitucionService;
import org.springframework.stereotype.Service;
import com.crusaders.jobKey.DTO.instituciones.InstitucionResponse;
import com.crusaders.jobKey.DTO.instituciones.InstitucionRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitucionServiceImpl implements InstitucionService {

    private final InstitucionRepository institucionRepository;
    private final DepartamentosRepository departamentosRepository;

    public InstitucionServiceImpl(
            InstitucionRepository institucionRepository,
            DepartamentosRepository departamentosRepository) {

        this.institucionRepository = institucionRepository;
        this.departamentosRepository = departamentosRepository;
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
    public InstitucionResponse actualizarInstitucion(Integer id, InstitucionRequest request) {

        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institucion no encontrada"));

        Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        institucion.setNombreInstitucion(request.getNombreInstitucion());
        institucion.setTelefono(request.getTelefono());
        institucion.setDescripcion(request.getDescripcion());
        institucion.setTipo(request.getTipo());
        institucion.setLogo(request.getLogo());
        institucion.setDepartamento(departamento);

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

    private InstitucionResponse mapToResponse(Institucion institucion) {

        return new InstitucionResponse(
                institucion.getIdInstitucion(),
                institucion.getUsuario().getIdUsuario(),
                institucion.getNombreInstitucion(),
                institucion.getTelefono(),
                institucion.getDescripcion(),
                institucion.getTipo(),
                institucion.getDepartamento().getIdDepartamento()
        );
    }
}
