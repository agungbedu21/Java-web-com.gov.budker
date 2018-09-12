package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstMenuRoleDao;
import com.gov.budker.dao.AppMstRoleDao;
import com.gov.budker.dao.AppMstRoleDivisionDao;
import com.gov.budker.model.AppMstMenuRole;
import com.gov.budker.model.AppMstRole;
import com.gov.budker.model.AppMstRoleDivision;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository("role_division")
public class AppMstRoleDivisionDaoImpl extends AbstractDao<BigDecimal, AppMstRoleDivision> implements AppMstRoleDivisionDao {

    @Autowired
    private AppMstMenuRoleDao menuRoleDao;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteRoleByDivId(BigDecimal divId) {
        Query query = getSession().createQuery("delete from AppMstRoleDivision a " +
                " where a.divisionId=:divisionId");
        query.setParameter("divisionId",divId);
        query.executeUpdate();

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstRoleDivision findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveEditDeleteAppMstRoleEntity(List<AppMstRoleDivision> listRole) {
        deleteMenuRoleByRoleID(listRole.get(0).getDivisionId());
        for (AppMstRoleDivision role : listRole) {
            if (role.getRoleDivisionId() == null) {
                super.saveOrUpdate(role);
            } else {
                getSession().update(role);
            }
        }

    }

    @Transactional
    public void deleteMenuRoleByRoleID(BigDecimal divisionId) {
        Query query = getSession().createQuery("delete from AppMstRoleDivision a " +
                " where a.divisionId=:divisionId");
        query.setParameter("divisionId", divisionId);
        query.executeUpdate();
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstRoleDivision> findAllAppMstRoleDivisionEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstRoleDivision>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstRoleDivision> findAppMstRoleDivisionEntity(BigDecimal divisionId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("divisionId", divisionId)));
        return (List<AppMstRoleDivision>) criteria.list();
    }


}
