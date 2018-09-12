package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.RlzTrxRealizationDao;
import com.gov.budker.model.RlzTrxRealization;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("rlz_")
public class RlzRealizationDaoImpl extends AbstractDao<BigDecimal, RlzTrxRealization> implements RlzTrxRealizationDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public RlzTrxRealization findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public BigDecimal saveEditDeleteRlzTrxRealizationEntity(RlzTrxRealization rlz) {
        BigDecimal id = null;
        if (rlz.getRealizationId() == null) {
//            rlz = findRlzTrxRealizationByprokerId(rlz.getProkerId());
            id = super.saveOrUpdate(rlz);
        } else {
            id = rlz.getRealizationId();
            getSession().update(rlz);
        }
        return id;
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RlzTrxRealization> findAllRlzTrxRealizationEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<RlzTrxRealization>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public RlzTrxRealization findRlzTrxRealizationByprokerId(BigDecimal prokerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("prokerId", String.valueOf(prokerId))));
        List<RlzTrxRealization> dataList = criteria.list();
        RlzTrxRealization dataResult = new RlzTrxRealization();
        for (RlzTrxRealization rlz : dataList) {
            dataResult = rlz;
        }
        return dataResult;
    }

}
