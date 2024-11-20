package com.example.ApiQuotations.controller;

import com.example.ApiQuotations.dto.AuthResponse;
import com.example.ApiQuotations.exception.InvalidCredentialsException;
import com.example.ApiQuotations.model.Users;
import com.example.ApiQuotations.service.UserService;
import com.example.ApiQuotations.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody Users user) {
        // Busca al usuario utilizando UserService
        Users userResult = userService.findByEmail(user.getEmail())
            .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        // Verifica la contraseña
        if (!passwordEncoder.matches(user.getPassword(), userResult.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // Genera el token JWT
        String token = jwtUtils.generateToken(user.getEmail());

        // Retorna la respuesta formateada
        return new AuthResponse(token, userResult.getEmail(), userResult.getRole());
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Users user) {
        // Verifica si el correo ya está registrado
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            throw new InvalidCredentialsException("Email already in use");
        }

        // Encripta la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        // Asigna un rol por defecto (opcional, si no se recibe del cliente)
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("admin");
        }

        // Guarda el usuario
        userService.saveUser(user);

        // Genera el token JWT
        String token = jwtUtils.generateToken(user.getEmail());

        // Respuesta formateada
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("token", token);
        response.put("email", user.getEmail());
        response.put("role", user.getRole());
        return response;
    }

}
