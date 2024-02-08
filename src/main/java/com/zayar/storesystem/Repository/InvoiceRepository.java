package com.zayar.storesystem.Repository;

import com.zayar.storesystem.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice , Long> {

}
