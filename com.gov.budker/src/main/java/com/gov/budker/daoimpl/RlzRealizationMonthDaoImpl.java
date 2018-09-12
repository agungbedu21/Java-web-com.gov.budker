package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.RlzTrxRealizationIkuDao;
import com.gov.budker.dao.RlzTrxRealizationMonthDao;
import com.gov.budker.model.RlzTrxRealizationIku;
import com.gov.budker.model.RlzTrxRealizationMonth;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("rlz_Month")
public class RlzRealizationMonthDaoImpl extends AbstractDao<BigDecimal, RlzTrxRealizationMonth> implements RlzTrxRealizationMonthDao {

    @Autowired
    private RlzTrxRealizationIkuDao daoIku;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public RlzTrxRealizationMonth findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveEditDeleteRlzTrxRealizationMonthEntity(RlzTrxRealizationMonth rlzMonth) {
        BigDecimal id = null;
        if (rlzMonth.getMonthTgtId() == null) {
            id = super.saveOrUpdate(rlzMonth);
        } else {
            id = rlzMonth.getMonthTgtId();
            getSession().update(rlzMonth);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveList(List<RlzTrxRealizationMonth> listMonth) {
        deleteBeforeSave(listMonth.get(0).getProkerId());
        for (RlzTrxRealizationMonth rlzMonth : listMonth) {
            if (rlzMonth.getRealizationMonthId() == null) {
                super.saveOrUpdate(rlzMonth);
            } else {
                getSession().update(rlzMonth);
            }
        }
    }

    @Override
    @Transactional
    public void saveListAll(List<RlzTrxRealizationMonth> listMonth, List<RlzTrxRealizationIku> listIku) {
        deleteBeforeSave(listMonth.get(0).getProkerId());
        daoIku.saveList(listIku);
        for (RlzTrxRealizationMonth rlzMonth : listMonth) {
            if (rlzMonth.getRealizationMonthId() == null) {
                super.saveOrUpdate(rlzMonth);
            } else {
                getSession().update(rlzMonth);
            }
        }
    }

    public void deleteBeforeSave(BigDecimal prokerId) {
        Query query = getSession().createQuery("delete from  RlzTrxRealizationMonth a" +
                " where a.prokerId=:prokerId");
        query.setParameter("prokerId", prokerId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RlzTrxRealizationMonth> findAllRlzTrxRealizationMonthEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<RlzTrxRealizationMonth>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RlzTrxRealizationMonth> findRlzTrxRealizationMonthByprokerId(BigDecimal prokerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("prokerId", prokerId)));
        return (List<RlzTrxRealizationMonth>) criteria.list();
    }

}
