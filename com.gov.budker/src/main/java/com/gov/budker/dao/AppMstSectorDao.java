package com.gov.budker.dao;

import com.gov.budker.model.AppMstSector;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstSectorDao {

    AppMstSector findById(BigDecimal id);

    void saveEditDeleteAppMstSectorEntity(AppMstSector appMstSector);

    List<AppMstSector> findAllAppMstSectorEntity();

}
