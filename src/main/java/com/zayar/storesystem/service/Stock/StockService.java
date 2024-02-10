package com.zayar.storesystem.service.Stock;

import com.zayar.storesystem.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockService {

    // Add Data to the Stock
    public Stock addStockData(Stock stock);

    // Getting Data from the Stock Data
    public List<Stock> getAllStockData();

    // Deleting Data from the Stock Data
    public void deleteStock(long id);
}
