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
}
