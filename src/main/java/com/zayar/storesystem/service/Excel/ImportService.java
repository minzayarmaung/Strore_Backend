package com.zayar.storesystem.service.Excel;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public interface ImportService {

    public List<Invoice> readInvoices(InputStream inputStream) throws IOException;

    public List<Stock> readStocks(InputStream inputStream) throws IOException;
}
