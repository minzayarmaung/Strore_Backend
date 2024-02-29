package com.zayar.storesystem.service.PDF;

import org.springframework.stereotype.Service;

@Service
public interface PDFService {
    byte[] generatePDF(Long invoiceId);
}
