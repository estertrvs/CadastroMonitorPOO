package cadastroMonitores;

import java.io.FileOutputStream;

import javax.swing.JTable;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios {
	public static void obterResultadoFinalDeEdital(JTable tabela,EditalMonitoria edital) throws Exception {
        Document document = new Document();
		PdfPTable pdfTable = new PdfPTable(tabela.getColumnCount());

        pdfTable.setWidthPercentage(100); 
        pdfTable.setSpacingBefore(0f);
        pdfTable.setSpacingAfter(0f);  

        PdfWriter.getInstance(document, new FileOutputStream("resultadoFinal.pdf"));
        document.open();
        Paragraph titulo = new Paragraph("Resultado final do edital " + edital.getEdital(), new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
        titulo.setAlignment(Element.ALIGN_CENTER);

        document.add(titulo);
        document.add(Chunk.NEWLINE);

        for (int i = 0; i < tabela.getColumnCount(); i++) {
            pdfTable.addCell(tabela.getColumnName(i));
        }

        for (int i = 0; i < tabela.getRowCount(); i++) {
            for (int j = 0; j < tabela.getColumnCount(); j++) {
                pdfTable.addCell(tabela.getValueAt(i, j).toString());
            }
        }

        document.add(pdfTable);
        document.close();
	}
}
