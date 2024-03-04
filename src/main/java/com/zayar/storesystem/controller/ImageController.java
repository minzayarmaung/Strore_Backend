package com.zayar.storesystem.controller;

import com.zayar.storesystem.service.Image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/images/{invoiceId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long invoiceId) throws IOException {
        byte[] image = imageService.getImageByInvoiceId(invoiceId);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
}


