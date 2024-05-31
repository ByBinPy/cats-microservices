package itmo.tech.security;

import itmo.tech.dto.OwnerDto;
import itmo.tech.messaging.rpc.OwnerRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final OwnerRpcClient ownerRpcClient;
    @Autowired
    public UserDetailsServiceImpl(OwnerRpcClient ownerRpcClient) {
        this.ownerRpcClient = ownerRpcClient;
    }
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        OwnerDto ownerDto = ownerRpcClient.findOwnerByName(name);
        return SecurityOwner.convertOwnerToUserDetails(ownerDto);
    }
}
