package com.menu.qrbhojan.common.service;

import com.menu.qrbhojan.common.dto.FileResponse;
import com.menu.qrbhojan.common.entity.FileType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileHandler {
    @Value("#{getFilePath}")
    String baseFilePath;

    public FileResponse saveMediaFile(MultipartFile file, String folderName) throws IOException {
        String fileName = file.getOriginalFilename();
        fileName = fileName.replace(" ", "");
        String directoryPath = baseFilePath + File.separator + folderName;
        String filePath = directoryPath + File.separator + fileName;
        Path path = Paths.get(directoryPath);
        Files.createDirectories(path);
        file.transferTo(new File(filePath));

        // Get the MIME type of the file
        String mimeType = Files.probeContentType(Paths.get(filePath));

        FileType type;
        // Check if the file is an image or a video
        if (mimeType != null) {
            if (mimeType.startsWith("image")) {
                type = FileType.IMAGE;
            } else if (mimeType.startsWith("video")) {
                type = FileType.VIDEO;
            } else {
                throw new IOException("Unsupported file type");
            }
        } else {
            throw new IOException("Could not determine the file type");
        }
        return new FileResponse(folderName+ File.separator + fileName, type);
    }

    public void deleteFile(String filePath) throws IOException {
        Files.deleteIfExists(Paths.get(filePath));
    }
}
