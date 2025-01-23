package com.menu.qrbhojan.socialsite.repository;

import com.menu.qrbhojan.socialsite.entity.SocialIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialIconRepository extends JpaRepository<SocialIcon, Long> {
}
