package com.zayar.storesystem.service.Excel;

import com.zayar.storesystem.Repository.InvoiceRepository;
import com.zayar.storesystem.Repository.StockRepository;
import com.zayar.storesystem.entity.ExportDTO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExportService {

    public List<ExportDTO> getExportData();

    public Workbook exportToExcel(List<ExportDTO> exportData);
}