package org.example.impl.services;

import org.example.declarations.OwnerDao;
import org.example.impl.dto.SecurityOwner;
import org.example.implementations.entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final OwnerDao ownerDao;

    @Autowired
    public UserDetailsServiceImpl(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Owner owner = ownerDao.findOwnerByName(name).orElseThrow(() -> new UsernameNotFoundException(String.format("User with name %s does not exist", name)));
        return SecurityOwner.convertOwnerToUserDetails(owner);
    }
}
