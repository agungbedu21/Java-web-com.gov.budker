package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalTrxGrowthDetailDao;
import com.gov.budker.model.CalTrxGrowthDetail;
import com.gov.budker.model.RptGrowthDto;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository("growth_detail")
public class CalTrxGrowthDetailDaoImpl extends AbstractDao<BigDecimal, CalTrxGrowthDetail> implements CalTrxGrowthDetailDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalTrxGrowthDetail findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteCalTrxGrowthDetailEntity(CalTrxGrowthDetail CalTrxGrowthDetail) {
        BigDecimal id = null;
        if (CalTrxGrowthDetail.getDetailId() == null) {
            id = super.saveOrUpdate(CalTrxGrowthDetail);
        } else {
            id = CalTrxGrowthDetail.getDetailId();
            getSession().update(CalTrxGrowthDetail);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveListGrowthDetail(List<CalTrxGrowthDetail> listDetail) {
        for (int i = 0; i < listDetail.size(); i++) {
            if (listDetail.get(i).getDetailId() == null) {
                super.saveOrUpdate(listDetail.get(i));
            } else {
                getSession().update(listDetail.get(i));
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxGrowthDetail> findAllCalTrxGrowthDetailEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<CalTrxGrowthDetail>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxGrowthDetail> findCalTrxGrowthDetailByGrowthId(BigDecimal growthId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("growthId", growthId)));
        return (List<CalTrxGrowthDetail>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RptGrowthDto> getListReportGrowth(BigDecimal divId, BigDecimal year, BigDecimal tw) {
        Query quer = null;
        quer = getSession().createSQLQuery("select a.bank_name as bankName, b.rbb_growth as rbbGrowth," +
                " b.growth_target as growthTarget, b.yoy_cost as yoyCost, b.month_cost as monthCost," +
                " b.growth_value as growthValue, b.last_year_value as lastYearValue, d.iku_target as ikuTarget" +
                " from app_mst_bank a, cal_trx_growth_detail b, cal_trx_growth d " +
                " where a.bank_id = b.bank_id and d.cal_growth_id = b.growth_id and b.growth_id=" +
                " (SELECT c.cal_growth_id from cal_trx_growth c where c.year=:year" +
                " and c.triwulan=:tw and c.division_id in (8,9,10,11))");
        quer.setParameter("year", year)
                .setParameter("tw", tw)
                .setResultTransformer(Transformers.aliasToBean(RptGrowthDto.class));
        List<RptGrowthDto> result = quer.list();
        return result;
    }

    @Override
    public List<RptGrowthDto> getListReportGrowth1(BigDecimal divId, BigDecimal year, BigDecimal id) {
        return null;
    }

}
