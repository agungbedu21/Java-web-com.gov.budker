package com.gov.budker.dao;

import com.gov.budker.model.AppMstBank;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstBankDao {

    AppMstBank findById(BigDecimal id);

    void saveEditDeleteAppMstBankEntity(AppMstBank appMstBank);

    List<AppMstBank> findAllAppMstBankEntity();

    List<AppMstBank> findAppMstBankByDivisionId(BigDecimal divisionId);
}
