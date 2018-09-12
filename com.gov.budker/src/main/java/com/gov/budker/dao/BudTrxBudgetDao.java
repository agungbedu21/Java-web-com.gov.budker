package com.gov.budker.dao;

import com.gov.budker.model.BudTrxAchTarget;
import com.gov.budker.model.BudTrxBudget;
import com.gov.budker.model.RptBudgetDto;

import java.math.BigDecimal;
import java.util.List;

public interface BudTrxBudgetDao {

    BudTrxBudget findById(BigDecimal id);

    void saveBudTrxBudgetEntity(BudTrxBudget budget);

    void saveBudgetAndAchievement(BudTrxBudget budget, List<BudTrxAchTarget> listAchTarget);

    public BigDecimal saveOrUpdate(BudTrxBudget budget);

    void deleteBudTrxBudgetEntity(BigDecimal Id);

    List<BudTrxBudget> findAllBudTrxBudgetEntity();

    BudTrxBudget findBudTrxBudgetEntity(BigDecimal BudTrxBudgetEntityId, BigDecimal year);

    List<RptBudgetDto> getBudgetReport(BigDecimal year, BigDecimal tw);

}
