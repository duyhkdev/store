package com.linhchou.store.controller;

import com.linhchou.store.entity.FeeFormula;
import com.linhchou.store.repository.FeeFormulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fee")
public class FeeFormulaController {

    @Autowired
    private FeeFormulaRepository repository;

    @PostMapping
    public void create(@RequestBody FeeFormula feeFormula) {
        repository.save(feeFormula);
    }
}
