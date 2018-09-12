package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstSectorDao;
import com.gov.budker.model.AppMstSector;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("sectoral_type")
public class AppMstSectorDaoImpl extends AbstractDao<BigDecimal, AppMstSector> implements AppMstSectorDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstSector findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteAppMstSectorEntity(AppMstSector appMstSector) {
        BigDecimal id = null;
        if (appMstSector.getSectorId() == null) {
            super.saveOrUpdate(appMstSector);
        }else{
            getSession().update(appMstSector);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstSector> findAllAppMstSectorEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstSector>) criteria.list();
    }

}
