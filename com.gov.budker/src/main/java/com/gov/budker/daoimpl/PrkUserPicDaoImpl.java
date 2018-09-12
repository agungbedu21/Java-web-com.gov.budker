package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.PrkUserPicDao;
import com.gov.budker.model.PrkUserPic;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("mst_user_pic")
public class PrkUserPicDaoImpl extends AbstractDao<BigDecimal, PrkUserPic> implements PrkUserPicDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public PrkUserPic findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal saveEditDeletePrkUserPicEntity(PrkUserPic prkUserPic) {
        if (prkUserPic.getUserPicId() == null) {
            return super.saveOrUpdate(prkUserPic);
        } else {
            getSession().update(prkUserPic);
            return prkUserPic.getProkerId();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PrkUserPic> findAllPrkUserPicEntity(BigDecimal prokerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("prokerId", prokerId)));
        return (List<PrkUserPic>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveListDivisionBank(List<PrkUserPic> listuserPic) {
        deleteBankDivByDivisionId(listuserPic.get(0).getProkerId());
        for (PrkUserPic userPic : listuserPic) {
            getSession().save(userPic);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public void deleteBankDivByDivisionId(BigDecimal prokerId) {
        Query query = getSession().createQuery("delete from PrkUserPic a " +
                " where a.prokerId=:prokerId");
        query.setParameter("prokerId", prokerId);
        query.executeUpdate();
    }
}
