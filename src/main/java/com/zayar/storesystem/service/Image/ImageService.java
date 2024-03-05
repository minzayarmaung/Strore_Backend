package com.zayar.storesystem.service.Image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ImageService {

    public byte[] getImageByInvoiceId(Long invoiceId) throws IOException;

    public void updateImagePhoto(Long invoiceId , MultipartFile file) throws IOException;
}
