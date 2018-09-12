package com.gov.budker.dao;

import com.gov.budker.model.RlzTrxRealizationIku;

import java.math.BigDecimal;
import java.util.List;

public interface RlzTrxRealizationIkuDao {

    RlzTrxRealizationIku findById(BigDecimal id);

    void saveEditDeleteRlzTrxRealizationIkuEntity(RlzTrxRealizationIku rlzIku);

    void saveList(List<RlzTrxRealizationIku> listEntity);

    List<RlzTrxRealizationIku> findAllRlzTrxRealizationIkuEntity();

    List<RlzTrxRealizationIku> findRlzTrxRealizationIkuByprokerId(BigDecimal prokerId);
}
