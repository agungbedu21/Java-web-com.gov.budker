package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstMenuRoleDao;
import com.gov.budker.dao.AppMstRoleDao;
import com.gov.budker.model.AppMstMenuRole;
import com.gov.budker.model.AppMstRole;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository("role")
public class AppMstRoleDaoImpl extends AbstractDao<BigDecimal, AppMstRole> implements AppMstRoleDao {

    @Autowired
    private AppMstMenuRoleDao menuRoleDao;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteRole(AppMstRole role) {
        getSession().update(role);
        Query query = getSession().createQuery("delete from AppMstMenuRole a " +
                " where a.roleId=:roleId");
        query.setParameter("roleId", role.getRoleId());
        query.executeUpdate();

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstRole findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveEditDeleteAppMstRoleEntity(AppMstRole role, List menuId) {
        BigDecimal id = null;
        if (role.getRoleId() == null) {
            id = super.saveOrUpdate(role);
        } else {
            id = role.getRoleId();
            getSession().update(role);
        }

        saveDataMenu(id, menuId);

    }

    @Transactional
    public void saveDataMenu(BigDecimal roleId, List menuId) {

        List<AppMstMenuRole> listMenuRole = new ArrayList<>();
        AppMstMenuRole menuRoleEntity = new AppMstMenuRole();
        for (int i = 0; i < menuId.size(); i++) {
            menuRoleEntity = new AppMstMenuRole();
            menuRoleEntity.setRoleId(roleId);
            menuRoleEntity.setMenuId(new BigDecimal(menuId.get(i).toString()));
            menuRoleEntity.setDeletedStatus(BigDecimal.ZERO);
            menuRoleEntity.setStatus(BigDecimal.ONE);
            listMenuRole.add(menuRoleEntity);
        }
        deleteMenuRoleByRoleID(roleId);
        menuRoleDao.saveListMenuRole(listMenuRole);
    }

    @Transactional
    public void deleteMenuRoleByRoleID(BigDecimal roleId) {
        Query query = getSession().createQuery("delete from AppMstMenuRole a " +
                " where a.roleId=:roleId");
        query.setParameter("roleId", roleId);
        query.executeUpdate();
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstRole> findAllAppMstRoleEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstRole>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstRole> findAppMstRoleEntity(BigDecimal AppMstRoleEntityId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("roleId", AppMstRoleEntityId)));
        return (List<AppMstRole>) criteria.list();
    }


}
