package com.menu.qrbhojan.menu_items.repository;

import com.menu.qrbhojan.menu_items.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findAllByMenuItemIdAndCafeSpecialId(Long menuItemId, String cafeSpecialId);
}
