package com.zayar.storesystem.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "result")
public class ResultTable {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Invoice invoice;
    private String cashierName;
    private Date date;
    private Time time;
    private String branch;
    private String center;

    public ResultTable() {
    }

    public ResultTable(Long id, Invoice invoice, String cashierName, Date date, Time time, String branch, String center) {
        this.id = id;
        this.invoice = invoice;
        this.cashierName = cashierName;
        this.date = date;
        this.time = time;
        this.branch = branch;
        this.center = center;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
}
