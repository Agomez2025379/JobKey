package com.crusaders.jobKey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // endpoints públicos
                        .requestMatchers("/api/auth/**").permitAll()

                        // ADMIN tiene acceso total
                        .requestMatchers("/api/admins/**").hasRole("ADMIN")

                        // CANDIDATOS
                        .requestMatchers("/api/candidatos/**")
                        .hasAnyRole("CANDIDATO", "EMPRESA", "INSTITUCION", "ADMIN")

                        // EMPRESAS
                        .requestMatchers("/api/empresas/**")
                        .hasAnyRole("EMPRESA", "CANDIDATO", "ADMIN")

                        // INSTITUCIONES
                        .requestMatchers("/api/instituciones/**")
                        .hasAnyRole("INSTITUCION", "ADMIN", "CANDIDATO")

                        // OFERTAS DE TRABAJO
                        .requestMatchers("/api/ofertas/**")
                        .hasAnyRole("EMPRESA", "CANDIDATO", "ADMIN")

                        // POSTULACIONES
                        .requestMatchers("/api/postulaciones/**")
                        .hasAnyRole("CANDIDATO", "EMPRESA", "ADMIN")

                        // RESEÑAS
                        .requestMatchers("/api/resenas/**")
                        .hasAnyRole("CANDIDATO", "EMPRESA", "ADMIN")

                        // DEPARTAMENTOS (datos públicos)
                        .requestMatchers("/api/departamentos/**")
                        .permitAll()

                        // cualquier otro endpoint requiere login
                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults())

                .build();
    }
}