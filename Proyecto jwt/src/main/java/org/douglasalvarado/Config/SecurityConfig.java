package org.douglasalvarado.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.douglasalvarado.Jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

// Clase de configuracion (metodos que estaran con bean)
@Configuration
// Anotación que habilita la seguridad web en una aplicación Spring
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    
    // 
    @Bean
    //Interfaz que define una cadena de filtros de seguridad que se aplican a las solicitudes entrantes
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Retornaremos con ciertos filtro a tomar en cuenta
        return http
                // Cross-Site Request Forgery (medida de seguridad para validar un token tipo SCRF (POST))
                .csrf(csrf ->
                    csrf.disable())
                // Método que permite definir las reglas de autorización para las solicitudes HTTP
                .authorizeHttpRequests(authRequest ->   
                    authRequest
                        // Permite el acceso a todos a la ruta "/auth/login o /auth/register"
                        .requestMatchers("/auth/**").permitAll() 
                        .requestMatchers("/v3/api-docs/index.html").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/v3/**").permitAll()
                        // Requiere autenticación para cualquier otra solicitud
                        .anyRequest().authenticated()
                    )
                // Configura el inicio de sesión por formulario con la configuración predeterminada
                //.formLogin(withDefaults())
                .sessionManagement(sessionManager -> 
                    sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // Construimos el SecurityFilterChain
                .build();
    }
}
