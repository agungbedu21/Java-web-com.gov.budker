package com.gov.budker.dao;

import com.gov.budker.model.AppMstRole;
import com.gov.budker.model.AppMstRoleDivision;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstRoleDivisionDao {

    void deleteRoleByDivId(BigDecimal divId);

    AppMstRoleDivision findById(BigDecimal id);

    void saveEditDeleteAppMstRoleEntity(List <AppMstRoleDivision> listRole);

    List<AppMstRoleDivision> findAllAppMstRoleDivisionEntity();

    List<AppMstRoleDivision> findAppMstRoleDivisionEntity(BigDecimal divisionId);
}
