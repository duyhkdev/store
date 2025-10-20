package com.linhchou.store.service;

import com.linhchou.store.dto.ProductDTO;
import com.linhchou.store.dto.filter.ProductFilter;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll(ProductFilter filter);
    void create(ProductDTO dto);
    void update(ProductDTO dto);
}
