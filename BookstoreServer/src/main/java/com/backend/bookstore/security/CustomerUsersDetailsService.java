package com.backend.bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import com.backend.bookstore.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.ArrayList;

@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private com.backend.bookstore.models.User user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        user = userRepository.findByEmailId(username);
        if(!Objects.isNull(user))
            return new User(user.getEmail(),user.getPassword(), new ArrayList<>());
        else
            throw new UsernameNotFoundException("Username not found");
    }
    
    public com.backend.bookstore.models.User getUserDetail(){
        return user;
    }
}
