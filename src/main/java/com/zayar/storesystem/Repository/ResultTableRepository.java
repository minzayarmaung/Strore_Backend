package com.zayar.storesystem.Repository;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.ResultTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultTableRepository extends JpaRepository<ResultTable, Long> {
}
