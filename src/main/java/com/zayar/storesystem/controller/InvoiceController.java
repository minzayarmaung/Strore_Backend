package com.zayar.storesystem.controller;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.service.Invoice.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

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

    // Soft Delete Function
    @PutMapping("/table/softDelete/{id}")
    public ResponseEntity<String> softDeleteData(@PathVariable Long id){
        invoiceService.softDelete(id);
        return ResponseEntity.ok("Entity Soft Deleted Successfully !");
    }

    // Get Invoice Data By Id
    @RequestMapping("/invoice/{id}")
    public Invoice getInvoiceDataById(@PathVariable("id") long id){
        return invoiceService.getInvoiceDataById(id);
    }

    // Updating Invoice Data
    @PutMapping("/updateInvoice/{id}")
    public String updateInvoiceData(@PathVariable("id") long id, @RequestBody Invoice invoice){
        invoiceService.updateInvoiceData(id , invoice);
        return "Invoice Data Update Successfully !...";
    }

    // Getting Stock Table with Stock Id and Stock Amount
    @GetMapping("/invoice-with-stock-details")
    public List<Object[]> getInvoiceWithStockDetails() {
        return invoiceService.getInvoiceWithStockDetails();
    }

}
