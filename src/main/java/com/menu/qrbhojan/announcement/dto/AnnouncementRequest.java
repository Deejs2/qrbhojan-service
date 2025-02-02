package com.menu.qrbhojan.announcement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementRequest {
    private String title;
    private String description;
    private String type;
    private MultipartFile image;
}
