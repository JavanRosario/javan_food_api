package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.api.dto.request.RestauranteRequest;
import com.javanfood.javanfood.api.mapper.restauranteMapper.RestauranteRequestMapper;
import com.javanfood.javanfood.domain.exception.CidadeNaoEncontradoException;
import com.javanfood.javanfood.domain.exception.CozinhaNaoEncontradoException;
import com.javanfood.javanfood.domain.exception.EntidadeEmUsoException;
import com.javanfood.javanfood.domain.exception.NegocioException;
import com.javanfood.javanfood.domain.exception.RestauranteNaoEncontradoException;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;
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


    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarOuFalha(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
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
        } catch (CozinhaNaoEncontradoException | CidadeNaoEncontradoException e) {
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
    public void ativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalha(restauranteId);
        restaurante.ativar();
    }

    @Transactional
    public void desativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalha(restauranteId);
        restaurante.desativar();
    }
}

