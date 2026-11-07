package com.javanfood.javanfood;

import com.javanfood.javanfood.infrastructure.report.JasperService;
import com.javanfood.javanfood.infrastructure.report.Reel001Service;
import net.sf.jasperreports.engine.JasperReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RelatorioJasperUnitTest {

    @Test
    void testReel001Service() throws Exception {
        Reel001Service service = new Reel001Service();
        byte[] pdf = service.gerarPdfVazio();
        Assertions.assertNotNull(pdf);
        Assertions.assertTrue(pdf.length > 0);
        
        // Salva o PDF gerado no disco para você abrir e conferir!
        java.nio.file.Files.write(java.nio.file.Path.of("relatorio_gerado.pdf"), pdf);
        System.out.println(">>> SUCESSO: Reel001Service gerou PDF com " + pdf.length + " bytes e salvou em relatorio_gerado.pdf!");
    }

    @Test
    void testJasperServiceCompile() {
        JasperService service = new JasperService();
        JasperReport report = service.compileReport("rel_SigSocial_teste");
        Assertions.assertNotNull(report, "O JasperReport não deveria ser nulo!");
        System.out.println(">>> SUCESSO: JasperService compilou rel_SigSocial_teste.jrxml!");
    }
}
