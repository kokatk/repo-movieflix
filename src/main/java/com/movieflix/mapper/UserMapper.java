package com.movieflix.mapper;


import com.movieflix.DTOrequest.UserRequest;
import com.movieflix.DTOresponse.UserResponse;
import com.movieflix.model.User;

public class UserMapper {
    public static User toUser(UserRequest request){
        return User
        .builder()
        .name(request.name())
        .email(request.email())
        .password(request.password())
        .build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse
        .builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();
    }
}
