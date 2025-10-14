package com.linhchou.store.repository;

import com.linhchou.store.entity.ProductDetailEntity;
import com.linhchou.store.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "select p from ProductEntity p " +
            " where 1 = 1 and (:keyWord is null or p.name like :keyWord)"
            , nativeQuery = false)
    Page<ProductEntity> getAll(@Param("keyWord") String keyWord, Pageable pageable);
}
