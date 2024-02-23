package com.zayar.storesystem.entity;

import org.apache.catalina.LifecycleState;

import java.util.List;

public class InvoiceAndStocksDTO {

    private Long invoiceId;
    private Invoice invoice;
    private List<Stock> stocks;


    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
