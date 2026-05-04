package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.exception.NegocioException;
import com.javanfood.javanfood.domain.model.Pedido;
import com.javanfood.javanfood.domain.model.StatusPedido;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlteracaoPedidoService {

    private final PedidoService pedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalha(pedidoId);

        if (!pedido.getStatusPedido().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(String.format("Status do pedido %d não pode ser alterado para %s para %s",
                    pedido.getId(), pedido.getStatusPedido(), StatusPedido.CONFIRMADO));
        }

        pedido.setStatusPedido(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(LocalDateTime.now());
    }
}
