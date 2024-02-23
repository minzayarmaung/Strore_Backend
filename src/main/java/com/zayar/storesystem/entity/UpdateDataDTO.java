package com.zayar.storesystem.entity;

import java.util.List;

public class UpdateDataDTO {
    private Invoice updatedInvoice;
    private List<Stock> updatedStocks;

    public UpdateDataDTO() {
    }

    public UpdateDataDTO(Invoice updatedInvoice, List<Stock> updatedStocks) {
        this.updatedInvoice = updatedInvoice;
        this.updatedStocks = updatedStocks;
    }

    public Invoice getUpdatedInvoice() {
        return updatedInvoice;
    }

    public void setUpdatedInvoice(Invoice updatedInvoice) {
        this.updatedInvoice = updatedInvoice;
    }

    public List<Stock> getUpdatedStocks() {
        return updatedStocks;
    }

    public void setUpdatedStocks(List<Stock> updatedStocks) {
        this.updatedStocks = updatedStocks;
    }
}
