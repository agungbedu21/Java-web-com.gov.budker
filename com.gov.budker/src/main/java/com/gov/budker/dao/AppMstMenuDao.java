package com.gov.budker.dao;

import com.gov.budker.model.AppMstMenu;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstMenuDao {

    AppMstMenu findById(BigDecimal id);

    void saveAppMstMenuEntity(AppMstMenu menu);

    public BigDecimal saveOrUpdate(AppMstMenu student);

    void deleteAppMstMenuEntity(BigDecimal Id);

    List<AppMstMenu> findAllAppMstMenuEntity();

    List<AppMstMenu> findParentMenu();

    List<AppMstMenu> getMenuEmployee(BigDecimal employeeId);

    List<AppMstMenu> getMenuByParentId(BigDecimal parentId);
}
