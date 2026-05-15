package com.crusaders.jobKey.service.implementes;


import com.crusaders.jobKey.dto.empresas.EmpresasRequest;
import com.crusaders.jobKey.dto.empresas.EmpresasResponse;
import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.entity.Empresas;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.EmpresasRepository;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.EmpresasService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresasServiceImpl implements EmpresasService {

    private final EmpresasRepository empresasRepository;
    private final DepartamentosRepository departamentosRepository;

    public EmpresasServiceImpl(
            EmpresasRepository empresasRepository,
            UsuariosRepository usuariosRepository,
            DepartamentosRepository departamentosRepository
    ) {
        this.empresasRepository = empresasRepository;
        this.departamentosRepository = departamentosRepository;
    }


    @Override
    public EmpresasResponse getCompany(Integer id) {

        Empresas empresa = empresasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        return mapToResponse(empresa);
    }

    @Override
    public List<EmpresasResponse> listCompanies() {

        return empresasRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmpresasResponse updateCompany(Integer id, EmpresasRequest request) {

        Empresas empresa = empresasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        empresa.setNombreEmpresa(request.getNombreEmpresa());
        empresa.setTelefono(request.getTelefono());
        empresa.setDescripcion(request.getDescripcion());
        empresa.setSectorEmpresarial(request.getSectorEmpresarial());
        empresa.setLogo(request.getLogo());
        empresa.setDepartamento(departamento);

        empresasRepository.save(empresa);

        return mapToResponse(empresa);
    }

    @Override
    public void deleteCompany(Integer id) {

        if (!empresasRepository.existsById(id)) {
            throw new RuntimeException("Company not found");
        }

        empresasRepository.deleteById(id);
    }

    private EmpresasResponse mapToResponse(Empresas empresa) {

        return new EmpresasResponse(
                empresa.getIdEmpresa(),
                empresa.getUsuario().getIdUsuario(),
                empresa.getNombreEmpresa(),
                empresa.getTelefono(),
                empresa.getDescripcion(),
                empresa.getSectorEmpresarial(),
                empresa.getDepartamento().getIdDepartamento()
        );
    }
}