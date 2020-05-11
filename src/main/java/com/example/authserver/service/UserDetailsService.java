package com.example.authserver.service;

import com.example.authserver.Utils.ExampleParam;
import com.example.authserver.config.JwtAuthenticationConfig;
import com.example.authserver.config.JwtGenerator;
import com.example.authserver.dao.UserDao;
import com.example.authserver.jwt.JwtUserDetailService;
import com.example.authserver.model.User;
import com.example.authserver.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Service
public class UserDetailsService {
    private final UserDao userDao;
    private final PasswordEncoder bcryptEncoder;
    private final JwtAuthenticationConfig jwtAuthenticationConfig;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailService jwtUserDetailService;

    @Autowired
    public UserDetailsService(UserDao userDao, PasswordEncoder bcryptEncoder, JwtAuthenticationConfig jwtAuthenticationConfig, AuthenticationManager authenticationManager, JwtUserDetailService jwtUserDetailService) {
        this.userDao = userDao;
        this.bcryptEncoder = bcryptEncoder;
        this.jwtAuthenticationConfig = jwtAuthenticationConfig;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailService = jwtUserDetailService;
    }

    public User userRegister(UserResource user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setRole(ExampleParam.USER_NORMAL);
        newUser.setUuid(UUID.randomUUID().toString());
        return userDao.save(newUser);
    }

    private String createAccessToken(UserResource userResource) {

        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(ExampleParam.ROLE_PREFIX + userResource.getRole()));

        return JwtGenerator.generateAccessJWT(userResource.getUsername(), userResource.getUuid(),
                grantedAuthorityList, jwtAuthenticationConfig.getAccessTokenExpiration(), jwtAuthenticationConfig.getSecret());
    }

    private String createRefreshToken(UserResource userResource) {
        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(ExampleParam.ROLE_PREFIX + userResource.getRole()));
        return JwtGenerator.generateRefreshToken(userResource.getUsername(), userResource.getUuid(),
                grantedAuthorityList, jwtAuthenticationConfig.getRefreshTokenExpiration(), jwtAuthenticationConfig.getSecret());
    }
}