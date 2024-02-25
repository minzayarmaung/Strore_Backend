package com.zayar.storesystem.controller;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.InvoiceAndStocksDTO;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.service.InvoiceAndStock.InvoiceAndStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class InvoiceAndStockController {

    @Autowired
    private InvoiceAndStockService invoiceAndStockService;

    @PutMapping("/updateInvoiceAndStock/{id}")
    public ResponseEntity<?> updateInvoiceAndStock(@PathVariable("id") Long id, @RequestBody InvoiceAndStocksDTO dto) {
        System.out.println("Controller - Updating invoice ID: " + id);
        System.out.println("Controller - Invoice Data : " + dto.getInvoice());
        System.out.println("Controller - Stock Data : " + dto.getStocks());
        Invoice invoice = dto.getInvoice();
        invoice.setInvoiceId(id);
        List<Stock> stocks = invoice.getStocks();

        invoiceAndStockService.updateInvoiceAndStock(invoice, stocks);

        return ResponseEntity.ok("Invoice and Stock Data Updated Successfully..");
    }
}
