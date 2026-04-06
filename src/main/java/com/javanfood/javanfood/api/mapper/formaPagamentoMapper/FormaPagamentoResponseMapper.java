package com.javanfood.javanfood.api.mapper.formaPagamentoMapper;

import com.javanfood.javanfood.api.dto.response.FormaPagamentoResponse;
import com.javanfood.javanfood.domain.model.FormaPagamento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormaPagamentoResponseMapper {
    FormaPagamentoResponse toDto(FormaPagamento formaPagamento);

    List<FormaPagamentoResponse> toDtoCollection(List<FormaPagamento> formaPagamentos);
}
