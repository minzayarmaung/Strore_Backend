package com.zayar.storesystem.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stockId;

    @JsonIgnoreProperties("stocks")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    private String name;
    private int quantity;
    private float price;
    private float amount;
    private String status;

    public Stock() {
    }

    public Stock(long stockId, Invoice invoice, String name, int quantity, float price, float amount, String status) {
        this.stockId = stockId;
        this.invoice = invoice;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
        this.status = status;
    }

    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = this.quantity * this.price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}