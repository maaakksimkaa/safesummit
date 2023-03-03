package com.ssummit.rest.controller;

import com.ssummit.dto.LoginDTO;
import com.ssummit.dto.SignUpDto;
import com.ssummit.model.Role;
import com.ssummit.model.User;
import com.ssummit.repository.RoleRepository;
import com.ssummit.repository.UserRepository;
import com.ssummit.security.JwtTokenUtil;
import com.ssummit.service.UserService;
import com.ssummit.service.userDetails.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(CustomUserDetailsService authenticationService, JwtTokenUtil jwtTokenUtil, UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.customUserDetailsService = authenticationService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

//    @Operation(description = "Обновить запись", method = "Update")
//    @PutMapping("/update")
//    public void update() {
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
//        }
//    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {

        if (userRepository.existsByLogin(signUpDto.getLogin())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setMiddleName(signUpDto.getMiddleName());
        user.setLastName(signUpDto.getLastName());
        user.setBirthDate(signUpDto.getBirthDate());
        user.setPhone(signUpDto.getPhone());
        user.setAddress(signUpDto.getAddress());
        user.setEmail(signUpDto.getEmail());
        user.setPassportNo(signUpDto.getPassportNo());
        user.setLogin(signUpDto.getLogin());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = roleRepository.findRoleById(signUpDto.getRoleId());
        user.setRole(role);
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

    @PostMapping("/authorization")
    public ResponseEntity<?> auth(@RequestBody LoginDTO loginDto) {
        Map<String, Object> response = new HashMap<>();
        if (!userService.checkPassword(loginDto)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user!\nWrongPassword");
        }
        UserDetails foundUser = customUserDetailsService.loadUserByUsername(loginDto.getLogin());
        String token = jwtTokenUtil.generateToken(foundUser);
        response.put("token", token);
        response.put("authorities", foundUser.getAuthorities());
        return ResponseEntity.ok().body(response);
    }
}

