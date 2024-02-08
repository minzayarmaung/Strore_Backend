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

    @Override
    public Stock addStockData(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> getAllStockData() {
        return (List<Stock>) stockRepository.findAll();
    }
}
