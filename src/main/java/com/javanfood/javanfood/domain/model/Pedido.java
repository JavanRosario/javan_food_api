package com.javanfood.javanfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private BigDecimal subTotal;
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	@Column(nullable = false)
	private BigDecimal valorTotal;

	@Embedded
	private Endereco endereco;

	private StatusPedido statusPedido;

	@CreationTimestamp
	private LocalDateTime dataCriacao;


	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataEntrega;
	private LocalDateTime dataCancelamento;


	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();

	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario usuario;

}
