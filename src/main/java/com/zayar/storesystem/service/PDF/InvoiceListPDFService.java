package com.zayar.storesystem.service.PDF;

import com.zayar.storesystem.entity.Invoice;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public interface InvoiceListPDFService {
    // Getting All Invoice with Stock Details
    public ByteArrayInputStream generatePDF(List<Invoice> invoiceDetails) throws IOException;
}
