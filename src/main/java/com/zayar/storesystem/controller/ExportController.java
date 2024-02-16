package com.zayar.storesystem.controller;

import com.zayar.storesystem.entity.ExportDTO;
import com.zayar.storesystem.service.Excel.ExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/excel")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @GetMapping("/exportData")
    public ResponseEntity<byte[]> exportToExcel(){
        List<ExportDTO> exportData = exportService.getExportData();
        Workbook workbook = exportService.exportToExcel(exportData);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment" , "export.xlsx");
        headers.setContentLength(bytes.length);

        return new ResponseEntity<>(bytes , headers , HttpStatus.OK);
    }
}
