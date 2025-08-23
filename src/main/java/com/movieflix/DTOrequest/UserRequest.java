package com.movieflix.DTOrequest;

import lombok.Builder;

@Builder
public record UserRequest(String name, String email, String password) {

}
