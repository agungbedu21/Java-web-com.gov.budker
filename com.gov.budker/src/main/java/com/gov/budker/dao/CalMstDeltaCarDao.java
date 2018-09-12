package com.gov.budker.dao;

import com.gov.budker.model.CalMstDeltaCar;

import java.math.BigDecimal;
import java.util.List;

public interface CalMstDeltaCarDao {

    CalMstDeltaCar findById(BigDecimal id);

    CalMstDeltaCar findByActiveByYear(BigDecimal year);

    void saveEditDeleteCalMstDeltaCarEntity(CalMstDeltaCar calMstDeltaCar);

    void saveListFinishingDetail(List<CalMstDeltaCar> listDetail);

    List<CalMstDeltaCar> findAllCalMstDeltaCarEntity();

    List<CalMstDeltaCar> findCalMstDeltaCarByBprsId(BigDecimal deltaCarId);

    List<CalMstDeltaCar> findDeltaCarByYear(String year);
}
