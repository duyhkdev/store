package com.linhchou.store.controller;

import com.linhchou.store.dto.BillDTO;
import com.linhchou.store.service.BillService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RequiredArgsConstructor
@RequestMapping("/api/sale")
@RestController
public class SaleController {
    private final BillService billService;
    private final MessageSource messageSource;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody BillDTO dto,
                                         Locale locale) {
        billService.create(dto);
        return ResponseEntity.ok(messageSource.getMessage("0x000001", null, locale));
    }

    @PostMapping("/{id}")
    public ResponseEntity<BillDTO> getById(@PathVariable Long id) {
        BillDTO dto = billService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody BillDTO dto,
                                         Locale locale) {
        billService.update(dto);
        return ResponseEntity.ok(messageSource.getMessage("0x000001", null, locale));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         Locale locale) {
        billService.delete(id);
        return ResponseEntity.ok(messageSource.getMessage("0x000001", null, locale));
    }
}
