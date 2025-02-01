package com.menu.qrbhojan.announcement.service;

import com.menu.qrbhojan.announcement.dto.AnnouncementRequest;
import com.menu.qrbhojan.announcement.dto.AnnouncementResponse;
import com.menu.qrbhojan.announcement.entity.AnnouncementType;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface AnnouncementService {
    AnnouncementResponse saveAnnouncement(AnnouncementRequest announcementRequest) throws IOException;
    AnnouncementResponse getAnnouncement(Long announcementId);
    List<AnnouncementResponse> getAllAnnouncements(Pageable pageable, AnnouncementType announcementStatus);
    AnnouncementResponse updateAnnouncement(Long announcementId, AnnouncementRequest announcementRequest) throws IOException;
    void deleteAnnouncement(Long announcementId);
}
