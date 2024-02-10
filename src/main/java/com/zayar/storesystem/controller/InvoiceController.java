package com.zayar.storesystem.controller;

import com.zayar.storesystem.Repository.InvoiceRepository;
import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.service.Invoice.InvoiceService;
import com.zayar.storesystem.serviceImpl.Invoice.InvoiceServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    // Saving Invoice Data
    @PostMapping("/save/invoiceData")
    public String saveInvoiceData(@RequestBody Invoice invoice){
        invoiceService.addInvoiceData(invoice);
        return "Invoice Data added Successfully !";
    }

    // Getting Invoice Data
    @GetMapping("/all_invoice_data")
    public List<Invoice> getInvoiceData(){
        System.out.println("All Invoice Data : ");
        return invoiceService.getAllInvoiceData();
    }

    // Deleting Invoice
}
