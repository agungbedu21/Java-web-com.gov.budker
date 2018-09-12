package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalTrxFinishingDao;
import com.gov.budker.model.CalTrxFinishing;
import com.gov.budker.model.CalTrxFinishing;
import com.gov.budker.model.CalTrxFinishingDetail;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository("cal_finishing")
public class CalTrxFinishingDaoImpl extends AbstractDao<BigDecimal, CalTrxFinishing> implements CalTrxFinishingDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalTrxFinishing findById(BigDecimal id) {
        Query query = getSession().createQuery("select a from CalTrxFinishing a " +
                " where a.finishingId=:calTrxFinishingId and a.deletedStatus=0");
        query.setParameter("calTrxFinishingId", id);
        List<CalTrxFinishing> listData = query.list();
        CalTrxFinishing result = new CalTrxFinishing();
        for (CalTrxFinishing enti : listData) {
            result = enti;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveCalTrxFinishingEntity(CalTrxFinishing calTrxFinishing) {
        if (calTrxFinishing.getFinishingId() == null) {
            super.saveOrUpdate(calTrxFinishing);
        } else {
            getSession().update(calTrxFinishing);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal saveOrUpdate(CalTrxFinishing calTrxFinishing) {
        return super.saveOrUpdate(calTrxFinishing);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteCalTrxFinishingEntity(BigDecimal calTrxFinishingId, BigDecimal userUpdated) {
        Query query = getSession().createQuery("update CalTrxFinishing a set a.deletedStatus=1, " +
                "a.userDeleted=:user, a.dateDeleted=:dateDel" +
                " where a.finishingId=:calTrxFinishingId");
        query.setParameter("calTrxFinishingId", calTrxFinishingId).setParameter("user", userUpdated)
                .setParameter("dateDel", new Date());
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxFinishing> findAllCalTrxFinishingEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<CalTrxFinishing>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableData(BigDecimal divisionId, String year, BigDecimal month) {
        Query query = getSession().createQuery("select a from CalTrxFinishing a " +
                " where a.divisionId=:divId and a.year=:year and a.month=:month and a.deletedStatus=0");
        query.setParameter("divId", divisionId).setParameter("year", year).setParameter("month", month);
        List<CalTrxFinishing> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableDataEdit(BigDecimal divisionId, String year, BigDecimal month, BigDecimal Id) {
        Query query = getSession().createQuery("select a from CalTrxFinishing a " +
                " where a.divisionId=:divId and a.year=:year and a.month=:month and" +
                " a.finishingId!=:id and a.deletedStatus=0");
        query.setParameter("divId", divisionId).setParameter("year", year)
                .setParameter("month", month).setParameter("id", Id);
        List<CalTrxFinishing> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

}
