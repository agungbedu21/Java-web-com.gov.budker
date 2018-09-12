package com.gov.budker.dao;

import com.gov.budker.model.BudTrxAchTarget;
import com.gov.budker.model.PrkMainProker;

import java.math.BigDecimal;
import java.util.List;

public interface PrkMainProkerDao {

    PrkMainProker findById(BigDecimal id);

    void savePrkMainProkerEntity(PrkMainProker budget);

    public BigDecimal saveOrUpdate(PrkMainProker budget);

    void deletePrkMainProkerEntity(BigDecimal Id);

    List<PrkMainProker> findAllPrkMainProkerEntity();

}
