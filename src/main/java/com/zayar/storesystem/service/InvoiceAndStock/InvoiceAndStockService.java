package com.zayar.storesystem.service.InvoiceAndStock;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceAndStockService {

    // Updating both Data
    public ResponseEntity<?> updateInvoiceAndStock(Invoice invoice, List<Stock> stocks);

}
