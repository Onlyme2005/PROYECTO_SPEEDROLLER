package com.speedroller.app_v1.config;

import com.speedroller.app_v1.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz UserDetails de Spring Security.
 * Actúa como adaptador para el objeto Usuario de la aplicación,
 * permitiendo que el framework de seguridad lo use para autenticar y autorizar.
 */
public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    // Campo opcional para mantener el objeto Usuario original
    private final Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
        this.enabled = usuario.isEnabled();

        // Mapea los roles del usuario a una lista de GrantedAuthority
        this.authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNameString()))
                .collect(Collectors.toList());
    }

    // --- Métodos requeridos por la interfaz UserDetails ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // --- Getter adicional para acceder al objeto Usuario original ---
    public Usuario getUsuario() {
        return usuario;
    }
}


