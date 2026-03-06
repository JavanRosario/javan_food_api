package com.javanfood.javanfood.api.controler.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.model.Restaurante;

import jakarta.persistence.OneToMany;

public class CidadeMixin {
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Estado estado;

	@JsonIgnore
	private List<Restaurante> restaurantes = new ArrayList<>();
}
