package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstMenuDao;
import com.gov.budker.model.AppMstMenu;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("menu")
public class AppMstMenuDaoImpl extends AbstractDao<BigDecimal, AppMstMenu> implements AppMstMenuDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstMenu findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveAppMstMenuEntity(AppMstMenu menu) {
        if (menu.getMenuId() == null) {
            super.saveOrUpdate(menu);
        } else {
            getSession().update(menu);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteAppMstMenuEntity(BigDecimal id) {
        Query query = getSession().createSQLQuery("update AppMstMenu a" +
                "set a.deletedStatus=1  where menuId =:menuId");
        query.setParameter("menuId", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstMenu> findAllAppMstMenuEntity() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", BigDecimal.ZERO));
        criteria.add(Restrictions.isNull("parentId"));

        return (List<AppMstMenu>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstMenu> findParentMenu() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.isEmpty("parentId")));
        return (List<AppMstMenu>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstMenu> getMenuEmployee(BigDecimal employeeId) {
        Query query = getSession().createQuery("SELECT a from AppMstMenu a " +
                " where a.menuId in (select b.menuId from AppMstMenuRole b where b.roleId =" +
                " (select c.roleId from AppMstEmployee c where c.employeeId =:empId ))" +
                " AND a.deletedStatus=0 order by a.menuOrder asc");
        query.setParameter("empId", employeeId);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstMenu> getMenuByParentId(BigDecimal parentId) {
        Query query = getSession().createQuery("SELECT a from AppMstMenu a " +
                " where a.parentId=:parentId and a.deletedStatus=0 order by a.menuOrder asc");
        query.setParameter("parentId", parentId);
        return query.list();
    }
}
