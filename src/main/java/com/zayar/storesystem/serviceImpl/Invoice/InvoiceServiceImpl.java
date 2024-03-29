package com.zayar.storesystem.serviceImpl.Invoice;

import com.zayar.storesystem.Repository.InvoiceRepository;
import com.zayar.storesystem.Repository.StockRepository;
import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.Stock;
import com.zayar.storesystem.service.Invoice.InvoiceService;
import com.zayar.storesystem.service.Stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    @Override
    public Invoice addInvoiceData(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoiceData() {
        return (List<Invoice>) invoiceRepository.findAll();
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    // Soft Delete
    @Override
    public void softDelete(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if(invoice != null){
            invoice.setStatus("inactive");
            invoiceRepository.save(invoice);
        }
    }


    // Get By ID
    @Override
    public Invoice getInvoiceDataById(Long id) {
        return invoiceRepository.findById(id).get();
    }

    // Updating Invoice Data
    @Override
    public Invoice updateInvoiceData(Long id, Invoice invoice) {
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(id);
        invoice1.setCashierName(invoice.getCashierName());
        invoice1.setBranch(invoice.getBranch());
        invoice1.setDate(invoice.getDate());
        invoice1.setTime(invoice.getTime());
        invoice1.setCenter(invoice.getCenter());

        String imagePath = generateImagePath(id);
        invoice1.setImagePath(imagePath);

        return invoiceRepository.save(invoice1);
    }
    private String generateImagePath(Long invoiceId){
        return "profileImage_ " + invoiceId + " .jpg";
    }

    // Getting Invoice table with Stock Id and Stock Amount
    @Override
    public List<Object[]> getInvoiceWithStockDetails() {
        return invoiceRepository.getInvoiceWithStockDetails();
    }

    @Override
    public List<Long> getAvailableInvoiceIds() {
        List<Long> availableInvoiceIds = invoiceRepository.findAvailableInvoiceIds();
        return availableInvoiceIds;
    }

    @Override
    public void saveInvoiceAndStocks(Invoice invoice, List<Stock> stocks) {
        Invoice savedInvoice = invoiceRepository.save(invoice);
        for (Stock stock : stocks){
            stock.setInvoice(savedInvoice);
            //stockRepository.save(stock);
            stock.setAmount(stock.getQuantity() * stock.getPrice());
            stockRepository.save(stock);
        }
    }
}
