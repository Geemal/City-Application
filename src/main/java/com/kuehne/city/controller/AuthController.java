package com.kuehne.city.controller;

import com.kuehne.city.config.JWTUtility;
import com.kuehne.city.config.WebSecurityConfig;
import com.kuehne.city.exception.CustomErrorException;
import com.kuehne.city.response.LoginResponse;
import com.kuehne.city.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final JWTUtility jwtUtility;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(JWTUtility jwtUtility,
                          UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.jwtUtility = jwtUtility;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public LoginResponse login(
            @RequestParam(name = "username" , required = false) String username,
            @RequestParam(name = "password", required = false) String password) throws CustomErrorException {

        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            logger.error("user not found");
            throw new CustomErrorException("User not found", HttpStatus.UNAUTHORIZED, new Date());
        }

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            logger.debug("Passwords are matched");
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);

            String authorities = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));
            //overrides the default _scope attribute with the "roles" in JWT token
            claims.put(WebSecurityConfig.AUTHORITIES_CLAIM_NAME, authorities);
            claims.put("userId", String.valueOf(1));

            String jwt = this.jwtUtility.generateToken(userDetails, claims);
            logger.debug("token generated");
            //return the JWT token to user
            return new LoginResponse(jwt);
        }

        throw new CustomErrorException("User not found", HttpStatus.UNAUTHORIZED, new Date());
    }
}
