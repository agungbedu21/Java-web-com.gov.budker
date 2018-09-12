package com.gov.budker.dao;

import com.gov.budker.model.AppMstDivision;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstDivisionDao {

    AppMstDivision findById(BigDecimal id);

    BigDecimal saveAppMstDivisionEntity(AppMstDivision division);

    public BigDecimal saveOrUpdate(AppMstDivision division);

    void deleteAppMstDivisionEntity(BigDecimal Id);

    List<AppMstDivision> findAllAppMstDivisionEntity();

    List<AppMstDivision> findAppMstDivisionEntity(BigDecimal AppMstDivisionEntityId);

    List<AppMstDivision> getDivisionByDirectorateId(BigDecimal directorateId);

}
