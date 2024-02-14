package com.zayar.storesystem.service.Invoice;

import com.zayar.storesystem.entity.Invoice;
import jakarta.persistence.criteria.CriteriaBuilder;
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
}
