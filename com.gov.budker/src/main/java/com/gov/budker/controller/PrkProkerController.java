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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/prk/proker")
public class PrkProkerController {

    @Autowired
    private PrkProkerDao daoProker;

    @Autowired
    private PrkMonthTargetDao daoMonth;

    @Autowired
    private PrkIkuTwTargetDao daoIku;

    @Autowired
    private PrkMainProkerDao daoMainProker;

    @Autowired
    private AppMstEmployeeDao daoEmp;

    @Autowired
    private AppMstDirectorateDao daoDir;

    @Autowired
    private PrkUserPicDao daoPic;

    @Autowired
    private AppMstDivisionDao daoDiv;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session,
                               HttpServletResponse response, HttpServletRequest request) {
        try {

        } catch (Exception e) {
            return "redirect:/login";
        }
        session.setAttribute("menuName", "Program Kerja");
        return "/prk/pages/prk_proker";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable,
                                           HttpSession session) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, PrkProker.class);
        if ("1".equals(session.getAttribute("roleId").toString())) {
            return result;
        }
        List<PrkProker> listData = result.getData();
        List<PrkProker> listDatanew = new ArrayList<>();
        BigDecimal id = new BigDecimal(session.getAttribute("divisionId").toString());
        for (PrkProker prk : listData) {
            if (id.equals(prk.getDivisionId())) {
                listDatanew.add(prk);
            }
        }

        List listDivId = (List) session.getAttribute("listDivisionAccess");
        for (int i = 0; i < listDivId.size(); i++) {
            for (PrkProker prk : listData) {
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
        List divList = new ArrayList();
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
        if (params != null && params.containsKey("prokerId")) {
            listDivision = daoDiv.findAllAppMstDivisionEntity();
            mvc.addObject("listDivision", listDivision);
            BigDecimal id = new BigDecimal(params.get("prokerId").toString());

            List<PrkUserPic> listPic = new ArrayList<>();
            listPic = daoPic.findAllPrkUserPicEntity(id);
            mvc.addObject("picUser", listPic);

            PrkProker data = daoProker.findById(id);
            if (data != null) {
                mvc.addObject("prokerData", data);
            }
            List<AppMstEmployee> listEmployee = new ArrayList<>();
            listEmployee = daoEmp.findAllAppMstEmployeeEntity();
            mvc.addObject("listEmp", listEmployee);

            List<PrkIkuTwTarget> ikuList = new ArrayList<>();
            ikuList = daoIku.findPrkIkuTwTargetByprokerId(new BigDecimal(params.get("prokerId").toString()));
            List<PrkMonthTarget> monthList = new ArrayList<>();
            monthList = daoMonth.findPrkMonthTargetByprokerId(new BigDecimal(params.get("prokerId").toString()));


            mvc.addObject("iku", ikuList);
            mvc.addObject("monthTarget", monthList);
        }
        DateTimeGenerator dg = new DateTimeGenerator();

        mvc.addObject("listYear", dg.getYear());
        List<PrkMainProker> listMainProker = new ArrayList<>();
        List<PrkMainProker> listNewMAinProker = new ArrayList<>();
        listMainProker = daoMainProker.findAllPrkMainProkerEntity();
        if (session.getAttribute("roleId").toString().equals("1")) {
            mvc.addObject("listMainProker", listMainProker);
        } else {
            for (PrkMainProker prk : listMainProker) {
                if (prk.getDivisionId().toString().equals(prk.getDivisionId().toString())) {
                    listNewMAinProker.add(prk);
                    break;
                }
            }
            for (PrkMainProker prk : listMainProker) {
                for (int i = 0; i < listDivId.size(); i++) {
                    if (prk.getDivisionId().toString().equals(listDivId.get(i).toString())) {
                        listNewMAinProker.add(prk);
                        break;
                    }
                }
            }
            mvc.addObject("listMainProker", listNewMAinProker);
        }
        mvc.addObject("userList", daoEmp.findAllAppMstEmployeeEntity());
        mvc.setViewName("/prk/partialPages/modal_prk_proker");
        return mvc;
    }


    /*
     * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(HttpSession session, @RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", Boolean.FALSE);
        PrkProker proker = new PrkProker();
        PrkIkuTwTarget ikuTarget = new PrkIkuTwTarget();
        PrkMonthTarget monthTarget = new PrkMonthTarget();
        PrkUserPic userPic = new PrkUserPic();


        List<PrkIkuTwTarget> listIkuTarget = new ArrayList<>();
        List<PrkMonthTarget> listMonthTarget = new ArrayList<>();
        List<PrkUserPic> listUserPic = new ArrayList<>();

        List ikuTargetList = (List) params.get("ikuTarget");
        List targetList = (List) params.get("target");
        List picUser = (List) params.get("picUser");
        try {
            String sDate1 = params.get("targetDate").toString();
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            if (params.get("prokerId").toString() != "") {
                proker = daoProker.findById(new BigDecimal(params.get("prokerId").toString()));
                listIkuTarget = daoIku.findPrkIkuTwTargetByprokerId(proker.getProkerId());
                listMonthTarget = daoMonth.findPrkMonthTargetByprokerId(proker.getProkerId());
            }
            proker.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
            proker.setProkerYear(new BigDecimal(params.get("prokerYear").toString()));
            proker.setIsIku(new BigDecimal(params.get("isIku").toString()));
            if (params.get("isIku").equals("1")) {
                proker.setIkuCode(params.get("ikuCode").toString());
            }
            proker.setMainProkerName(params.get("mainProkerName").toString());
            proker.setSubMainProkerName(params.get("subProkerName").toString());
            proker.setDateTarget(date1);
            proker.setProkerBudget(params.get("prokerBudget").toString().replace(",", ""));
            proker.setStatus(BigDecimal.ONE);
            proker.setDeletedStatus(BigDecimal.ZERO);

            if (params.get("prokerId").toString() == "") {
                proker.setUserCreated(new BigDecimal(session.getAttribute("userId").toString()));
                proker.setDateCreated(new Date());
                for (int i = 0; i < ikuTargetList.size(); i++) {
                    ikuTarget = new PrkIkuTwTarget();
                    ikuTarget.setIkuTarget(ikuTargetList.get(i).toString());
                    ikuTarget.setIkuTw(new BigDecimal(i + 1));
                    listIkuTarget.add(ikuTarget);
                }
                for (int i = 0; i < targetList.size(); i++) {
                    monthTarget = new PrkMonthTarget();
                    monthTarget.setTarget(targetList.get(i).toString());
                    monthTarget.setMonth(new BigDecimal(i + 1));
                    listMonthTarget.add(monthTarget);
                }

                for (int i = 0; i < picUser.size(); i++) {
                    userPic = new PrkUserPic();
                    userPic.setUserId(new BigDecimal(picUser.get(i).toString()));
                    listUserPic.add(userPic);
                }
                daoProker.saveCompleteProker(proker, listIkuTarget, listMonthTarget, listUserPic);
            } else if (params.get("prokerId").toString() != null) {
                List<PrkIkuTwTarget> listIkuTargetNew = new ArrayList<>();
                List<PrkMonthTarget> listMonthTargetNew = new ArrayList<>();
                for (int i = 0; i < listIkuTarget.size(); i++) {
                    ikuTarget = new PrkIkuTwTarget();
                    ikuTarget = listIkuTarget.get(i);
                    ikuTarget.setIkuTarget(ikuTargetList.get(i).toString());
                    ikuTarget.setIkuTw(new BigDecimal(i + 1));
                    listIkuTargetNew.add(ikuTarget);
                }
                for (int i = 0; i < listMonthTarget.size(); i++) {
                    monthTarget = new PrkMonthTarget();
                    monthTarget = listMonthTarget.get(i);
                    monthTarget.setTarget(targetList.get(i).toString());
                    monthTarget.setMonth(new BigDecimal(i + 1));
                    listMonthTargetNew.add(monthTarget);
                }

                for (int i = 0; i < picUser.size(); i++) {
                    userPic = new PrkUserPic();
                    userPic.setUserId(new BigDecimal(picUser.get(i).toString()));
                    listUserPic.add(userPic);
                }
                daoProker.saveCompleteProker(proker, listIkuTargetNew, listMonthTargetNew, listUserPic);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/getbyid"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editAppMstCity(
            @RequestBody Map<String, Object> params) {
        BigDecimal id = new BigDecimal(params.get("prokerId").toString());
        Map<String, Object> dataResult = new HashMap<>();
        PrkProker proker = new PrkProker();
        proker = daoProker.findById(id);
        dataResult.put("proker", proker);
        return dataResult;
    }

    @RequestMapping(value = {"/delete/{prokerId}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteproker(@PathVariable(value = "prokerId") BigDecimal prokerId) {
        Map<String, Object> dataResult = new HashMap<>();
        daoProker.deletePrkProkerEntity(prokerId);
        dataResult.put("status", Boolean.TRUE);
        return dataResult;
    }

    @RequestMapping(value = {"/getprokerbydivid/{divid}/{year}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProkerByDiv(@PathVariable(value = "divid") BigDecimal divisionId,
                                              @PathVariable(value = "year") BigDecimal year) {
        Map<String, Object> dataResult = new HashMap<>();
        List<PrkProker> listProkerByDiv = daoProker.getProkerByDivisionId(divisionId, year);
        dataResult.put("data", listProkerByDiv);
        return dataResult;
    }

    @RequestMapping(value = {"/getprokerbyid/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getById(@PathVariable(value = "id") BigDecimal prokerId) {
        Map<String, Object> dataResult = new HashMap<>();
        PrkProker listProkerByDiv = daoProker.findById(prokerId);
        dataResult.put("data", listProkerByDiv);

        DateTimeGenerator dg = new DateTimeGenerator();
        List<PrkIkuTwTarget> listIkuTw = new ArrayList<>();
        listIkuTw = daoIku.findPrkIkuTwTargetByprokerId(prokerId);
        List<PrkMonthTarget> listMonth = new ArrayList<>();
        listMonth = daoMonth.findPrkMonthTargetByprokerId(prokerId);
        dataResult.put("month", dg.getMonth());
        dataResult.put("dataMonth", listMonth);
        dataResult.put("dataIku", listIkuTw);
        return dataResult;
    }

    @RequestMapping(value = {"/gethighchartdata"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBudget(@RequestBody(required = false) Map<String, Object> params, HttpSession session) {
        Map<String, Object> dataResult = new HashMap<>();
        BigDecimal year = null;
        BigDecimal month = null;
        List listMonth = new ArrayList();
        try {
            year = new BigDecimal(params.get("year").toString());
        } catch (Exception ex) {
        }
        try {
            month = new BigDecimal(params.get("month").toString());
            if (month.toString().equals("1")) {
                listMonth.add(BigDecimal.ONE);
            } else if (month.toString().equals("2")) {
                for (int i = 1; i <= 2; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            } else if (month.toString().equals("3")) {
                for (int i = 1; i <= 3; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            } else if (month.toString().equals("4")) {
                for (int i = 1; i <= 4; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            } else if (month.toString().equals("5")) {
                for (int i = 1; i <= 5; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            } else if (month.toString().equals("6")) {
                for (int i = 1; i <= 6; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            } else if (month.toString().equals("7")) {
                for (int i = 1; i <= 7; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            } else if (month.toString().equals("8")) {
                for (int i = 1; i <= 8; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            } else if (month.toString().equals("9")) {
                for (int i = 1; i <= 9; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            } else if (month.toString().equals("10")) {
                for (int i = 1; i <= 10; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            } else if (month.toString().equals("11")) {
                for (int i = 1; i <= 11; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            } else if (month.toString().equals("12")) {
                for (int i = 1; i <= 12; i++) {
                    listMonth.add(BigDecimal.valueOf(i));
                }
            }
        } catch (Exception ex) {

        }
        dataResult = daoProker.getProkerDetail(year, listMonth);
        return dataResult;
    }

    @RequestMapping(value = {"/getlistprokerdivision"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getProkerDashboard(@RequestBody(required = false) Map<String, Object> params, HttpSession session) {
        Map<String, Object> dataResult = new HashMap<>();
        BigDecimal year = null;
        try {
            year = new BigDecimal(params.get("year").toString());
        } catch (Exception ex) {
        }
        dataResult.put("data", daoProker.listDtoProker(year));
        return dataResult;
    }

    @RequestMapping(value = {"/getpiechart"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPieCHart(@RequestBody(required = false) Map<String, Object> params, HttpSession session) {
        Map<String, Object> dataResult = new HashMap<>();
        try {
            BigDecimal year = new BigDecimal(params.get("year").toString());
            BigDecimal triwulan = new BigDecimal(params.get("triwulan").toString());
            List monthList = new ArrayList();
            if (triwulan.toString().equals("1")) {
                monthList.add(BigDecimal.ONE);
                monthList.add(BigDecimal.valueOf(2));
                monthList.add(BigDecimal.valueOf(3));
            } else if (triwulan.toString().equals("2")) {
                monthList.add(BigDecimal.ONE);
                monthList.add(BigDecimal.valueOf(2));
                monthList.add(BigDecimal.valueOf(3));
                monthList.add(BigDecimal.valueOf(4));
                monthList.add(BigDecimal.valueOf(5));
                monthList.add(BigDecimal.valueOf(6));
            } else if (triwulan.toString().equals("3")) {
                monthList.add(BigDecimal.ONE);
                monthList.add(BigDecimal.valueOf(2));
                monthList.add(BigDecimal.valueOf(3));
                monthList.add(BigDecimal.valueOf(4));
                monthList.add(BigDecimal.valueOf(5));
                monthList.add(BigDecimal.valueOf(6));
                monthList.add(BigDecimal.valueOf(7));
                monthList.add(BigDecimal.valueOf(8));
                monthList.add(BigDecimal.valueOf(9));
            } else if (triwulan.toString().equals("4")) {
                monthList.add(BigDecimal.ONE);
                monthList.add(BigDecimal.valueOf(2));
                monthList.add(BigDecimal.valueOf(3));
                monthList.add(BigDecimal.valueOf(4));
                monthList.add(BigDecimal.valueOf(5));
                monthList.add(BigDecimal.valueOf(6));
                monthList.add(BigDecimal.valueOf(7));
                monthList.add(BigDecimal.valueOf(8));
                monthList.add(BigDecimal.valueOf(9));
                monthList.add(BigDecimal.valueOf(10));
                monthList.add(BigDecimal.valueOf(11));
                monthList.add(BigDecimal.valueOf(12));
            }
            DsbPieDto data = daoProker.getDataPiePrk(year, monthList);
            dataResult.put("balance", data.getBalance() - data.getRealization());
            dataResult.put("realization", data.getRealization());

        } catch (Exception e) {

        }

        return dataResult;
    }
}
