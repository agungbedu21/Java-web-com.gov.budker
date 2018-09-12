package com.gov.budker.dao;

import com.gov.budker.model.AppMstEmployee;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstEmployeeDao {

    AppMstEmployee findById(BigDecimal id);

    void saveAppMstEmployeeEntity(AppMstEmployee employee);

    public AppMstEmployee login(String employeeUserName, String employeePassword);

    public BigDecimal saveOrUpdate(AppMstEmployee student);

    void deleteAppMstEmployeeEntity(String ssn);

    List<AppMstEmployee> findAllAppMstEmployeeEntity();

    List<AppMstEmployee> findAppMstEmployeeEntity(BigDecimal divId);

}
