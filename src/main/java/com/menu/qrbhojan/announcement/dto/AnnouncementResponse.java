package com.menu.qrbhojan.announcement.dto;

import com.menu.qrbhojan.announcement.entity.Announcement;
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

    public AnnouncementResponse(Announcement announcement){
        this.id = announcement.getId();
        this.title = announcement.getTitle();
        this.description = announcement.getDescription();
        this.type = announcement.getType().name();
        this.imageUrl = announcement.getImageUrl();
    }
}
