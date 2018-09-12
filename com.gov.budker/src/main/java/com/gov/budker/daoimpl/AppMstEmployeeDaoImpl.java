package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.AppMstEmployeeDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.gov.budker.model.AppMstEmployee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("employee")
public class AppMstEmployeeDaoImpl extends AbstractDao<BigDecimal, AppMstEmployee> implements AppMstEmployeeDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstEmployee findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveAppMstEmployeeEntity(AppMstEmployee employee) {
        if(employee.getEmployeeId()==null){
            super.saveOrUpdate(employee);
        }else{
            getSession().update(employee);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public AppMstEmployee login(String employeeUserName, String employeePassword) {
        AppMstEmployee result = new AppMstEmployee();
        Query query = getSession().createQuery("SELECT a from AppMstEmployee a " +
                " where a.employeeUserName=:employeeUserName AND a.employeePassword=:employeePassword" +
                " AND a.deletedStatus=0");
        query.setString("employeeUserName", employeeUserName);
        query.setString("employeePassword", employeePassword);
        List resultList = new ArrayList<>();
        List<AppMstEmployee> entityList = query.list();
        return entityList.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal saveOrUpdate(AppMstEmployee employee) {
        if(employee.getEmployeeId()==null){
            super.saveOrUpdate(employee);
        }else{
            getSession().update(employee);

        }
        return BigDecimal.ZERO;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteAppMstEmployeeEntity(String employeeId) {
        Query query = getSession().createSQLQuery("delete from AppMstEmployeeEntity where employeeId = :employeeId");
        query.setString("employeeId", employeeId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstEmployee> findAllAppMstEmployeeEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstEmployee>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstEmployee> findAppMstEmployeeEntity(BigDecimal divId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("employeeDivision", divId)));
        return (List<AppMstEmployee>) criteria.list();
    }

}
