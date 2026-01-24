package com.javanfood.javanfood.domain.exeption;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidacaoExeption extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private BindingResult bindingResult;
}
