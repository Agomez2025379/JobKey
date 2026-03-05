package com.crusaders.jobKey.service;

import com.crusaders.jobKey.DTO.auth.LoginRequest;
import com.crusaders.jobKey.DTO.auth.LoginResponse;
import com.crusaders.jobKey.DTO.auth.RegisterRequest;
import com.crusaders.jobKey.entity.Candidatos;
import com.crusaders.jobKey.entity.Empresas;
import com.crusaders.jobKey.entity.Institucion;
import com.crusaders.jobKey.repository.CandidatosRepository;
import com.crusaders.jobKey.repository.EmpresasRepository;
import com.crusaders.jobKey.repository.InstitucionRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CandidatosRepository candidatosRepository;
    private final EmpresasRepository empresasRepository;
    private final InstitucionRepository institucionRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            CandidatosRepository candidatosRepository,
            EmpresasRepository empresasRepository,
            InstitucionRepository institucionRepository,
            PasswordEncoder passwordEncoder) {

        this.candidatosRepository = candidatosRepository;
        this.empresasRepository = empresasRepository;
        this.institucionRepository = institucionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest req, String tipoUsuario) {

        String tipo = tipoUsuario.toLowerCase();
        String hash = passwordEncoder.encode(req.password);

        switch (tipo) {

            case "candidato" -> {
                if (candidatosRepository.existsByEmail(req.email)) {
                    throw new IllegalArgumentException("El email ya está registrado");
                }

                Candidatos c = new Candidatos();
                c.setNombre(req.name);
                c.setEmail(req.email);
                c.setPasswordHash(hash);

                candidatosRepository.save(c);
            }

            case "empresa" -> {
                if (empresasRepository.existsByEmail(req.email)) {
                    throw new IllegalArgumentException("El email ya está registrado");
                }

                Empresas e = new Empresas();
                e.setNombreEmpresa(req.name);
                e.setEmail(req.email);
                e.setPasswordHash(hash);

                empresasRepository.save(e);
            }

            case "institucion" -> {
                if (institucionRepository.existsByEmail(req.email)) {
                    throw new IllegalArgumentException("El email ya está registrado");
                }

                Institucion i = new Institucion();
                i.setNombreInstitucion(req.name);
                i.setEmail(req.email);
                i.setPassword(hash);

                institucionRepository.save(i);
            }

            default -> throw new IllegalArgumentException("Tipo de usuario inválido");
        }
    }

    public LoginResponse login(LoginRequest req) {

        String tipo = req.getTipoUsuario().toLowerCase();

        return switch (tipo) {

            case "candidato" -> {
                Candidatos c = candidatosRepository.findByEmail(req.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("Correo no existe"));

                validar(req.getPassword(), c.getPasswordHash());

                yield new LoginResponse(
                        c.getIdCandidato(),
                        c.getNombre(),
                        c.getEmail(),
                        "candidato",
                        "Login exitoso"
                );
            }

            case "empresa" -> {
                Empresas e = empresasRepository.findByEmail(req.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("Correo no existe"));

                validar(req.getPassword(), e.getPasswordHash());

                yield new LoginResponse(
                        e.getIdEmpresa(),
                        e.getNombreEmpresa(),
                        e.getEmail(),
                        "empresa",
                        "Login exitoso"
                );
            }

            case "institucion" -> {
                Institucion i = institucionRepository.findByEmail(req.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("Correo no existe"));

                validar(req.getPassword(), i.getPassword());

                yield new LoginResponse(
                        i.getId(),
                        i.getNombreInstitucion(),
                        i.getEmail(),
                        "institucion",
                        "Login exitoso"
                );
            }

            default -> throw new IllegalArgumentException("Tipo de usuario inválido");
        };
    }

    private void validar(String passwordPlano, String hashDB) {
        if (!passwordEncoder.matches(passwordPlano, hashDB)) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
    }
}