package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstDivisionDao;
import com.gov.budker.model.AppMstDivision;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("division")
public class AppMstDivisionDaoImpl extends AbstractDao<BigDecimal, AppMstDivision> implements AppMstDivisionDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstDivision findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public BigDecimal saveAppMstDivisionEntity(AppMstDivision appMstDivision) {
        if (appMstDivision.getDivisionId() == null) {
            return super.saveOrUpdate(appMstDivision);
        } else {
            getSession().update(appMstDivision);
            return appMstDivision.getDivisionId();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteAppMstDivisionEntity(BigDecimal id) {
        Query query = getSession().createSQLQuery("update AppMstDivision a" +
                "set a.deletedStatus=1  where menuId =:menuId");
        query.setParameter("menuId", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstDivision> findAllAppMstDivisionEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstDivision>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstDivision> findAppMstDivisionEntity(BigDecimal divisionId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.ne("divisionId", divisionId)));
        return (List<AppMstDivision>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstDivision> getDivisionByDirectorateId(BigDecimal directorateId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("directorateId", directorateId)));
        return (List<AppMstDivision>) criteria.list();
    }

}
