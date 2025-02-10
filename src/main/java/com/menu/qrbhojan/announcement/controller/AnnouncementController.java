package com.menu.qrbhojan.announcement.controller;

import com.menu.qrbhojan.announcement.dto.AnnouncementRequest;
import com.menu.qrbhojan.announcement.dto.AnnouncementResponse;
import com.menu.qrbhojan.announcement.entity.AnnouncementType;
import com.menu.qrbhojan.announcement.service.AnnouncementService;
import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.global.BaseController;
import com.menu.qrbhojan.global.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcement")
public class AnnouncementController extends BaseController {
    private final AnnouncementService announcementService;

    @PostMapping("/save")
    public ResponseEntity<GlobalApiResponse> saveAnnouncement(@ModelAttribute AnnouncementRequest announcementRequest) throws IOException {
        return successResponse(announcementService.saveAnnouncement(announcementRequest), SystemMessage.ANNOUNCEMENT_CREATED);
    }
    @GetMapping("/{announcementId}")
    public ResponseEntity<GlobalApiResponse> getAnnouncement(@PathVariable Long announcementId) {
        return successResponse(announcementService.getAnnouncement(announcementId), SystemMessage.ANNOUNCEMENT_FETCHED);
    }
    @GetMapping("/all")
    public ResponseEntity<GlobalApiResponse> getAllAnnouncements(Pageable pageable) {
        return successResponse(announcementService.getAllAnnouncements(pageable), SystemMessage.ANNOUNCEMENT_FETCHED);
    }
    @PutMapping("/{announcementId}")
    public ResponseEntity<GlobalApiResponse> updateAnnouncement(@PathVariable Long announcementId, @ModelAttribute AnnouncementRequest announcementRequest) throws IOException {
        return successResponse(announcementService.updateAnnouncement(announcementId, announcementRequest), SystemMessage.ANNOUNCEMENT_UPDATED);
    }
    @DeleteMapping("/{announcementId}")
    public ResponseEntity<GlobalApiResponse> deleteAnnouncement(@PathVariable Long announcementId) {
        announcementService.deleteAnnouncement(announcementId);
        return successResponse(SystemMessage.ANNOUNCEMENT_DELETED);
    }
}
