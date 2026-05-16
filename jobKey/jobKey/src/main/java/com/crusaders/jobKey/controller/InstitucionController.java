package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.instituciones.InstitucionRequest;
import com.crusaders.jobKey.dto.instituciones.InstitucionResponse;
import com.crusaders.jobKey.service.services.InstitucionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/instituciones")
public class InstitucionController {

    private final InstitucionService institucionService;

    public InstitucionController(InstitucionService institucionService) {
        this.institucionService = institucionService;
    }

    /**
     * Muestra la página principal con la lista de instituciones
     */
    @GetMapping
    public String index(Model model) {
        List<InstitucionResponse> instituciones = institucionService.listarInstituciones();
        model.addAttribute("instituciones", instituciones);
        return "instituciones"; // Renderiza instituciones.html
    }

    /**
     * Muestra el formulario para crear una nueva institución
     */
    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("institucion", new InstitucionRequest());
        model.addAttribute("accion", "crear");
        return "formulario-institucion";
    }

    /**
     * Procesa la creación de una nueva institución
     */
    @PostMapping("/guardar")
    public String crearInstitucion(@ModelAttribute InstitucionRequest request,
                                   RedirectAttributes redirectAttributes) {
        try {
            // Necesitas agregar el método crearInstitucion a tu service
            // institucionService.crearInstitucion(request);
            redirectAttributes.addFlashAttribute("success", "Institución creada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear la institución");
        }
        return "redirect:/instituciones";
    }

    /**
     * Muestra el formulario para editar una institución
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        InstitucionResponse institucion = institucionService.obtenerPorId(id);
        model.addAttribute("institucion", institucion);
        model.addAttribute("accion", "editar");
        return "formulario-institucion";
    }

    /**
     * Procesa la actualización de una institución
     */
    @PostMapping("/actualizar/{id}")
    public String actualizarInstitucion(@PathVariable Integer id,
                                        @ModelAttribute InstitucionRequest request,
                                        RedirectAttributes redirectAttributes) {
        try {
            institucionService.actualizarInstitucion(id, request);
            redirectAttributes.addFlashAttribute("success", "Institución actualizada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar la institución");
        }
        return "redirect:/instituciones";
    }

    /**
     * Elimina una institución
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarInstitucion(@PathVariable Integer id,
                                      RedirectAttributes redirectAttributes) {
        try {
            institucionService.eliminarInstitucion(id);
            redirectAttributes.addFlashAttribute("success", "Institución eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la institución");
        }
        return "redirect:/instituciones";
    }
}