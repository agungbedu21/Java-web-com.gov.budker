package com.gov.budker.dao;

import com.gov.budker.model.CalTrxGrowth;
import com.gov.budker.model.DsbCalculationDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CalTrxGrowthDao {

    CalTrxGrowth findById(BigDecimal id);

    void saveCalTrxGrowthEntity(CalTrxGrowth calTrxGrowth);

    public BigDecimal saveOrUpdate(CalTrxGrowth calTrxGrowth);

    void deleteCalTrxGrowthEntity(BigDecimal Id, BigDecimal userDeleted);

    List<CalTrxGrowth> findAllCalTrxGrowthEntity();

    Boolean cekAvailableData(BigDecimal divisionId, String year, BigDecimal triwulan);

    Boolean cekAvailableDataEdit(BigDecimal divisionId, String year, BigDecimal triwulan, BigDecimal Id);

    List<DsbCalculationDto> getDashboardData(BigDecimal year);



}
