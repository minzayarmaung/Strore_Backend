package com.zayar.storesystem.controller;

import com.zayar.storesystem.service.PDF.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @GetMapping("/pdf/generatePDFById/{invoiceId}")
    public ResponseEntity<byte[]> generatePDF(@PathVariable Long invoiceId){
        byte[] pdfBytes = pdfService.generatePDF(invoiceId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("GettingDataByID" , "invoice_ID :" + invoiceId + ".pdf");
        return new ResponseEntity<>(pdfBytes , headers , HttpStatus.OK);
    }
}
