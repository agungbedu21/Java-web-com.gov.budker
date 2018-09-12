package com.gov.budker.dao;

import com.gov.budker.model.CalMstRiskProfile;

import java.math.BigDecimal;
import java.util.List;

public interface CalMstRiskProfileDao {

    CalMstRiskProfile findById(BigDecimal id);

    CalMstRiskProfile findByActiveByYear(BigDecimal year);

    void saveEditDeleteCalMstRiskProfileEntity(CalMstRiskProfile calMstRiskProfile);

    void saveListFinishingDetail(List<CalMstRiskProfile> listDetail);

    List<CalMstRiskProfile> findAllCalMstRiskProfileEntity();

    List<CalMstRiskProfile> findCalMstRiskProfileByBprsId(BigDecimal deltaCarId);

    List<CalMstRiskProfile> findRiskProfileByYear(String year);
}
