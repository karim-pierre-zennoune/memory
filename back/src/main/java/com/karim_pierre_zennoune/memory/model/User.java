package com.karim_pierre_zennoune.memory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.karim_pierre_zennoune.memory.dto.UserAuthDto;

@Entity(name = "users")
@Table(name = "users")
@Setter
@Getter
@RequiredArgsConstructor
public class User implements UserDetails {

    public User(UserAuthDto userLoginDto) {
        login = userLoginDto.login();
        password = userLoginDto.password();
    }

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Score> scores;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
        return true;
    }

}

// @Entity(name = "users")
// @ToString
// @Data
// public class UsersEntity {
// @Id
// @GeneratedValue(strategy = GenerationType.SEQUENCE)
// private Integer id;
// private String username;
// private String password;
// }