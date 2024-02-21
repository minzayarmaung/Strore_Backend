package com.zayar.storesystem.Repository;

import com.zayar.storesystem.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice , Long> {

    // To list active Data
    List<Invoice> findByStatus(String status);

    // To get Stock Id and Stock Amount
    @Query("SELECT i.invoiceId, i.cashierName, i.date, i.time, i.branch, i.center, i.status, s.stockId,s.name, s.amount , s.quantity , s.price FROM Invoice i LEFT JOIN i.stocks s")
    List<Object[]> getInvoiceWithStockDetails();

}
