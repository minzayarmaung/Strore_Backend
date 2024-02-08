package com.zayar.storesystem.serviceImpl.ResultTable;

import com.zayar.storesystem.Repository.ResultTableRepository;
import com.zayar.storesystem.entity.Invoice;
import com.zayar.storesystem.entity.ResultTable;
import com.zayar.storesystem.service.ResultTable.ResultTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultTableServiceImpl implements ResultTableService {

    @Autowired
    private ResultTableRepository resultTableRepository;

    @Override
    public List<ResultTable> getAllData() {
        return (List<ResultTable>) resultTableRepository.findAll();
    }
}
