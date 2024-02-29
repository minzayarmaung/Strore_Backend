package com.zayar.storesystem.service.PDF;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public interface InvoiceListPDFService {
    // Getting All Invoice with Stock Details
    public ByteArrayInputStream generatePDF(List<Object[]> invoiceDetails);
}
