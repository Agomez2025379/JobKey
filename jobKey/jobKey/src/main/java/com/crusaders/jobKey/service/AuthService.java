package com.crusaders.jobKey.service;

import com.crusaders.jobKey.dto.auth.LoginRequest;
import com.crusaders.jobKey.dto.auth.LoginResponse;
import com.crusaders.jobKey.dto.auth.RegisterRequest;
import com.crusaders.jobKey.entity.*;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuariosRepository usuarioRepository;
    private final CandidatosRepository candidatoRepository;
    private final EmpresasRepository empresaRepository;
    private final InstitucionRepository institucionRepository;
    private final AdminsRepository adminRepository;

    private final PasswordEncoder passwordEncoder;
    private final DepartamentosRepository departamentosRepository;

    @Transactional
    public LoginResponse register(RegisterRequest req) {

        if (usuarioRepository.existsByEmail(req.getEmail())) {
            throw new ResourceNotFoundException("Email ya registrado");
        }

        Usuarios usuario = new Usuarios();
        usuario.setEmail(req.getEmail());
        usuario.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        usuario.setRol(req.getRol());
        usuario = usuarioRepository.save(usuario);

        String nombre = "";
        Integer entidadId = null;

        switch (req.getRol()) {
            case ADMIN:
                Admins admin = new Admins();
                admin.setUsuario(usuario);
                admin.setNombre(req.getNombreAdmin());
                admin = adminRepository.save(admin);
                nombre = admin.getNombre();
                entidadId = admin.getIdAdmin();
                break;
            case CANDIDATO:
                Candidatos candidato = new Candidatos();
                candidato.setUsuario(usuario);
                candidato.setNombre(req.getNombre());
                candidato.setApellido(req.getApellido());
                candidato.setTelefono(req.getTelefono());
                candidato.setProfesion(req.getProfesion());
                candidato.setExperiencia(req.getExperiencia());
                candidato.setEducacion(req.getEducacion());
                candidato.setHabilidades(req.getHabilidades());
                candidato.setCurriculumUrl(req.getCurriculumUrl());
                candidato.setDepartamento(departamentosRepository.findById(req.getDepartamentoId())
                        .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado")));
                candidato = candidatoRepository.save(candidato);
                nombre = candidato.getNombre() + " " + candidato.getApellido();
                entidadId = candidato.getIdCandidato();
                break;
            case EMPRESA:
                Empresas empresa = new Empresas();
                empresa.setUsuario(usuario);
                empresa.setNombreEmpresa(req.getNombreEmpresa());
                empresa.setTelefono(req.getTelefonoEmpresa());
                empresa.setDescripcion(req.getDescripcionEmpresa());
                empresa.setSectorEmpresarial(req.getSectorEmpresarial());
                empresa.setLogo(req.getLogoEmpresa());
                empresa.setDepartamento(departamentosRepository.findById(req.getDepartamentoId())
                        .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado")));
                empresa = empresaRepository.save(empresa);
                nombre = empresa.getNombreEmpresa();
                entidadId = empresa.getIdEmpresa();
                break;
            case INSTITUCION:
                Institucion institucion = new Institucion();
                institucion.setUsuario(usuario);
                institucion.setNombreInstitucion(req.getNombreInstitucion());
                institucion.setTelefono(req.getTelefonoInstitucion());
                institucion.setDescripcion(req.getDescripcionInstitucion());
                institucion.setTipo(req.getTipoInstitucion());
                institucion.setLogo(req.getLogoInstitucion());
                institucion.setDepartamento(departamentosRepository.findById(req.getDepartamentoId())
                        .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado")));
                institucion = institucionRepository.save(institucion);
                nombre = institucion.getNombreInstitucion();
                entidadId = institucion.getIdInstitucion();
                break;
        }

        return new LoginResponse(
                "Usuario registrado",
                usuario.getIdUsuario(),
                usuario.getEmail(),
                nombre,
                entidadId,
                usuario.getRol()
        );
    }

    public LoginResponse login(LoginRequest request) {
        Usuarios usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            throw new ResourceNotFoundException("Contraseña incorrecta");
        }

        String nombre = "";
        Integer entidadId = null;

        switch (usuario.getRol()) {
            case ADMIN:
                Admins admin = adminRepository.findByUsuario_IdUsuario(usuario.getIdUsuario());
                nombre = admin.getNombre();
                entidadId = admin.getIdAdmin();
                break;
            case CANDIDATO:
                Candidatos candidato = candidatoRepository.findByUsuario_IdUsuario(usuario.getIdUsuario());
                nombre = candidato.getNombre() + " " + candidato.getApellido();
                entidadId = candidato.getIdCandidato();
                break;
            case EMPRESA:
                Empresas empresa = empresaRepository.findByUsuario_IdUsuario(usuario.getIdUsuario());
                nombre = empresa.getNombreEmpresa();
                entidadId = empresa.getIdEmpresa();
                break;
            case INSTITUCION:
                Optional<Institucion> optInstitucion = institucionRepository.findFirstByUsuario_IdUsuario(usuario.getIdUsuario());
                if (optInstitucion.isPresent()) {
                    Institucion institucion = optInstitucion.get();
                    nombre = institucion.getNombreInstitucion();
                    entidadId = institucion.getIdInstitucion();
                } else {
                    nombre = "";
                    entidadId = null;
                }
                break;
        }

        return new LoginResponse(
                "Login exitoso",
                usuario.getIdUsuario(),
                usuario.getEmail(),
                nombre,
                entidadId,
                usuario.getRol()
        );
    }
}