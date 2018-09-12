package com.gov.budker.dao;

import com.gov.budker.model.AppMstMenuRole;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstMenuRoleDao {

    AppMstMenuRole findById(BigDecimal id);

    void saveEditDeleteAppMstMenuRoleEntity(AppMstMenuRole menuRole);

    void saveListMenuRole(List<AppMstMenuRole> menuRole);

    List<AppMstMenuRole> findAllAppMstMenuRoleEntity();

    List<AppMstMenuRole> findAppMstMenuRoleEntity(BigDecimal AppMstMenuRoleEntityId);

    List<AppMstMenuRole> getListMenuRoleByRoleId(BigDecimal roleId);
}
