package com.javanfood.javanfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.javanfood.javanfood.domain.exception.NegocioException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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

	private String codigo;

	@Column(nullable = false)
	private BigDecimal subTotal;
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	@Column(nullable = false)
	private BigDecimal valorTotal;

	@Embedded
	private Endereco endereco;

	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido;

	@CreationTimestamp
	private LocalDateTime dataCriacao;
	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataEntrega;
	private LocalDateTime dataCancelamento;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itemPedido = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario usuario;

	public void confirmar() {
		this.setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(LocalDateTime.now());
	}

	public void entregar() {
		this.setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(LocalDateTime.now());
	}

	public void cancelar() {
		this.setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(LocalDateTime.now());
	}

	private void setStatus(StatusPedido novoStatus) {
		if (getStatusPedido().naoPodeAlterar(novoStatus)) {
			throw new NegocioException(String.format("Status do pedido %s não pode ser alterado de %s para %s",
					getCodigo(), getStatusPedido(), novoStatus));
		}

		this.statusPedido = novoStatus;
	}
	

	@PrePersist
	private void gerarCodigo() {
		setCodigo(UUID.randomUUID().toString());
	}

}
