package com.linhchou.store.repository;

import com.linhchou.store.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long> {
    List<ProductDetailEntity> findByProductId(Long id);
}
