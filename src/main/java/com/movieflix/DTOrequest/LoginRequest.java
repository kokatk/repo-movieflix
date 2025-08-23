package com.movieflix.DTOrequest;

import lombok.Builder;

@Builder
public record LoginRequest(String email, String password) {

}
