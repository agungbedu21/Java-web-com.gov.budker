package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstFinishingTypeDao;
import com.gov.budker.model.AppMstFinishingType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("finishing_type")
public class AppMstFinishingTypeDaoImpl extends AbstractDao<BigDecimal, AppMstFinishingType> implements AppMstFinishingTypeDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstFinishingType findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteAppMstFinishingTypeEntity(AppMstFinishingType appMstFinishingType) {
        BigDecimal id = null;
        if (appMstFinishingType.getFinishingTypeId() == null) {
            id = super.saveOrUpdate(appMstFinishingType);
        }else{
            id = appMstFinishingType.getFinishingTypeId();
            getSession().update(appMstFinishingType);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstFinishingType> findAllAppMstFinishingTypeEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstFinishingType>) criteria.list();
    }

}
