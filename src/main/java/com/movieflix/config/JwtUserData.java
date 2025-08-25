package com.movieflix.config;

import lombok.Builder;

@Builder
public record JwtUserData(Long Id, String name, String email) {

}
