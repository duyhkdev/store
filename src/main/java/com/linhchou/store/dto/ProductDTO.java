package com.linhchou.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String code;
    private Double priceImport;
    private Double priceSell;
    private String urlImage;
    private List<ProductDetailDTO> details;
}
