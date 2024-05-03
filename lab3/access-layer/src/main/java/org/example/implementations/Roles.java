package org.example.implementations;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public enum Roles {
    ADMIN(List.of(Permissions.READ, Permissions.WRITE)),
    USER(List.of(Permissions.READ));
    private final List<Permissions> authorities;
    Roles(List<Permissions> authorities) {
        this.authorities = authorities;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
    }
}
