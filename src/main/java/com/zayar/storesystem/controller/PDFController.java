package com.zayar.storesystem.controller;

import com.zayar.storesystem.service.Invoice.InvoiceService;
import com.zayar.storesystem.service.PDF.InvoiceListPDFService;
import com.zayar.storesystem.service.PDF.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class PDFController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private PDFService pdfService;

    @Autowired
    private InvoiceListPDFService invoiceListPDFService;

    @GetMapping("/pdf/generatePDFById/{invoiceId}")
    public ResponseEntity<byte[]> generatePDF(@PathVariable Long invoiceId){
        byte[] pdfBytes = pdfService.generatePDF(invoiceId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("GettingDataByID" , "invoice_ID :" + invoiceId + ".pdf");
        return new ResponseEntity<>(pdfBytes , headers , HttpStatus.OK);
    }

    @GetMapping("/pdf/generatePDFInvoiceList")
    public ResponseEntity<InputStreamResource> downloadInvoicePdf(){
        List<Object[]> invoiceDetails = invoiceService.getInvoiceWithStockDetails();
        ByteArrayInputStream bis = invoiceListPDFService.generatePDF(invoiceDetails);

        HttpHeaders headers = new HttpHeaders();

        return ResponseEntity.ok().headers(headers)
                .contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }
}
