package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstDivisionBankDao;
import com.gov.budker.model.AppMstDivisionBank;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("mst_division_bank")
public class AppMstDivisionBankDaoImpl extends AbstractDao<BigDecimal, AppMstDivisionBank> implements AppMstDivisionBankDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstDivisionBank findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public BigDecimal saveEditDeleteAppMstDivisionBankEntity(AppMstDivisionBank appMstDivisionBank) {
        if (appMstDivisionBank.getDivisionBankId() == null) {
            return super.saveOrUpdate(appMstDivisionBank);
        } else {
            getSession().update(appMstDivisionBank);
            return appMstDivisionBank.getDivisionBankId();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstDivisionBank> findAllAppMstDivisionBankEntity(BigDecimal divId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("divisionId", divId)));
        return (List<AppMstDivisionBank>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveListDivisionBank(List<AppMstDivisionBank> listDivBank) {
        deleteBankDivByDivisionId(listDivBank.get(0).getDivisionId());
        for (AppMstDivisionBank divBank : listDivBank) {
            getSession().save(divBank);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public void deleteBankDivByDivisionId(BigDecimal divId) {
        Query query = getSession().createQuery("delete from AppMstDivisionBank a " +
                " where a.divisionId=:divisionId");
        query.setParameter("divisionId", divId);
        query.executeUpdate();
    }
}
