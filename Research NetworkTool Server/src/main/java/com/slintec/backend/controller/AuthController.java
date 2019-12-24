package com.slintec.backend.controller;

import com.slintec.backend.data.Role;
import com.slintec.backend.data.RoleName;
import com.slintec.backend.data.User;
import com.slintec.backend.exception.AppException;
import com.slintec.backend.payload.ApiResponse;
import com.slintec.backend.payload.JwtAuthenticationResponse;
import com.slintec.backend.payload.LoginRequest;
import com.slintec.backend.payload.SignUpRequest;
import com.slintec.backend.repo.RoleRepository;
import com.slintec.backend.repo.UserRepository;
import com.slintec.backend.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmailOrNic(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        User x=userRepository.findByUserNameOrEmailOrNicAndDeleted(loginRequest.getUsernameOrEmailOrNic(),loginRequest.getUsernameOrEmailOrNic(),loginRequest.getUsernameOrEmailOrNic(),false)
                .orElse(null);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,x));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUserName(signUpRequest.getUserName())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User();

        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(signUpRequest,user);

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElse(null);

        if(userRole==null){
            Role role=new Role();
            role.setName(RoleName.ROLE_USER);
            userRole=role;
            roleRepository.saveAndFlush(role);

        }
        user.setRoles(userRole);


        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUserName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
