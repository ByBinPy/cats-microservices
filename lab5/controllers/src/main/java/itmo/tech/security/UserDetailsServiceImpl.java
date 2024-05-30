package itmo.tech.security;

import itmo.tech.dto.OwnerDto;
import itmo.tech.messaging.rpc.OwnerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final OwnerClient ownerClient;
    @Autowired
    public UserDetailsServiceImpl(OwnerClient ownerClient) {
        this.ownerClient = ownerClient;
    }
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        OwnerDto owner = ownerClient.findOwnerByName(name);
        return SecurityOwner.convertOwnerToUserDetails(owner);
    }
}
