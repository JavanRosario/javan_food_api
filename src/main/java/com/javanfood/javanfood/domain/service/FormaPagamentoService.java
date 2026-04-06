package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.exception.EntidadeEmUsoException;
import com.javanfood.javanfood.domain.exception.PagamentoNaoEncontradoException;
import com.javanfood.javanfood.domain.model.FormaPagamento;
import com.javanfood.javanfood.domain.repository.FormaPagamentoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;
    private static final String MSG_ENTIDADE_EM_USO = "Forma de Pagamento de código: %d não pode ser removida, pois está em uso";

    public FormaPagamento buscaOuFalha(Long pagamentoId) {
        return formaPagamentoRepository.findById(pagamentoId).orElseThrow(() -> new PagamentoNaoEncontradoException(pagamentoId));
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void excluir(Long formaPagamentoId) {
        if (!formaPagamentoRepository.existsById(formaPagamentoId)) {
            throw new PagamentoNaoEncontradoException(formaPagamentoId);
        }

        try {
            formaPagamentoRepository.deleteById(formaPagamentoId);
            formaPagamentoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, formaPagamentoId));
        }
    }
}
