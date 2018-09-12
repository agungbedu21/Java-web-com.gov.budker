package com.gov.budker.dao;

import com.gov.budker.model.CalTrxGrowthDetail;
import com.gov.budker.model.RptGrowthDto;

import java.math.BigDecimal;
import java.util.List;

public interface CalTrxGrowthDetailDao {

    CalTrxGrowthDetail findById(BigDecimal id);

    void saveEditDeleteCalTrxGrowthDetailEntity(CalTrxGrowthDetail CalTrxGrowthDetail);

    void saveListGrowthDetail(List<CalTrxGrowthDetail> listDetail);

    List<CalTrxGrowthDetail> findAllCalTrxGrowthDetailEntity();

    List<CalTrxGrowthDetail> findCalTrxGrowthDetailByGrowthId(BigDecimal growthId);

    List<RptGrowthDto> getListReportGrowth(BigDecimal divId, BigDecimal year, BigDecimal id);

    List<RptGrowthDto> getListReportGrowth1(BigDecimal divId, BigDecimal year, BigDecimal id);
}
