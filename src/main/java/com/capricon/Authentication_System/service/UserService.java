package com.capricon.Authentication_System.service;

import com.capricon.Authentication_System.model.User;
import com.capricon.Authentication_System.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User findUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User signUpUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(User user) {
        try {
            Authentication authentication = authManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                System.out.println(jwtService.getToken(user.getEmail()));
                return jwtService.getToken(user.getEmail());
            }
        } catch (AuthenticationException ae) {
            System.out.println(ae.getMessage());
        }
        return "Failed";
    }


//    public Object getProfileData() {
//
//    }
}
