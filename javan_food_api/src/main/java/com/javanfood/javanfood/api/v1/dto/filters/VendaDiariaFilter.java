package com.javanfood.javanfood.api.v1.dto.filters;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.Date;

public record VendaDiariaFilter(
    Long restauranteId,
    @JsonFormat(pattern = "dd/MM/yyyy") Date dataInicio,
    @JsonFormat(pattern = "dd/MM/yyyy") Date dataFim) {}
