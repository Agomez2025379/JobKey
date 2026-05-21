package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.dto.empresas.EmpresasRequest;
import com.crusaders.jobKey.dto.empresas.EmpresasResponse;
import com.crusaders.jobKey.entity.Departamentos;
import com.crusaders.jobKey.entity.Empresas;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.EmpresasRepository;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.EmpresasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresasServiceImpl implements EmpresasService {

    @Autowired
    private EmpresasRepository empresasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private DepartamentosRepository departamentosRepository;

    @Override
    public List<EmpresasResponse> listCompanies() {
        return empresasRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmpresasResponse getCompany(Integer id) {
        Empresas empresa = empresasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con el ID: " + id));
        return mapToResponse(empresa);
    }

    @Override
    public EmpresasResponse updateCompany(Integer id, EmpresasRequest request) {
        Empresas empresaExistente = empresasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con el ID: " + id));

        Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        empresaExistente.setNombreEmpresa(request.getNombreEmpresa());
        empresaExistente.setTelefono(request.getTelefono());
        empresaExistente.setDescripcion(request.getDescripcion());
        empresaExistente.setSectorEmpresarial(request.getSectorEmpresarial());
        empresaExistente.setLogo(request.getLogo());
        empresaExistente.setDepartamento(departamento);

        Empresas empresaActualizada = empresasRepository.save(empresaExistente);
        return mapToResponse(empresaActualizada);
    }

    @Override
    public void deleteCompany(Integer id) {
        Empresas empresa = empresasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con el ID: " + id));
        empresasRepository.delete(empresa);
    }

    // --- NUEVOS MÉTODOS REQUERIDOS ---

    @Override
    public EmpresasResponse findByUsuarioId(Integer idUsuario) {
        Empresas empresa = empresasRepository.findByUsuario_IdUsuario(idUsuario);
        if (empresa == null) {
            return null;
        }
        return mapToResponse(empresa);
    }

    @Override
    public EmpresasResponse createCompany(EmpresasRequest request, Integer idUsuario) {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado para asociar a la empresa"));

        Departamentos departamento = departamentosRepository.findById(request.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        Empresas nuevaEmpresa = new Empresas();
        nuevaEmpresa.setUsuario(usuario);
        nuevaEmpresa.setNombreEmpresa(request.getNombreEmpresa());
        nuevaEmpresa.setTelefono(request.getTelefono());
        nuevaEmpresa.setDescripcion(request.getDescripcion());
        nuevaEmpresa.setSectorEmpresarial(request.getSectorEmpresarial());
        nuevaEmpresa.setLogo(request.getLogo());
        nuevaEmpresa.setDepartamento(departamento);

        Empresas empresaGuardada = empresasRepository.save(nuevaEmpresa);
        return mapToResponse(empresaGuardada);
    }

    // --- MÉTODO DE MAPEO REUTILIZABLE ---

    private EmpresasResponse mapToResponse(Empresas empresa) {
        Integer usuarioId = (empresa.getUsuario() != null) ? empresa.getUsuario().getIdUsuario() : null;
        Integer departamentoId = (empresa.getDepartamento() != null) ? empresa.getDepartamento().getIdDepartamento() : null;

        return new EmpresasResponse(
                empresa.getIdEmpresa(),
                usuarioId,
                empresa.getNombreEmpresa(),
                empresa.getTelefono(),
                empresa.getDescripcion(),
                empresa.getSectorEmpresarial(),
                departamentoId
        );
    }
}