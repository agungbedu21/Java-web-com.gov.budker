package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.PrkMonthTargetDao;
import com.gov.budker.model.PrkMonthTarget;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("iku_target")
public class PrkMonthTargetDaoImpl extends AbstractDao<BigDecimal, PrkMonthTarget> implements PrkMonthTargetDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public PrkMonthTarget findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeletePrkMonthTargetEntity(PrkMonthTarget monthTarget) {
        BigDecimal id = null;
        if (monthTarget.getMonthTargetId() == null) {
            id = super.saveOrUpdate(monthTarget);
        }else{
            id = monthTarget.getMonthTargetId();
            getSession().update(monthTarget);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PrkMonthTarget> findAllPrkMonthTargetEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<PrkMonthTarget>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PrkMonthTarget> findPrkMonthTargetByprokerId(BigDecimal prokerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("prokerId", prokerId)));
        return (List<PrkMonthTarget>) criteria.list();
    }

}
