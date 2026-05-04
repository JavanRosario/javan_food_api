package com.javanfood.javanfood.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteIdRequest {

    @NotNull
    Long id;
}
