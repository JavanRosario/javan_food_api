package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.api.dto.request.FormaPagamentoRequest;
import com.javanfood.javanfood.api.mapper.formapagamento.FormaPagamentoRequestMapper;
import com.javanfood.javanfood.domain.exception.EntidadeEmUsoException;
import com.javanfood.javanfood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.javanfood.javanfood.domain.model.FormaPagamento;
import com.javanfood.javanfood.domain.repository.FormaPagamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormaPagamentoService {

    private static final String MSG_ENTIDADE_EM_USO = "Forma de Pagamento de código: %d não pode ser removida, pois está em uso";
    private final FormaPagamentoRepository formaPagamentoRepository;
    private final FormaPagamentoRequestMapper formaPagamentoRequestMapper;

    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    public FormaPagamento buscarOuFalha(Long pagamentoId) {
        return formaPagamentoRepository.findById(pagamentoId)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(pagamentoId));
    }

    @Transactional
    public FormaPagamento atualizar(Long id, FormaPagamentoRequest formaPagamentoRequest) {
        FormaPagamento formaPagamento = buscarOuFalha(id);
        formaPagamentoRequestMapper.updateEntityFromDto(formaPagamentoRequest, formaPagamento);
        return salvar(formaPagamento);
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void excluir(Long formaPagamentoId) {
        buscarOuFalha(formaPagamentoId);
        try {
            formaPagamentoRepository.deleteById(formaPagamentoId);
            formaPagamentoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, formaPagamentoId));
        }
    }
}
