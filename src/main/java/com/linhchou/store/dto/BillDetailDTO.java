package com.linhchou.store.dto;

import com.linhchou.store.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDetailDTO {
    private Long id;

    private Integer quality;

    private Double interestAmount;

    private ProductDTO product;

    private String platForm;
}
