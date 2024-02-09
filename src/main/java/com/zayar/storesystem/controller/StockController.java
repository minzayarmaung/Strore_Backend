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

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/save/stockData")
    public String saveStockData(@RequestBody Stock stock){
        stockService.addStockData(stock);
        return "Stock Data Added Successfully";
    }

    @GetMapping("/all_stock_data")
    public List<Stock> getStockData(){
        System.out.println("All Stock Data : ");
        return stockService.getAllStockData();
    }

}
