package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.candidatos.*;
import com.crusaders.jobKey.service.services.CandidatosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidatos")
public class CandidatosController {

    private final CandidatosService candidatosService;

    public CandidatosController(CandidatosService candidatosService) {
        this.candidatosService = candidatosService;
    }

    @GetMapping
    public String showCandidates(Model model) {
        model.addAttribute("candidatos", candidatosService.listCandidates());
        return "candidatos";
    }
}