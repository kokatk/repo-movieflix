package com.movieflix.service;

import org.springframework.stereotype.Service;

import com.movieflix.model.User;
import com.movieflix.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public User save(User user){
        return userRepository.save(user);
    }
}
