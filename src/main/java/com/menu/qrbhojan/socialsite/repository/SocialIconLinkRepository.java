package com.menu.qrbhojan.socialsite.repository;

import com.menu.qrbhojan.socialsite.entity.SocialIconLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialIconLinkRepository extends JpaRepository<SocialIconLink, Long> {
}
