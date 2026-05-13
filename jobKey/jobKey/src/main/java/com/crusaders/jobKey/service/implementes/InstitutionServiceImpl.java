package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.entity.Institutions;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.InstitutionRepository;
import com.crusaders.jobKey.service.services.InstitutionService;
import org.springframework.stereotype.Service;
import com.crusaders.jobKey.DTO.institutions.InstitutionRequest;
import com.crusaders.jobKey.DTO.institutions.InstitutionResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final DepartamentosRepository departmentRepository;

    public InstitutionServiceImpl(
            InstitutionRepository institutionRepository,
            DepartamentosRepository departmentRepository) {

        this.institutionRepository = institutionRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public InstitutionResponse getById(Integer id) {

        Institutions institution = institutionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found"));

        return mapToResponse(institution);
    }

    @Override
    public List<InstitutionResponse> listInstitutions() {

        return institutionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InstitutionResponse updateInstitution(Integer id, InstitutionRequest request) {

        Institutions institution = institutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institution not found"));

        Departamentos department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        institution.setInstitutionName(request.getInstitutionName());
        institution.setPhone(request.getPhone());
        institution.setDescription(request.getDescription());
        institution.setType(request.getType());
        institution.setLogo(request.getLogo());
        institution.setDepartment(department);

        institutionRepository.save(institution);

        return mapToResponse(institution);
    }

    @Override
    public void deleteInstitution(Integer id) {

        if (!institutionRepository.existsById(id)) {
            throw new RuntimeException("Institution not found");
        }

        institutionRepository.deleteById(id);
    }

    private InstitutionResponse mapToResponse(Institutions institution) {

        return new InstitutionResponse(
                institution.getInstitutionId(),
                institution.getUser().getIdUsuario(),  // ← Cambiado a getIdUsuario()
                institution.getInstitutionName(),
                institution.getPhone(),
                institution.getDescription(),
                institution.getType(),
                institution.getDepartment().getIdDepartamento()
        );
    }
}