package com.zayar.storesystem.controller;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.InvoiceAndStocksDTO;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.entity.UpdateDataDTO;
import com.zayar.storesystem.service.Invoice.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    // Getting Available Invoice Ids
    @GetMapping("/invoice/getInvoiceIds")
    public List<Long> getAvailableInvoiceIds (){
        return invoiceService.getAvailableInvoiceIds();
    }

    // Updating Both Invoice and Stock Data
    @PutMapping("/updateInvoiceAndStock/{invoiceId}")
    public ResponseEntity<String> updateInvoiceAndStockData(@PathVariable long invoiceId, @RequestBody UpdateDataDTO updateData){
        Invoice updatedInvoice = updateData.getUpdatedInvoice();
        List<Stock> updatedStocks = updateData.getUpdatedStocks();

        invoiceService.updateInvoiceAndStockData(invoiceId , updatedInvoice , updatedStocks);

        return ResponseEntity.ok("Invoice and Stock Updated Successfully");
    }

    @PostMapping("/saveInvoiceAndStockData")
    public ResponseEntity<?> saveInvoiceAndStocks(@RequestBody InvoiceAndStocksDTO invoiceAndStocksDTO){
        try{
            Long invoiceId = invoiceAndStocksDTO.getInvoiceId();
            if(invoiceId != null){
                invoiceAndStocksDTO.getInvoice().setInvoiceId(invoiceId);
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invoiceId is NULL");
            }
            invoiceService.saveInvoiceAndStocks(invoiceAndStocksDTO.getInvoice() , invoiceAndStocksDTO.getStocks());
            return ResponseEntity.ok("Saved Invoice And Stock Data Successfully");
        }catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occurred While Saving Invoice and Stock Data");
        }
    }
}
