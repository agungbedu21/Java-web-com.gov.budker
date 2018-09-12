package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalTrxGrowthDao;
import com.gov.budker.model.CalTrxGrowth;
import com.gov.budker.model.DsbCalculationDto;
import com.gov.budker.model.DsbHChartDto;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("cal_growth")
public class CalTrxGrowthDaoImpl extends AbstractDao<BigDecimal, CalTrxGrowth> implements CalTrxGrowthDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalTrxGrowth findById(BigDecimal id) {
        Query query = getSession().createQuery("select a from CalTrxGrowth a " +
                " where a.calGrowthId=:calGrowthId and a.deletedStatus=0");
        query.setParameter("calGrowthId", id);
        List<CalTrxGrowth> listData = query.list();
        CalTrxGrowth result = new CalTrxGrowth();
        for (CalTrxGrowth enti : listData) {
            result = enti;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveCalTrxGrowthEntity(CalTrxGrowth calTrxGrowth) {
        if (calTrxGrowth.getCalGrowthId() == null) {
            super.saveOrUpdate(calTrxGrowth);
        } else {
            getSession().update(calTrxGrowth);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal saveOrUpdate(CalTrxGrowth CalTrxGrowth) {
        return super.saveOrUpdate(CalTrxGrowth);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deleteCalTrxGrowthEntity(BigDecimal calGrowthId, BigDecimal userDeleted) {
        Query query = getSession().createQuery("update CalTrxGrowth a set a.deletedStatus=1, " +
                "a.userDeleted=:user, a.dateDeleted=:dateDel" +
                " where a.calGrowthId=:calGrowthId");
        query.setParameter("calGrowthId", calGrowthId).setParameter("user", userDeleted)
                .setParameter("dateDel", new Date());
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxGrowth> findAllCalTrxGrowthEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<CalTrxGrowth>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableData(BigDecimal divisionId, String year, BigDecimal triwulan) {
        Query query = getSession().createQuery("select a from CalTrxGrowth a " +
                " where a.divisionId=:divId and a.year=:year and a.triwulan=:tw and a.deletedStatus=0");
        query.setParameter("divId", divisionId).setParameter("year", year).setParameter("tw", triwulan);
        List<CalTrxGrowth> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Boolean cekAvailableDataEdit(BigDecimal divisionId, String year, BigDecimal triwulan, BigDecimal Id) {
        Query query = getSession().createQuery("select a from CalTrxGrowth a " +
                " where a.divisionId=:divId and a.year=:year and a.triwulan=:tw and" +
                " a.calGrowthId!=:id and a.deletedStatus=0");
        query.setParameter("divId", divisionId).setParameter("year", year)
                .setParameter("tw", triwulan).setParameter("id", Id);
        List<CalTrxGrowth> listData = query.list();
        if (listData.size() > 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<DsbCalculationDto> getDashboardData(BigDecimal year) {
        Query query = getSession().createSQLQuery("SELECT a.year as tahun, c.finishing_type_name as kalkulasi," +
                " a.iku_target As ikuTarget, ((sum(b.realization)/sum(b.problems))*100) as achTotal, " +
                "((((sum(b.realization)/sum(b.problems))*100)/a.iku_target)*100) as achIku" +
                " FROM app_mst_finishing_type c LEFT join cal_trx_finishing a on c.finishing_type_id=a.finishing_type" +
                " left join cal_trx_finishing_detail b on a.finishing_id = b.finishing_id" +
                " where a.year=:year" +
                " GROUP by c.finishing_type_name" +
                " UNION ALL " +
                " SELECT a.year as tahun, c.sector_name as kalkulasi, a.iku_target as ikuTarget," +
                " ((sum(b.realization)/sum(b.target))*100) as achTotal, ((((sum(b.realization)/sum(b.target))*100)/a.iku_target)*100) as achIku" +
                " FROM cal_trx_sectoral a right join app_mst_sector c on c.sector_id=a.sectoral_id " +
                " left join cal_trx_sectoral_detail b on a.sectoral_id = b.sectoral_id " +
                " where a.year=:year" +
                " GROUP by c.sector_name" +
                " UNION ALL " +
                " SELECT a.year as tahun, 'Pertumbuhan' as kalkulasi, (select iku_target from cal_trx_growth where" +
                " triwulan=(select max(triwulan) from cal_trx_growth where year=:year limit 1) and year=:year) as ikuTarget," +
                " (sum(b.yoy_cost)/sum(b.month_cost)) as achTotal, ((sum(b.month_cost)-sum(b.yoy_cost))/(sum(b.yoy_cost)*100))" +
                " as achIku from cal_trx_growth a RIGHT JOIN cal_trx_growth_detail b ON a.cal_growth_id = b.growth_id" +
                " where year=:year  group by a.year" +
                " UNION  ALL " +
                " SELECT a.year as tahun, 'CAR BUS' as kalkulasi, (select iku_target from cal_trx_car_bus where" +
                " triwulan=(select max(triwulan) from cal_trx_car_bus where year=:year limit 1) and year=:year) as ikuTarget," +
                " avg(b.achievement) as achTotal, avg(b.achievement) as achIku  from cal_trx_car_bus a RIGHT JOIN cal_trx_bus_detail b" +
                " ON a.cal_car_bus_id=b.car_bus_id where year=:year  group by a.year" +
                " UNION ALL " +
                " SELECT a.year as tahun, 'CAR BPRS' as kalkulasi, (select iku_target from cal_trx_car_bprs where triwulan=" +
                " (select max(triwulan) from cal_trx_car_bprs where year=:year limit 1) and year=:year) as ikuTarget," +
                " (((select count(detail_id) from cal_trx_bprs_detail where car_bprs_id = a.cal_car_bprs_id and is_balance=1)/" +
                " (select count(detail_id) from cal_trx_bprs_detail where car_bprs_id = a.cal_car_bprs_id))/a.iku_target) as achTotal ," +
                " (((select count(detail_id) from cal_trx_bprs_detail where car_bprs_id = a.cal_car_bprs_id and is_balance=1)/" +
                " (select count(detail_id) from cal_trx_bprs_detail where car_bprs_id = a.cal_car_bprs_id))/a.iku_target)" +
                " as ikuAch from cal_trx_car_bprs a LEFT JOIN cal_trx_bprs_detail b ON a.cal_car_bprs_id=b.car_bprs_id" +
                " where a.year=:year and a.deleted_status=0" +
                " group by a.year");
        query.setParameter("year", year)
                .setResultTransformer(Transformers.aliasToBean(DsbCalculationDto.class));

        List<DsbCalculationDto> data = query.list();
        return data;
    }

}
