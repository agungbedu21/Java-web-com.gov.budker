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
@RequestMapping("/rlz/realization")
public class RlzProkerController {

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
    private AppMstDivisionDao daoDiv;

    @Autowired
    private AppMstDocumentDao daoDoc;

    @Autowired
    private RlzTrxRealizationDao daoRlz;

    @Autowired
    private RlzTrxRealizationIkuDao daoRlzIku;

    @Autowired
    private RlzTrxRealizationMonthDao daoRlzMonth;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session,
                               HttpServletResponse response, HttpServletRequest request) {
        try {

        } catch (Exception e) {
            return "redirect:/login";
        }
        session.setAttribute("menuName", "Realisasi");
        return "/rlz/pages/rlz_proker";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable, HttpSession session) {
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
        divList = (List) session.getAttribute("listDivisionAccess");
        DateTimeGenerator dg = new DateTimeGenerator();
        Boolean isAlowed = Boolean.FALSE;
        int divId = 0;
        if ("1".equals(session.getAttribute("roleId").toString())) {
            isAlowed = Boolean.TRUE;
        }
        if (params != null && params.containsKey("prokerId")) {

            BigDecimal id = new BigDecimal(params.get("prokerId").toString());
            PrkProker data = daoProker.findById(id);


            if (!isAlowed) {
                //cek is user allow to update realisasi or not
                isAlowed = Boolean.TRUE;
                for (int i = 0; i < divList.size(); i++) {
                    if (data.getDivisionId().equals(divList.get(i)) ||
                            data.getDivisionId().equals(session.getAttribute("divisionId"))) {
                        isAlowed = Boolean.TRUE;
                        break;
                    }
                }
            }
            //ceking is user allow to update realisasi or not
            if (!isAlowed) {
                return mvc;
            }
            if (data != null) {
                mvc.addObject("prokerData", data);
                divId = Integer.parseInt(data.getDivisionId().toString());
                List<AppMstDivision> listDivision = new ArrayList<>();
                listDivision.add(daoDiv.findById(new BigDecimal(divId)));
                mvc.addObject("listDivision", listDivision);
            }


            List<AppMstEmployee> listEmployee = new ArrayList<>();
            listEmployee = daoEmp.findAllAppMstEmployeeEntity();
            mvc.addObject("listEmp", listEmployee);

            List<RlzTrxRealizationIku> ikuList = new ArrayList<>();
            ikuList = daoRlzIku.findRlzTrxRealizationIkuByprokerId(new BigDecimal(params.get("prokerId").toString()));
            List<RlzTrxRealizationMonth> monthList = new ArrayList<>();
            monthList = daoRlzMonth.findRlzTrxRealizationMonthByprokerId(new BigDecimal(params.get("prokerId").toString()));

            mvc.addObject("iku", ikuList);
            mvc.addObject("ikuX", daoIku.findPrkIkuTwTargetByprokerId(new BigDecimal(params.get("prokerId").toString())));


            mvc.addObject("monthTarget", monthList);
            mvc.addObject("monthTargetX", daoMonth.findPrkMonthTargetByprokerId(new BigDecimal(params.get("prokerId").toString())));


            List<PrkProker> listProker = new ArrayList<>();
            listProker = daoProker.findPrkProkerEntity(id);
            mvc.addObject("listProker", listProker);

            mvc.addObject("monthList", dg.getMonth());

            if (divId == 15) {
                RlzTrxRealization rlz = new RlzTrxRealization();
                rlz = daoRlz.findRlzTrxRealizationByprokerId(id);
                mvc.addObject("rlzHeader", rlz);
            }

        }

        mvc.addObject("listYear", dg.getYear());
        List<AppMstDocument> listDocument = new ArrayList<>();
        listDocument = daoDoc.findAllAppMstDocumentEntity();
        mvc.addObject("listDoc", listDocument);


        if (divId == 15) {
            mvc.setViewName("/rlz/partialPages/modal_rlz_realization_bd");
        } else if (divId == 8 || divId == 9 || divId == 10 || divId == 11) {
            mvc.setViewName("/rlz/partialPages/modal_rlz_realization_ac_d");
        } else {
            mvc.setViewName("/rlz/partialPages/modal_rlz_realization_ae_d");
        }
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
        RlzTrxRealizationIku rlzIku = new RlzTrxRealizationIku();
        RlzTrxRealizationMonth rlzMonth = new RlzTrxRealizationMonth();
        List<RlzTrxRealizationIku> listIkuEntity = new ArrayList<>();
        List<RlzTrxRealizationMonth> listMonthEntity = new ArrayList<>();
        RlzTrxRealization dataRlz = new RlzTrxRealization();
        BigDecimal idRlz = null;
        try {
            try {
                Map<String, Object> dataRlzB = (Map) params.get("headRlz");
                if (!dataRlzB.get("realizationId").toString().equals("")) {
                    dataRlz = daoRlz.findById(new BigDecimal(dataRlzB.get("realizationId").toString()));
                }
                if (dataRlzB != null) {
                    dataRlz.setIsOntime(new BigDecimal(dataRlzB.get("isOnTime").toString()));
                    dataRlz.setAverageValue(dataRlzB.get("averageValue").toString());
                    dataRlz.setProkerId(params.get("prokerId").toString());
                    dataRlz.setDocumentType(new BigDecimal(dataRlzB.get("documentType").toString()));
                    dataRlz.setSumDay(new BigDecimal(dataRlzB.get("sumDay").toString()));
                    String sDate1 = dataRlzB.get("approveDate").toString();
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                    dataRlz.setApproveDate(date1);
                    sDate1 = dataRlzB.get("documentDate").toString();
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                    dataRlz.setDocumentDate(date1);
                    idRlz = daoRlz.saveEditDeleteRlzTrxRealizationEntity(dataRlz);
                }
            } catch (Exception ex) {
                result.put("status", Boolean.FALSE);

            }

            if (params.get("isIku").toString().equals("1")) {
                int addStatus = 1;
                List target = new ArrayList();
                try {
                    target = (List) params.get("ikuTarget");
                } catch (Exception e) {
                    addStatus = 0;
                }
                List ikuId = (List) params.get("ikuId");
                List period = (List) params.get("ikuPeriod");
                List ikuRlz = (List) params.get("ikuRealization");
                List ikuAch = (List) params.get("ikuAchievement");
                List ikuExpl = (List) params.get("ikuExplanation");
                for (int i = 0; i < ikuId.size(); i++) {
                    rlzIku = new RlzTrxRealizationIku();
                    if (target.size() > 0) {
                        rlzIku.setTarget(target.get(i).toString());
                    }
                    if (period.size() > 0) {
                        rlzIku.setPeriode(period.get(i).toString());
                    }
                    rlzIku.setAchievement(ikuAch.get(i).toString());
                    rlzIku.setExplanation(ikuExpl.get(i).toString());
                    rlzIku.setProkerId(new BigDecimal(params.get("prokerId").toString()));
                    rlzIku.setRealization(ikuRlz.get(i).toString());
                    rlzIku.setIkuTwId(new BigDecimal(ikuId.get(i).toString()));
                    rlzIku.setDeletedStatus(BigDecimal.ZERO);
                    rlzIku.setStatus(BigDecimal.ONE);
                    if (rlzIku.getRealization() != null) {
                        listIkuEntity.add(rlzIku);
                    }
                }
            }
            List monthId = (List) params.get("monthId");
            List monthProgress = (List) params.get("monthProgress");
            List monthRealization = (List) params.get("monthRealization");
            List monthSubsequence = (List) params.get("monthSubsequence");
            int tw = 1;
            for (int i = 0; i < monthId.size(); i++) {
                rlzMonth = new RlzTrxRealizationMonth();
                rlzMonth.setTriwulan(new BigDecimal(tw));
                if ((i + 1) % 3 == 0) {
                    tw = tw + 1;
                }
                rlzMonth.setMonthTgtId(new BigDecimal(monthId.get(i).toString()));
                rlzMonth.setProgress(monthProgress.get(i).toString());
                rlzMonth.setRealization(monthRealization.get(i).toString().replace(",", ""));
                rlzMonth.setProkerId(new BigDecimal(params.get("prokerId").toString()));
                rlzMonth.setSubsequence(monthSubsequence.get(i).toString().replace(",", ""));
                rlzMonth.setStatus(BigDecimal.ONE);
                rlzMonth.setDeletedStatus(BigDecimal.ZERO);
                rlzMonth.setRlzHeaderId(idRlz);
                listMonthEntity.add(rlzMonth);
            }
            if (params.get("isIku").toString().equals("1")) {
                daoRlzMonth.saveListAll(listMonthEntity, listIkuEntity);
            } else {
                daoRlzMonth.saveList(listMonthEntity);
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
            @RequestBody Map<String, Object> params, ModelMap model) {
        BigDecimal id = new BigDecimal(params.get("employeeId").toString());
        Map<String, Object> dataResult = new HashMap<>();
        PrkProker dataBud = new PrkProker();
//        dataBud = daoproker.findById(id);
        dataResult.put("dataBud", dataBud);
        return dataResult;
    }

    @RequestMapping(value = {"/delete/{prokerId}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteproker(@PathVariable(value = "prokerId") BigDecimal prokerId) {
        Map<String, Object> dataResult = new HashMap<>();
//        daoproker.deletePrkProkerEntity(prokerId);
        dataResult.put("status", Boolean.TRUE);
        return dataResult;
    }

    @RequestMapping(value = {"/getdetailbyproker/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getDetailProker(@PathVariable(value = "id") BigDecimal prokerId) {
        Map<String, Object> dataResult = new HashMap<>();

        return dataResult;
    }
}
