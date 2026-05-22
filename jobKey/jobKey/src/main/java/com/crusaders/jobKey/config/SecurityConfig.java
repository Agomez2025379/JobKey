package com.crusaders.jobKey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. RUTAS ESTÁTICAS Y PÚBLICAS GENERALES
                        .requestMatchers("/", "/home", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()

                        // 2. VISTAS PÚBLICAS DE LECTURA (Especificando método HTTP de forma correcta)
                        .requestMatchers(HttpMethod.GET, "/ofertas", "/empresas", "/instituciones").permitAll()
                        .requestMatchers(HttpMethod.GET, "/candidatos").authenticated()

                        // 3. API REST
                        .requestMatchers("/api/resenas/**").permitAll()
                        .requestMatchers("/api/postulaciones/**").authenticated()

                        // 4. EMPRESA - Gestión de ofertas de trabajo (Corregido agrupando métodos)
                        .requestMatchers(HttpMethod.GET, "/ofertas/crear", "/ofertas/editar/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.POST, "/ofertas/crear", "/ofertas/editar/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.GET, "/ofertas/eliminar/**").hasRole("EMPRESA")

                        // 5. INSTITUCIÓN - Gestión de instituciones (Corregido agrupando métodos)
                        .requestMatchers(HttpMethod.GET, "/instituciones/nueva", "/instituciones/editar/**").hasAnyRole("ADMIN", "INSTITUCION")
                        .requestMatchers(HttpMethod.POST, "/instituciones/nueva", "/instituciones/editar/**").hasAnyRole("ADMIN", "INSTITUCION")
                        .requestMatchers(HttpMethod.GET, "/instituciones/eliminar/**").hasAnyRole("ADMIN", "INSTITUCION")

                        // 6. REGLAS JERÁRQUICAS POR ROLES (Las rutas genéricas van ABAJO de las específicas)
                        .requestMatchers("/usuarios", "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers("/admins", "/admins/**").hasRole("ADMIN")
                        .requestMatchers("/ofertas/**").hasAnyRole("ADMIN", "EMPRESA")
                        .requestMatchers("/candidatos/**").hasAnyRole("ADMIN", "CANDIDATO")
                        .requestMatchers("/empresas/**").hasAnyRole("ADMIN", "EMPRESA")
                        .requestMatchers("/instituciones/**").hasAnyRole("ADMIN", "INSTITUCION")

                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/home", true)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}