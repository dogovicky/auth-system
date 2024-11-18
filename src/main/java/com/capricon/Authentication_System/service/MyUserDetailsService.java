package com.capricon.Authentication_System.service;

import com.capricon.Authentication_System.model.User;
import com.capricon.Authentication_System.model.UserPrincipal;
import com.capricon.Authentication_System.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepo repo;

    public MyUserDetailsService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        try {
            User user = repo.findByEmail(email);
            return new UserPrincipal(user);
        } catch (UsernameNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
