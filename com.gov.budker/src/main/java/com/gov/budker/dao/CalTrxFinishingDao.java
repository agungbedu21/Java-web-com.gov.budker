package com.gov.budker.dao;

import com.gov.budker.model.CalTrxFinishing;
import com.gov.budker.model.CalTrxFinishingDetail;

import java.math.BigDecimal;
import java.util.List;

public interface CalTrxFinishingDao {

    CalTrxFinishing findById(BigDecimal id);

    void saveCalTrxFinishingEntity(CalTrxFinishing calTrxFinishing);

    public BigDecimal saveOrUpdate(CalTrxFinishing calTrxFinishing);

    void deleteCalTrxFinishingEntity(BigDecimal Id, BigDecimal userUpdated);

    List<CalTrxFinishing> findAllCalTrxFinishingEntity();

    Boolean cekAvailableData(BigDecimal divisionId, String year, BigDecimal triwulan);

    Boolean cekAvailableDataEdit(BigDecimal divisionId, String year, BigDecimal triwulan, BigDecimal Id);
}
