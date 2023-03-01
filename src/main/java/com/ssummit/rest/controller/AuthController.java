package com.ssummit.rest.controller;

import com.ssummit.dto.LoginDTO;
import com.ssummit.security.JwtTokenUtil;
import com.ssummit.service.UserService;
import com.ssummit.service.userDetails.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public AuthController(CustomUserDetailsService authenticationService, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.customUserDetailsService = authenticationService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/authorization")
    public ResponseEntity<?> auth(@RequestBody LoginDTO loginDto) {
        Map<String, Object> response = new HashMap<>();

        if(!userService.checkPassword(loginDto)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user!\nWrongPassword");
        }

        UserDetails foundUser = customUserDetailsService.loadUserByUsername(loginDto.getLogin());
        String token = jwtTokenUtil.generateToken(foundUser);
        response.put("token", token);
        response.put("authorities", foundUser.getAuthorities());
        return ResponseEntity.ok().body(response);

    }
}

