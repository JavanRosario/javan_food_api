package com.javanfood.javanfood.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaInput {
	@NotNull
	private Long id;

}
