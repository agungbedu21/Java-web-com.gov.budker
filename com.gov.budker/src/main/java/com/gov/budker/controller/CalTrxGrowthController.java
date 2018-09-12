package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.dao.*;
import com.gov.budker.model.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/cal/growth")
public class CalTrxGrowthController {

    @Autowired
    private CalTrxGrowthDao daoCalTrxGrowth;

    @Autowired
    private CalTrxGrowthDetailDao growthDetailDao;

    @Autowired
    private CalMstDeltaCarDao daoDeltaCar;

    @Autowired
    private AppMstDivisionDao daoDiv;

    @Autowired
    private CalMstRiskProfileDao daoRiskProfile;


    @Autowired
    private AppMstBankDao daoBank;

    @Autowired
    private MessageSource messageSource;

    private DateTimeGenerator dtg;

    /*
     * Add a new AppMstCity.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session,
                               HttpServletResponse response, HttpServletRequest request) {

        session.setAttribute("menuName", "Pertumbuhan");
        return "cal/pages/cal_growth";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable, HttpSession session) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, CalTrxGrowth.class);
        if ("1".equals(session.getAttribute("roleId").toString())) {
            return result;
        }
        List<CalTrxGrowth> listData = result.getData();
        List<CalTrxGrowth> listDatanew = new ArrayList<>();
        BigDecimal id = new BigDecimal(session.getAttribute("divisionId").toString());
        for (CalTrxGrowth prk : listData) {
            if (id.equals(prk.getDivisionId())) {
                listDatanew.add(prk);
            }
        }
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        for (int i = 0; i < listDivId.size(); i++) {
            for (CalTrxGrowth prk : listData) {
                if (listDivId.get(i).toString().equals(prk.getDivisionId().toString())) {
                    listDatanew.add(prk);
                }
            }
        }
        result.setData(listDatanew);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        List<AppMstDivision> listDivision = new ArrayList<>();
        Boolean isAllowed = Boolean.FALSE;
        if (session.getAttribute("roleId").toString().equals("1")) {
            listDivision = daoDiv.findAllAppMstDivisionEntity();
            mvc.addObject("listDivision", listDivision);
        } else {
            //get division list with access security
            listDivision.add(daoDiv.findById(divId));
            //getListAccess available division
            for (int i = 0; i < listDivId.size(); i++) {
                listDivision.add(daoDiv.findById(new BigDecimal(listDivId.get(i).toString())));
            }
            mvc.addObject("listDivision", listDivision);
        }
        if (params != null && params.containsKey("calGrowthId")) {
            BigDecimal id = new BigDecimal(params.get("calGrowthId").toString());
            CalTrxGrowth data = daoCalTrxGrowth.findById(id);
            if (data != null) {
                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataCalTrxGrowth", data);
                    isAllowed = Boolean.TRUE;
                } else {
                    if (data.getDivisionId().equals(divId)) {
                        isAllowed = Boolean.TRUE;
                    } else {
                        for (int i = 0; i < listDivId.size(); i++) {
                            if (listDivId.get(i).equals(data.getDivisionId())) {
                                isAllowed = Boolean.TRUE;
                                break;
                            }
                        }
                    }
                    if (isAllowed) {
                        mvc.addObject("dataCalTrxGrowth", data);
                    } else {
                        return mvc;
                    }
                }
            }
        }

        dtg = new DateTimeGenerator();
        mvc.addObject("listTriwulan", dtg.getTriwulan());
        mvc.addObject("listYears", dtg.getYear());

        mvc.setViewName("cal/partialPages/modal_cal_growth");
        return mvc;
    }

    @RequestMapping(value = {"/modalview"}, method = RequestMethod.POST)
    public ModelAndView getModalView(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();

        Boolean isAllowed = Boolean.FALSE;
        BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        if (params != null && params.containsKey("calGrowthId")) {
            BigDecimal id = new BigDecimal(params.get("calGrowthId").toString());
            CalTrxGrowth data = daoCalTrxGrowth.findById(id);
            if (data != null) {

                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataCalTrxGrowth", data);
                    isAllowed = Boolean.TRUE;
                } else {
                    if (data.getDivisionId().equals(divId)) {
                        isAllowed = Boolean.TRUE;
                    } else {
                        for (int i = 0; i < listDivId.size(); i++) {
                            if (listDivId.get(i).equals(data.getDivisionId())) {
                                isAllowed = Boolean.TRUE;
                                break;
                            }
                        }
                    }
                    if (isAllowed) {
                        mvc.addObject("dataCalTrxGrowth", data);
                    } else {
                        return mvc;
                    }
                }
            }
            List<AppMstBank> listBank = new ArrayList<>();
            listBank = daoBank.findAppMstBankByDivisionId(data.getDivisionId());
            mvc.addObject("bankList", listBank);

            List<CalTrxGrowthDetail> listDetail = growthDetailDao.findCalTrxGrowthDetailByGrowthId(id);
            mvc.addObject("listDetail", listDetail);
        } else {
            return mvc;
        }
        dtg = new DateTimeGenerator();
        mvc.addObject("listTriwulan", dtg.getTriwulan());

        mvc.setViewName("/cal/partialPages/modal_cal_growth_view");
        return mvc;
    }


    /*
     * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(@RequestBody Map<String, Object> params, HttpSession session,
                                              @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        CalTrxGrowth CalTrxGrowth = new CalTrxGrowth();
        Boolean status = Boolean.FALSE;
        String message = "";
        try {
            if (isSave == 1) {
                CalTrxGrowth = new CalTrxGrowth();
                CalTrxGrowth.setCalGrowthId(null);
                CalTrxGrowth.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                CalTrxGrowth.setYear(params.get("year").toString());
                CalTrxGrowth.setTriwulan(new BigDecimal(params.get("triwulan").toString()));
                CalTrxGrowth.setIkuTarget(new BigDecimal(params.get("ikuTarget").toString()));
                CalTrxGrowth.setUserCreated(new BigDecimal(session.getAttribute("userId").toString()));
                CalTrxGrowth.setDeletedStatus(BigDecimal.ZERO);
                CalTrxGrowth.setDateCreated(new Date());
                if (daoCalTrxGrowth.cekAvailableData(CalTrxGrowth.getDivisionId(), CalTrxGrowth.getYear(),
                        CalTrxGrowth.getTriwulan())) {
                    daoCalTrxGrowth.saveCalTrxGrowthEntity(CalTrxGrowth);
                    status = Boolean.TRUE;
                    message = "Data Berhasil Disimpan!";
                } else {
                    status = Boolean.FALSE;
                    message = "Data Telah Digunakan!!";
                }
            } else if (isSave == 2) {
                BigDecimal id = new BigDecimal(params.get("calGrowthId").toString());
                CalTrxGrowth = new CalTrxGrowth();
                CalTrxGrowth = daoCalTrxGrowth.findById(id);
                CalTrxGrowth.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                CalTrxGrowth.setYear(params.get("year").toString());
                CalTrxGrowth.setTriwulan(new BigDecimal(params.get("triwulan").toString()));
                CalTrxGrowth.setIkuTarget(new BigDecimal(params.get("ikuTarget").toString()));
                CalTrxGrowth.setUserUpdated(new BigDecimal(session.getAttribute("userId").toString()));
                CalTrxGrowth.setDeletedStatus(BigDecimal.ZERO);
                CalTrxGrowth.setDateUpdated(new Date());
                if (daoCalTrxGrowth.cekAvailableDataEdit(CalTrxGrowth.getDivisionId(), CalTrxGrowth.getYear(),
                        CalTrxGrowth.getTriwulan(), CalTrxGrowth.getCalGrowthId())) {
                    daoCalTrxGrowth.saveCalTrxGrowthEntity(CalTrxGrowth);
                    status = Boolean.TRUE;
                } else {
                    message = "Data Telah Digunakan";
                }
            } else {
                CalTrxGrowth = new CalTrxGrowth();
                BigDecimal id = new BigDecimal(params.get("carBusId").toString());
                CalTrxGrowth = daoCalTrxGrowth.findById(id);
                CalTrxGrowth.setDeletedStatus(BigDecimal.ONE);
                CalTrxGrowth.setDateDeleted(new Date());
                CalTrxGrowth.setUserDeleted(new BigDecimal(session.getAttribute("userId").toString()));
                daoCalTrxGrowth.saveCalTrxGrowthEntity(CalTrxGrowth);
            }
            result.put("status", status);
            result.put("message", message);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/detail/saveOrUpdate"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> savesectoralDetail(@RequestBody Map<String, Object> params, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        CalTrxGrowthDetail calTrxGrowthDetail = new CalTrxGrowthDetail();
        List<CalTrxGrowthDetail> listDetail = new ArrayList<>();
        try {
            BigDecimal calGrowthId = new BigDecimal(params.get("calGrowthId").toString());
            List detailId = new ArrayList();
            if (params.containsKey("detailId")) {
                detailId = (List) params.get("detailId");
            }
            List rbbGrowthList = (List) params.get("rbbGrowth");
            List growthTargetList = (List) params.get("growthTarget");
            List bankId = (List) params.get("bankId");
            List yoyCostList = (List) params.get("yoyCost");
            List monthCostList = (List) params.get("monthCost");
            List growthValueList = (List) params.get("growthValue");

            for (int i = 0; i < bankId.size(); i++) {
                calTrxGrowthDetail = new CalTrxGrowthDetail();
                if (detailId.size() > 0) {
                    calTrxGrowthDetail.setDetailId(new BigDecimal(detailId.get(i).toString()));
                }
                calTrxGrowthDetail.setGrowthId(calGrowthId);
                calTrxGrowthDetail.setRbbGrowth(new BigDecimal(rbbGrowthList.get(i).toString()));
                calTrxGrowthDetail.setBankId(new BigDecimal(bankId.get(i).toString()));
                calTrxGrowthDetail.setGrowthTarget(new BigDecimal(growthTargetList.get(i).toString()));
                calTrxGrowthDetail.setYoyCost(yoyCostList.get(i).toString());
                calTrxGrowthDetail.setMonthCost(monthCostList.get(i).toString());
                calTrxGrowthDetail.setGrowthValue(new BigDecimal(growthValueList.get(i).toString()));
                listDetail.add(calTrxGrowthDetail);
            }
            growthDetailDao.saveListGrowthDetail(listDetail);
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/delete/{CalTrxGrowthId}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBudget(HttpSession session, @PathVariable(value = "CalTrxGrowthId")
            BigDecimal CalTrxGrowthId) {
        Map<String, Object> dataResult = new HashMap<>();


        try {
            daoCalTrxGrowth.deleteCalTrxGrowthEntity(CalTrxGrowthId,
                    new BigDecimal(session.getAttribute("empId").toString()));
            dataResult.put("status", Boolean.TRUE);
        } catch (Exception ex){
            dataResult.put("status", Boolean.FALSE);
            dataResult.put("message", "Data Gagal Dihapus!");
        }

        return dataResult;
    }

    @RequestMapping(value = {"/gettabelcalculation"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBudget(@RequestBody(required = false) Map<String, Object> params, HttpSession session) {
        List<DsbCalculationDto> dataResult = new ArrayList<>();
        BigDecimal year = null;
        BigDecimal divisionId = null;
        try {
            year = new BigDecimal(params.get("year").toString());
        } catch (Exception ex) {
        }
        dataResult = daoCalTrxGrowth.getDashboardData(year);
        Map<String, Object> res = new HashMap<>();
        res.put("data", dataResult);
        return res;
    }
}
