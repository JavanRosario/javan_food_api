package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.exception.NegocioException;
import com.javanfood.javanfood.domain.exception.PedidoNaoEncontradoException;
import com.javanfood.javanfood.domain.model.*;
import com.javanfood.javanfood.domain.repository.PedidoRepository;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final FormaPagamentoService formaPagamentoService;
    private final RestauranteService restauranteService;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;
    private final CidadeService cidadeService;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido buscarOuFalha(String codigo) {
        return pedidoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
    }

    @Transactional
    public Pedido adicionarPedido(Pedido pedido) {
        Long restauranteId = pedido.getRestaurante().getId();
        Long formaPagamentoId = pedido.getFormaPagamento().getId();

        Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalha(formaPagamentoId);

        Long cidadeId = pedido.getEndereco().getCidade().getId();
        Cidade cidade = cidadeService.buscarOuFalha(cidadeId);
        pedido.getEndereco().setCidade(cidade);

        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setUsuario(usuarioService.buscarOuFalha(1L));

        if (!restaurante.getFormasPagamento().contains(formaPagamento)) {
            throw new NegocioException(String.format(
                    "Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }

        calcularItemPedido(pedido, restauranteId, restaurante);

        BigDecimal subTotal = calcularSubTotal(pedido);

        pedido.setSubTotal(subTotal);
        pedido.setTaxaFrete(restaurante.getTaxaFrete());
        pedido.setValorTotal(subTotal.add(restaurante.getTaxaFrete()));
        pedido.setStatusPedido(StatusPedido.CRIADO);

        return salvar(pedido);
    }

    private void calcularItemPedido(Pedido pedido, Long restauranteId, Restaurante restaurante) {
        for (ItemPedido item : pedido.getItemPedido()) {
            Produto produto = produtoService.buscarOuFalha(item.getProduto().getId());

            if (!produto.getRestaurante().getId().equals(restauranteId)) {
                throw new NegocioException(String.format(
                        "Produto '%s' não pertence ao restaurante '%s'.",
                        produto.getNome(), restaurante.getNome()));
            }

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
            item.setPrecoTotal(produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
        }
    }

    @Nonnull
    private static BigDecimal calcularSubTotal(Pedido pedido) {
        return pedido.getItemPedido().stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
