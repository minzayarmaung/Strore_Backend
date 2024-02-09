package com.zayar.storesystem.entity;


import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

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

    public long getInvoiceId() {
        return invoiceId;
    }

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

    public Invoice() {
    }

    public Invoice(String cashierName, String branch, String date, String time, String center) {
        this.cashierName = cashierName;
        this.branch = branch;
        this.date = date;
        this.time = time;
        this.center = center;
    }
}

