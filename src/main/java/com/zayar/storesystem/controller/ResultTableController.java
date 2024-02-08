package com.zayar.storesystem.controller;

import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.ResultTable;
import com.zayar.storesystem.service.ResultTable.ResultTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResultTableController {

    @Autowired
    private ResultTableService resultTableService;

    @GetMapping("/all_result_table")
    public List<ResultTable> getAllData(){
        System.out.println("All Result Data : ");
        return resultTableService.getAllData();
    }
}
