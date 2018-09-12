package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalMstDeltaCarDao;
import com.gov.budker.model.CalMstDeltaCar;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Repository("mst_delta_car")
public class CalMstDeltaCarDaoImpl extends AbstractDao<BigDecimal, CalMstDeltaCar> implements CalMstDeltaCarDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalMstDeltaCar findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalMstDeltaCar findByActiveByYear(BigDecimal year) {
        int yearInt = Calendar.getInstance().get(Calendar.YEAR);

        Query query = getSession().createQuery("select a from CalMstDeltaCar a " +
                " where a.isBprs=1 and a.deletedStatus=0 and a.year=:yr");
        query.setParameter("yr", year);
        List<CalMstDeltaCar> listData = query.list();
        CalMstDeltaCar result = new CalMstDeltaCar();
        for (CalMstDeltaCar enti : listData) {
            result = enti;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteCalMstDeltaCarEntity(CalMstDeltaCar calMstDeltaCar) {
        BigDecimal id = null;
        if (calMstDeltaCar.getDeltaCarId() == null) {
            super.saveOrUpdate(calMstDeltaCar);
        } else {
            getSession().update(calMstDeltaCar);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveListFinishingDetail(List<CalMstDeltaCar> listDetail) {
        for (int i = 0; i < listDetail.size(); i++) {
            super.saveOrUpdate(listDetail.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalMstDeltaCar> findAllCalMstDeltaCarEntity() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("year", new BigDecimal(year))));
        return (List<CalMstDeltaCar>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalMstDeltaCar> findCalMstDeltaCarByBprsId(BigDecimal carBprsId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("deltaCarId", carBprsId)));
        return (List<CalMstDeltaCar>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalMstDeltaCar> findDeltaCarByYear(String year) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("year", new BigDecimal(year))));
        return (List<CalMstDeltaCar>) criteria.list();
    }

}
