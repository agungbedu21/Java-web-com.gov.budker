package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstBankTypeDetailDao;
import com.gov.budker.model.AppMstBankTypeDetail;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("mst_bank_type_detail")
public class AppMstBankTypeDetailDaoImpl extends AbstractDao<BigDecimal, AppMstBankTypeDetail> implements AppMstBankTypeDetailDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstBankTypeDetail findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteAppMstBankTypeEntity(AppMstBankTypeDetail appMstBankTypeDetail) {

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteAppMstBankTypeListEntity(List<AppMstBankTypeDetail> appMstBankTypeDetailList) {
        deleteDetail(appMstBankTypeDetailList.get(0).getTypeId());
        for (AppMstBankTypeDetail typeDetail : appMstBankTypeDetailList) {
            if (typeDetail.getDetailId() == null) {
                super.saveOrUpdate(typeDetail);
            } else {
                getSession().update(typeDetail);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public void deleteDetail(BigDecimal typeId) {
        Query query = getSession().createQuery("delete from AppMstBankTypeDetail a " +
                " where a.typeId=:typeId");
        query.setParameter("typeId", typeId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstBankTypeDetail> findAppMstBankTypeDetailEntityByBankTypeId(BigDecimal bankTypeId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("typeId", bankTypeId)));
        return (List<AppMstBankTypeDetail>) criteria.list();
    }


}
