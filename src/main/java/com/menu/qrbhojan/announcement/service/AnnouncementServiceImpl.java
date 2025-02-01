package com.menu.qrbhojan.announcement.service;

import com.menu.qrbhojan.announcement.dto.AnnouncementRequest;
import com.menu.qrbhojan.announcement.dto.AnnouncementResponse;
import com.menu.qrbhojan.announcement.entity.Announcement;
import com.menu.qrbhojan.announcement.entity.AnnouncementType;
import com.menu.qrbhojan.announcement.repository.AnnouncementRepository;
import com.menu.qrbhojan.common.service.FileHandler;
import com.menu.qrbhojan.constant.SystemMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {
    private final FileHandler fileHandler;
    private final AnnouncementRepository announcementRepository;

    @Override
    public AnnouncementResponse saveAnnouncement(AnnouncementRequest announcementRequest) throws IOException {
        log.info("Saving announcement: {}", announcementRequest);
        Announcement announcement = buildAnnouncementFromRequest(announcementRequest, new Announcement());
        announcementRepository.save(announcement);
        return new AnnouncementResponse(announcement);
    }

    @Override
    public AnnouncementResponse getAnnouncement(Long announcementId) {
        log.info("Fetching announcement with id: {}", announcementId);
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.ANNOUNCEMENT_NOT_FOUND));
        return new AnnouncementResponse(announcement);
    }

    @Override
    public List<AnnouncementResponse> getAllAnnouncements(Pageable pageable, AnnouncementType announcementStatus) {
        List<Announcement> announcements = announcementRepository.findAllByType(pageable, announcementStatus)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.ANNOUNCEMENT_NOT_FOUND));
        return announcements.stream().map(AnnouncementResponse::new).toList();
    }

    @Override
    public AnnouncementResponse updateAnnouncement(Long announcementId, AnnouncementRequest announcementRequest) throws IOException {
        log.info("Updating announcement with id: {}", announcementId);
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.ANNOUNCEMENT_NOT_FOUND));
        buildAnnouncementFromRequest(announcementRequest, announcement);
        announcementRepository.save(announcement);
        return new AnnouncementResponse(announcement);
    }

    @Override
    public void deleteAnnouncement(Long announcementId) {
        log.info("Deleting announcement with id: {}", announcementId);
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new EntityNotFoundException(SystemMessage.ANNOUNCEMENT_NOT_FOUND));
        announcementRepository.delete(announcement);
    }

    private Announcement buildAnnouncementFromRequest(AnnouncementRequest announcementRequest, Announcement announcement) throws IOException {
        announcement.setTitle(announcementRequest.getTitle());
        announcement.setDescription(announcementRequest.getDescription());
        announcement.setType(AnnouncementType.valueOf(announcementRequest.getType()));
        if (announcementRequest.getImage() != null) {
            announcement.setImageUrl(saveMediaFile(announcementRequest.getImage()));
        }
        return announcement;
    }

    private String saveMediaFile(MultipartFile mediaFile) throws IOException {
        String directory = "announcement";
        return fileHandler.saveMediaFile(mediaFile, directory).getFilePath();
    }
}