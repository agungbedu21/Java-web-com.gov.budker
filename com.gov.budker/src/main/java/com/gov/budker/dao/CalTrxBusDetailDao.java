package com.gov.budker.dao;

import com.gov.budker.model.CalTrxBusDetail;

import java.math.BigDecimal;
import java.util.List;

public interface CalTrxBusDetailDao {

    CalTrxBusDetail findById(BigDecimal id);

    void saveEditDeleteCalTrxBusDetailEntity(CalTrxBusDetail calTrxBusDetail);

    void saveListBusDetail(List<CalTrxBusDetail> listDetail);

    List<CalTrxBusDetail> findAllCalTrxBusDetailEntity();

    List<CalTrxBusDetail> findCalTrxBusDetailByBusId(BigDecimal carBusId);
}
