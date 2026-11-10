package com.javanfood.javanfood.api.v1.controller;

import com.javanfood.javanfood.api.v1.dto.filters.VendaDiariaFilter;
import com.javanfood.javanfood.api.v1.dto.response.VendaDiariaResponse;
import com.javanfood.javanfood.domain.service.EstatisticasService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/estatisticas")
@RequiredArgsConstructor
public class EstatisticasController {
  public final EstatisticasService estatisticasService;

  @GetMapping("/vendas-diarias")
  public List<VendaDiariaResponse> consultarVendaDiaria(
      @ParameterObject VendaDiariaFilter vendaDiariaFilter) {
    return estatisticasService.consultarVendasDiarias(vendaDiariaFilter);
  }
}
