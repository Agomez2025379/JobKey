package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.enums.EUsuarioRol;
import com.crusaders.jobKey.repository.UsuariosRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("authWeb")
public class AuthController {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuariosRepository usuariosRepository, PasswordEncoder passwordEncoder) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Ruta raíz
    @GetMapping("/")
    public String index() {
        return "home";
    }

    // Ruta /home (ahora solo hay una)
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam EUsuarioRol rol
    ) {
        String passwordCifrada = passwordEncoder.encode(password);
        Usuarios nuevoUsuario = new Usuarios(email, passwordCifrada, rol);
        usuariosRepository.save(nuevoUsuario);

        return "redirect:/login?registered=true";
    }
}