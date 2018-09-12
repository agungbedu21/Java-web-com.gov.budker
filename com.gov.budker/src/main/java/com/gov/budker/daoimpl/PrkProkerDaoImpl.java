package com.gov.budker.daoimpl;

import com.gov.budker.dao.*;
import com.gov.budker.model.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Repository("proker")
public class PrkProkerDaoImpl extends AbstractDao<BigDecimal, PrkProker> implements PrkProkerDao {


    @Autowired
    private PrkIkuTwTargetDao daoIku;

    @Autowired
    private PrkMonthTargetDao daoMonth;

    @Autowired
    private PrkUserPicDao daoPic;


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public PrkProker findById(BigDecimal id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void savePrkProkerEntity(PrkProker employee) {
        super.saveOrUpdate(employee);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void saveCompleteProker(PrkProker proker, List<PrkIkuTwTarget> listIkuTarget, List<PrkMonthTarget> listMonthTarget, List<PrkUserPic> listPic) {
        BigDecimal prokerId = null;
        if (proker.getProkerId() == null) {
            prokerId = super.saveOrUpdate(proker);
            for (PrkIkuTwTarget ikuTarget : listIkuTarget) {
                ikuTarget.setProkerId(prokerId);
                ikuTarget.setStatus(proker.getStatus());
                ikuTarget.setDeletedStatus(proker.getDeletedStatus());
                daoIku.saveEditDeletePrkIkuTwTargetEntity(ikuTarget);
            }
            for (PrkMonthTarget monthTarget : listMonthTarget) {
                monthTarget.setProkerId(prokerId);
                monthTarget.setDeletedStatus(proker.getDeletedStatus());
                monthTarget.setStatus(proker.getStatus());
                daoMonth.saveEditDeletePrkMonthTargetEntity(monthTarget);
            }
            for (PrkUserPic pic : listPic) {
                pic.setProkerId(prokerId);
                daoPic.saveEditDeletePrkUserPicEntity(pic);
            }
        } else {
            proker.setDateUpdated(new Date());
            getSession().update(proker);
            for (PrkIkuTwTarget ikuTarget : listIkuTarget) {
                ikuTarget.setStatus(proker.getStatus());
                ikuTarget.setDeletedStatus(proker.getDeletedStatus());
                daoIku.saveEditDeletePrkIkuTwTargetEntity(ikuTarget);
            }
            for (PrkMonthTarget monthTarget : listMonthTarget) {
                monthTarget.setDeletedStatus(proker.getDeletedStatus());
                monthTarget.setStatus(proker.getStatus());
                daoMonth.saveEditDeletePrkMonthTargetEntity(monthTarget);
            }
            for (PrkUserPic pic : listPic) {
                pic.setProkerId(proker.getProkerId());
                daoPic.saveEditDeletePrkUserPicEntity(pic);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal saveOrUpdate(PrkProker proker) {
        return super.saveOrUpdate(proker);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void deletePrkProkerEntity(BigDecimal prokerId) {
        Query query = getSession().createQuery("update PrkProker a set a.deletedStatus=1" +
                " where a.prokerId=:prokerId");
        query.setParameter("prokerId", prokerId);
        query.executeUpdate();
        query = null;
        query = getSession().createQuery("update PrkIkuTwTarget a set a.deletedStatus=1" +
                " where a.prokerId=:prokerId");
        query.setParameter("prokerId", prokerId);
        query.executeUpdate();
        query = null;
        query = getSession().createQuery("update PrkMonthTarget a set a.deletedStatus=1" +
                " where a.prokerId=:prokerId");
        query.setParameter("prokerId", prokerId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PrkProker> findAllPrkProkerEntity() {
        Criteria criteria = createEntityCriteria();
        return (List<PrkProker>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PrkProker> findPrkProkerEntity(BigDecimal budgetId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("prokerId", budgetId)));
        return (List<PrkProker>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<PrkProker> getProkerByDivisionId(BigDecimal divisionId, BigDecimal year) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("divisionId", divisionId)));
        criteria.add(Restrictions.and(Restrictions.eq("prokerYear", year)));
        return (List<PrkProker>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Map<String, Object> getProkerDetail(BigDecimal year, List month) {
        Query query = null;
//        if (year == null && month == null) {
//            query = getSession().createSQLQuery("SELECT a.division_name as divisionName, b.proker_budget as prokerBudget," +
//                    " sum(c.realization) as realization" +
//                    " FROM app_mst_division a LEFT JOIN prk_proker b on b.division_id=a.division_id " +
//                    " LEFT JOIN prk_month_target d " +
//                    " on b.proker_id = d.proker_id" +
//                    " LEFT JOIN rlz_trx_realization_month c " +
//                    " on d.month_target_id = c.month_tgt_id " +
//                    " where a.deleted_status=0 GROUP BY a.division_name").setResultTransformer(Transformers.aliasToBean(DsbHChartDto.class));
//        } else if (year != null && month != null) {
        query = getSession().createSQLQuery("SELECT a.division_name as divisionName, b.proker_budget as prokerBudget," +
                " sum(c.realization) as realization" +
                " FROM app_mst_division a LEFT JOIN prk_proker b on b.division_id=a.division_id and b.proker_year=:yr" +
                " LEFT JOIN prk_month_target d " +
                " on b.proker_id = d.proker_id and d.month in(:mt)" +
                " LEFT JOIN rlz_trx_realization_month c " +
                " on d.month_target_id = c.month_tgt_id " +
                " where a.deleted_status=0 GROUP BY a.division_name")
                .setParameter("yr", String.valueOf(year))
                .setParameterList("mt", month)
                .setResultTransformer(Transformers.aliasToBean(DsbHChartDto.class));
//        } else if (year == null && month != null) {
//            query = getSession().createSQLQuery("SELECT a.division_name as divisionName, b.proker_budget as prokerBudget," +
//                    " sum(c.realization) as realization" +
//                    " FROM app_mst_division a LEFT JOIN prk_proker b on b.division_id=a.division_id" +
//                    " LEFT JOIN prk_month_target d " +
//                    " on b.proker_id = d.proker_id and d.month=:mt" +
//                    " LEFT JOIN rlz_trx_realization_month c " +
//                    " on d.month_target_id = c.month_tgt_id " +
//                    " where a.deleted_status=0 GROUP BY a.division_name")
//                    .setParameter("mt", month)
//                    .setResultTransformer(Transformers.aliasToBean(DsbHChartDto.class));
//        } else if (month == null && year != null) {
//            query = getSession().createSQLQuery("SELECT a.division_name as divisionName, b.proker_budget as prokerBudget," +
//                    " sum(c.realization) as realization" +
//                    " FROM app_mst_division a LEFT JOIN prk_proker b on b.division_id=a.division_id and b.proker_year=:yr" +
//                    " LEFT JOIN prk_month_target d " +
//                    " on b.proker_id = d.proker_id" +
//                    " LEFT JOIN rlz_trx_realization_month c " +
//                    " on d.month_target_id = c.month_tgt_id " +
//                    " where a.deleted_status=0 GROUP BY a.division_name")
//                    .setParameter("yr", String.valueOf(year))
//                    .setResultTransformer(Transformers.aliasToBean(DsbHChartDto.class));
//        }
        List<DsbHChartDto> reslt = new ArrayList<>();

        reslt = query.list();
        List Div = new ArrayList();
        List Bud = new ArrayList();
        List Rlz = new ArrayList();
        Double max = new Double(0);
        for (DsbHChartDto db : reslt) {
            if (db.getDivisionName() == null) {
                continue;
            } else {
                Div.add(db.getDivisionName());
            }
            if (db.getProkerBudget() == null || db.getProkerBudget() == "") {
                Bud.add(0);
            } else {
                try {
                    if (max < Double.parseDouble(db.getProkerBudget())) {
                        try {
                            max = Double.parseDouble(db.getProkerBudget());
                        } catch (Exception ex) {
                            max = Double.valueOf(0);
                        }

                    }
                } catch (Exception ex) {

                }

                try {
                    Bud.add(Integer.parseInt(db.getProkerBudget()));
                } catch (Exception ex) {
                    Bud.add(0);
                }

            }
            if (db.getRealization() == null) {
                Rlz.add(0);
            } else {
                if (max < db.getRealization()) {
                    max = db.getRealization();
                }
                Rlz.add(db.getRealization());
            }
        }
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("divisionList", Div);
        mapResult.put("budgetList", Bud);
        mapResult.put("realizationList", Rlz);
        mapResult.put("max", max);
        return mapResult;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<DsbProkerListDto> listDtoProker(BigDecimal year) {
        Query query = getSession().createSQLQuery("SELECT a.division_name as divisionName, b.iku_code as ikuCode," +
                " b.main_proker_name as prokerName, sum(b.proker_budget)-" +
                " (SELECT sum(realization) from rlz_trx_realization_month WHERE proker_id=b.proker_id) as achievement" +
                " FROM app_mst_division a LEFT JOIN prk_proker b on b.division_id=a.division_id" +
                " WHERE b.proker_year=:yr" +
                " GROUP by a.division_name")
                .setParameter("yr", year).setResultTransformer(Transformers.aliasToBean(DsbProkerListDto.class));
        List<DsbProkerListDto> listData = new ArrayList<>();
        listData = query.list();
        return listData;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public DsbPieDto getDataPiePrk(BigDecimal year, List monthList) {
        Query query = getSession().createSQLQuery("select sum(a.proker_budget) as balance," +
                " (select sum(b.realization) from rlz_trx_realization_month b where b.proker_id in(select proker_id" +
                " from prk_proker where proker_year=:yr)" +
                " and b.month_tgt_id in(SELECT c.month_target_id from prk_month_target c where c.proker_id in" +
                " (select proker_id from prk_proker where proker_year=:yr) and c.month in(:lm))) as realization" +
                " from prk_proker a " +
                " where a.proker_year=:yr")
                .setParameter("yr", year).setParameterList("lm", monthList)
                .setResultTransformer(Transformers.aliasToBean(DsbPieDto.class));
        List<DsbPieDto> listData = new ArrayList<>();
        listData = query.list();
        for (DsbPieDto dt : listData) {
            return dt;
        }
        return new DsbPieDto();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RptProkerDto> getProkerReport(BigDecimal year, BigDecimal tw, BigDecimal month) {
        Query quer = null;
        quer = getSession().createSQLQuery("SELECT a.iku_code as ikuCode, a.main_proker_name as prokerName," +
                " a.proker_budget as prokerBudget, sum(b.realization) as realization, sum(b.subsequence) as subsequence" +
                " from prk_proker a left join rlz_trx_realization_month b " +
                " on a.proker_id = b.proker_id" +
                " where a.proker_year=:year AND a.is_iku=1" +
                " GROUP by a.proker_id, a.proker_id" +
                " ORDER by a.proker_id desc");
        quer.setParameter("year", year)
                .setResultTransformer(Transformers.aliasToBean(RptProkerDto.class));
        List<RptProkerDto> result = quer.list();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RptProkerIkuDto> getProkerIkuReport(BigDecimal year, BigDecimal tw, BigDecimal month) {
        Query quer = null;
        quer = getSession().createSQLQuery("select a.iku_code as ikuCode, a.main_proker_name as prokerName," +
                " a.pic_user_name as picName, b.target as target, b.periode as periode, b.realization as realization," +
                " b.achievement as achievement, b.explanation as explanation" +
                " from rlz_trx_realization_iku b LEFT JOIN prk_proker a" +
                " on b.proker_id=a.proker_id" +
                " where a.proker_year=:year and a.division_id in (4,8,9,10,11) and a.is_iku=1");
        quer.setParameter("year", year)
                .setResultTransformer(Transformers.aliasToBean(RptProkerIkuDto.class));
        List<RptProkerIkuDto> result = quer.list();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<RptProkerNonIkuDto> getProkerNonIkuReport(BigDecimal year, BigDecimal month) {
        Query quer = null;
        quer = getSession().createSQLQuery("select a.main_proker_name as prokerName,a.date_target as dateTarget," +
                " a.pic_user_name as picName, b.progress as progress" +
                " from rlz_trx_realization_month b LEFT JOIN prk_proker a" +
                " on b.proker_id=a.proker_id" +
                " where a.proker_year=:year and a.division_id in (8,9,10,11) and a.is_iku=0" +
                " order by b.proker_id asc");
        quer.setParameter("year", year)
                .setResultTransformer(Transformers.aliasToBean(RptProkerNonIkuDto.class));
        List<RptProkerNonIkuDto> result = quer.list();
        return result;
    }
}
