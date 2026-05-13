package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.DTO.institutions.InstitutionRequest;
import com.crusaders.jobKey.DTO.institutions.InstitutionResponse;
import com.crusaders.jobKey.service.services.InstitutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institutions")
public class InstitutionController {

    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    /* Get institution by id */
    @GetMapping("/{id}")
    public ResponseEntity<InstitutionResponse> getInstitution(
            @PathVariable Integer id) {

        InstitutionResponse response = institutionService.getById(id);
        return ResponseEntity.ok(response);
    }

    /* List all institutions */
    @GetMapping
    public ResponseEntity<List<InstitutionResponse>> listInstitutions() {

        List<InstitutionResponse> response = institutionService.listInstitutions();
        return ResponseEntity.ok(response);
    }

    /* Update institution */
    @PutMapping("/{id}")
    public ResponseEntity<InstitutionResponse> updateInstitution(
            @PathVariable Integer id,
            @RequestBody InstitutionRequest request) {

        InstitutionResponse response = institutionService.updateInstitution(id, request);
        return ResponseEntity.ok(response);
    }

    /* Delete institution */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitution(
            @PathVariable Integer id) {

        institutionService.deleteInstitution(id);
        return ResponseEntity.noContent().build();
    }
}