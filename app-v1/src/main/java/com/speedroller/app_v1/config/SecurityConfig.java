package com.speedroller.app_v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // ✅ 1. Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ 2. Usuarios simulados (en memoria para pruebas)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails instructor = User.withUsername("instructor")
                .password(encoder.encode("instructor123"))
                .roles("INSTRUCTOR")
                .build();

        UserDetails estudiante = User.withUsername("estudiante")
                .password(encoder.encode("estudiante123"))
                .roles("ALUMNO")
                .build();

        return new InMemoryUserDetailsManager(admin, instructor, estudiante);
    }

    // ✅ 3. Authentication Provider (para UsernamePasswordAuthenticationToken)
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    // ✅ 4. Authentication Manager (para inyección en controladores)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // ✅ 5. Configuración de seguridad y rutas
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, DaoAuthenticationProvider authProvider) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para APIs REST
            .authenticationProvider(authProvider)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // Login, register
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/instructor/**").hasRole("INSTRUCTOR")
                .requestMatchers("/api/students/**").hasAnyRole("ADMIN", "ALUMNO")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.permitAll())
            .httpBasic(httpBasic -> {}); // Permite login desde Swagger/Postman

        return http.build();
    }
}


