package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.RlzTrxRealizationIkuDao;
import com.gov.budker.model.RlzTrxRealizationIku;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("rlz_iku")
public class RlzRealizationIkuDaoImpl extends AbstractDao<BigDecimal, RlzTrxRealizationIku> implements RlzTrxRealizationIkuDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public RlzTrxRealizationIku findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveEditDeleteRlzTrxRealizationIkuEntity(RlzTrxRealizationIku rlzIku) {
        BigDecimal id = null;
        if (rlzIku.getIkuTwId() == null) {
            id = super.saveOrUpdate(rlzIku);
        } else {
            id = rlzIku.getIkuTwId();
            getSession().update(rlzIku);
        }

    }

    @Override
    @Transactional
    public void saveList(List<RlzTrxRealizationIku> listEntity) {
        deleteBeforeSave(listEntity.get(0).getProkerId());
        for (RlzTrxRealizationIku rlzIku : listEntity) {
            if (rlzIku.getRealizationIkuId ()== null) {
                getSession().save(rlzIku);
            } else {
                getSession().update(rlzIku);
            }
        }
    }

    public void deleteBeforeSave(BigDecimal prokerId){
        Query query = getSession().createQuery("delete from  RlzTrxRealizationIku a" +
                " where a.prokerId=:prokerId");
        query.setParameter("prokerId", prokerId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RlzTrxRealizationIku> findAllRlzTrxRealizationIkuEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<RlzTrxRealizationIku>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RlzTrxRealizationIku> findRlzTrxRealizationIkuByprokerId(BigDecimal prokerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("prokerId", prokerId)));
        return (List<RlzTrxRealizationIku>) criteria.list();
    }

}
