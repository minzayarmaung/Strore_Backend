package com.zayar.storesystem.service.PDF;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public interface PDFService {

    // Get by Each ID
    byte[] generatePDF(Long invoiceId);



}
