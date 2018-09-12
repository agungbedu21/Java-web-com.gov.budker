package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalTrxBprsDetailDao;
import com.gov.budker.model.CalTrxBprsDetail;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("bprs_detail")
public class CalTrxBprsDetailDaoImpl extends AbstractDao<BigDecimal, CalTrxBprsDetail> implements CalTrxBprsDetailDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalTrxBprsDetail findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteCalTrxBprsDetailEntity(CalTrxBprsDetail CalTrxBprsDetail) {
        BigDecimal id = null;
        if (CalTrxBprsDetail.getDetailId() == null) {
            id = super.saveOrUpdate(CalTrxBprsDetail);
        } else {
            id = CalTrxBprsDetail.getDetailId();
            getSession().update(CalTrxBprsDetail);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveListFinishingDetail(List<CalTrxBprsDetail> listDetail) {
        for (int i = 0; i < listDetail.size(); i++) {
            if (listDetail.get(i).getDetailId() == null) {
                super.saveOrUpdate(listDetail.get(i));
            } else {
                getSession().update(listDetail.get(i));
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxBprsDetail> findAllCalTrxBprsDetailEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<CalTrxBprsDetail>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxBprsDetail> findCalTrxBprsDetailByBprsId(BigDecimal carBprsId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("carBprsId", carBprsId)));
        return (List<CalTrxBprsDetail>) criteria.list();
    }

}
