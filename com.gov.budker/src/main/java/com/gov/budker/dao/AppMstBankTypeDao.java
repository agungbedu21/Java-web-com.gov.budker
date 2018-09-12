package com.gov.budker.dao;

import com.gov.budker.model.AppMstBankType;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstBankTypeDao {

    AppMstBankType findById(BigDecimal id);

    BigDecimal saveEditDeleteAppMstBankTypeEntity(AppMstBankType appMstBankType);

    List<AppMstBankType> findAllAppMstBankTypeEntity();
}
