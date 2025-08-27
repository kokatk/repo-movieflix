package com.movieflix.DTOrequest;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotEmpty(message = "Nome da categoria é obrigatório.") String name) {
    
}
