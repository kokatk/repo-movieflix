package com.movieflix.DTOresponse;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {

}
