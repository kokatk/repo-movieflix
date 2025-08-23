package com.movieflix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.DTOrequest.UserRequest;
import com.movieflix.DTOresponse.UserResponse;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.model.Category;
import com.movieflix.model.User;
import com.movieflix.repository.UserRepository;
import com.movieflix.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;


    public ResponseEntity<UserResponse> register (@RequestBody UserRequest request){
        User registerUser = userService.createdCategory(CategoryMapper.toUser(request));
    }
    

}
