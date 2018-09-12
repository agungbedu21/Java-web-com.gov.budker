package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalTrxSectoralDetailDao;
import com.gov.budker.model.CalTrxSectoralDetail;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("sectoral_detail")
public class CalTrxSectoralDetailDaoImpl extends AbstractDao<BigDecimal, CalTrxSectoralDetail> implements CalTrxSectoralDetailDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalTrxSectoralDetail findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteCalTrxSectoralDetailEntity(CalTrxSectoralDetail calTrxSectoralDetail) {
        BigDecimal id = null;
        if (calTrxSectoralDetail.getDetailId() == null) {
            id = super.saveOrUpdate(calTrxSectoralDetail);
        } else {
            id = calTrxSectoralDetail.getDetailId();
            getSession().update(calTrxSectoralDetail);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveListFinishingDetail(List<CalTrxSectoralDetail> listDetail) {
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
    public List<CalTrxSectoralDetail> findAllCalTrxSectoralDetailEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<CalTrxSectoralDetail>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxSectoralDetail> findCalTrxSectoralDetailBysectoralId(BigDecimal sectoralId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("sectoralId", sectoralId)));
        return (List<CalTrxSectoralDetail>) criteria.list();
    }

}
