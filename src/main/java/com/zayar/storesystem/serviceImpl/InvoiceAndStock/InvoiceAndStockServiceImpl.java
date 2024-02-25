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

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceAndStockServiceImpl implements InvoiceAndStockService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private StockRepository stockRepository;

    @Transactional
    @Override
    public ResponseEntity<?> updateInvoiceAndStock(Invoice invoice, List<Stock> stocks) {
        // Update Invoice
        Optional<Invoice> existingInvoiceOptional = invoiceRepository.findById(invoice.getInvoiceId());
        if (existingInvoiceOptional.isPresent()) {
            Invoice existingInvoice = existingInvoiceOptional.get();
            existingInvoice.setCashierName(invoice.getCashierName());
            existingInvoice.setBranch(invoice.getBranch());
            existingInvoice.setDate(invoice.getDate());
            existingInvoice.setTime(invoice.getTime());
            existingInvoice.setCenter(invoice.getCenter());

            invoiceRepository.save(existingInvoice);

            // Update Stock
            for (Stock stock : stocks) {
                Optional<Stock> existingStockOptional = stockRepository.findById(stock.getStockId());
                if (existingStockOptional.isPresent()) {
                    Stock existingStock = existingStockOptional.get();
                    existingStock.setName(stock.getName());
                    existingStock.setQuantity(stock.getQuantity());
                    existingStock.setPrice(stock.getPrice());
                    existingStock.setAmount(stock.getAmount());
                    existingStock.setStatus(stock.getStatus());

                    existingStock.setInvoice(existingInvoice);
                    stockRepository.save(existingStock);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock ID Not Found : " + stock.getStockId());
                }
            }
            return ResponseEntity.ok("Invoice and Stock Data Updated Successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice ID not Found :" + invoice.getInvoiceId());
        }
    }
}
