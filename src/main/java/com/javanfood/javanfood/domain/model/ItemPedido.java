package com.javanfood.javanfood.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private Integer quantidade;

	@Column(name = "preco_unitario", nullable = false)
	private BigDecimal precoUnitatio;

	@Column(nullable = false)
	private BigDecimal precoTotal;

	@Column(nullable = false)
	private String observacao;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Pedido pedido;

}
