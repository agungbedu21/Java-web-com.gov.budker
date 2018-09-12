package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstDocumentDao;
import com.gov.budker.model.AppMstDocument;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("rlz_doc")
public class AppMstDocumentDaoImpl extends AbstractDao<BigDecimal, AppMstDocument> implements AppMstDocumentDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstDocument findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteAppMstDocumentEntity(AppMstDocument appMstDocument) {
        BigDecimal id = null;
        if (appMstDocument.getDocId() == null) {
            super.saveOrUpdate(appMstDocument);
        }else{
            getSession().update(appMstDocument);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstDocument> findAllAppMstDocumentEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstDocument>) criteria.list();
    }

}
