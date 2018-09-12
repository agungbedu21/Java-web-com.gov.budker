package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.dao.*;
import com.gov.budker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/cal/finishing")
public class CalTrxFinishingController {

    @Autowired
    private CalTrxFinishingDao daoCalTrxFinishing;

    @Autowired
    private CalTrxFinishingDetailDao finishingDetailDao;

    @Autowired
    private AppMstDivisionDao daoDiv;

    @Autowired
    private AppMstFinishingTypeDao daoFinType;

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
//        try {
//            session.getAttribute("menuList");
//        } catch (Exception e) {
//            return "redirect:/login";
//        }
        session.setAttribute("menuName", "Penyelesaian Komitmen");
        return "/cal/pages/cal_finishing";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable, HttpSession session) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, CalTrxFinishing.class);
        if ("1".equals(session.getAttribute("roleId").toString())) {
            return result;
        }
        List<CalTrxFinishing> listData = result.getData();
        List<CalTrxFinishing> listDatanew = new ArrayList<>();
        BigDecimal id = new BigDecimal(session.getAttribute("divisionId").toString());
        for (CalTrxFinishing prk : listData) {
            if (id.equals(prk.getDivisionId())) {
                listDatanew.add(prk);
            }
        }
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        for (int i = 0; i < listDivId.size(); i++) {
            for (CalTrxFinishing prk : listData) {
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

        if (params != null && params.containsKey("finishingId")) {
            BigDecimal id = new BigDecimal(params.get("finishingId").toString());
            CalTrxFinishing data = daoCalTrxFinishing.findById(id);
            if (data != null) {

                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataCalTrxFinishing", data);
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
                        mvc.addObject("dataCalTrxFinishing", data);
                    } else {
                        return mvc;
                    }
                }
            }
        }

        dtg = new DateTimeGenerator();
        mvc.addObject("listMonth", dtg.getMonth());
        mvc.addObject("listYears", dtg.getYear());
        List<AppMstFinishingType> listFinType = new ArrayList<>();
        listFinType = daoFinType.findAllAppMstFinishingTypeEntity();
        mvc.addObject("listFinishingType", listFinType);

        mvc.setViewName("/cal/partialPages/modal_cal_finishing");
        return mvc;
    }

    @RequestMapping(value = {"/modalview"}, method = RequestMethod.POST)
    public ModelAndView getModalView(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();

        BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        Boolean isAllowed = Boolean.FALSE;

        if (params != null && params.containsKey("finishingId")) {
            BigDecimal id = new BigDecimal(params.get("finishingId").toString());
            CalTrxFinishing data = daoCalTrxFinishing.findById(id);
            if (data != null) {
                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataCalTrxFinishing", data);
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
                        mvc.addObject("dataCalTrxFinishing", data);
                    } else {
                        return mvc;
                    }
                }
            }
            List<AppMstBank> listBank = new ArrayList<>();
            listBank = daoBank.findAppMstBankByDivisionId(data.getDivisionId());
            mvc.addObject("bankList", listBank);

            List<CalTrxFinishingDetail> listDetail = finishingDetailDao.findCalTrxFinishingDetailByFinishingId(id);
            mvc.addObject("listDetail", listDetail);
        } else {
            return mvc;
        }
        dtg = new DateTimeGenerator();
        mvc.addObject("listMonth", dtg.getMonth());
        mvc.setViewName("/cal/partialPages/modal_cal_finishing_view");
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
        CalTrxFinishing calTrxFinishing = new CalTrxFinishing();
        Boolean status = Boolean.FALSE;
        String message = "";
        try {
            if (isSave == 1) {
                calTrxFinishing = new CalTrxFinishing();
                calTrxFinishing.setFinishingId(null);
                calTrxFinishing.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                calTrxFinishing.setYear(params.get("year").toString());
                calTrxFinishing.setMonth(new BigDecimal(params.get("month").toString()));
                calTrxFinishing.setFinishingType(new BigDecimal(params.get("finishingType").toString()));
                calTrxFinishing.setIkuTarget(new BigDecimal(params.get("ikuTarget").toString()));
                calTrxFinishing.setUserCreated(new BigDecimal(session.getAttribute("userId").toString()));
                calTrxFinishing.setDeletedStatus(BigDecimal.ZERO);
                calTrxFinishing.setDateCreated(new Date());

                if (daoCalTrxFinishing.cekAvailableData(calTrxFinishing.getDivisionId(), calTrxFinishing.getYear(),
                        calTrxFinishing.getMonth())) {
                    daoCalTrxFinishing.saveCalTrxFinishingEntity(calTrxFinishing);
                    status = Boolean.TRUE;
                } else {
                    status = Boolean.FALSE;
                    message = "Data Telah Digunakan";
                }
            } else if (isSave == 2) {
                BigDecimal id = new BigDecimal(params.get("finishingId").toString());
                calTrxFinishing = new CalTrxFinishing();
                calTrxFinishing = daoCalTrxFinishing.findById(id);
                calTrxFinishing.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                calTrxFinishing.setYear(params.get("year").toString());
                calTrxFinishing.setMonth(new BigDecimal(params.get("month").toString()));
                calTrxFinishing.setFinishingType(new BigDecimal(params.get("finishingType").toString()));
                calTrxFinishing.setIkuTarget(new BigDecimal(params.get("ikuTarget").toString()));
                calTrxFinishing.setUserUpdated(new BigDecimal(session.getAttribute("userId").toString()));
                calTrxFinishing.setDeletedStatus(BigDecimal.ZERO);
                calTrxFinishing.setDateUpdated(new Date());

                if (daoCalTrxFinishing.cekAvailableDataEdit(calTrxFinishing.getDivisionId(), calTrxFinishing.getYear(),
                        calTrxFinishing.getMonth(), calTrxFinishing.getFinishingId())) {
                    daoCalTrxFinishing.saveCalTrxFinishingEntity(calTrxFinishing);
                    status = Boolean.TRUE;
                } else {
                    status = Boolean.FALSE;
                    message = "Data Telah Digunakan";
                }
            } else {
                calTrxFinishing = new CalTrxFinishing();
                BigDecimal id = new BigDecimal(params.get("finsihingId").toString());
                calTrxFinishing = daoCalTrxFinishing.findById(id);
                calTrxFinishing.setDeletedStatus(BigDecimal.ONE);
                calTrxFinishing.setDateDeleted(new Date());
                calTrxFinishing.setUserDeleted(new BigDecimal(session.getAttribute("userId").toString()));
                daoCalTrxFinishing.saveCalTrxFinishingEntity(calTrxFinishing);
                status = Boolean.TRUE;
            }
            result.put("status", status);
            result.put("message", message);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
            result.put("message", "Data Gagal Disimpan!");
        }

        return result;
    }

    @RequestMapping(value = {"/detail/saveOrUpdate"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveFinishingDetail(@RequestBody Map<String, Object> params, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        CalTrxFinishingDetail calTrxFinishingDetail = new CalTrxFinishingDetail();
        List<CalTrxFinishingDetail> listDetail = new ArrayList<>();
        try {
            BigDecimal finishingId = new BigDecimal(params.get("finishingId").toString());
            List detailId = new ArrayList();
            if (params.containsKey("detailId")) {
                detailId = (List) params.get("detailId");
            }
            List problems = (List) params.get("problems");
            List realization = (List) params.get("realization");
            List explan = (List) params.get("explanation");
            List bankId = (List) params.get("bankId");
            for (int i = 0; i < bankId.size(); i++) {
                calTrxFinishingDetail = new CalTrxFinishingDetail();
                if (detailId.size() > 0 && i < detailId.size()) {
                    calTrxFinishingDetail.setDetailId(new BigDecimal(detailId.get(i).toString()));
                }
                calTrxFinishingDetail.setBankId(new BigDecimal(bankId.get(i).toString()));
                calTrxFinishingDetail.setFinishingId(finishingId);
                calTrxFinishingDetail.setProblems(problems.get(i).toString());
                calTrxFinishingDetail.setRealization(realization.get(i).toString());
                calTrxFinishingDetail.setExplanation(explan.get(i).toString());
                listDetail.add(calTrxFinishingDetail);
            }
            finishingDetailDao.saveListFinishingDetail(listDetail);
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/getbyid"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editAppMstCity(
            @RequestBody Map<String, Object> params, ModelMap model) {
        BigDecimal id = new BigDecimal(params.get("employeeId").toString());
        Map<String, Object> dataResult = new HashMap<>();
        CalTrxFinishing dataBud = new CalTrxFinishing();
        dataBud = daoCalTrxFinishing.findById(id);
        dataResult.put("dataBud", dataBud);
        return dataResult;
    }

    @RequestMapping(value = {"/delete/{calTrxFinishingId}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBudget(HttpSession session, @PathVariable(value = "calTrxFinishingId")
            BigDecimal calTrxFinishingId) {
        Map<String, Object> dataResult = new HashMap<>();


        try {
            daoCalTrxFinishing.deleteCalTrxFinishingEntity(calTrxFinishingId,
                    new BigDecimal(session.getAttribute("empId").toString()));
            dataResult.put("status", Boolean.TRUE);
        } catch(Exception ex) {
            dataResult.put("status", Boolean.FALSE);
            dataResult.put("message", "Data Gagal Dihapus!");
        }

        return dataResult;
    }
}
