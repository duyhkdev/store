package com.linhchou.store.repository;

import com.linhchou.store.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    void deleteByBillId(Long id);
}
