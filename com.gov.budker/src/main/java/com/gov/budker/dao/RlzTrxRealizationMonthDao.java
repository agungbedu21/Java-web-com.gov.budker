package com.gov.budker.dao;

import com.gov.budker.model.RlzTrxRealizationIku;
import com.gov.budker.model.RlzTrxRealizationMonth;

import java.math.BigDecimal;
import java.util.List;

public interface RlzTrxRealizationMonthDao {

    RlzTrxRealizationMonth findById(BigDecimal id);

    void saveEditDeleteRlzTrxRealizationMonthEntity(RlzTrxRealizationMonth rlzMonth);

    void saveList(List<RlzTrxRealizationMonth> listMonth);

    void saveListAll(List<RlzTrxRealizationMonth> listMonth, List<RlzTrxRealizationIku> listIku);

    List<RlzTrxRealizationMonth> findAllRlzTrxRealizationMonthEntity();

    List<RlzTrxRealizationMonth> findRlzTrxRealizationMonthByprokerId(BigDecimal prokerId);
}
