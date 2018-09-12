package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.BudTrxAchTargetDao;
import com.gov.budker.dao.BudTrxBudgetDao;
import com.gov.budker.model.BudTrxAchTarget;
import com.gov.budker.model.BudTrxBudget;
import com.gov.budker.model.RptBudgetDto;
import com.gov.budker.model.RptGrowthDto;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository("budget")
public class BudTrxBudgetDaoImpl extends AbstractDao<BigDecimal, BudTrxBudget> implements BudTrxBudgetDao {

    @Autowired
    private BudTrxAchTargetDao daoTarget;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BudTrxBudget findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveBudTrxBudgetEntity(BudTrxBudget employee) {
        super.saveOrUpdate(employee);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveBudgetAndAchievement(BudTrxBudget budget, List<BudTrxAchTarget> listAchTarget) {
        BigDecimal budgetId = null;
        BigDecimal id = null;
        if (budget.getBudgetId() == null) {
            budget.setDateCreated(new Date());
            id = super.saveOrUpdate(budget);
            for (int i = 0; i < listAchTarget.size(); i++) {
                listAchTarget.get(i).setBudgetId(id);
                listAchTarget.get(i).setDeletedStatus(budget.getDeletedStatus());
                listAchTarget.get(i).setStatus(budget.getStatus());
                daoTarget.saveEditDeleteBudTrxAchTargetEntity(listAchTarget.get(i));
            }
        } else {
            id = budget.getBudgetId();
            budget.setDateUpdated(new Date());
            getSession().update(budget);
            for (int i = 0; i < listAchTarget.size(); i++) {
                listAchTarget.get(i).setBudgetId(id);
                listAchTarget.get(i).setDeletedStatus(budget.getDeletedStatus());
                listAchTarget.get(i).setStatus(budget.getStatus());
                daoTarget.saveEditDeleteBudTrxAchTargetEntity(listAchTarget.get(i));
            }
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal saveOrUpdate(BudTrxBudget employee) {
        return super.saveOrUpdate(employee);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteBudTrxBudgetEntity(BigDecimal budgetId) {
        Query query = getSession().createQuery("update BudTrxBudget a set a.deletedStatus=1" +
                " where a.budgetId=:budgetId");
        query.setParameter("budgetId", budgetId);
        query.executeUpdate();
        query = null;
        query = getSession().createQuery("update BudTrxAchTarget a set a.deletedStatus=1" +
                " where a.budgetId=:budgetId");
        query.setParameter("budgetId", budgetId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<BudTrxBudget> findAllBudTrxBudgetEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<BudTrxBudget>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BudTrxBudget findBudTrxBudgetEntity(BigDecimal divisionId, BigDecimal year) {
        Date dt = new Date();
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("budgetDivisionId", divisionId)));
        criteria.add(Restrictions.and(Restrictions.eq("budgetYear", year)));
        List<BudTrxBudget> data = criteria.list();
        return data.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RptBudgetDto> getBudgetReport(BigDecimal year, BigDecimal tw) {
        Query quer = null;
        quer = getSession().createSQLQuery("select sum(a.ach_target_value) as fieldA," +
                " sum(a.ach_target_percentage) as fieldB" +
                " from bud_trx_ach_target a, bud_trx_budget b" +
                " where a.budget_id = b.budget_id and b.budget_division_id in (8,9,10,11) and b.budget_year=:year" +
                " GROUP BY a.bud_target_tw" +
                " union all" +
                " select sum(a.realization), sum(a.subsequence)" +
                " from rlz_trx_realization_month a" +
                " where a.proker_id in" +
                " (select d.proker_id from prk_proker d where d.division_id in (8,9,10,11)" +
                " and d.proker_year=:year)" +
                " GROUP by a.triwulan");
        quer.setParameter("year", year)
                .setResultTransformer(Transformers.aliasToBean(RptBudgetDto.class));
        List<RptBudgetDto> result = quer.list();
        return result;
    }

}
