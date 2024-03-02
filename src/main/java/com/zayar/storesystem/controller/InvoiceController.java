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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            // JSON TO DTO
            InvoiceAndStocksDTO invoiceAndStocksDTO = new ObjectMapper()
                    .readValue(invoiceAndStocksDTOJson , InvoiceAndStocksDTO.class);

            if(invoiceAndStocksDTO.getInvoice().getInvoiceId() == 0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invoice ID is NULL..");
            }
            // Save Profile Image
            if(profileImage != null && !profileImage.isEmpty()){
                String directoryPath = "E:\\Development\\MIT Assignments\\Strore_Backend\\src\\main\\java\\com\\zayar\\storesystem\\images";

                String filename = "ProfileImage_ " + invoiceAndStocksDTO.getInvoice().getInvoiceId() +".jpg";
                Path path = Paths.get(directoryPath, filename);

                Files.createDirectories(path.getParent());

                Files.copy(profileImage.getInputStream() , path , StandardCopyOption.REPLACE_EXISTING);

                invoiceAndStocksDTO.getInvoice().setImagePath(filename);
            }else {
                System.out.println("Controller : No Profile Image Received.");
            }
            // Saving Invoice and Stock Data
            invoiceService.saveInvoiceAndStocks(invoiceAndStocksDTO.getInvoice() , invoiceAndStocksDTO.getStocks());
            return ResponseEntity.ok("Saved Invoice and Stock Data Successfully.");
        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Proccessing Image Upload");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occurred While Saving Invoice and Stock Data");
        }

    }
}
