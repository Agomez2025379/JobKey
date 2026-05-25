package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.candidatos.CandidatosRequest;
import com.crusaders.jobKey.dto.candidatos.CandidatosResponse;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.enums.EUsuarioRol;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.CandidatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/candidatos")
public class CandidatosController {

    @Autowired
    private CandidatosService candidatosService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private DepartamentosRepository departamentosRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CANDIDATO', 'EMPRESA')")
    public String listarOCrearCandidato(Authentication authentication, Model model) {
        String emailLogueado = authentication.getName();
        Usuarios usuario = usuariosRepository.findByEmail(emailLogueado)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getRol() == EUsuarioRol.EMPRESA || usuario.getRol() == EUsuarioRol.ADMIN) {
            model.addAttribute("candidatos", candidatosService.listarTodos());
            return "lista-candidatos";
        }

        CandidatosResponse candidato = candidatosService.findByUsuarioId(usuario.getIdUsuario());
        if (candidato != null) {
            model.addAttribute("candidato", candidato);
            return "mi-perfil-candidato";
        }

        model.addAttribute("listaDepartamentos", departamentosRepository.findAll());
        model.addAttribute("nuevoCandidato", new CandidatosRequest());
        return "formulario-postulacion";
    }

    @GetMapping("/ver/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public String verDetalleCandidatoParaEmpresa(@PathVariable Integer id, Model model) {
        model.addAttribute("candidato", candidatosService.getCandidate(id));
        return "mi-perfil-candidato";
    }

    @PostMapping("/guardar")
    @PreAuthorize("hasRole('CANDIDATO')")
    public String guardarPostulacion(@ModelAttribute("nuevoCandidato") CandidatosRequest request, Authentication authentication) {
        String emailLogueado = authentication.getName();
        Usuarios usuario = usuariosRepository.findByEmail(emailLogueado)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CandidatosResponse existente = candidatosService.findByUsuarioId(usuario.getIdUsuario());

        if (existente != null) {
            candidatosService.updateCandidate(existente.getIdCandidato(), request);
        } else {
            candidatosService.createCandidate(request, usuario.getIdUsuario());
        }

        return "redirect:/candidatos";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasRole('CANDIDATO')")
    public String mostrarFormularioEditar(@PathVariable Integer id, Authentication authentication, Model model) {
        String emailLogueado = authentication.getName();
        Usuarios usuario = usuariosRepository.findByEmail(emailLogueado)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CandidatosResponse candidato = candidatosService.getCandidate(id);

        if (candidato != null && candidato.getUsuarioId().equals(usuario.getIdUsuario())) {
            CandidatosRequest editRequest = new CandidatosRequest();
            editRequest.setNombre(candidato.getNombre());
            editRequest.setApellido(candidato.getApellido());
            editRequest.setTelefono(candidato.getTelefono());
            editRequest.setProfesion(candidato.getProfesion());
            editRequest.setExperiencia(candidato.getExperiencia());
            editRequest.setEducacion(candidato.getEducacion());
            editRequest.setHabilidades(candidato.getHabilidades());
            editRequest.setCurriculumUrl(candidato.getCurriculumUrl());
            editRequest.setDepartamentoId(candidato.getDepartamentoId());

            model.addAttribute("listaDepartamentos", departamentosRepository.findAll());
            model.addAttribute("nuevoCandidato", editRequest);
            return "formulario-postulacion";
        }

        return "redirect:/candidatos";
    }

    @PostMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('CANDIDATO')")
    public String eliminarPostulacion(@PathVariable Integer id, Authentication authentication) {
        String emailLogueado = authentication.getName();
        Usuarios usuario = usuariosRepository.findByEmail(emailLogueado)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CandidatosResponse candidato = candidatosService.getCandidate(id);

        if (candidato != null && candidato.getUsuarioId().equals(usuario.getIdUsuario())) {
            candidatosService.deleteCandidate(id);
        }

        return "redirect:/candidatos";
    }
}