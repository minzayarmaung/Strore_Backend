package com.zayar.storesystem.serviceImpl.importImpl;

import com.zayar.storesystem.Repository.InvoiceRepository;
import com.zayar.storesystem.Repository.StockRepository;
import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.service.Excel.ImportService;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ImportServiceImpl implements ImportService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Invoice> readInvoices(InputStream inputStream) throws IOException {
        List<Invoice> invoices = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();

            if(row.getRowNum() == 0){
                continue;
            }
            Invoice invoice  = new Invoice();

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex){
                    case 4:
                        invoice.setCashierName(cell.getStringCellValue());
                        break;
                    case 3:
                        invoice.setBranch(cell.getStringCellValue());
                        break;
                    case 2:
                        invoice.setDate(cell.getStringCellValue());
                        break;
                    case 1:
                        invoice.setTime(cell.getStringCellValue());
                        break;
                }
            }
            invoices.add(invoice);
        }
        invoiceRepository.saveAll(invoices);
        workbook.close();
        return invoices;
    }

    @Override
    public List<Stock> readStocks(InputStream inputStream) throws IOException {
        List<Stock> stocks = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.getSheetAt(1);

        Iterator<Row> rowIterator = sheet.rowIterator();
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();

            if(row.getRowNum() == 0){
                continue;
            }

            Stock stock = new Stock();

            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();

                switch (columnIndex){
                    case 0:
                        stock.setStockId((long) cell.getNumericCellValue());
                        break;
                    case 1:
                        stock.setName(cell.getStringCellValue());
                        break;
                    case 2:
                        stock.setPrice((float) cell.getNumericCellValue());
                        break;
                    case 3:
                        stock.setQuantity((int) cell.getNumericCellValue());
                        break;
                    case 4:
                        stock.setAmount((float) cell.getNumericCellValue());
                        break;
                }
            }
            stocks.add(stock);
        }
        stockRepository.saveAll(stocks);
        workbook.close();
        return stocks;
    }
}
