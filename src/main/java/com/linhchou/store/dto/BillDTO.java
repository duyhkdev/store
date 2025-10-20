package com.linhchou.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDTO {
    private Long id;
    private Date createdTime;
    private Double adsCosts;
    private Double sumInterestAmount;
    private List<BillDetailDTO> details;
}
