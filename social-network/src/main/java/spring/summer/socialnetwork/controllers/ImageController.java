package spring.summer.socialnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.summer.socialnetwork.services.StorageService;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/image")
public class ImageController {

    @Autowired
    private StorageService service;

    @PostMapping()
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
       // System.out.println("rozmiar pliku: "+file.getSize());

        String uploadImage = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("profile")
    public ResponseEntity<?> downloadProfileImage() {

        byte[] imageData = service.downloadProfileImage();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
    @PostMapping("profile")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadProfileImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }


}