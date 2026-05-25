package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.empresas.EmpresasRequest;
import com.crusaders.jobKey.dto.empresas.EmpresasResponse;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.CandidatosService;
import com.crusaders.jobKey.service.services.EmpresasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresas")
public class EmpresasController {

    @Autowired
    private EmpresasService empresasService;

    @Autowired
    private CandidatosService candidatosService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private DepartamentosRepository departamentosRepository;

    @GetMapping
    public String gestionarEmpresa(Authentication authentication, Model model) {
        String emailLogueado = authentication.getName();
        Usuarios usuario = usuariosRepository.findByEmail(emailLogueado)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EmpresasResponse empresa = empresasService.findByUsuarioId(usuario.getIdUsuario());

        if (empresa != null) {
            model.addAttribute("empresa", empresa);
            return "mi-perfil-empresa";
        }

        model.addAttribute("listaDepartamentos", departamentosRepository.findAll());
        model.addAttribute("nuevaEmpresa", new EmpresasRequest());
        return "formulario-empresa";
    }

    @GetMapping("/lista")
    public String listarEmpresasPublico(Model model) {
        model.addAttribute("empresas", empresasService.listCompanies());
        return "empresas";
    }

    @PostMapping("/guardar")
    public String guardarEmpresa(@ModelAttribute("nuevaEmpresa") EmpresasRequest request, Authentication authentication) {
        String emailLogueado = authentication.getName();
        Usuarios usuario = usuariosRepository.findByEmail(emailLogueado)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EmpresasResponse existente = empresasService.findByUsuarioId(usuario.getIdUsuario());

        if (existente != null) {
            empresasService.updateCompany(existente.getIdEmpresa(), request);
        } else {
            empresasService.createCompany(request, usuario.getIdUsuario());
        }

        return "redirect:/empresas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Authentication authentication, Model model) {
        String emailLogueado = authentication.getName();
        Usuarios usuario = usuariosRepository.findByEmail(emailLogueado)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EmpresasResponse empresa = empresasService.getCompany(id);

        if (empresa != null && empresa.getUsuarioId().equals(usuario.getIdUsuario())) {
            EmpresasRequest editRequest = new EmpresasRequest();
            editRequest.setNombreEmpresa(empresa.getNombreEmpresa());
            editRequest.setTelefono(empresa.getTelefono());
            editRequest.setDescripcion(empresa.getDescripcion());
            editRequest.setSectorEmpresarial(empresa.getSectorEmpresarial());
            editRequest.setDepartamentoId(empresa.getDepartamentoId());

            model.addAttribute("listaDepartamentos", departamentosRepository.findAll());
            model.addAttribute("nuevaEmpresa", editRequest);
            return "formulario-empresa";
        }

        return "redirect:/empresas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarEmpresa(@PathVariable Integer id, Authentication authentication) {
        String emailLogueado = authentication.getName();
        Usuarios usuario = usuariosRepository.findByEmail(emailLogueado)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EmpresasResponse empresa = empresasService.getCompany(id);

        if (empresa != null && empresa.getUsuarioId().equals(usuario.getIdUsuario())) {
            empresasService.deleteCompany(id);
        }

        return "redirect:/empresas";
    }

    @GetMapping("/candidatos")
    @PreAuthorize("hasAnyRole('EMPRESA', 'INSTITUCION', 'ADMIN')")
    public String listarCandidatos(Model model) {
        model.addAttribute("candidatos", candidatosService.listarTodos());
        return "lista-candidatos";
    }
}