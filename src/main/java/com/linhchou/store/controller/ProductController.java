package com.linhchou.store.controller;

import com.linhchou.store.dto.ProductDTO;
import com.linhchou.store.dto.filter.ProductFilter;
import com.linhchou.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/get-all")
    public ResponseEntity<List<ProductDTO>> getAll(@RequestBody ProductFilter filter) {
        return ResponseEntity.ok(productService.getAll(filter));
    }

}
