package com.zayar.storesystem.controller;

import com.zayar.storesystem.Repository.InvoiceRepository;
import com.zayar.storesystem.Repository.StockRepository;
import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/excel")
public class ImportExcelController {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private StockRepository stockRepository;

    @PostMapping("/importData")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {

        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());

            Sheet sheet = workbook.getSheetAt(0);



            Iterator<Row> rowIterator = sheet.iterator();
            if(rowIterator.hasNext()){
                rowIterator.next(); // Skipping Header Row
            }
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();

                Invoice invoice = new Invoice();
                Stock stock = new Stock();

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    switch (cell.getCellType()){
                        case STRING :
                            String stringValue = cell.getStringCellValue();
                            if(columnIndex == 1){
                                invoice.setCashierName(stringValue);
                            } else if (columnIndex == 4) {
                                invoice.setBranch(stringValue);
                            } else if (columnIndex == 2) {
                                invoice.setDate(stringValue);
                            } else if (columnIndex == 3) {
                                invoice.setTime(stringValue);
                            } else if (columnIndex == 5) {
                                invoice.setCenter(stringValue);
                            }else if (columnIndex == 6){
                                stock.setName(stringValue);
                            }
                            break;
                        case NUMERIC:
                            double numericValue = cell.getNumericCellValue();
                            if (columnIndex == 9) {
                                stock.setAmount((float) numericValue);
                            } else if (columnIndex == 7) {
                                stock.setPrice((float) numericValue);
                            } else if (columnIndex == 8) {
                                stock.setQuantity((int) numericValue);
                            }
                            break;
                        default:
                            System.out.println("Default");
                            break;
                    }
                }
                invoice = invoiceRepository.save(invoice);
                stock.setInvoice(invoice);
                stockRepository.save(stock);

            }

            workbook.close();
            return ResponseEntity.ok("File Imported Successfully...");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Upload File " + e.getMessage());
        }
    }
}

