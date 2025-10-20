package com.linhchou.store.service;

import com.linhchou.store.dto.BillDTO;

public interface BillService {
    void create(BillDTO billDTO);
    void update(BillDTO billDTO);
    void delete(Long id);

    BillDTO getById(Long id);
}
