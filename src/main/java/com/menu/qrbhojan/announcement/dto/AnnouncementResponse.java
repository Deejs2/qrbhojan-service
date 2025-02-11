package com.menu.qrbhojan.announcement.dto;

import com.menu.qrbhojan.announcement.entity.Announcement;
import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.utils.DateTimeConverter;
import com.menu.qrbhojan.utils.ImageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnnouncementResponse {
    private Long id;
    private String title;
    private String description;
    private String type;
    private String imageUrl;
    private String createdAt;

    public AnnouncementResponse(Announcement announcement){
        this.id = announcement.getId();
        this.title = announcement.getTitle();
        this.description = announcement.getDescription();
        this.type = announcement.getType().name();
        this.imageUrl = ImageResponse.setImageUrl(SystemMessage.IMAGE_API_PATH, announcement.getImageUrl());
        this.createdAt = DateTimeConverter.convertDateTime(announcement.getCreatedAt());
    }
}
