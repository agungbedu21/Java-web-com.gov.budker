package com.gov.budker.daoimpl;

import com.gov.budker.dao.AbstractDao;
import com.gov.budker.dao.CalTrxFinishingDetailDao;
import com.gov.budker.model.CalTrxFinishingDetail;
import com.gov.budker.model.RptFinishingADetailDto;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository("finishing_detail")
public class CalTrxFinishingDetailDaoImpl extends AbstractDao<BigDecimal, CalTrxFinishingDetail> implements CalTrxFinishingDetailDao {

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public CalTrxFinishingDetail findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveEditDeleteCalTrxFinishingDetailEntity(CalTrxFinishingDetail calTrxFinishingDetail) {
        BigDecimal id = null;
        if (calTrxFinishingDetail.getDetailId() == null) {
            id = super.saveOrUpdate(calTrxFinishingDetail);
        } else {
            id = calTrxFinishingDetail.getDetailId();
            getSession().update(calTrxFinishingDetail);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveListFinishingDetail(List<CalTrxFinishingDetail> listDetail) {
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
    public List<CalTrxFinishingDetail> findAllCalTrxFinishingDetailEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<CalTrxFinishingDetail>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<CalTrxFinishingDetail> findCalTrxFinishingDetailByFinishingId(BigDecimal finishingId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("finishingId", finishingId)));
        return (List<CalTrxFinishingDetail>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RptFinishingADetailDto> getListFinishingA(BigDecimal divId, BigDecimal year, BigDecimal tw) {
        List<RptFinishingADetailDto> result = new ArrayList<>();
        List id = new ArrayList();
        if (tw.toString().equals("1")) {
            id.add(1);
            id.add(2);
            id.add(3);
        } else if (tw.toString().equals("2")) {
            id.add(4);
            id.add(5);
            id.add(6);
        } else if (tw.toString().equals("3")) {
            id.add(7);
            id.add(8);
            id.add(9);
        } else if (tw.toString().equals("4")) {
            id.add(10);
            id.add(11);
            id.add(12);
        }
        Query quer = null;
        quer = getSession().createSQLQuery("SELECt a.bank_name as bankName, b.iku_target as ikuTarget," +
                " c.problems as problems, c.realization as realization,c.explanation as explanation" +
                " from app_mst_bank a, cal_trx_finishing b, cal_trx_finishing_detail c" +
                " where a.bank_id = c.bank_id and c.finishing_id in" +
                " (select m.finishing_id from cal_trx_finishing m where m.year=:yearS and m.month in (:monthId)" +
                " and m.division_id in (8,9,10,11) and m.finishing_type=1)");
        quer.setParameter("yearS", year).setParameterList("monthId", id)
                .setResultTransformer(Transformers.aliasToBean(RptFinishingADetailDto.class));
        result = quer.list();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RptFinishingADetailDto> getListFinishingB(BigDecimal divId, BigDecimal year, BigDecimal tw) {
        List<RptFinishingADetailDto> result = new ArrayList<>();
        List id = new ArrayList();
        if (tw.toString().equals("1")) {
            id.add(1);
            id.add(2);
            id.add(3);
        } else if (tw.toString().equals("2")) {
            id.add(4);
            id.add(5);
            id.add(6);
        } else if (tw.toString().equals("3")) {
            id.add(7);
            id.add(8);
            id.add(9);
        } else if (tw.toString().equals("4")) {
            id.add(10);
            id.add(11);
            id.add(12);
        }
        Query quer = null;
        quer = getSession().createSQLQuery("SELECt a.bank_name as bankName, b.iku_target as ikuTarget," +
                " c.problems as problems, c.realization as realization,c.explanation as explanation" +
                " from app_mst_bank a, cal_trx_finishing b, cal_trx_finishing_detail c" +
                " where a.bank_id = c.bank_id and c.finishing_id in" +
                " (select m.finishing_id from cal_trx_finishing m where m.year=:yearS and m.month in (:monthId)" +
                " and m.division_id in (8,9,10,11) and m.finishing_type=2)");
        quer.setParameter("yearS", year).setParameterList("monthId", id)
                .setResultTransformer(Transformers.aliasToBean(RptFinishingADetailDto.class));
        result = quer.list();
        return result;
    }

}
