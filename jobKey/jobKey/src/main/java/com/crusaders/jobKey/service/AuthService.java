package com.crusaders.jobKey.service;

import com.crusaders.jobKey.DTO.LoginRequest;
import com.crusaders.jobKey.DTO.LoginResponse;
import com.crusaders.jobKey.entity.*;
import com.crusaders.jobKey.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired private CandidatosRepository candidatosRepository;
    @Autowired private EmpresasRepository empresasRepository;
    @Autowired private InstitucionRepository institucionRepository;
    @Autowired private AdminsRepository adminsRepository;

    public LoginResponse login(LoginRequest request) {
        return switch (request.getTipoUsuario().toLowerCase()) {
            case "candidato"   -> loginCandidato(request);
            case "empresa"     -> loginEmpresa(request);
            case "institucion" -> loginInstitucion(request);
            case "admin"       -> loginAdmin(request);
            default -> throw new RuntimeException("Tipo de usuario no válido. Use: candidato, empresa, institucion, admin");
        };
    }

    private LoginResponse loginCandidato(LoginRequest request) {
        Candidatos c = candidatosRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("No existe una cuenta con ese email"));
        validar(request.getPassword(), c.getPasswordHash());
        return new LoginResponse(
                c.getIdCandidato(),
                c.getNombre() + " " + c.getApellido(),
                c.getEmail(),
                "candidato",
                "Login exitoso"
        );
    }

    private LoginResponse loginEmpresa(LoginRequest request) {
        Empresas e = empresasRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("No existe una cuenta con ese email"));
        validar(request.getPassword(), e.getPasswordHash());
        return new LoginResponse(
                e.getIdEmpresa(),
                e.getNombreEmpresa(),
                e.getEmail(),
                "empresa",
                "Login exitoso"
        );
    }

    private LoginResponse loginInstitucion(LoginRequest request) {
        Institucion i = institucionRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("No existe una cuenta con ese email"));
        validar(request.getPassword(), i.getPassword());
        return new LoginResponse(
                i.getId(),
                i.getNombreInstitucion(),
                i.getEmail(),
                "institucion",
                "Login exitoso"
        );
    }

    private LoginResponse loginAdmin(LoginRequest request) {
        Admins a = adminsRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("No existe una cuenta con ese email"));
        validar(request.getPassword(), a.getPasswordHash());

        // Registra último acceso
        a.setUltimoAcceso(java.time.LocalDateTime.now());
        adminsRepository.save(a);

        return new LoginResponse(
                a.getIdAdmin(),
                a.getNombre(),
                a.getEmail(),
                "admin",
                "Login exitoso"
        );
    }

    private void validar(String passwordPlano, String hashDB) {
        if (!passwordEncoder.matches(passwordPlano, hashDB)) {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }

    public String hashPassword(String passwordPlano) {
        return passwordEncoder.encode(passwordPlano);
    }
}