package com.javanfood.javanfood.infrastructure.report;

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
            InputStream stream = this.getClass().getResourceAsStream("/reports/rel_SigSocial_teste.jrxml");

            if (stream == null) {
                throw new RuntimeException("Arquivo .jrxml nao encontrado!");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(stream);

            Map<String, Object> parametros = new HashMap<>();

            ClassPathResource logoOndas = new ClassPathResource("reports/waves.png");
            if (logoOndas.exists()) {
                parametros.put("parRodapeOndas", logoOndas.getInputStream());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
