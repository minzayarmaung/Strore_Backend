package com.zayar.storesystem.service.Stock;

import com.zayar.storesystem.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public interface StockService {

    // Add Data to the Stock
    public Stock addStockData(Stock stock);

    // Getting Data from the Stock Data
    public List<Stock> getAllStockData();

    // Deleting Data from the Stock Data
    public void deleteStock(long id);

    // Soft Delete from the Stock Data
    public void softDelete(long id);

    //Get Stock By Id
    public Stock getStockData(long id);

    // Updating Data from the Stock Data
    public Stock updateStockData(Long id , Stock stock);

    // Updating Stock Data
    public void updateStock(Stock stock);

    // Getting Available Stock Ids
    public List<Long> getAvailableStockIds();

//    // Getting StockId and Stock Amount
//    public List<Object[]> getStockIdAndAmountByInvoiceId(Long invoiceId);
}
