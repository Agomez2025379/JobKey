package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.postulaciones.PostulacionesRequest;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.CandidatosService;
import com.crusaders.jobKey.service.services.PostulacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/postulaciones")
@RequiredArgsConstructor
public class PostulacionesController {

    private final PostulacionesService postulacionesService;
    private final UsuariosRepository usuariosRepository;
    private final CandidatosService candidatosService;

    @PostMapping("/aplicar/{idOferta}")
    @PreAuthorize("hasRole('CANDIDATO')")
    public String aplicar(@PathVariable Integer idOferta,
                          Authentication authentication,
                          RedirectAttributes redirectAttributes) {

        String email = authentication.getName();
        Usuarios usuario = usuariosRepository.findByEmail(email).orElseThrow();
        var candidato = candidatosService.findByUsuarioId(usuario.getIdUsuario());

        if (candidato == null) {
            redirectAttributes.addFlashAttribute("error", "Debes completar tu perfil de candidato antes de aplicar.");
            return "redirect:/candidatos";
        }

        PostulacionesRequest request = new PostulacionesRequest();
        request.setOfertaId(idOferta);
        request.setCandidatoId(candidato.getIdCandidato());

        postulacionesService.crearPostulacion(request);
        redirectAttributes.addFlashAttribute("success", "¡Te has postulado con éxito!");
        return "redirect:/ofertas";
    }
}