package com.gov.budker.dao;

import com.gov.budker.model.RlzTrxRealization;

import java.math.BigDecimal;
import java.util.List;

public interface RlzTrxRealizationDao {

    RlzTrxRealization findById(BigDecimal id);

    BigDecimal saveEditDeleteRlzTrxRealizationEntity(RlzTrxRealization rlz);

    List<RlzTrxRealization> findAllRlzTrxRealizationEntity();

    RlzTrxRealization findRlzTrxRealizationByprokerId(BigDecimal prokerId);
}
