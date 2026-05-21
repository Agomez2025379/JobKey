// AdminsServiceImpl.java
package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.dto.admins.*;
import com.crusaders.jobKey.entity.Admins;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.enums.EUsuarioRol;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.AdminsRepository;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.AdminsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminsServiceImpl implements AdminsService {

    private final AdminsRepository adminsRepository;
    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminsServiceImpl(
            AdminsRepository adminsRepository,
            UsuariosRepository usuariosRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.adminsRepository = adminsRepository;
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ── Listar ───────────────────────────────────────────
    @Override
    public List<AdminResponse> listar() {
        return adminsRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ── Obtener por ID ───────────────────────────────────
    @Override
    public AdminResponse obtenerPorId(Integer id) {
        return toResponse(buscarOLanzar(id));
    }

    // ── Crear ────────────────────────────────────────────
    @Override
    @Transactional
    public AdminResponse crear(AdminRequest request) {
        if (usuariosRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "El email " + request.getEmail() + " ya está registrado."
            );
        }

        // 1. Crear Usuario
        Usuarios usuario = new Usuarios(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                EUsuarioRol.ADMIN
        );
        usuariosRepository.save(usuario);

        // 2. Crear Admin vinculado
        Admins admin = new Admins();
        admin.setUsuario(usuario);
        admin.setNombre(request.getNombre());
        adminsRepository.save(admin);

        return toResponse(admin);
    }

    // ── Actualizar ───────────────────────────────────────
    @Override
    @Transactional
    public AdminResponse actualizar(Integer id, AdminUpdateRequest request) {
        Admins admin = buscarOLanzar(id);
        Usuarios usuario = admin.getUsuario();

        // Verificar email duplicado (ignorando el propio usuario)
        if (!usuario.getEmail().equals(request.getEmail())
                && usuariosRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "El email " + request.getEmail() + " ya está en uso."
            );
        }

        admin.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());

        adminsRepository.save(admin);
        usuariosRepository.save(usuario);

        return toResponse(admin);
    }

    // ── Eliminar ─────────────────────────────────────────
    @Override
    @Transactional
    public void eliminar(Integer id) {
        Admins admin = buscarOLanzar(id);
        Usuarios usuario = admin.getUsuario();

        adminsRepository.delete(admin);       // primero el hijo
        usuariosRepository.delete(usuario);   // luego el padre
    }

    // ── Helpers ──────────────────────────────────────────
    private Admins buscarOLanzar(Integer id) {
        return adminsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Admin no encontrado con ID: " + id
                ));
    }

    private AdminResponse toResponse(Admins admin) {
        AdminResponse res = new AdminResponse();
        res.setIdAdmin(admin.getIdAdmin());
        res.setNombre(admin.getNombre());
        res.setEmail(admin.getUsuario().getEmail());
        res.setUltimoAcceso(admin.getUsuario().getUltimoAcceso());
        res.setFechaRegistro(admin.getUsuario().getFechaRegistro());
        return res;
    }
}