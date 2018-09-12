package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstBankTypeDao;
import com.gov.budker.model.AppMstBankType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("mst_bank_type")
public class AppMstBankTypeDaoImpl extends AbstractDao<BigDecimal, AppMstBankType> implements AppMstBankTypeDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstBankType findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public BigDecimal saveEditDeleteAppMstBankTypeEntity(AppMstBankType AppMstBankType) {
        if (AppMstBankType.getBankTypeId() == null) {
           return super.saveOrUpdate(AppMstBankType);
        } else {
            getSession().update(AppMstBankType);
            return  AppMstBankType.getBankTypeId();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstBankType> findAllAppMstBankTypeEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstBankType>) criteria.list();
    }
}
