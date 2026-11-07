package com.javanfood.javanfood.api.v1.mapper.formapagamento;

import com.javanfood.javanfood.api.v1.dto.response.FormaPagamentoResponse;
import com.javanfood.javanfood.domain.model.FormaPagamento;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FormaPagamentoResponseMapper {
    FormaPagamentoResponse toDto(FormaPagamento formaPagamento);

    List<FormaPagamentoResponse> toDtoCollection(Collection<FormaPagamento> formaPagamentos);
}
