package com.gov.budker.dao;

import com.gov.budker.model.AppMstFinishingType;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstFinishingTypeDao {

    AppMstFinishingType findById(BigDecimal id);

    void saveEditDeleteAppMstFinishingTypeEntity(AppMstFinishingType appMstFinishingType);

    List<AppMstFinishingType> findAllAppMstFinishingTypeEntity();

}
