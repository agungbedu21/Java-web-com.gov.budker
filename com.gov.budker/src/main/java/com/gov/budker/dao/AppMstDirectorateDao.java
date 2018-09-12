package com.gov.budker.dao;

import com.gov.budker.model.AppMstDirectorate;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstDirectorateDao {

    AppMstDirectorate findById(BigDecimal id);

    void saveAppMstDirectorateEntity(AppMstDirectorate directorate);

    public BigDecimal saveOrUpdate(AppMstDirectorate directorate);

    void deleteAppMstDirectorateEntity(BigDecimal Id);

    List<AppMstDirectorate> findAllAppMstDirectorateEntity();

    List<AppMstDirectorate> findAppMstDirectorateEntity(BigDecimal AppMstDirectorateEntityId);

}
