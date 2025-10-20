package com.linhchou.store.repository;

import com.linhchou.store.entity.FeeFormula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeFormulaRepository extends JpaRepository<FeeFormula, Long> {
    List<FeeFormula> findByPlatform(String platForm);
}
