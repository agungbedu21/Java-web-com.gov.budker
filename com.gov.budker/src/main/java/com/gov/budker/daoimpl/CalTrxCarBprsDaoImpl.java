package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalTrxCarBprsDao;
import com.gov.budker.model.CalTrxCarBprs;
import com.gov.budker.model.CalTrxCarBprs;
import com.gov.budker.model.RptReportBprsDto;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.transform.Transformer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository("cal_car_bprs")
public class CalTrxCarBprsDaoImpl extends AbstractDao<BigDecimal, CalTrxCarBprs> implements CalTrxCarBprsDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalTrxCarBprs findById(BigDecimal id) {
        Query query = getSession().createQuery("select a from CalTrxCarBprs a " +
                " where a.calCarBprsId=:CalTrxCarBprsId and a.deletedStatus=0");
        query.setParameter("CalTrxCarBprsId", id);
        List<CalTrxCarBprs> listData = query.list();
        CalTrxCarBprs result = new CalTrxCarBprs();
        for (CalTrxCarBprs enti : listData) {
            result = enti;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveCalTrxCarBprsEntity(CalTrxCarBprs calTrxCarBprs) {
        if (calTrxCarBprs.getCalCarBprsId() == null) {
            super.saveOrUpdate(calTrxCarBprs);
        } else {
            getSession().update(calTrxCarBprs);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal saveOrUpdate(CalTrxCarBprs CalTrxCarBprs) {
        return super.saveOrUpdate(CalTrxCarBprs);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteCalTrxCarBprsEntity(BigDecimal calTrxCarBprsId, BigDecimal userDeleted) {
        Query query = getSession().createQuery("update CalTrxCarBprs a set a.deletedStatus=1, " +
                "a.userDeleted=:user, a.dateDeleted=:dateDel" +
                " where a.calCarBprsId=:CalTrxCarBprsId");
        query.setParameter("CalTrxCarBprsId", calTrxCarBprsId).setParameter("user", userDeleted)
                .setParameter("dateDel", new Date());
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxCarBprs> findAllCalTrxCarBprsEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<CalTrxCarBprs>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableData(BigDecimal divisionId, String year, BigDecimal triwulan) {
        Query query = getSession().createQuery("select a from CalTrxCarBprs a " +
                " where a.divisionId=:divId and a.year=:year and a.triwulan=:tw and a.deletedStatus=0");
        query.setParameter("divId", divisionId).setParameter("year", year).setParameter("tw", triwulan);
        List<CalTrxCarBprs> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableDataEdit(BigDecimal divisionId, String year, BigDecimal triwulan, BigDecimal Id) {
        Query query = getSession().createQuery("select a from CalTrxCarBprs a " +
                " where a.divisionId=:divId and a.year=:year and a.triwulan=:tw and" +
                " a.calCarBprsId!=:id and a.deletedStatus=0");
        query.setParameter("divId", divisionId).setParameter("year", year)
                .setParameter("tw", triwulan).setParameter("id", Id);
        List<CalTrxCarBprs> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RptReportBprsDto> getListReportBprs(BigDecimal divId, BigDecimal year, BigDecimal triwulan) {
        Query quer = null;
        if (divId == null) {
            quer = getSession().createSQLQuery("select d.iku_target as ikuTarget, c.division_name as divisionName, a.bank_name as bankName," +
                    " b.supervision_status as supStatus,b.car_value as car, b.is_balance as isBalance," +
                    " (((SELECT count(c.is_balance) from cal_trx_bprs_detail c where c.is_balance=1 and c.car_bprs_id in " +
                    " (SELECT e.cal_car_bprs_id from cal_trx_car_bprs e where e.year=:yr and e.triwulan=:tw))/" +
                    " (SELECT count(c.is_balance) from cal_trx_bprs_detail c where c.car_bprs_id in" +
                    " (SELECT e.cal_car_bprs_id from cal_trx_car_bprs e where e.year=:yr and  e.triwulan=:tw)))/d.iku_target) as ach" +
                    " from app_mst_bank a LEFT join cal_trx_bprs_detail b " +
                    " on a.bank_id = b.bank_id LEFT join cal_trx_car_bprs d" +
                    " on b.car_bprs_id=d.cal_car_bprs_id LEFT join app_mst_division c" +
                    " on c.division_id=d.division_id" +
                    " where d.year=:yr and d.triwulan=:tw")
                    .setParameter("yr", year)
                    .setParameter("tw", triwulan)
                    .setResultTransformer(Transformers.aliasToBean(RptReportBprsDto.class));
        } else {
            quer = getSession().createSQLQuery("select d.iku_target as ikuTarget, c.division_name as divisionName, a.bank_name as bankName," +
                    " b.supervision_status as supStatus,b.car_value as car, b.is_balance as isBalance," +
                    " (((SELECT count(c.is_balance) from cal_trx_bprs_detail c where c.is_balance=1 and c.car_bprs_id in " +
                    " (SELECT e.cal_car_bprs_id from cal_trx_car_bprs e where e.division_id=:divId and e.year=:yr and e.triwulan=:tw))/" +
                    " (SELECT count(c.is_balance) from cal_trx_bprs_detail c where c.car_bprs_id in" +
                    " (SELECT e.cal_car_bprs_id from cal_trx_car_bprs e where e.division_id=:divId and e.year=:yr and  e.triwulan=:tw)))/d.iku_target) as ach" +
                    " from app_mst_bank a LEFT join cal_trx_bprs_detail b " +
                    " on a.bank_id = b.bank_id LEFT join cal_trx_car_bprs d" +
                    " on b.car_bprs_id=d.cal_car_bprs_id LEFT join app_mst_division c" +
                    " on c.division_id=d.division_id" +
                    " where d.year=:yr and d.triwulan=:tw and d.division_id=:divId")
                    .setParameter("yr", year)
                    .setParameter("divId", divId)
                    .setParameter("tw", triwulan)
                    .setResultTransformer(Transformers.aliasToBean(RptReportBprsDto.class));
        }

        List<RptReportBprsDto> listResult = quer.list();
        return listResult;

    }

}
