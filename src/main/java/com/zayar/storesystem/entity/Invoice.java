package com.zayar.storesystem.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long invoiceId;

    private String cashierName;

    private String branch;

    private String date;

    private String time;

    private String center;

    @Column(name = "status")
    private String status;

    @Lob
    @Column(name = "image_data" , length = 5000000)
    private byte[] imageData;

    public long getInvoiceId() {
        return invoiceId;
    }

    //@JsonManagedReference
    @OneToMany(mappedBy = "invoice")
    private List<Stock> stocks;

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Invoice() {
    }

    public Invoice(long invoiceId, String cashierName, String branch, String date, String time, String center, String status, byte[] imageData, List<Stock> stocks) {
        this.invoiceId = invoiceId;
        this.cashierName = cashierName;
        this.branch = branch;
        this.date = date;
        this.time = time;
        this.center = center;
        this.status = status;
        this.imageData = imageData;
        this.stocks = stocks;
    }
}

