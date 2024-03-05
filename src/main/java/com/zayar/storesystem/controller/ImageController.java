package com.zayar.storesystem.controller;

import com.zayar.storesystem.service.Image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    // Getting Image by Invoice ID
    @GetMapping("/images/{invoiceId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long invoiceId) throws IOException {
        byte[] image = imageService.getImageByInvoiceId(invoiceId);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
    // Updating Image By Invoice ID
    @PutMapping("/updateImage/{invoiceId}")
    public ResponseEntity<?> updateImage(@PathVariable Long invoiceId ,
                                         @RequestParam("profileImage")MultipartFile file){
        try{
            imageService.updateImagePhoto(invoiceId , file );
            return ResponseEntity.ok().body("Updated Successfully !");
        }catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Update Image.");
        }

    }

}


