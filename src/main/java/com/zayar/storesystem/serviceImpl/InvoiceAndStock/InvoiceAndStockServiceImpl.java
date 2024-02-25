package com.zayar.storesystem.serviceImpl.InvoiceAndStock;

import com.zayar.storesystem.Repository.InvoiceRepository;
import com.zayar.storesystem.Repository.StockRepository;
import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.service.InvoiceAndStock.InvoiceAndStockService;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceAndStockServiceImpl implements InvoiceAndStockService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private StockRepository stockRepository;

    @Transactional
    @Override
    public ResponseEntity<?> updateInvoiceAndStock(Invoice invoice, List<Stock> stocks) {
        System.out.println("Service - Received invoice ID for update: " + invoice.getInvoiceId());
        System.out.println("Service - Invoice data: " + invoice.toString());
        System.out.println("Service - Stock data : " + stocks.toString());
        // Update Invoice
        Invoice existingInvoice = invoiceRepository.findById(invoice.getInvoiceId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Invoice ID Not Found : " + invoice.getInvoiceId()));

        existingInvoice.setCashierName(invoice.getCashierName());
        existingInvoice.setBranch(invoice.getBranch());
        existingInvoice.setDate(invoice.getDate());
        existingInvoice.setTime(invoice.getTime());
        existingInvoice.setCenter(invoice.getCenter());

        invoiceRepository.save(existingInvoice);

        if (stocks != null && !stocks.isEmpty()) {
            List<String> notFoundStockIds = new ArrayList<>();

            for (Stock stock : stocks) {
                stockRepository.findById(stock.getStockId()).ifPresentOrElse(existingStock -> {
                    existingStock.setName(stock.getName());
                    existingStock.setQuantity(stock.getQuantity());
                    existingStock.setPrice(stock.getPrice());
                    existingStock.setAmount(stock.getAmount());
                    existingStock.setStatus(stock.getStatus());
                    // Assuming the invoice is already updated and linked
                    stockRepository.save(existingStock);
                }, () -> notFoundStockIds.add(String.valueOf(stock.getStockId())));
            }

            if (!notFoundStockIds.isEmpty()) {
                // Handle the error scenario, maybe logging or sending a specific response
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Some stock IDs not found: " + String.join(", ", notFoundStockIds));
            }
        } else {
            System.out.println("Stock list is empty or null.");
        }

        return ResponseEntity.ok("Invoice and Stock Data Updated Successfully!");
    }
}