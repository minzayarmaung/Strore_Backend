package com.zayar.storesystem.controller;

import com.zayar.storesystem.Repository.InvoiceRepository;
import com.zayar.storesystem.Repository.StockRepository;
import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.service.Stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    // Saving Stock Data
    @PostMapping("/save/stockData")
    public String saveStockData(@RequestBody List<Stock> stocks){
        for (Stock stock : stocks) {
            // Set the amount for each stock before saving
            Optional<Invoice> existingInvoiceOptional =
                    invoiceRepository.findById(stock.getInvoice().getInvoiceId());
            if(existingInvoiceOptional.isPresent()){
                stock.setInvoice(existingInvoiceOptional.get());
            }else {
                throw new IllegalArgumentException
                        ("Invoice with id" + stock.getInvoice().getInvoiceId() + " does not exist.");
            }

            stock.setAmount(stock.getQuantity() * stock.getPrice());
            stockService.addStockData(stock);
        }
        return "Stock Data Added Successfully";
    }

    // Getting Stock Data
    @GetMapping("/view_stocks")
    public List<Stock> getStockData(){
        System.out.println("All Stock Data : ");
        return stockService.getAllStockData();
    }

    // Deleting Stock Data
    @DeleteMapping("/deleteStockData/{id}")
    public String deleteStockData(@PathVariable("id") long id){
        stockService.deleteStock(id);
        return "Stock Data Deleted Successfully !";
    }

    // Soft Delete Stock Data
    @PutMapping("stock/softDelete/{id}")
    public ResponseEntity<String> softDeleteData(@PathVariable Long id){
        stockService.softDelete(id);
        return ResponseEntity.ok("Entity Soft Deleted Successfully !");
    }

    // Get Stock By ID
    @RequestMapping("/stock/{id}")
    public Stock getStockById(@PathVariable("id") long id){
        return stockService.getStockData(id);
    }

    // Updating Stock Data
    @PutMapping("/updateStock/{id}")
    public String updateStockData(@PathVariable("id") long id , @RequestBody Stock stock){
        stockService.updateStockData(id , stock);
        return "Stock Data Updated Successfully...";
    }

    // Getting Available Stock Ids
    @GetMapping("/stocks/getStockIds")
    public List<Long> getAvailableStockIds(){
        return stockService.getAvailableStockIds();
    }

//    // Getting Stock Id and Stock Amount by Invoice Id
//    @GetMapping("/invoice/{invoiceId}/stock")
//    public List<Object[]> getStockIdAndAmountByInvoiceId(@PathVariable Long invoiceId){
//        return stockService.getStockIdAndAmountByInvoiceId(invoiceId);
//    }
}
