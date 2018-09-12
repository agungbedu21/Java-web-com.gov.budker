package com.gov.budker.dao;

import com.gov.budker.model.CalTrxBprsDetail;

import java.math.BigDecimal;
import java.util.List;

public interface CalTrxBprsDetailDao {

    CalTrxBprsDetail findById(BigDecimal id);

    void saveEditDeleteCalTrxBprsDetailEntity(CalTrxBprsDetail CalTrxBprsDetail);

    void saveListFinishingDetail(List<CalTrxBprsDetail> listDetail);

    List<CalTrxBprsDetail> findAllCalTrxBprsDetailEntity();

    List<CalTrxBprsDetail> findCalTrxBprsDetailByBprsId(BigDecimal carBprsId);
}
