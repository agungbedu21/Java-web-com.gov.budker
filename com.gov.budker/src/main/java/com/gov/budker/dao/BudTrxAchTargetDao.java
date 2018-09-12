package com.gov.budker.dao;

import com.gov.budker.model.BudTrxAchTarget;
import com.gov.budker.model.BudTrxAchTarget;

import java.math.BigDecimal;
import java.util.List;

public interface BudTrxAchTargetDao {

    BudTrxAchTarget findById(BigDecimal id);

    void saveEditDeleteBudTrxAchTargetEntity(BudTrxAchTarget role);

    List<BudTrxAchTarget> findAllBudTrxAchTargetEntity();

    List<BudTrxAchTarget> findBudTrxAchTargetByBudgetId(BigDecimal budgetId);
}
