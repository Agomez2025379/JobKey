package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.service.services.EmpresasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresas")
public class EmpresasController {

    private final EmpresasService empresasService;

    public EmpresasController(EmpresasService empresasService) {
        this.empresasService = empresasService;
    }

    @GetMapping
    public String showCompanies(Model model) {
        // Obtenemos las empresas de la DB
        model.addAttribute("empresas", empresasService.listCompanies());
        return "empresas"; // Renderiza empresas.html
    }
}