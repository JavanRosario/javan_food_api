package com.javanfood.javanfood.infrastructure.report;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperReportService {

    public byte[] gerarRelatorioVazio() {
        try {
            InputStream stream = this.getClass().getResourceAsStream("/reports/rel_teste.jrxml");

            if (stream == null) {
                throw new RuntimeException("Arquivo de template .jrxml não encontrado!");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(stream);
            Map<String, Object> parametros = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o relatório PDF via Jasper", e);
        }
    }
}
