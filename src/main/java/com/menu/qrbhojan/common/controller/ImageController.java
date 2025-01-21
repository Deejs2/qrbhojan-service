package com.menu.qrbhojan.common.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

    @Value("#{getFilePath}")
    private String baseDirectory;

    @GetMapping("/getImage/{imagePath}")
    public ResponseEntity<Resource> getImage(@PathVariable String imagePath) throws FileNotFoundException {
        // Replace "&&&" with the system's file separator instead of "."
        imagePath = imagePath.replace("&&&", separator); // Use the system's file separator
        imagePath = baseDirectory.concat(separator).concat(imagePath);
        Path path = Paths.get(imagePath);

        File filePath = new File(imagePath);
        if (!filePath.exists()) {
            throw new FileNotFoundException("File not found: " + imagePath);
        }
        Resource resource = new InputStreamResource(new FileInputStream(filePath));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
