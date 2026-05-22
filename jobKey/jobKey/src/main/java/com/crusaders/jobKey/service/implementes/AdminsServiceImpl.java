package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.dto.admins.AdminRequest;
import com.crusaders.jobKey.dto.admins.AdminResponse;
import com.crusaders.jobKey.dto.admins.AdminUpdateRequest;
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

    @Override
    public List<AdminResponse> listar() {
        return adminsRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public AdminResponse obtenerPorId(Integer id) {
        return toResponse(buscarOLanzar(id));
    }

    @Override
    @Transactional
    public AdminResponse crear(AdminRequest request) {
        if (usuariosRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "El email " + request.getEmail() + " ya está registrado."
            );
        }

        Usuarios usuario = new Usuarios(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                EUsuarioRol.ADMIN
        );
        usuariosRepository.save(usuario);

        Admins admin = new Admins();
        admin.setUsuario(usuario);
        admin.setNombre(request.getNombre());
        adminsRepository.save(admin);

        return toResponse(admin);
    }

    @Override
    @Transactional
    public AdminResponse actualizar(Integer id, AdminUpdateRequest request) {
        Admins admin = buscarOLanzar(id);
        Usuarios usuario = admin.getUsuario();

        if (!usuario.getEmail().equals(request.getEmail())
                && usuariosRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "El email " + request.getEmail() + " ya está en uso."
            );
        }

        admin.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        // NO modificar password_hash en edición

        adminsRepository.save(admin);
        usuariosRepository.save(usuario);

        return toResponse(admin);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        Admins admin = buscarOLanzar(id);
        Usuarios usuario = admin.getUsuario();

        adminsRepository.delete(admin);
        usuariosRepository.delete(usuario);
    }

    private Admins buscarOLanzar(Integer id) {
        return adminsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin no encontrado con ID: " + id));
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