package com.gov.budker.dao;

import com.gov.budker.model.AppMstRole;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstRoleDao {

    void deleteRole(AppMstRole role);

    AppMstRole findById(BigDecimal id);

    void saveEditDeleteAppMstRoleEntity(AppMstRole role, List menuId);

    List<AppMstRole> findAllAppMstRoleEntity();

    List<AppMstRole> findAppMstRoleEntity(BigDecimal AppMstRoleEntityId);
}
