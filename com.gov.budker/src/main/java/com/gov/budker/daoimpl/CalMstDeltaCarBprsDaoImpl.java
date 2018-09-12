package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalMstDeltaCarBprsDao;
import com.gov.budker.model.CalMstCarBprs;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Repository("mst_delta_car_bprs")
public class CalMstDeltaCarBprsDaoImpl extends AbstractDao<BigDecimal, CalMstCarBprs> implements CalMstDeltaCarBprsDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalMstCarBprs findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalMstCarBprs findByActiveByYear(String year) {
        int yearInt = Calendar.getInstance().get(Calendar.YEAR);

        Query query = getSession().createQuery("select a from CalMstCarBprs a " +
                " where a.deletedStatus=0 and a.year=:yr");
        query.setParameter("yr", String.valueOf(year));
        List<CalMstCarBprs> listData = query.list();
        CalMstCarBprs result = new CalMstCarBprs();
        for (CalMstCarBprs enti : listData) {
            result = enti;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableData(String year) {
        Query query = getSession().createQuery("select a from CalMstCarBprs a " +
                " where a.year=:year and a.deletedStatus=0");
        query.setParameter("year", year);
        List<CalMstCarBprs> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableDataEdit(String year, BigDecimal id) {
        Query query = getSession().createQuery("select a from CalMstCarBprs a " +
                " where a.year=:year and a.deletedStatus=0 and a.bprsId!=:id");
        query.setParameter("year", year).setParameter("id", id);
        List<CalMstCarBprs> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteCalMstCarBprsEntity(CalMstCarBprs calMstCarBprs) {
        BigDecimal id = null;
        if (calMstCarBprs.getBprsId() == null) {
            super.saveOrUpdate(calMstCarBprs);
        } else {
            getSession().update(calMstCarBprs);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveListFinishingDetail(List<CalMstCarBprs> listDetail) {
        for (int i = 0; i < listDetail.size(); i++) {
            super.saveOrUpdate(listDetail.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalMstCarBprs> findAllCalMstCarBprsEntity() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("year", new BigDecimal(year))));
        return (List<CalMstCarBprs>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalMstCarBprs> findCalMstCarBprsByBprsId(BigDecimal carBprsId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("bprsId", carBprsId)));
        return (List<CalMstCarBprs>) criteria.list();
    }

}
