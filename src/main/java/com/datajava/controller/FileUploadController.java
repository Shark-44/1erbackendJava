package com.datajava.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "http://192.168.1.157:4200")
@RequestMapping("/api/files")
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("Received file: " + file.getOriginalFilename());
        if (file.isEmpty()) {
            System.out.println("File is empty");
            return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
        }

        try {
            // Ensure the directory exists
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);

            System.out.println("File uploaded successfully: " + file.getOriginalFilename());
            return new ResponseEntity<>("File uploaded successfully: " + file.getOriginalFilename(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
