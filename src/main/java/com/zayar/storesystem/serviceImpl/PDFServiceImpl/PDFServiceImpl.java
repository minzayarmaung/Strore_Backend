package com.zayar.storesystem.serviceImpl.PDFServiceImpl;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.service.Image.ImageService;
import com.zayar.storesystem.service.Invoice.InvoiceService;
import com.zayar.storesystem.service.PDF.PDFService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PDFServiceImpl implements PDFService {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ImageService imageService;

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

                byte[] imageBytes = imageService.getImageByInvoiceId(invoice.getInvoiceId());

                if(imageBytes != null){
                    PDImageXObject pdImage = PDImageXObject.createFromByteArray(document , imageBytes  , null);

                    contentStream.saveGraphicsState();

                    float centerX = 440;
                    float centerY = 670;
                    float radius = 50;

                    contentStream.moveTo(centerX + radius , centerY);

                    // Drawing the Circle Using Curves
                    final float kappa = 0.552284749831F;

                    contentStream.curveTo(centerX + radius, centerY + kappa * radius,
                            centerX + kappa * radius, centerY + radius,
                            centerX, centerY + radius);
                    contentStream.curveTo(centerX - kappa * radius, centerY + radius,
                            centerX - radius, centerY + kappa * radius,
                            centerX - radius, centerY);
                    contentStream.curveTo(centerX - radius, centerY - kappa * radius,
                            centerX - kappa * radius, centerY - radius,
                            centerX, centerY - radius);
                    contentStream.curveTo(centerX + kappa * radius, centerY - radius,
                            centerX + radius, centerY - kappa * radius,
                            centerX + radius, centerY);

                    // Close the path to complete the circle
                    contentStream.closePath();

                    // Clip to the path
                    contentStream.clip();

                    // Draw the image
                    contentStream.drawImage(pdImage, centerX - radius, centerY - radius, 2 * radius, 2 * radius);

                    // Restore the graphics state to remove the clipping path
                    contentStream.restoreGraphicsState();
                }

                String[] detailHeaders = new String[]{
                        "Invoice ID: " + invoice.getInvoiceId(),
                        "Cashier Name: " + invoice.getCashierName(),
                        "Branch: " + invoice.getBranch(),
                        "Date: " + invoice.getDate(),
                        "Time: " + invoice.getTime()
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