package com.zayar.storesystem.serviceImpl.exportImpl;

import com.zayar.storesystem.Repository.InvoiceRepository;
import com.zayar.storesystem.Repository.StockRepository;
import com.zayar.storesystem.entity.ExportDTO;
import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.service.Excel.ExportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<ExportDTO> getExportData() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<ExportDTO> exportData = new ArrayList<>();

        for (Invoice invoice : invoices) {
            List<Stock> stocks = stockRepository.findByInvoiceInvoiceId(invoice.getInvoiceId());
                for (Stock stock : stocks) {
                    ExportDTO exportDTO = new ExportDTO();
                    exportDTO.setInvoiceId(invoice.getInvoiceId());
                    exportDTO.setCashierName(invoice.getCashierName());
                    exportDTO.setDate(invoice.getDate());
                    exportDTO.setTime(invoice.getTime());
                    exportDTO.setBranch(invoice.getBranch());
                    exportDTO.setCenter(invoice.getCenter());
                    exportDTO.setStockName(stock.getName());
                    exportDTO.setStockPrice(stock.getPrice());
                    exportDTO.setStockQuantity(stock.getQuantity());
                    exportDTO.setAmount(stock.getAmount());

                    exportData.add(exportDTO);
                }
        }
        return exportData;
    }


    @Override
    public Workbook exportToExcel(List<ExportDTO> exportData) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Exported Data");
        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Invoice ID");
        headerRow.createCell(1).setCellValue("Cashier Name");
        headerRow.createCell(2).setCellValue("Date");
        headerRow.createCell(3).setCellValue("Time");
        headerRow.createCell(4).setCellValue("Branch");
        headerRow.createCell(5).setCellValue("Center");
        headerRow.createCell(6).setCellValue("Stock Name");
        headerRow.createCell(7).setCellValue("Stock Price");
        headerRow.createCell(8).setCellValue("Stock Quantity");
        headerRow.createCell(9).setCellValue("Amount");


        // Populate data rows
        int rowNum = 1;
        for (ExportDTO exportDTO : exportData) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(exportDTO.getInvoiceId());
            row.createCell(1).setCellValue(exportDTO.getCashierName());
            row.createCell(2).setCellValue(exportDTO.getDate().toString()); // Assuming date is a LocalDate
            row.createCell(3).setCellValue(exportDTO.getTime().toString()); // Assuming time is a LocalTime
            row.createCell(4).setCellValue(exportDTO.getBranch());
            row.createCell(5).setCellValue(exportDTO.getCenter());
            row.createCell(6).setCellValue(exportDTO.getStockName());
            row.createCell(7).setCellValue(exportDTO.getStockPrice());
            row.createCell(8).setCellValue(exportDTO.getStockQuantity());
            row.createCell(9).setCellValue(exportDTO.getAmount());
        }

        return workbook;
    }
}
