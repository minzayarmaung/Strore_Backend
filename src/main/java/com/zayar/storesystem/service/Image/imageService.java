package com.zayar.storesystem.service.Image;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public interface imageService {

    public byte[] getImageByInvoiceId(Long invoiceId) throws IOException;
}
