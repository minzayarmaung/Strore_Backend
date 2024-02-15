package com.zayar.storesystem.service.ResultTable;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.ResultTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultTableService {

    // Getting data for Result Table
    public List<ResultTable> getAllData();

    // Exporting
}
