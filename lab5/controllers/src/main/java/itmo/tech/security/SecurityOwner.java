package itmo.tech.security;

import itmo.tech.dto.OwnerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class SecurityOwner implements UserDetails {
    private final String name;
    private final String password;
    private final Integer id;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isValid;


    @Override
    public String getUsername() {
        return name;
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


    public static UserDetails convertOwnerToUserDetails(OwnerDto owner) {
        return new SecurityOwner(
                owner.getName(),
                owner.getPassword(),
                owner.getId(),
                owner.getRole().getAuthorities(),
                true);
    }
}

