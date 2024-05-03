package org.example.impl.dto;

import lombok.Data;
import org.example.implementations.entities.Owner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityOwner implements UserDetails {
    private final String name;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isValid;


    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isValid;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isValid;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isValid;
    }

    @Override
    public boolean isEnabled() {
        return isValid;
    }


    public static UserDetails convertOwnerToUserDetails(Owner owner) {
        return new org.springframework.security.core.userdetails.User(
                owner.getName(),
                owner.getPassword(),
                owner.getRole().getAuthorities()
        );
    }
}

