package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalTrxSectoralDao;
import com.gov.budker.model.CalTrxSectoral;
import com.gov.budker.model.CalTrxSectoral;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository("cal_sectoral")
public class CalTrxSectoralDaoImpl extends AbstractDao<BigDecimal, CalTrxSectoral> implements CalTrxSectoralDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalTrxSectoral findById(BigDecimal id) {
        Query query = getSession().createQuery("select a from CalTrxSectoral a " +
                " where a.calSectoralId=:calTrxSectoralId and a.deletedStatus=0");
        query.setParameter("calTrxSectoralId", id);
        List<CalTrxSectoral> listData = query.list();
        CalTrxSectoral result = new CalTrxSectoral();
        for (CalTrxSectoral enti : listData) {
            result = enti;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveCalTrxSectoralEntity(CalTrxSectoral calTrxSectoral) {
        if (calTrxSectoral.getCalSectoralId() == null) {
            super.saveOrUpdate(calTrxSectoral);
        } else {
            getSession().update(calTrxSectoral);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal saveOrUpdate(CalTrxSectoral calTrxSectoral) {
        return super.saveOrUpdate(calTrxSectoral);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteCalTrxSectoralEntity(BigDecimal calTrxSectoralId, BigDecimal userDeleted) {
        Query query = getSession().createQuery("update CalTrxSectoral a set a.deletedStatus=1, " +
                "a.userDeleted=:user, a.dateDeleted=:dateDel" +
                " where a.calSectoralId=:calTrxSectoralId");
        query.setParameter("calTrxSectoralId", calTrxSectoralId).setParameter("user", userDeleted)
                .setParameter("dateDel", new Date());
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxSectoral> findAllCalTrxSectoralEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<CalTrxSectoral>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableData(BigDecimal divisionId, String year, BigDecimal triwulan) {
        Query query = getSession().createQuery("select a from CalTrxSectoral a " +
                " where a.divisionId=:divId and a.year=:year and a.triwulan=:tw and a.deletedStatus=0");
        query.setParameter("divId", divisionId).setParameter("year", year).setParameter("tw", triwulan);
        List<CalTrxSectoral> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableDataEdit(BigDecimal divisionId, String year, BigDecimal triwulan, BigDecimal Id) {
        Query query = getSession().createQuery("select a from CalTrxSectoral a " +
                " where a.divisionId=:divId and a.year=:year and a.triwulan=:tw and" +
                " a.calSectoralId!=:id and a.deletedStatus=0");
        query.setParameter("divId", divisionId).setParameter("year", year)
                .setParameter("tw", triwulan).setParameter("id", Id);
        List<CalTrxSectoral> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

}
