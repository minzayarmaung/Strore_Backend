package com.zayar.storesystem.entity;

import org.apache.catalina.LifecycleState;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class InvoiceAndStocksDTO {

    private long invoiceId;
    private Invoice invoice;
    private List<Stock> stocks;
    private MultipartFile profileImage;


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

    public MultipartFile getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartFile profileImage) {
        this.profileImage = profileImage;
    }
}
