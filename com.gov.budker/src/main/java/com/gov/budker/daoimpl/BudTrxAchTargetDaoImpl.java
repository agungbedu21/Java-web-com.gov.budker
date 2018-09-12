package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstMenuRoleDao;
import com.gov.budker.dao.BudTrxAchTargetDao;
import com.gov.budker.model.AppMstMenuRole;
import com.gov.budker.model.BudTrxAchTarget;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository("budget_target")
public class BudTrxAchTargetDaoImpl extends AbstractDao<BigDecimal, BudTrxAchTarget> implements BudTrxAchTargetDao {

    @Autowired
    private AppMstMenuRoleDao menuRoleDao;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BudTrxAchTarget findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveEditDeleteBudTrxAchTargetEntity(BudTrxAchTarget role) {
        BigDecimal id = null;
        if (role.getBudTargetId() == null) {
            id = super.saveOrUpdate(role);
        }else{
            getSession().update(role);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<BudTrxAchTarget> findAllBudTrxAchTargetEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<BudTrxAchTarget>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<BudTrxAchTarget> findBudTrxAchTargetByBudgetId(BigDecimal budgetId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("budgetId", budgetId)));
        return (List<BudTrxAchTarget>) criteria.list();
    }

}
