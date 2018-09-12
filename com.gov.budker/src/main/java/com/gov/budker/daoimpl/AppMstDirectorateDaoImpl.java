package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstDirectorateDao;
import com.gov.budker.model.AppMstDirectorate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("directorate")
public class AppMstDirectorateDaoImpl extends AbstractDao<BigDecimal, AppMstDirectorate> implements AppMstDirectorateDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstDirectorate findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveAppMstDirectorateEntity(AppMstDirectorate directorate) {
        if (directorate.getDirectorateId() == null) {
            super.saveOrUpdate(directorate);
        } else {
            getSession().update(directorate);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteAppMstDirectorateEntity(BigDecimal id) {
        Query query = getSession().createSQLQuery("update AppMstDirectorate a" +
                "set a.deletedStatus=1  where directorateId =:directorateId");
        query.setParameter("menuId", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstDirectorate> findAllAppMstDirectorateEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstDirectorate>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstDirectorate> findAppMstDirectorateEntity(BigDecimal AppMstDirectorateEntityId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("directorateId", AppMstDirectorateEntityId)));
        return (List<AppMstDirectorate>) criteria.list();
    }

}
