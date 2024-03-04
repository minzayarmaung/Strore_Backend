package com.zayar.storesystem.serviceImpl.imageServiceImpl;

import com.zayar.storesystem.service.Image.ImageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class imageServiceImpl implements ImageService {
    @Override
    public byte[] getImageByInvoiceId(Long invoiceId) throws IOException {
        String imagePath = "E:\\Store System\\Strore_Backend\\src\\main\\java\\com\\zayar\\storesystem\\images\\" + "ProfileImage_" +invoiceId+ ".jpg";

        Path path = Paths.get(imagePath);
        try {
            return Files.readAllBytes(path);
        }catch (NoSuchFileException e){
            System.err.println("No Such File Exists : " + imagePath);
            String defaultImagePath =
                    "E:\\Store System\\Strore_Backend\\src\\main\\java\\com\\zayar\\storesystem\\images\\DefaultImage.jpg";
            path = Paths.get(defaultImagePath);
            return Files.readAllBytes(path);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
