package com.zayar.storesystem.service.Image;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ImageService {

    public byte[] getImageByInvoiceId(Long invoiceId) throws IOException;
}
