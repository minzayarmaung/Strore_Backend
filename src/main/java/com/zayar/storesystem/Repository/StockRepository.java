package com.zayar.storesystem.Repository;

import com.zayar.storesystem.entity.ResultTable;
import com.zayar.storesystem.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
