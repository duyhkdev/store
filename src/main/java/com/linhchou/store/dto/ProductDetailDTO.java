package com.linhchou.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailDTO {
    private Long id;

    private ColorDTO color;
    private SizeDTO size;
}
