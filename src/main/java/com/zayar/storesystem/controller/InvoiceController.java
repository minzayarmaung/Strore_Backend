package com.zayar.storesystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.InvoiceAndStocksDTO;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.entity.UpdateDataDTO;
import com.zayar.storesystem.service.Invoice.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

    // Getting Available Invoice Ids
    @GetMapping("/invoice/getInvoiceIds")
    public List<Long> getAvailableInvoiceIds (){
        return invoiceService.getAvailableInvoiceIds();
    }

    @PostMapping(path = "/saveInvoiceAndStockData" , consumes = {"multipart/form-data"})
    public ResponseEntity<?> saveInvoiceAndStocks(@RequestParam ("invoiceAndStocks") String invoiceAndStocksDTOJson ,
                                                  @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws IOException {


        try{
            InvoiceAndStocksDTO invoiceAndStocksDTO = new ObjectMapper().readValue
                    (invoiceAndStocksDTOJson , InvoiceAndStocksDTO.class);

            Long invoiceId = invoiceAndStocksDTO.getInvoice().getInvoiceId();
            System.out.println("Received invoiceAndStocksDTOJson: " + invoiceAndStocksDTOJson);
            System.out.println("Controller : InvoiceID : " + invoiceId);

            if(invoiceId != null){
                invoiceAndStocksDTO.getInvoice().setInvoiceId(invoiceId);
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invoiceId is NULL");
            }

            if(profileImage != null && !profileImage.isEmpty()){
                byte[] imageData = profileImage.getBytes();
                invoiceAndStocksDTO.getInvoice().setImageData(imageData);
                System.out.println("Received Image Size : " + profileImage.getSize());
            }else {
                System.out.println("Controller : Image Data Not Receiving...");
            }
            invoiceService.saveInvoiceAndStocks(invoiceAndStocksDTO.getInvoice() , invoiceAndStocksDTO.getStocks());
            return ResponseEntity.ok("Saved Invoice And Stock Data Successfully");
        }catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occurred While Saving Invoice and Stock Data");
        }
    }
}
