package com.menu.qrbhojan.website.repository;

import com.menu.qrbhojan.website.entity.CafeWebsite;
import com.menu.qrbhojan.website.entity.WebsiteStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CafeWebsiteRepository extends JpaRepository<CafeWebsite, Long> {
    Optional<CafeWebsite> findByCafeSpecialId(String cafeSpecialId);
    Optional<Page<CafeWebsite>> findAllByCafeWebsiteStatus(Pageable pageable, WebsiteStatus cafeWebsiteStatus);
}
