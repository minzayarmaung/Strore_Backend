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

    private static final int PAGE_WIDTH = 600;
    private static final int PAGE_HEIGHT = 800;
    private static final int MARGIN = 50;
    private static final int ROW_HEIGHT = 20;

    @Override
    public ByteArrayInputStream generatePDF(List<Object[]> invoiceDetails) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = null;
            int yOffset = PAGE_HEIGHT - MARGIN - ROW_HEIGHT;

            try {
                contentStream = new PDPageContentStream(document, page);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(MARGIN, PAGE_HEIGHT - MARGIN);
                contentStream.showText("Invoice ID        Cashier Name                      Date                         Branch                      Center        ");
                contentStream.endText();

                for (Object[] detail : invoiceDetails) {
                    int xOffset = MARGIN;
                    for (int i = 0; i < detail.length; i++) {
                        // Skip the third element
                        if (i == 3) {
                            continue;
                        }
                        if (i == 6){
                            continue;
                        }
                        Object data = detail[i];
                        String cellData = (data != null) ? data.toString() : ""; // Check for null
                        contentStream.beginText();
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        contentStream.newLineAtOffset(xOffset, yOffset);
                        contentStream.showText(cellData);
                        contentStream.endText();
                        xOffset += 100; // Adjust column width as needed
                    }
                    yOffset -= ROW_HEIGHT;
                    if (yOffset <= MARGIN) {
                        // Handle page break
                        contentStream.close();
                        page = new PDPage();
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        contentStream.beginText();
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                        contentStream.newLineAtOffset(MARGIN, PAGE_HEIGHT - MARGIN);
                        contentStream.showText("Invoice ID        Cashier Name        Date                Branch                Center               Stock ID");
                        contentStream.endText();
                        yOffset = PAGE_HEIGHT - MARGIN - ROW_HEIGHT;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (contentStream != null) {
                    try {
                        contentStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}