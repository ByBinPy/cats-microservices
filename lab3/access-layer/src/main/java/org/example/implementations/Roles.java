package org.example.implementations;

import lombok.Getter;

import java.util.List;

public enum Roles {
    ADMIN(List.of(Permissions.READ, Permissions.WRITE)),
    USER(List.of(Permissions.READ));

    @Getter
    private List<Permissions> authorities;
    Roles(List<Permissions> authorities) {
        this.authorities = authorities;
    }
}
