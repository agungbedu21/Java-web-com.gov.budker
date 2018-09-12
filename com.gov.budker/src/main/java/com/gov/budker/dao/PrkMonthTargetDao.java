package com.gov.budker.dao;

import com.gov.budker.model.PrkMonthTarget;

import java.math.BigDecimal;
import java.util.List;

public interface PrkMonthTargetDao {

    PrkMonthTarget findById(BigDecimal id);

    void saveEditDeletePrkMonthTargetEntity(PrkMonthTarget monthEntity);

    List<PrkMonthTarget> findAllPrkMonthTargetEntity();

    List<PrkMonthTarget> findPrkMonthTargetByprokerId(BigDecimal prokerId);
}
