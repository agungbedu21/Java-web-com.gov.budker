package com.gov.budker.dao;

import com.gov.budker.model.CalTrxFinishingDetail;
import com.gov.budker.model.RptFinishingADetailDto;

import java.math.BigDecimal;
import java.util.List;

public interface CalTrxFinishingDetailDao {

    CalTrxFinishingDetail findById(BigDecimal id);

    void saveEditDeleteCalTrxFinishingDetailEntity(CalTrxFinishingDetail calTrxFinishingDetail);

    void saveListFinishingDetail(List<CalTrxFinishingDetail> listDetail);

    List<CalTrxFinishingDetail> findAllCalTrxFinishingDetailEntity();

    List<CalTrxFinishingDetail> findCalTrxFinishingDetailByFinishingId(BigDecimal finishingId);

    List<RptFinishingADetailDto> getListFinishingA(BigDecimal div, BigDecimal year, BigDecimal tw);

    List<RptFinishingADetailDto> getListFinishingB(BigDecimal div, BigDecimal year, BigDecimal tw);
}
