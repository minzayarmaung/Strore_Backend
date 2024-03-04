package com.zayar.storesystem.serviceImpl.PDFServiceImpl;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.service.PDF.InvoiceListPDFService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class InvoiceListPDFServiceImpl implements InvoiceListPDFService {

    @Override
    public ByteArrayInputStream generatePDF(List<Invoice> invoiceDetails) throws IOException {
        System.out.println(" Invoice Details Size : "+invoiceDetails.size());
        try (PDDocument document = new PDDocument()) {
            PDPage firstPage = new PDPage(PDRectangle.A4);
            document.addPage(firstPage);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, firstPage)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Invoice List");
                contentStream.endText();

                // Setting Invoice Info Text
                String[] infoTexts = {"MIT Company", "Insein Township, Yangon", "Myanmar", "mit.com.mm"};
                int yPos = 720;
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                for (String text : infoTexts) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, yPos);
                    contentStream.showText(text);
                    contentStream.endText();
                    yPos -= 15;
                }
                // Drawing table
                drawTable(contentStream, 50, 660, invoiceDetails);
                contentStream.close();
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private void drawTable(PDPageContentStream contentStream, int startX, int startY, List<Invoice> invoiceDetails) throws IOException {
        final int cellHeight = 30;
        final int cellWidth = 100;
        final int colCount = 5;
        final String[] headers = {"Invoice ID", "Cashier Name", "Date", "Branch", "Center"};

        contentStream.setFont(PDType1Font.HELVETICA, 10);

        // Draw Header
        int headerY = startY;
        for (String header : headers) {
            contentStream.beginText();
            contentStream.newLineAtOffset(startX + 10, headerY - 20);
            contentStream.showText(header);
            contentStream.endText();
            startX += cellWidth;
        }

        startY -= cellHeight; // Move to next row position after header

        // Reset startX to initial position after header row
        startX -= cellWidth * headers.length;

        // Draw Rows
        for (Invoice row : invoiceDetails) {
            if(startY < 50){
                contentStream.close();
                PDPage newPage = new PDPage(PDRectangle.A4);
                PDDocument document = new PDDocument();
                document.addPage(newPage);
                contentStream = new PDPageContentStream(document , newPage);
                contentStream.setFont(PDType1Font.HELVETICA , 10);
                startY = 750;
            }
                startX = 50;
                String[] rowData = {
                        String.valueOf(row.getInvoiceId()),
                        row.getCashierName(),
                        row.getDate(),
                        row.getBranch(),
                        row.getCenter()
                };

            for (int j = 0; j < colCount; j++) {
                contentStream.addRect(startX, startY, cellWidth, cellHeight);
                contentStream.beginText();
                contentStream.newLineAtOffset(startX + 10, startY - 20); // Adjust text positioning as needed
                contentStream.showText(rowData[j]);
                contentStream.endText();
                startX += cellWidth;
            }
            startY -= cellHeight; // Move to next row position
        }
        contentStream.stroke();
    }
}