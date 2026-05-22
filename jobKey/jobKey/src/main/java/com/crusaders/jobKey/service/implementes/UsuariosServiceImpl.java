package com.crusaders.jobKey.service.implementes;

import com.crusaders.jobKey.dto.usuarios.UsuarioResponse;
import com.crusaders.jobKey.dto.usuarios.UsuarioUpdateRequest;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.exception.ResourceNotFoundException;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.UsuariosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public List<UsuarioResponse> listar() {
        return usuariosRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public UsuarioResponse obtenerPorId(Integer id) {
        return toResponse(buscarOLanzar(id));
    }

    @Override
    @Transactional
    public UsuarioResponse actualizar(Integer id, UsuarioUpdateRequest request) {
        Usuarios usuario = buscarOLanzar(id);

        // Verificar email duplicado ignorando el propio usuario
        if (!usuario.getEmail().equals(request.getEmail())
                && usuariosRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "El email " + request.getEmail() + " ya está en uso."
            );
        }

        usuario.setEmail(request.getEmail());
        usuario.setRol(request.getRol());
        // NO modificar password_hash en edición
        usuariosRepository.save(usuario);

        return toResponse(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!usuariosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }
        usuariosRepository.deleteById(id);
    }

    private Usuarios buscarOLanzar(Integer id) {
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    private UsuarioResponse toResponse(Usuarios u) {
        UsuarioResponse res = new UsuarioResponse();
        res.setIdUsuario(u.getIdUsuario());
        res.setEmail(u.getEmail());
        res.setRol(u.getRol());
        res.setUltimoAcceso(u.getUltimoAcceso());
        res.setFechaRegistro(u.getFechaRegistro());
        return res;
    }
}