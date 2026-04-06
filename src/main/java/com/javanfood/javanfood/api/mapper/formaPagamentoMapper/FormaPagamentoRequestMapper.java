package com.javanfood.javanfood.api.mapper.formaPagamentoMapper;

import com.javanfood.javanfood.api.dto.request.FormaPagamentoRequest;
import com.javanfood.javanfood.domain.model.FormaPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FormaPagamentoRequestMapper {
    FormaPagamento toDomainObject(FormaPagamentoRequest formaPagamentoRequest);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(FormaPagamentoRequest formaPagamentoRequest, @MappingTarget FormaPagamento formaPagamento);
}
