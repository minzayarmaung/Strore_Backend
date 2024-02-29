package com.zayar.storesystem.serviceImpl.PDFServiceImpl;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.service.Invoice.InvoiceService;
import com.zayar.storesystem.service.PDF.PDFService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PDFServiceImpl implements PDFService {

    @Autowired
    private InvoiceService invoiceService;

    @Override
    public byte[] generatePDF(Long invoiceId) {
        try{
            Invoice invoice = invoiceService.getInvoiceDataById(invoiceId);
            if(invoice == null){
                return null;
            }
            return createPDF(invoice);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    private byte[] createPDF(Invoice invoice) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Invoice Details");
                contentStream.endText();

                String[] detailHeaders = new String[]{
                        "Invoice ID: " + invoice.getInvoiceId(),
                        "Cashier Name: " + invoice.getCashierName(),
                        "Branch: " + invoice.getBranch(),
                        "Date: " + invoice.getDate(),
                        "Time: " + invoice.getTime()
                        // Center not used
                };

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 680);
                for (String header : detailHeaders) {
                    contentStream.showText(header);
                    contentStream.newLineAtOffset(0, -15);
                }
                contentStream.showText("-------------------------------------------------------------------------------------------");
                contentStream.endText();

                float startY = 590;
                drawTableHeader(contentStream, startY, 100, new String[]{"Name", "Quantity", "Price", "Amount"});
                startY -= 30;

                float rowHeight = 20;
                float nextY = startY;

                for (Stock stock : invoice.getStocks()) {
                    String[] stockDetails = {
                            stock.getName(),
                            String.valueOf(stock.getQuantity()),
                            String.format("%.2f", stock.getPrice()),
                            String.format("%.2f", stock.getAmount())
                    };
                    drawTableRow(contentStream, nextY, stockDetails);
                    nextY -= rowHeight;
                }
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        }
    }

    private void drawTableHeader(PDPageContentStream contentStream, float y, float margin, String[] headers) throws IOException {
        float colWidth = 100;
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, y);
        for (String header : headers) {
            contentStream.showText(header);
            contentStream.newLineAtOffset(colWidth, 0);
        }
        contentStream.endText();
    }

    private void drawTableRow(PDPageContentStream contentStream, float y, String[] rowContent) throws IOException {
        float xPosition = 100;
        float colWidth = 100;
        contentStream.setFont(PDType1Font.HELVETICA, 12);

        for (String cell : rowContent) {
            contentStream.beginText();
            contentStream.newLineAtOffset(xPosition, y);
            contentStream.showText(cell);
            contentStream.endText();
            xPosition += colWidth;
        }
    }
}