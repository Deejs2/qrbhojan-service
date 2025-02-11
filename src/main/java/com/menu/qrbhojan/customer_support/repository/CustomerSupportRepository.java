package com.menu.qrbhojan.customer_support.repository;

import com.menu.qrbhojan.customer_support.entity.CustomerSupport;
import com.menu.qrbhojan.customer_support.entity.SupportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerSupportRepository extends JpaRepository<CustomerSupport, Long> {
    Optional<Page<CustomerSupport>> findAllByStatus(Pageable pageable, SupportStatus supportStatus);
    Optional<Page<CustomerSupport>> findAllByUserIdAndStatus(Pageable pageable, Long userId, SupportStatus supportStatus);
}
