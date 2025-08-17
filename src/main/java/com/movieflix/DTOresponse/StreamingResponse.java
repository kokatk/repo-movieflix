package com.movieflix.DTOresponse;

import lombok.Builder;

@Builder
public record StreamingResponse(long id, String name) {

}
