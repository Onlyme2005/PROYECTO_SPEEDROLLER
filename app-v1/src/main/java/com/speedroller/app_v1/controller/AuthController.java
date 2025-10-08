package com.speedroller.app_v1.controller;

import java.util.Collections;
import java.util.Optional;

import com.speedroller.app_v1.model.Role;
import com.speedroller.app_v1.model.Usuario;
import com.speedroller.app_v1.repository.UsuarioRepository;
import com.speedroller.app_v1.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // --- Registro ---
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario userRequest) {
        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            return new ResponseEntity<>("Nombre de usuario ya existe.", HttpStatus.BAD_REQUEST);
        }

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        // Asignar rol por defecto
        Role defaultRole = roleRepository.findByNameString("ROLE_USER");
        if (defaultRole == null) {
            defaultRole = new Role();
            defaultRole.setName("ROLE_USER");
            roleRepository.save(defaultRole);
        }
        userRequest.setRoles(Collections.singleton(defaultRole));

        userRepository.save(userRequest);
        return new ResponseEntity<>("Registro de usuario exitoso.", HttpStatus.CREATED);
    }

    // --- Login ---
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> userOpt = userRepository.findByUsername(loginRequest.getUsername());

        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Credenciales incorrectas.");
            }

            // Construir lista de roles
            StringBuilder rolesList = new StringBuilder();
            user.getRoles().forEach(role -> rolesList.append(role.getNameString()).append(", "));
            if (rolesList.length() > 0) rolesList.setLength(rolesList.length() - 2);

            return ResponseEntity.ok("Inicio de sesión exitoso ✅. ID: " + user.getId() + ", Roles: " + rolesList);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Credenciales incorrectas.");
    }
}


