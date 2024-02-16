package com.zayar.storesystem.entity;

public class ExportDTO {

    private long invoiceId;
    private String cashierName;
    private String date;
    private String time;
    private String branch;
    private String center;
    private String stockName;
    private Float stockPrice;
    private int stockQuantity;

    public ExportDTO(long invoiceId, String cashierName, String date, String time, String branch, String center, String stockName, Float stockPrice, int stockQuantity) {
        this.invoiceId = invoiceId;
        this.cashierName = cashierName;
        this.date = date;
        this.time = time;
        this.branch = branch;
        this.center = center;
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockQuantity = stockQuantity;
    }

    public ExportDTO() {
    }

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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}