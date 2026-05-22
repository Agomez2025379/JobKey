package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.ofertas.OfertasTrabajoRequest;
import com.crusaders.jobKey.enums.EModalidad;
import com.crusaders.jobKey.service.services.DepartamentosService;
import com.crusaders.jobKey.service.services.EmpresasService; // Asegúrate de tener este import
import com.crusaders.jobKey.service.services.OfertasTrabajoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ofertas")
@RequiredArgsConstructor
public class OfertasTrabajoController {

    private final OfertasTrabajoService service;
    private final DepartamentosService departamentosService;
    private final EmpresasService empresasService;

    // LISTAR + FILTROS
    @GetMapping
    public String listar(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer departamentoId,
            @RequestParam(required = false) EModalidad modalidad,
            Model model
    ) {
        model.addAttribute("ofertas", service.filtrar(keyword, departamentoId, modalidad));
        model.addAttribute("departamentos", departamentosService.listar());
        return "ofertas-trabajo";
    }

    // MOSTRAR FORM CREAR
    @GetMapping("/crear")
    public String mostrarCrear(Model model) {
        model.addAttribute("oferta", new OfertasTrabajoRequest());
        model.addAttribute("departamentos", departamentosService.listar());
        model.addAttribute("empresas", empresasService.listCompanies());
        model.addAttribute("modoEdicion", false);
        return "ofertas-form";
    }

    // PROCESAR CREAR
    @PostMapping("/crear")
    public String crear(
            @Valid @ModelAttribute("oferta") OfertasTrabajoRequest request,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            model.addAttribute("departamentos", departamentosService.listar()); // Mantiene la lista en caso de error
            model.addAttribute("empresas", empresasService.listCompanies());           // Mantiene la lista en caso de error
            return "ofertas-form";
        }

        service.crear(request);
        return "redirect:/ofertas";
    }

    // MOSTRAR FORM EDITAR
    @GetMapping("/editar/{id}")
    public String mostrarEditar(
            @PathVariable Integer id,
            Model model
    ) {
        var oferta = service.obtenerPorId(id);
        OfertasTrabajoRequest form = new OfertasTrabajoRequest();

        form.setTitulo(oferta.getTitulo());
        form.setDescripcion(oferta.getDescripcion());
        form.setRequisitos(oferta.getRequisitos());
        form.setSalario(oferta.getSalario());
        form.setModalidad(oferta.getModalidad());
        form.setTipoJornada(oferta.getTipoJornada());
        form.setNivelRequerido(oferta.getNivelRequerido());
        form.setFechaCierre(oferta.getFechaCierre());
        form.setEmpresaId(oferta.getEmpresaId());
        form.setDepartamentoId(oferta.getDepartamentoId());

        model.addAttribute("oferta", form);
        model.addAttribute("id", id);
        model.addAttribute("departamentos", departamentosService.listar());
        model.addAttribute("empresas", empresasService.listCompanies()); // Cargamos las empresas
        model.addAttribute("modoEdicion", true);

        return "ofertas-form";
    }

    // PROCESAR ACTUALIZACIÓN
    @PostMapping("/editar/{id}")
    public String actualizar(
            @PathVariable Integer id,
            @Valid @ModelAttribute("oferta") OfertasTrabajoRequest request,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            model.addAttribute("id", id);
            model.addAttribute("departamentos", departamentosService.listar());
            model.addAttribute("empresas", empresasService.listCompanies());
            return "ofertas-form";
        }

        service.actualizar(id, request);
        return "redirect:/ofertas";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "redirect:/ofertas";
    }
}