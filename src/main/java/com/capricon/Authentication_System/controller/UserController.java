package com.capricon.Authentication_System.controller;

import com.capricon.Authentication_System.model.User;
import com.capricon.Authentication_System.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    private UserService service;

    private UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUpUser(@RequestBody User newUser) {

        User user = service.findUserByEmail(newUser.getEmail());
        if (user == null) {
            try {
                service.signUpUser(newUser);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("User already registered");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = service.verify(user);
        return ResponseEntity.ok().body(Map.of("token", token));
    }

//    @GetMapping("/profile")
//    public ResponseEntity<?> getProfileData(@AuthenticationPrincipal UserDetails userDetails) {
//        return new ResponseEntity<>(service.getProfileData(), HttpStatus.OK);
//    }


}
