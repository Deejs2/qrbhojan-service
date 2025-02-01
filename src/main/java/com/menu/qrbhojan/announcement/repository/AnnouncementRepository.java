package com.menu.qrbhojan.announcement.repository;

import com.menu.qrbhojan.announcement.entity.Announcement;
import com.menu.qrbhojan.announcement.entity.AnnouncementType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Optional<List<Announcement>> findAllByType(Pageable pageable, AnnouncementType announcementType);
}
