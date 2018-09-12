package com.gov.budker.dao;

import com.gov.budker.model.CalMstCarBprs;

import java.math.BigDecimal;
import java.util.List;

public interface CalMstDeltaCarBprsDao {

    CalMstCarBprs findById(BigDecimal id);

    CalMstCarBprs findByActiveByYear(String year);

    void saveEditDeleteCalMstCarBprsEntity(CalMstCarBprs calMstCarBprs);

    void saveListFinishingDetail(List<CalMstCarBprs> listDetail);

    List<CalMstCarBprs> findAllCalMstCarBprsEntity();

    List<CalMstCarBprs> findCalMstCarBprsByBprsId(BigDecimal deltaCarId);

    Boolean cekAvailableDataEdit(String year, BigDecimal id);

    Boolean cekAvailableData(String year);
}
