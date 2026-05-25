package com.crusaders.jobKey.config;

import com.crusaders.jobKey.config.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/login", "/register","/resenas" ,"/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/ofertas", "/empresas", "/instituciones").permitAll()
                        .requestMatchers(HttpMethod.GET, "/candidatos").authenticated()
                        .requestMatchers("/api/resenas/**").permitAll()
                        .requestMatchers("/api/postulaciones/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/empresas/lista").permitAll()

                        .requestMatchers(HttpMethod.GET, "/ofertas/crear", "/ofertas/editar/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.POST, "/ofertas/crear", "/ofertas/editar/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.GET, "/ofertas/eliminar/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.GET, "/instituciones/nueva", "/instituciones/editar/**").hasAnyRole("ADMIN", "INSTITUCION")
                        .requestMatchers(HttpMethod.POST, "/instituciones/nueva", "/instituciones/editar/**").hasAnyRole("ADMIN", "INSTITUCION")
                        .requestMatchers(HttpMethod.GET, "/instituciones/eliminar/**").hasAnyRole("ADMIN", "INSTITUCION")
                        .requestMatchers("/usuarios", "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers("/admins", "/admins/**").hasRole("ADMIN")
                        .requestMatchers("/ofertas/**").hasAnyRole("ADMIN", "EMPRESA")
                        .requestMatchers("/candidatos/**").hasAnyRole("ADMIN", "CANDIDATO")
                        .requestMatchers("/empresas/**").hasAnyRole("ADMIN", "EMPRESA")
                        .requestMatchers("/instituciones/**").hasAnyRole("ADMIN", "INSTITUCION")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .permitAll()
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