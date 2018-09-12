package com.gov.budker.dao;

import com.gov.budker.model.CalTrxCarBprs;
import com.gov.budker.model.RptReportBprsDto;

import java.math.BigDecimal;
import java.util.List;

public interface CalTrxCarBprsDao {

    CalTrxCarBprs findById(BigDecimal id);

    void saveCalTrxCarBprsEntity(CalTrxCarBprs calTrxCarBprs);

    public BigDecimal saveOrUpdate(CalTrxCarBprs calTrxCarBprs);

    void deleteCalTrxCarBprsEntity(BigDecimal Id, BigDecimal userDeleted);

    List<CalTrxCarBprs> findAllCalTrxCarBprsEntity();

    Boolean cekAvailableData(BigDecimal divisionId, String year, BigDecimal triwulan);

    Boolean cekAvailableDataEdit(BigDecimal divisionId, String year, BigDecimal triwulan, BigDecimal Id);

    List<RptReportBprsDto> getListReportBprs(BigDecimal divId, BigDecimal year, BigDecimal trieulan);

}
