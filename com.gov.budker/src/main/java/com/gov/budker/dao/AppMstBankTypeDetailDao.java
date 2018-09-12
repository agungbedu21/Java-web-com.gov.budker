package com.gov.budker.dao;

import com.gov.budker.model.AppMstBankType;
import com.gov.budker.model.AppMstBankTypeDetail;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstBankTypeDetailDao {

    AppMstBankTypeDetail findById(BigDecimal id);

    void saveEditDeleteAppMstBankTypeEntity(AppMstBankTypeDetail appMstBankTypeDetail);

    void saveEditDeleteAppMstBankTypeListEntity(List<AppMstBankTypeDetail> appMstBankTypeDetailList);

    List<AppMstBankTypeDetail> findAppMstBankTypeDetailEntityByBankTypeId(BigDecimal bankTypeId);
}
