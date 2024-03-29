package com.zayar.storesystem.service.Invoice;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {

    // Adding Data to Invoice
    public Invoice addInvoiceData(Invoice invoice);

    // Getting Invoice Data
    public List<Invoice> getAllInvoiceData();

    // Deleting Invoice Data
    public void deleteInvoice(Long id);

    // Soft Delete
    public void softDelete(Long id);

    // Get By Id
    public Invoice getInvoiceDataById(Long id);

    // Update Invoice Data
    public Invoice updateInvoiceData(Long id , Invoice invoice);

    // Get Stock Id and Stock Amount
    public List<Object[]> getInvoiceWithStockDetails();

    // Getting Available Invoice Ids
    public List<Long> getAvailableInvoiceIds();


    // Saving Both Invoice and Stock Data
    public void saveInvoiceAndStocks(Invoice invoice , List<Stock> stocks);
}