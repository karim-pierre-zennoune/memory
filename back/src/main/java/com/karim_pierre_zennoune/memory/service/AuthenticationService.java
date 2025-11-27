package com.karim_pierre_zennoune.memory.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.karim_pierre_zennoune.memory.dto.RegisterUserDto;
import com.karim_pierre_zennoune.memory.dto.UserAuthDto;
import com.karim_pierre_zennoune.memory.model.Role;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.repository.UserRepository;
import com.karim_pierre_zennoune.memory.types.RoleEnum;

/**
 * Service de gestion de l'authentification.
 * Ce service gère :
 * <ul>
 * <li>L'inscription des nouveaux utilisateurs</li>
 * <li>L'authentification des utilisateurs</li>
 * <li>Le hachage des mots de passe</li>
 * </ul>
 */
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    /**
     * Constructeur du service d'authentification.
     *
     * @param userRepository        Le repository de gestion des utilisateurs
     * @param passwordEncoder       L'encodeur de mots de passe
     * @param authenticationManager Le gestionnaire d'authentification
     */
    public AuthenticationService(UserRepository userRepository, RoleService roleService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Inscrit un nouvel utilisateur.
     * Cette méthode :
     * <ul>
     * <li>Crée un nouvel utilisateur</li>
     * <li>Encode le mot de passe</li>
     * <li>Sauvegarde l'utilisateur</li>
     * </ul>
     *
     * @param input Les informations d'inscription
     * @return L'utilisateur créé
     */
    public User signup(UserAuthDto input) {

        Optional<Role> optionalRole = roleService.findByName(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setLogin(input.login());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }

    /**
     * Authentifie un utilisateur.
     * Cette méthode :
     * <ul>
     * <li>Vérifie les identifiants de connexion</li>
     * <li>Authentifie l'utilisateur via le gestionnaire d'authentification</li>
     * <li>Récupère les détails de l'utilisateur</li>
     * </ul>
     *
     * @param input Les informations de connexion
     * @return Les détails de l'utilisateur authentifié
     * @throws RuntimeException Si l'authentification échoue ou si l'utilisateur
     *                          n'est pas trouvé
     */
    public User authenticate(UserAuthDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.login(),
                        input.password()));

        return userRepository.findByLogin(input.login());
        // .orElseThrow();
    }
}
