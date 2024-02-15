package com.zayar.storesystem.serviceImpl.Invoice;

import com.zayar.storesystem.Repository.InvoiceRepository;
import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.service.Invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice addInvoiceData(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoiceData() {
        return (List<Invoice>) invoiceRepository.findAll();
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    // Soft Delete
    @Override
    public void softDelete(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if(invoice != null){
            invoice.setStatus("inactive");
            invoiceRepository.save(invoice);
        }
    }


    // Get By ID
    @Override
    public Invoice getInvoiceDataById(Long id) {
        return invoiceRepository.findById(id).get();
    }

    // Updating Invoice Data
    @Override
    public Invoice updateInvoiceData(Long id, Invoice invoice) {
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(id);
        invoice1.setCashierName(invoice.getCashierName());
        invoice1.setBranch(invoice.getBranch());
        invoice1.setDate(invoice.getDate());
        invoice1.setTime(invoice.getTime());
        invoice1.setCenter(invoice.getCenter());
        return invoiceRepository.save(invoice1);
    }
}
