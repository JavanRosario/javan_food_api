package com.javanfood.javanfood.api.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record VendaDiariaResponse(Long restauranteId,
                                  @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data, Long totalVendas,
                                  BigDecimal totalFaturado) {

    public VendaDiariaResponse(Long restauranteId, Date data, Long totalVendas, BigDecimal totalFaturado) {
        this(restauranteId, new java.sql.Date(data.getTime()).toLocalDate(), totalVendas, totalFaturado);
    }
}
