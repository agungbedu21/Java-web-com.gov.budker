package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalTrxCarBusDao;
import com.gov.budker.model.CalTrxCarBus;
import com.gov.budker.model.CalTrxCarBus;
import com.gov.budker.model.RptReportBprsDto;
import com.gov.budker.model.RptReportBusDto;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository("cal_car_bus")
public class CalTrxCarBusDaoImpl extends AbstractDao<BigDecimal, CalTrxCarBus> implements CalTrxCarBusDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalTrxCarBus findById(BigDecimal id) {
        Query query = getSession().createQuery("select a from CalTrxCarBus a " +
                " where a.calCarBusId=:calCarBusId and a.deletedStatus=0");
        query.setParameter("calCarBusId", id);
        List<CalTrxCarBus> listData = query.list();
        CalTrxCarBus result = new CalTrxCarBus();
        for (CalTrxCarBus enti : listData) {
            result = enti;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveCalTrxCarBusEntity(CalTrxCarBus calTrxCarBus) {
        if (calTrxCarBus.getCalCarBusId() == null) {
            super.saveOrUpdate(calTrxCarBus);
        } else {
            getSession().update(calTrxCarBus);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal saveOrUpdate(CalTrxCarBus CalTrxCarBus) {
        return super.saveOrUpdate(CalTrxCarBus);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteCalTrxCarBusEntity(BigDecimal calCarBusId, BigDecimal userDeleted) {
        Query query = getSession().createQuery("update CalTrxCarBus a set a.deletedStatus=1, " +
                "a.userDeleted=:user, a.dateDeleted=:dateDel" +
                " where a.calCarBusId=:calCarBusId");
        query.setParameter("calCarBusId", calCarBusId).setParameter("user", userDeleted)
                .setParameter("dateDel", new Date());
        query.executeUpdate();
//        query = null;
//        query = getSession().createQuery("delete CalTrxBusDetail a " +
//                " where a.carBusId=:calCarBusId");
//        query.setParameter("calCarBusId", calCarBusId);
//        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxCarBus> findAllCalTrxCarBusEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<CalTrxCarBus>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableData(BigDecimal divisionId, String year, BigDecimal triwulan) {
        Query query = getSession().createQuery("select a from CalTrxCarBus a " +
                " where a.divisionId=:divId and a.year=:year and a.triwulan=:tw and a.deletedStatus=0");
        query.setParameter("divId", divisionId).setParameter("year", year).setParameter("tw", triwulan);
        List<CalTrxCarBus> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableDataEdit(BigDecimal divisionId, String year, BigDecimal triwulan, BigDecimal Id) {
        Query query = getSession().createQuery("select a from CalTrxCarBus a " +
                " where a.divisionId=:divId and a.year=:year and a.triwulan=:tw and" +
                " a.calCarBusId!=:id and a.deletedStatus=0");
        query.setParameter("divId", divisionId).setParameter("year", year)
                .setParameter("tw", triwulan).setParameter("id", Id);
        List<CalTrxCarBus> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RptReportBusDto> getListReportBus(BigDecimal divId, BigDecimal year, BigDecimal triwulan) {
        Query quer = null;
        if (divId.toString().equals("0")) {
            quer = getSession().createSQLQuery("select a.division_name as divisionName, b.bank_name as bankName," +
                    " c.car_bus_value as carBusValue," +
                    " d.risk_profile_name as riskProfile,d.car_min_value carMin, (c.car_bus_value-d.car_min_value) as deltaCar," +
                    " c.achievement as ach, e.iku_target as ikuTarget" +
                    " from app_mst_bank b LEFT join cal_trx_bus_detail c on b.bank_id=c.bank_id" +
                    " left JOIN cal_trx_car_bus e on c.car_bus_id=e.cal_car_bus_id" +
                    " left join cal_mst_risk_profile d on c.risk_profile_id=d.risk_profile_id and d.year=e.year" +
                    " left join app_mst_division a on a.division_id=e.division_id" +
                    " where e.year=:yr and e.triwulan=:tw")
                    .setParameter("yr", year)
                    .setParameter("tw", triwulan)
                    .setResultTransformer(Transformers.aliasToBean(RptReportBusDto.class));
        } else {
            quer = getSession().createSQLQuery("select a.division_name as divisionName, b.bank_name as bankName," +
                    " c.car_bus_value as carBusValue," +
                    " d.risk_profile_name as riskProfile,d.car_min_value carMin, (c.car_bus_value-d.car_min_value) as deltaCar," +
                    " c.achievement as ach, e.iku_target as ikuTarget" +
                    " from app_mst_bank b LEFT join cal_trx_bus_detail c on b.bank_id=c.bank_id" +
                    " left JOIN cal_trx_car_bus e on c.car_bus_id=e.cal_car_bus_id" +
                    " left join cal_mst_risk_profile d on c.risk_profile_id=d.risk_profile_id and d.year=e.year" +
                    " left join app_mst_division a on a.division_id=e.division_id" +
                    " where e.year=:yr and e.division_id=:divId and e.triwulan=:tw")
                    .setParameter("yr", year)
                    .setParameter("divId", divId)
                    .setParameter("tw", triwulan)
                    .setResultTransformer(Transformers.aliasToBean(RptReportBusDto.class));
        }

        List<RptReportBusDto> listResult = quer.list();
        return listResult;
    }

}
