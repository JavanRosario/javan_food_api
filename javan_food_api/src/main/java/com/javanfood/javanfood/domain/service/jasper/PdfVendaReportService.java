package com.javanfood.javanfood.domain.service.jasper;

import com.javanfood.javanfood.api.v1.dto.filters.VendaDiariaFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class PdfVendaReportService {

    public void emitirPdf(VendaDiariaFilter vendaDiariaFilter, String timeOffset, HttpServletResponse httpServletResponse) {

        httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias-pdf");
        httpServletResponse.setContentType(MediaType.APPLICATION_PDF_VALUE);
    }
}
