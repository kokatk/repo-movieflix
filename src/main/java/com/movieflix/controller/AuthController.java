package com.movieflix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.DTOrequest.LoginRequest;
import com.movieflix.DTOrequest.UserRequest;
import com.movieflix.DTOresponse.LoginResponse;
import com.movieflix.DTOresponse.UserResponse;
import com.movieflix.config.JwtTokenProvider;
import com.movieflix.mapper.UserMapper;
import com.movieflix.model.User;
import com.movieflix.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/movieflix/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register (@RequestBody UserRequest request){
        User registerUser = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(registerUser));
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest request){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authenticated = authenticationManager.authenticate(userAndPass);

        User user = (User) authenticated.getPrincipal();
        String token = jwtTokenProvider.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    
    }

}
