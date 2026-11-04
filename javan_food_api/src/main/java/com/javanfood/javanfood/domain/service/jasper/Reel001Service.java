package com.javanfood.javanfood.domain.service.jasper;

import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class Reel001Service {

    public byte[] gerarPdfVazio() {
        try {
            InputStream stream = this.getClass().getResourceAsStream("/reports/rel_teste.jrxml");

            if (stream == null) {
                throw new RuntimeException("Arquivo .jrxml nao encontrado!");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(stream);

            Map<String, Object> parametros = new HashMap<>();
            ClassPathResource logoOndas = new ClassPathResource("reports/waves.png");
            parametros.put("parRodapeOndas", logoOndas.getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o relatorio local", e);
        }
    }
}
