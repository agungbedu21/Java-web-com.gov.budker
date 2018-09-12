package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstBankDao;
import com.gov.budker.model.AppMstBank;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("mst_bank")
public class AppMstBankDaoImpl extends AbstractDao<BigDecimal, AppMstBank> implements AppMstBankDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstBank findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveEditDeleteAppMstBankEntity(AppMstBank appMstBank) {
        BigDecimal id = null;
        if (appMstBank.getBankId() == null) {
            id = super.saveOrUpdate(appMstBank);
        } else {
            id = appMstBank.getBankId();
            getSession().update(appMstBank);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstBank> findAllAppMstBankEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstBank>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstBank> findAppMstBankByDivisionId(BigDecimal divisionId) {
        Query query = getSession().createQuery("SELECT a FROM AppMstBank a WHERE a.bankId in " +
                "(select b.bankId from AppMstBankTypeDetail b where b.typeId in" +
                "(select c.bankTypeId from AppMstBankType c where c.bankTypeId in " +
                "(select d.bankTypeId from AppMstDivisionBank d where d.divisionId=:divisionId)))");
        query.setParameter("divisionId", divisionId);
        return (List<AppMstBank>) query.list();
    }

}
