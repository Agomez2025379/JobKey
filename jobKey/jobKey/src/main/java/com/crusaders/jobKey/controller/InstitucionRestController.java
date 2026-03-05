package com.crusaders.jobKey.controller;



import com.crusaders.jobKey.DTO.Intituciones.InstitucionDTO;

import com.crusaders.jobKey.entity.Institucion;
import com.crusaders.jobKey.repository.InstitucionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instituciones")
@CrossOrigin(origins = "*")
public class InstitucionRestController {

    @Autowired
    private InstitucionRepository institucionRepository;

    @GetMapping
    public ResponseEntity<List<Institucion>> listarTodas() {
        return ResponseEntity.ok(institucionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Institucion> obtenerPorId(@PathVariable Integer id) {
        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Institución no encontrada con ID: " + id));
        return ResponseEntity.ok(institucion);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Institucion> obtenerPorEmail(@PathVariable String email) {
        Institucion institucion = institucionRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Institución no encontrada con email: " + email));
        return ResponseEntity.ok(institucion);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Institucion>> obtenerPorTipo(@PathVariable String tipo) {
        try {
            Institucion.TipoInstitucion tipoEnum = Institucion.TipoInstitucion.valueOf(tipo);
            return ResponseEntity.ok(institucionRepository.findByTipo(tipoEnum));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Tipo no válido. Use: universidad, instituto, bootcamp, colegio");
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearInstitucion(@Valid @RequestBody InstitucionDTO dto) {
        Map<String, Object> response = new HashMap<>();

        // Validar email único
        if (institucionRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El email ya está registrado");
        }


        Institucion institucion = dto.toEntity();

        // institucion.setPassword(dto.getPassword()); // Esto ya viene en toEntity()

        Institucion nueva = institucionRepository.save(institucion);

        response.put("mensaje", "Institución creada exitosamente");
        response.put("institucion", nueva);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarInstitucion(
            @PathVariable Integer id,
            @Valid @RequestBody InstitucionDTO dto) {

        Map<String, Object> response = new HashMap<>();

        // Verificar que existe
        Institucion existente = institucionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Institución no encontrada"));

        // Validar email único (si cambió)
        if (!existente.getEmail().equals(dto.getEmail()) &&
                institucionRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El email ya está registrado");
        }

        // Actualizar datos
        existente.setNombreInstitucion(dto.getNombreInstitucion());
        existente.setEmail(dto.getEmail());
        existente.setTelefono(dto.getTelefono());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existente.setPassword(dto.getPassword());
        }

        existente.setDescripcion(dto.getDescripcion());
        existente.setTipo(Institucion.TipoInstitucion.valueOf(dto.getTipo()));

        Institucion actualizada = institucionRepository.save(existente);

        response.put("mensaje", "Institución actualizada exitosamente");
        response.put("institucion", actualizada);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarInstitucion(@PathVariable Integer id) {

        Map<String, String> response = new HashMap<>();

        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Institución no encontrada"));

        institucionRepository.delete(institucion);

        response.put("mensaje", "Institución eliminada exitosamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/estadisticas/tipos")
    public ResponseEntity<List<Object[]>> getConteoPorTipo() {
        return ResponseEntity.ok(institucionRepository.countByTipo());
    }
}