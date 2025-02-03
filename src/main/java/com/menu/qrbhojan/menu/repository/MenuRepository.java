package com.menu.qrbhojan.menu.repository;

import com.menu.qrbhojan.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findMenuByAvailabilityStatusAndCafeSpecialId(Boolean True, String cafeSpecialId, Pageable pageable);

    Menu findByMenuIdAndCafeSpecialIdAndAvailabilityStatus(Long id, String cafeSpecialId, Boolean availabilityStatus);
}
