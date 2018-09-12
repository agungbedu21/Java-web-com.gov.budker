package com.gov.budker.dao;

import com.gov.budker.model.CalTrxSectoralDetail;

import java.math.BigDecimal;
import java.util.List;

public interface CalTrxSectoralDetailDao {

    CalTrxSectoralDetail findById(BigDecimal id);

    void saveEditDeleteCalTrxSectoralDetailEntity(CalTrxSectoralDetail calTrxSectoralDetail);

    void saveListFinishingDetail(List<CalTrxSectoralDetail> listDetail);

    List<CalTrxSectoralDetail> findAllCalTrxSectoralDetailEntity();

    List<CalTrxSectoralDetail> findCalTrxSectoralDetailBysectoralId(BigDecimal sectoralId);
}
