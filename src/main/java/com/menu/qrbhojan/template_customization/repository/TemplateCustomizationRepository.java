package com.menu.qrbhojan.template_customization.repository;

import com.menu.qrbhojan.template_customization.entity.TemplateCustomization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateCustomizationRepository extends JpaRepository<TemplateCustomization, Long> {
    Optional<TemplateCustomization> findByCafeSpecialId(String cafeSpecialId);
}
