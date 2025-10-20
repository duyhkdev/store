package com.linhchou.store.repository;

import com.linhchou.store.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    void deleteByBillId(Long id);

    List<BillDetail> findByBillId(Long id);
}
