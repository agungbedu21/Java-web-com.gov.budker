package com.gov.budker.dao;

import com.gov.budker.model.CalTrxCarBus;
import com.gov.budker.model.RptReportBusDto;

import java.math.BigDecimal;
import java.util.List;

public interface CalTrxCarBusDao {

    CalTrxCarBus findById(BigDecimal id);

    void saveCalTrxCarBusEntity(CalTrxCarBus calTrxCarBus);

    public BigDecimal saveOrUpdate(CalTrxCarBus calTrxCarBus);

    void deleteCalTrxCarBusEntity(BigDecimal Id, BigDecimal userDeleted);

    List<CalTrxCarBus> findAllCalTrxCarBusEntity();

    Boolean cekAvailableData(BigDecimal divisionId, String year, BigDecimal triwulan);

    Boolean cekAvailableDataEdit(BigDecimal divisionId, String year, BigDecimal triwulan, BigDecimal Id);

    List<RptReportBusDto> getListReportBus(BigDecimal divId, BigDecimal year, BigDecimal triwulan);

}
