package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstMenuRoleDao;
import com.gov.budker.dao.PrkIkuTwTargetDao;
import com.gov.budker.model.PrkIkuTwTarget;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("iku_tw_target")
public class PrkIkuTwTargetDaoImpl extends AbstractDao<BigDecimal, PrkIkuTwTarget> implements PrkIkuTwTargetDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public PrkIkuTwTarget findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveEditDeletePrkIkuTwTargetEntity(PrkIkuTwTarget role) {
        BigDecimal id = null;
        if (role.getIkuTwId() == null) {
            id = super.saveOrUpdate(role);
        }else{
            id = role.getIkuTwId();
            getSession().update(role);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PrkIkuTwTarget> findAllPrkIkuTwTargetEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<PrkIkuTwTarget>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PrkIkuTwTarget> findPrkIkuTwTargetByprokerId(BigDecimal prokerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("prokerId", prokerId)));
        return (List<PrkIkuTwTarget>) criteria.list();
    }

}
