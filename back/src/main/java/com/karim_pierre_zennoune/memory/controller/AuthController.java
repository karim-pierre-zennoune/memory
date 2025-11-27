package com.karim_pierre_zennoune.memory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karim_pierre_zennoune.memory.dto.LoginResponse;
import com.karim_pierre_zennoune.memory.dto.RegisterUserDto;
import com.karim_pierre_zennoune.memory.dto.UserAuthDto;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.service.AuthenticationService;
import com.karim_pierre_zennoune.memory.service.JwtService;

/**
 * Contrôleur de gestion de l'authentification.
 * Ce contrôleur expose :
 * <ul>
 * <li>Un endpoint d'inscription (/auth/signup)</li>
 * <li>Un endpoint de connexion (/auth/login)</li>
 * </ul>
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    /**
     * Constructeur du contrôleur d'authentification.
     *
     * @param jwtService            Le service de gestion des JWT
     * @param authenticationService Le service d'authentification
     */
    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint d'inscription d'un nouvel utilisateur.
     * Cette méthode :
     * <ul>
     * <li>Reçoit les informations d'inscription</li>
     * <li>Crée un nouvel utilisateur</li>
     * <li>Retourne l'utilisateur créé</li>
     * </ul>
     *
     * @param registerUserDto Les informations d'inscription
     * @return L'utilisateur créé
     */
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserAuthDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    /**
     * Endpoint de connexion utilisateur.
     * Cette méthode :
     * <ul>
     * <li>Authentifie l'utilisateur</li>
     * <li>Génère un token JWT</li>
     * <li>Retourne le token et sa durée de validité</li>
     * </ul>
     *
     * @param loginUserDto Les informations de connexion
     * @return Le token JWT et sa durée de validité
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserAuthDto loginUserDto) {

        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        System.out.println(authenticatedUser.getId());

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime()).setLogin(authenticatedUser.getUsername())
                .setId(authenticatedUser.getId());

        return ResponseEntity.ok(loginResponse);
    }
}
