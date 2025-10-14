package com.linhchou.store.repository;

import com.linhchou.store.entity.ProductDetailEntity;
import com.linhchou.store.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<SizeEntity, Long> {
}
