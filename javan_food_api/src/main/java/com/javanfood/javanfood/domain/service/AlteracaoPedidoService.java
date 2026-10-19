package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.model.Pedido;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlteracaoPedidoService {

    private final PedidoService pedidoService;

    @Transactional
    public void confirmar(String codigo) {
        Pedido pedido = pedidoService.buscarOuFalha(codigo);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigo) {
        Pedido pedido = pedidoService.buscarOuFalha(codigo);
        pedido.entregar();
    }

    @Transactional
    public void calcelar(String codigo) {
        Pedido pedido = pedidoService.buscarOuFalha(codigo);
        pedido.cancelar();
    }
}
