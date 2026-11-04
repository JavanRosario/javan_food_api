package com.javanfood.javanfood.api.controllers.jasper;

import com.javanfood.javanfood.domain.service.jasper.Reel001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/relatorios")
public class JasperController {

    @Autowired
    Reel001Service reel001Service;

    @GetMapping("/gerar-pdf")
    public ResponseEntity<byte[]> objerRelatorioVazio() {
        byte[] pdfBytes = reel001Service.gerarPdfVazio();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio_vazio.pdf");

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
    }
}
