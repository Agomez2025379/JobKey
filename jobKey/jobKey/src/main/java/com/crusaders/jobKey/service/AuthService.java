package com.crusaders.jobKey.service;

import com.crusaders.jobKey.DTO.auth.LoginRequest;
import com.crusaders.jobKey.DTO.auth.LoginResponse;
import com.crusaders.jobKey.DTO.auth.RegisterRequest;
import com.crusaders.jobKey.entity.*;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    // esto hace que el proceso de registro sea una sola transaccion
    // si algo falla en medio (por ejemplo guardar candidato o empresa)
    // la base de datos revierte y no quedan datos incompletos
    @Transactional
    public LoginResponse register(RegisterRequest req) {

        // aqui verificamos si ya existe un usuario con ese email
        // si existe tiramos una excepcion para evitar duplicados
        if (usuarioRepository.existsByEmail(req.getEmail())) {
            throw new ResourceNotFoundException("Email ya registrado");
        }

        Usuarios usuario = new Usuarios();
        usuario.setEmail(req.getEmail());

        // aqui se encripta la contraseña antes de guardarla
        usuario.setPasswordHash(passwordEncoder.encode(req.getPassword()));

        usuario.setRol(req.getRol());

        usuario = usuarioRepository.save(usuario);

        // estas variables se usan para construir la respuesta final
        // dependiendo del tipo de usuario registrado
        // miku miku uh iuuuu
        String nombre = "";
        Integer entidadId = null;


        // aqui decidimos que tipo de entidad crear segun el rol
        // cada rol crea un we  diferente relacionandolo con usuario
        switch (req.getRol()) {

            case ADMIN -> {
                Admins admin = new Admins();
                admin.setUsuario(usuario);
                admin.setNombre(req.getNombreAdmin());

                admin = adminRepository.save(admin);

                nombre = admin.getNombre();
                entidadId = admin.getIdAdmin();
            }

            case CANDIDATO -> {
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
            }

            case EMPRESA -> {
                Empresas empresa = new Empresas();
                empresa.setUsuario(usuario);
                empresa.setNombreEmpresa(req.getNombreEmpresa());
                empresa.setTelefono(req.getTelefonoEmpresa());
                empresa.setDescripcion(req.getDescripcionEmpresa());
                empresa.setSectorEmpresarial(req.getSectorEmpresarial());
                empresa.setLogo(req.getLogoEmpresa());

                // por si acaso, aca se jala departamento para relacionarlo con empresa
                // igual que candidato, lo hize con repository porque no encontraba otra
                // forma de solucionar el tema de la relacion con departamento  sin
                // pasar directamente el objeto departamento en el request porque eso
                // filtra informacion y nao nao, ademas de que el request ya tiene el
                // departamentoId asi que no es necesario
                empresa.setDepartamento(departamentosRepository.findById(req.getDepartamentoId())
                        .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado")));

                empresa = empresaRepository.save(empresa);

                nombre = empresa.getNombreEmpresa();
                entidadId = empresa.getIdEmpresa();
            }

            case INSTITUCION -> {
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
            }
        }
        // aqui devolvemos la respuesta del registro
        // basicamente informacion del usuario que acaba de crearse
        // devuelve el nombre y tal, como el juja cuando se pone jugar
        // profe ya estoy cansado de escribir comentarios
        // bueno, igual lo hago por si acaso
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


        // aqui comparamos la contraseña enviada esta cosa del matches es porque la contraseña en la base de datos esta
        // encriptada, entonces se encripta la contraseña del request y se compara con la que esta guardada, si no
        // coinciden, se lanza una excepcion de contraseña incorrecta y faaaah baneao
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            throw new ResourceNotFoundException("Contraseña incorrecta");
        }

        String nombre = "";
        Integer entidadId = null;


        // dependiendo del rol buscamos la entidad asociada al usuario
        // info extra al login
        switch (usuario.getRol()) {

            case ADMIN -> {
                Admins admin = adminRepository.findByUsuario_IdUsuario(usuario.getIdUsuario());
                nombre = admin.getNombre();
                entidadId = admin.getIdAdmin();
            }

            case CANDIDATO -> {
                Candidatos candidato = candidatoRepository.findByUsuario_IdUsuario(usuario.getIdUsuario());
                nombre = candidato.getNombre() + " " + candidato.getApellido();
                entidadId = candidato.getIdCandidato();
            }

            case EMPRESA -> {
                Empresas empresa = empresaRepository.findByUsuario_IdUsuario(usuario.getIdUsuario());
                nombre = empresa.getNombreEmpresa();
                entidadId = empresa.getIdEmpresa();
            }

            case INSTITUCION -> {
                Institucion institucion = institucionRepository.findByUsuario_IdUsuario(usuario.getIdUsuario());
                nombre = institucion.getNombreInstitucion();
                entidadId = institucion.getIdInstitucion();
            }
        }

        // devolvemos la informacion del login exitoso
        // no me pagan lo suficiente
        // ser scup mashler no esta chido :(
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