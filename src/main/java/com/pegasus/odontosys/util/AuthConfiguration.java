package com.pegasus.odontosys.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pegasus.odontosys.repository.UserRepository;

import jakarta.transaction.Transactional;
@Transactional

@Service 
public class AuthConfiguration implements UserDetailsService{

    @Autowired 
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow(()-> new UsernameNotFoundException(username));    
    }

    
}
