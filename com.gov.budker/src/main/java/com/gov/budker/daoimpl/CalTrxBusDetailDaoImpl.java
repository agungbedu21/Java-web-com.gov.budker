package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalTrxBusDetailDao;
import com.gov.budker.model.CalTrxBusDetail;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("bus_detail")
public class CalTrxBusDetailDaoImpl extends AbstractDao<BigDecimal, CalTrxBusDetail> implements CalTrxBusDetailDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalTrxBusDetail findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteCalTrxBusDetailEntity(CalTrxBusDetail calTrxBusDetail) {
        BigDecimal id = null;
        if (calTrxBusDetail.getDetailId() == null) {
            id = super.saveOrUpdate(calTrxBusDetail);
        } else {
            id = calTrxBusDetail.getDetailId();
            getSession().update(calTrxBusDetail);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveListBusDetail(List<CalTrxBusDetail> listDetail) {
        for (int i = 0; i < listDetail.size(); i++) {
            if(listDetail.get(i).getDetailId()==null){
                super.saveOrUpdate(listDetail.get(i));
            }else{
                getSession().update(listDetail.get(i));
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxBusDetail> findAllCalTrxBusDetailEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<CalTrxBusDetail>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxBusDetail> findCalTrxBusDetailByBusId(BigDecimal carBusId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("carBusId", carBusId)));
        return (List<CalTrxBusDetail>) criteria.list();
    }

}
