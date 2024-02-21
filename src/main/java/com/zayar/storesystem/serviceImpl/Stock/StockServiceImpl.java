package com.zayar.storesystem.serviceImpl.Stock;

import com.zayar.storesystem.Repository.StockRepository;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.service.Stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    // Saving Stock Data
    @Override
    public Stock addStockData(Stock stock) {
        Stock newStock =new Stock();
        newStock.setInvoice(stock.getInvoice());
        newStock.setName(stock.getName());
        newStock.setPrice(stock.getPrice());
        newStock.setQuantity(stock.getQuantity());
        newStock.setAmount(stock.getAmount());
        return stockRepository.save(newStock);
    }

    // Getting Stock Data
    @Override
    public List<Stock> getAllStockData() {
        return (List<Stock>) stockRepository.findAll();
    }

    // Deleting Stock Data
    @Override
    public void deleteStock(long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public void softDelete(long id) {
        Stock stock = stockRepository.findById(id).orElse(null);
        if(stock != null){
            stock.setStatus("inactive");
            stockRepository.save(stock);
        }
    }

    @Override
    public Stock getStockData(long id) {
        return stockRepository.findById(id).get();
    }

    @Override
    public Stock updateStockData(Long id, Stock stock) {
        Stock stock1 = new Stock();
        stock1.setStockId(id);
        stock1.setName(stock.getName());
        stock1.setPrice(stock.getPrice());
        stock1.setQuantity(stock.getQuantity());
        stock1.setAmount(stock.getAmount());

        return stockRepository.save(stock1);
    }

    @Override
    public List<Long> getAvailableStockIds() {
        List<Long> availableStockIds = stockRepository.findAvailableStockIds();
        return availableStockIds;
    }

//    @Override
//    public List<Object[]> getStockIdAndAmountByInvoiceId(Long invoiceId) {
//        return stockRepository.findStockIdAndAmountByInvoiceId(invoiceId);
//    }
}
