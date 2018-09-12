package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstMenuRoleDao;
import com.gov.budker.model.AppMstMenuRole;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("menuRole")
public class AppMstMenuRoleDaoImpl extends AbstractDao<BigDecimal, AppMstMenuRole> implements AppMstMenuRoleDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstMenuRole findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveEditDeleteAppMstMenuRoleEntity(AppMstMenuRole menuRole) {
        super.saveOrUpdate(menuRole);
    }

    @Override
    @Transactional
    public void saveListMenuRole(List<AppMstMenuRole> menuRole) {
        for (AppMstMenuRole appMstMenuRole : menuRole) {
            super.saveOrUpdate(appMstMenuRole);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstMenuRole> findAllAppMstMenuRoleEntity() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", BigDecimal.ZERO));
        return (List<AppMstMenuRole>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstMenuRole> findAppMstMenuRoleEntity(BigDecimal AppMstMenuRoleEntityId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        criteria.add(Restrictions.and(Restrictions.eq("menuRoleId", AppMstMenuRoleEntityId)));
        return (List<AppMstMenuRole>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstMenuRole> getListMenuRoleByRoleId(BigDecimal roleId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", BigDecimal.ZERO));
        criteria.add(Restrictions.and(Restrictions.eq("roleId", roleId)));
        return (List<AppMstMenuRole>) criteria.list();
    }


}
