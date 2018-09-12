package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.PrkMainProkerDao;
import com.gov.budker.dao.PrkMainProkerDao;
import com.gov.budker.model.BudTrxAchTarget;
import com.gov.budker.model.PrkMainProker;
import com.gov.budker.model.PrkMainProker;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository("main_proker")
public class PrkMainProkerDaoImpl extends AbstractDao<BigDecimal, PrkMainProker> implements PrkMainProkerDao {

    @Autowired
    private PrkMainProkerDao daoTarget;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public PrkMainProker findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void savePrkMainProkerEntity(PrkMainProker mainProker) {
        if (mainProker.getMainProkerId() == null) {
            super.saveOrUpdate(mainProker);
        } else {
            getSession().update(mainProker);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal saveOrUpdate(PrkMainProker mainProker) {
        return super.saveOrUpdate(mainProker);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deletePrkMainProkerEntity(BigDecimal mainProkerId) {
        Query query = getSession().createQuery("update PrkMainProker a set a.deletedStatus=1" +
                " where a.mainProkerId=:mainProkerId");
        query.setParameter("mainProkerId", mainProkerId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PrkMainProker> findAllPrkMainProkerEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<PrkMainProker>) criteria.list();
    }

}
