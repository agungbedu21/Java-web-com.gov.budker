package com.gov.budker.dao;

import com.gov.budker.model.AppMstDivisionBank;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstDivisionBankDao {

    AppMstDivisionBank findById(BigDecimal id);

    BigDecimal saveEditDeleteAppMstDivisionBankEntity(AppMstDivisionBank appMstDivisionBank);

    List<AppMstDivisionBank> findAllAppMstDivisionBankEntity(BigDecimal divId);

    void saveListDivisionBank(List<AppMstDivisionBank> listDivBank);

}
