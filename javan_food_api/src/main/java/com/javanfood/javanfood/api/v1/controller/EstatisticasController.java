package com.javanfood.javanfood.api.v1.controller;

import com.javanfood.javanfood.api.v1.dto.filters.VendaDiariaFilter;
import com.javanfood.javanfood.api.v1.dto.response.VendaDiariaResponse;
import com.javanfood.javanfood.domain.service.EstatisticasService;
import com.javanfood.javanfood.domain.service.jasper.PdfVendaReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/estatisticas")
@RequiredArgsConstructor
public class EstatisticasController {
    public final EstatisticasService estatisticasService;
    public final PdfVendaReportService pdfVendaReportService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiariaResponse> consultarVendaDiaria(
            @ParameterObject VendaDiariaFilter vendaDiariaFilter) {
        return estatisticasService.consultarVendas(vendaDiariaFilter);
    }

    @GetMapping(value = "/gerar-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void emitirPdfVendaDiaria(
            @RequestBody(required = false) @ParameterObject VendaDiariaFilter vendaDiariaFilter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffset,
            HttpServletResponse httpServletResponse) {

        pdfVendaReportService.emitirPdf(vendaDiariaFilter, timeOffset, httpServletResponse);
    }
}
