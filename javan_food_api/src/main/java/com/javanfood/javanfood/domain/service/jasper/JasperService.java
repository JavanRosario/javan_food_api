package com.javanfood.javanfood.domain.service.jasper;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class JasperService {

    public byte[] export(final JasperPrint print) throws JRException {
        final Exporter exporter;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        exporter = new HtmlExporter();

        SimpleHtmlExporterOutput sHtmlOut = new SimpleHtmlExporterOutput(out);

        exporter.setExporterOutput(sHtmlOut);
        exporter.setExporterInput(new SimpleExporterInput(print));

        SimpleHtmlReportConfiguration reportExportConfiguration = new SimpleHtmlReportConfiguration();
        reportExportConfiguration.setWhitePageBackground(false);
        reportExportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        exporter.setConfiguration(reportExportConfiguration);

        exporter.exportReport();

        return out.toByteArray();
    }

    public static JasperPrint fillReportWithTimeout(JasperReport jasperReport, Map<String, Object> parametros,
                                                    JRDataSource dataSource) throws Exception {

        final long TIMEOUT_MS_GERACAO_REL = 20000;

        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<JasperPrint> future = executor
                    .submit(() -> JasperFillManager.fillReport(jasperReport, parametros, dataSource));

            // Espera até o timeout
            return future.get(TIMEOUT_MS_GERACAO_REL, TimeUnit.MILLISECONDS);
        } finally {
            executor.shutdownNow(); // força o encerramento da thread
        }
    }

    public void gerandoRelatorio(String nomeRelatorio, HashMap<String, Object> parametros,
                                 JRBeanCollectionDataSource dataSource, String type, HttpServletResponse response)
            throws JRException, IOException {

        JasperReport jasperReport = compileReport(nomeRelatorio);
        JasperPrint jasperPrint = null;

//		// Tema
//        ArrayList<JRSimpleTemplate> templateList = themeService.getTheme(tema);
//        templateList.forEach(s -> System.out.println(s));
//        parametros.put(JRParameter.REPORT_TEMPLATES, templateList);

        try {
            if (dataSource == null)
                jasperPrint = fillReportWithTimeout(jasperReport, parametros, new JREmptyDataSource());
            else
                jasperPrint = fillReportWithTimeout(jasperReport, parametros, dataSource);
        } catch (TimeoutException e) {
            throw new RuntimeException("Falha ao gerar relatório (tempo de processamento máximo esgotado).");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao gerar relatório: "+e.getMessage());
        }

        if (type.compareToIgnoreCase("pdf") == 0) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + nomeRelatorio + ".pdf");

            final OutputStream outStream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        }
        if (type.compareToIgnoreCase("html") == 0) {
            byte[] bytes = export(jasperPrint);

            response.setContentType("text/html");
            response.setHeader("Content-Disposition", "inline; filename=" + nomeRelatorio + ".html");
            response.setContentLength(bytes.length);

            final OutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        }
        if (type.compareToIgnoreCase("docx") == 0) {

            response.setContentType("application/ms-word");
            response.setHeader("Content-Disposition", "inline; filename=" + nomeRelatorio + ".docx");

            final OutputStream outStream = response.getOutputStream();

            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
                    /* OutputStream fileOutputStream = new FileOutputStream(outputFile) */) {
                Exporter exporter = new JRDocxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                exporter.exportReport();
                byteArrayOutputStream.writeTo(outStream);
            }
        }
        if (type.compareToIgnoreCase("odt") == 0) {

            response.setContentType("application/vnd.oasis.opendocument.text");
            response.setHeader("Content-Disposition", "inline; filename=" + nomeRelatorio + ".odt");

            final OutputStream outStream = response.getOutputStream();

            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                JROdtExporter exporter = new JROdtExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                exporter.exportReport();
                byteArrayOutputStream.writeTo(outStream);
            }
        }
        if (type.compareToIgnoreCase("ods") == 0) {

            response.setContentType("application/vnd.oasis.opendocument.text");
            response.setHeader("Content-Disposition", "inline; filename=" + nomeRelatorio + ".ods");

            final OutputStream outStream = response.getOutputStream();

            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                JROdsExporter exporter = new JROdsExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                exporter.exportReport();
                byteArrayOutputStream.writeTo(outStream);
            }
        }
        if (type.compareToIgnoreCase("xlsx") == 0) {

            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=" + nomeRelatorio + ".xlsx");

            final OutputStream outStream = response.getOutputStream();

            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
                    /* OutputStream fileOutputStream = new FileOutputStream(outputFile) */) {
                Exporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                exporter.exportReport();
                byteArrayOutputStream.writeTo(outStream);
            }
        }

    }

    public JasperReport compileReport(String reportFileName) {

        JasperReport jasperReport = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("./reports/" + reportFileName + ".jrxml");

            InputStream inputStream = classPathResource.getInputStream();

            File tempFile = File.createTempFile("temp", ".jrxml");
            OutputStream outStream = new FileOutputStream(tempFile);

            try {
                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1)
                    outStream.write(buffer, 0, bytesRead);

            } finally {
                inputStream.close();
                outStream.close();
            }

            jasperReport = JasperCompileManager.compileReport(tempFile.getPath());

        } catch (IOException | JRException ex) {
            Logger.getLogger(JasperService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jasperReport;
    }
}
