package com.example.authserver.controller;

import com.example.authserver.model.AuthTokenResponse;
import com.example.authserver.model.JwtRequest;
import com.example.authserver.resource.UserResource;
import com.example.authserver.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserResource user) {
        return ResponseEntity.ok(userDetailsService.userRegister(user));
    }
}