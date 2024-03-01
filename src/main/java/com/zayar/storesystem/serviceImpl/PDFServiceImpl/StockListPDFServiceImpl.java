package com.zayar.storesystem.serviceImpl.PDFServiceImpl;

import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.service.PDF.StockListPDFService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class StockListPDFServiceImpl implements StockListPDFService {

    private static final int PAGE_WIDTH = 600;
    private static final int PAGE_HEIGHT = 800;
    private static final int MARGIN = 50;
    private static final int ROW_HEIGHT = 20;
    private static final int COLUMN_WIDTH = (PAGE_WIDTH - 2 * MARGIN) / 4; // Adjust based on the number of columns

    @Override
    public ByteArrayInputStream generatePDF(List<Stock> stockDetails) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = null;
            int yOffset = PAGE_HEIGHT - MARGIN - ROW_HEIGHT;

            try {
                contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                drawTableHeader(contentStream);

                for (Stock stock : stockDetails) {
                    drawTableRow(contentStream, stock, yOffset);
                    yOffset -= ROW_HEIGHT;
                    if (yOffset <= MARGIN) {
                        contentStream.close();
                        page = new PDPage();
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                        drawTableHeader(contentStream);
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

    private void drawTableHeader(PDPageContentStream contentStream) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(MARGIN, PAGE_HEIGHT - MARGIN);
        contentStream.showText("ID");
        contentStream.newLineAtOffset(COLUMN_WIDTH, 0);
        contentStream.showText("Name");
        contentStream.newLineAtOffset(COLUMN_WIDTH, 0);
        contentStream.showText("Quantity");
        contentStream.newLineAtOffset(COLUMN_WIDTH, 0);
        contentStream.showText("Price");
        contentStream.endText();
    }

    private void drawTableRow(PDPageContentStream contentStream, Stock stock, int yOffset) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(MARGIN, yOffset);
        contentStream.showText(String.valueOf(stock.getStockId()));
        contentStream.newLineAtOffset(COLUMN_WIDTH, 0);
        contentStream.showText(stock.getName());
        contentStream.newLineAtOffset(COLUMN_WIDTH, 0);
        contentStream.showText(String.valueOf(stock.getQuantity()));
        contentStream.newLineAtOffset(COLUMN_WIDTH, 0);
        contentStream.showText(String.valueOf(stock.getPrice()));
        contentStream.endText();
    }
}