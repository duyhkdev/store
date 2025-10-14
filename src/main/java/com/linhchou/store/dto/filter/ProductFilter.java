package com.linhchou.store.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilter {
    private String keyWord;
    private Integer pageIndex;
    private Integer pageSize;
}
