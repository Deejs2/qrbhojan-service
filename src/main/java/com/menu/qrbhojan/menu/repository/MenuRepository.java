package com.menu.qrbhojan.menu.repository;

import com.menu.qrbhojan.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findMenuByCafeSpecialId(String cafeSpecialId, Pageable pageable);

    Menu findByMenuIdAndCafeSpecialId(Long id, String cafeSpecialId);

    Page<Menu> findMenuByCafeSpecialIdAndMenuCategoriesCategoryId(String cafeSpecialId, Pageable pageable, Long id);

}
