package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Resenas;
import com.crusaders.jobKey.enums.ResenaTipo;
import com.crusaders.jobKey.dto.usuarios.UsuarioResponse;
import com.crusaders.jobKey.dto.candidatos.CandidatosResponse;
import com.crusaders.jobKey.dto.empresas.EmpresasResponse;
import com.crusaders.jobKey.service.services.UsuariosService;
import com.crusaders.jobKey.service.services.CandidatosService;
import com.crusaders.jobKey.service.services.EmpresasService;
import com.crusaders.jobKey.service.services.ResenasService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/resenas")
public class ResenasController {

    private final ResenasService resenasService;
    private final UsuariosService usuariosService;
    private final CandidatosService candidatosService;
    private final EmpresasService empresasService;

    public ResenasController(ResenasService resenasService,
                             UsuariosService usuariosService,
                             CandidatosService candidatosService,
                             EmpresasService empresasService) {
        this.resenasService = resenasService;
        this.usuariosService = usuariosService;
        this.candidatosService = candidatosService;
        this.empresasService = empresasService;
    }

    @GetMapping
    public String mostrarVistaResenas(Model model, Authentication authentication) {
        model.addAttribute("resenasCandidatos", resenasService.obtenerPorTipo(ResenaTipo.candidato_a_empresa));
        model.addAttribute("resenasEmpresas", resenasService.obtenerPorTipo(ResenaTipo.empresa_a_candidato));

        Resenas nuevaResena = new Resenas();

        if (authentication != null && authentication.isAuthenticated()) {
            String emailLogueado = authentication.getName();
            UsuarioResponse usuario = usuariosService.listar().stream()
                    .filter(u -> u.getEmail() != null && u.getEmail().equals(emailLogueado))
                    .findFirst()
                    .orElse(null);

            if (usuario != null) {
                model.addAttribute("usuarioLogueado", usuario);

                // Revisa si en tu UsuarioResponse la propiedad se llama getId() o getIdUsuario()
                Integer idUsuario = usuario.getIdUsuario();

                // Evaluamos el rol para asociar el perfil correspondiente
                CandidatosResponse candidato = candidatosService.findByUsuarioId(idUsuario);
                EmpresasResponse empresa = empresasService.findByUsuarioId(idUsuario);

                if (candidato != null) {
                    nuevaResena.setCandidatoId(candidato.getIdCandidato());
                    nuevaResena.setTipo(ResenaTipo.candidato_a_empresa);
                } else if (empresa != null) {
                    nuevaResena.setEmpresaId(empresa.getIdEmpresa());
                    nuevaResena.setTipo(ResenaTipo.empresa_a_candidato);
                }
            }
        }

        model.addAttribute("nuevaResena", nuevaResena);
        return "resenas";
    }

    @PostMapping("/guardar")
    public String guardarNuevaWeb(@ModelAttribute("nuevaResena") Resenas nuevaResena) {
        try {
            nuevaResena.setIdResena(null);
            resenasService.guardar(nuevaResena);
        } catch (Exception e) {
            return "redirect:/resenas?error=" + e.getMessage();
        }
        return "redirect:/resenas";
    }
}