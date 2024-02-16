package com.zayar.storesystem.Repository;

import com.zayar.storesystem.entity.ResultTable;
import com.zayar.storesystem.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByInvoiceInvoiceId(long invoiceId);
}
