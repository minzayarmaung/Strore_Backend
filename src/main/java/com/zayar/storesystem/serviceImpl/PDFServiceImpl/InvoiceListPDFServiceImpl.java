package com.zayar.storesystem.serviceImpl.PDFServiceImpl;

import com.zayar.storesystem.service.PDF.InvoiceListPDFService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class InvoiceListPDFServiceImpl implements InvoiceListPDFService {

    @Override
    public ByteArrayInputStream generatePDF(List<Object[]> invoiceDetails) {
        try(PDDocument document = new PDDocument()){
            PDPage page = new PDPage();
            document.addPage(page);

            try(PDPageContentStream contentStream = new PDPageContentStream(document , page)){
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD , 12);
                contentStream.newLineAtOffset(100 , 700);
                contentStream.showText("Invoice with Stock Details");
                contentStream.endText();

                int yOffset = 680;
                for(Object[] detail : invoiceDetails){
                    String line = "Stock ID: "+ detail[0] + ", Stock Amount: "+ detail[1];
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA , 12);
                    contentStream.newLineAtOffset(100 , yOffset);
                    yOffset -= 20;
                    contentStream.showText(line);
                    contentStream.endText();
                }
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            return new ByteArrayInputStream(out.toByteArray());
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
