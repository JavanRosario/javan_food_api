package com.javanfood.javanfood.api.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record VendaDiariaResponse(
    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data,
    Long totalVendas,
    BigDecimal totalFaturado) {

  public VendaDiariaResponse(Date data, Long totalVendas, BigDecimal totalFaturado) {
    this(new java.sql.Date(data.getTime()).toLocalDate(), totalVendas, totalFaturado);
  }
}
