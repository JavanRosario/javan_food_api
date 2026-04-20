package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.api.dto.request.ProdutoRequest;
import com.javanfood.javanfood.api.dto.request.RestauranteRequest;
import com.javanfood.javanfood.api.mapper.produtoMapper.ProdutoRequestMapper;
import com.javanfood.javanfood.api.mapper.restauranteMapper.RestauranteRequestMapper;
import com.javanfood.javanfood.domain.exception.*;
import com.javanfood.javanfood.domain.model.*;
import com.javanfood.javanfood.domain.repository.ProdutoRepository;
import com.javanfood.javanfood.domain.repository.RestauranteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestauranteService {

    private static final String MSG_ENTIDADE_EM_USO = "Restaurante de código: %d não pode ser removida, pois está em uso";
    private final RestauranteRepository restauranteRepository;
    private final CozinhaService cozinhaService;
    private final CidadeService cidadeService;
    private final RestauranteRequestMapper restauranteRequestMapper;
    private final FormaPagamentoService formaPagamentoService;
    private final ProdutoRepository produtoRepository;
    private final ProdutoRequestMapper produtoRequestMapper;


    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarOuFalha(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    @Transactional
    public Produto adicionarProduto(Long restauranteId, Produto produto) {
        Restaurante restaurante = buscarOuFalha(restauranteId);
        produto.setRestaurante(restaurante);
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizarProduto(Long restauranteId, ProdutoRequest produtoRequest, Long produtoId) {
        Restaurante restaurante = buscarOuFalha(restauranteId);
        Produto produto = getProdutoId(restauranteId, produtoId, restaurante);
        produtoRequestMapper.updateEntityFromDto(produtoRequest, produto);
        return produtoRepository.save(produto);
    }

    public FormaPagamento getFormaPagamentoId(Long restauranteId, Long formaPagamentoId, Restaurante restaurante) {
        return restaurante.getFormasPagamento()
                .stream()
                .filter(fp -> fp.getId().equals(formaPagamentoId))
                .findFirst()
                .orElseThrow(() -> new NegocioException(
                        String.format("Forma de pagamento %d não encontrada no restaurante %d", formaPagamentoId, restauranteId)
                ));
    }

    public Produto getProdutoId(Long restauranteId, Long produtoId, Restaurante restaurante) {
        return restaurante.getProdutos()
                .stream()
                .filter(g -> g.getId().equals(produtoId))
                .findFirst()
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }

    @Transactional
    public Restaurante atualizar(Long id, RestauranteRequest request) {
        Restaurante restaurante = buscarOuFalha(id);
        restauranteRequestMapper.updateEntityFromDto(request, restaurante);
        return salvar(restaurante);
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        try {
            Long cozinhaId = restaurante.getCozinha().getId();
            Long cidadeId = restaurante.getEndereco().getCidade().getId();
            Cozinha cozinha = cozinhaService.buscarOuFalha(cozinhaId);
            Cidade cidade = cidadeService.buscarOuFalha(cidadeId);
            restaurante.setCozinha(cozinha);
            restaurante.getEndereco().setCidade(cidade);
            return restauranteRepository.save(restaurante);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public void excluir(Long restauranteId) {

        if (!restauranteRepository.existsById(restauranteId)) {
            throw new RestauranteNaoEncontradoException(restauranteId);
        }

        try {
            restauranteRepository.deleteById(restauranteId);
            restauranteRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, restauranteId));
        }
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalha(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalha(formaPagamentoId);
        restaurante.getFormasPagamento().remove(formaPagamento);
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalha(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalha(formaPagamentoId);
        restaurante.getFormasPagamento().add(formaPagamento);
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalha(restauranteId);
        restaurante.ativar();
    }

    @Transactional
    public void desativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalha(restauranteId);
        restaurante.desativar();
    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restaurante = buscarOuFalha(restauranteId);
        restaurante.abrir();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalha(restauranteId);
        restaurante.fechar();
    }
}

