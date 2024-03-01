package com.zayar.storesystem.service.PDF;

import com.zayar.storesystem.entity.Stock;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public interface StockListPDFService {

    // Getting All Stock Details
    public  ByteArrayInputStream generatePDF(List<Stock> invoiceDetails);
}
