package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalMstRiskProfileDao;
import com.gov.budker.model.CalMstRiskProfile;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Repository("mst_risk_profile")
public class CalMstRiskProfileDaoImpl extends AbstractDao<BigDecimal, CalMstRiskProfile> implements CalMstRiskProfileDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalMstRiskProfile findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalMstRiskProfile findByActiveByYear(BigDecimal year) {
        Query query = getSession().createQuery("select a from CalMstRiskProfile a " +
                " where a.isBprs=1 and a.deletedStatus=0 and a.year=:yr");
        query.setParameter("yr", year);
        List<CalMstRiskProfile> listData = query.list();
        CalMstRiskProfile result = new CalMstRiskProfile();
        for (CalMstRiskProfile enti : listData) {
            result = enti;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteCalMstRiskProfileEntity(CalMstRiskProfile calMstRiskProfile) {
        if (calMstRiskProfile.getRiskProfileId() == null) {
            super.saveOrUpdate(calMstRiskProfile);
        } else {
            calMstRiskProfile.getRiskProfileId();
            getSession().update(calMstRiskProfile);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveListFinishingDetail(List<CalMstRiskProfile> listDetail) {
        for (int i = 0; i < listDetail.size(); i++) {
            super.saveOrUpdate(listDetail.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalMstRiskProfile> findAllCalMstRiskProfileEntity() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("year", new BigDecimal(year))));
        return (List<CalMstRiskProfile>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalMstRiskProfile> findCalMstRiskProfileByBprsId(BigDecimal carBprsId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("riskProfileId", carBprsId)));
        return (List<CalMstRiskProfile>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalMstRiskProfile> findRiskProfileByYear(String year) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("year",new BigDecimal(year))));
        return (List<CalMstRiskProfile>) criteria.list();
    }

}
