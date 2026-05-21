package com.crusaders.jobKey.config;

import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.repository.UsuariosRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuariosRepository usuariosRepository;

    public CustomUserDetailsService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscamos al usuario por email en la base de datos
        Usuarios usuario = usuariosRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPasswordHash())
                // Convertimos el Enum EUsuarioRol a String para los roles
                .roles(usuario.getRol().name())
                .build();
    }
}