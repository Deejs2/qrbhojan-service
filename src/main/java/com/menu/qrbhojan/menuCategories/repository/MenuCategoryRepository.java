package com.menu.qrbhojan.menuCategories.repository;

import com.menu.qrbhojan.menuCategories.dto.response.MenuCategoryResponse;
import com.menu.qrbhojan.menuCategories.entity.MenuCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategories, Long> {
    Page<MenuCategories> findAllByCafeSpecialId(String cafeSpecialId, Pageable pageable);

    MenuCategories findByCafeSpecialIdAndCategoryId(String cafeSpecialId, Long id);

}
