package com.crusaders.jobKey.service.implementes;



import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.entity.Institutions;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.InstitutionRepository;
import com.crusaders.jobKey.service.services.InstitutionService;
import org.springframework.stereotype.Service;
import com.crusaders.jobKey.DTO.instituciones.InstitucionResponse;
import com.crusaders.jobKey.DTO.instituciones.InstitucionRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitucionServiceImpl implements InstitutionService {

    private final InstitutionRepository institucionRepository;
    private final DepartamentosRepository departamentosRepository;

    public InstitucionServiceImpl(
            InstitutionRepository institucionRepository,
            DepartamentosRepository departamentosRepository) {

        this.institucionRepository = institucionRepository;
        this.departamentosRepository = departamentosRepository;
    }


    @Override
    public InstitucionResponse obtenerPorId(Integer id) {

        Institutions institutions = institucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institucion no encontrada"));

        return mapToResponse(institutions);
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

        Institutions institutions = institucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institucion no encontrada"));

        Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        institutions.setInstitutionName(request.getNombreInstitucion());
        institutions.setPhone(request.getTelefono());
        institutions.setDescription(request.getDescripcion());
        institutions.setType(request.getTipo());
        institutions.setLogo(request.getLogo());
        institutions.setDepartment(departamento);

        institucionRepository.save(institutions);

        return mapToResponse(institutions);
    }

    @Override
    public void eliminarInstitucion(Integer id) {

        if (!institucionRepository.existsById(id)) {
            throw new RuntimeException("Institucion no encontrada");
        }

        institucionRepository.deleteById(id);
    }

    private InstitucionResponse mapToResponse(Institutions institutions) {

        return new InstitucionResponse(
                institutions.getInstitutionId(),
                institutions.getUser().(),
                institutions.getInstitutionName(),
                institutions.getPhone(),
                institutions.getDescription(),
                institutions.getType(),
                institutions.getDepartment().getIdDepartamento()
        );
    }
}
