package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.dao.AppMstDirectorateDao;
import com.gov.budker.dao.AppMstDivisionDao;
import com.gov.budker.dao.BudTrxAchTargetDao;
import com.gov.budker.dao.PrkMainProkerDao;
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
@RequestMapping("/prk/mainproker")
public class PrkMainProkerController {

    @Autowired
    private PrkMainProkerDao daoMainProker;

    @Autowired
    private AppMstDivisionDao daoDiv;

    @Autowired
    private AppMstDirectorateDao daoDir;

    @Autowired
    private MessageSource messageSource;

    /*
     * Add a new AppMstCity.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session,
                               HttpServletResponse response, HttpServletRequest request) {
        try {
            session.getAttribute("menuList");
        } catch (Exception e) {
            return "redirect:/login";
        }
        session.setAttribute("menuName", "Kategori Proker");
        return "/prk/pages/prk_main_proker";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable,
                                           HttpSession session) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, PrkMainProker.class);
        if ("1".equals(session.getAttribute("roleId").toString())) {
            return result;
        }
        List<PrkMainProker> listData = result.getData();
        List<PrkMainProker> listDatanew = new ArrayList<>();
        BigDecimal id = new BigDecimal(session.getAttribute("divisionId").toString());
        for (PrkMainProker prk : listData) {
            if (id.equals(prk.getDivisionId())) {
                listDatanew.add(prk);
            }
        }
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        for (int i = 0; i < listDivId.size(); i++) {
            for (PrkMainProker prk : listData) {
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
        if (params != null && params.containsKey("mainProkerId")) {
            BigDecimal id = new BigDecimal(params.get("mainProkerId").toString());
            PrkMainProker data = daoMainProker.findById(id);
            if (data != null) {
                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataMainProker", data);
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
                        mvc.addObject("dataMainProker", data);
                    } else {
                        return mvc;
                    }
                }
            }

        }

        DateTimeGenerator dtg = new DateTimeGenerator();
        mvc.addObject("listYear", dtg.getYear());
        mvc.setViewName("/prk/partialPages/modal_prk_main_proker");
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
        PrkMainProker mainProker = new PrkMainProker();

        try {
            if (isSave == 1) {
                BigDecimal isIku = new BigDecimal(params.get("isIku").toString());
                mainProker = new PrkMainProker();
                mainProker.setMainProkerId(null);
                mainProker.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                mainProker.setMainProkerYear(new BigDecimal(params.get("mainProkerYear").toString()));
                mainProker.setMainProkerName(params.get("mainProkerName").toString());
                mainProker.setIsIku(isIku);
                if (isIku.toString().equals("1")) {
                    mainProker.setIkuCode(params.get("ikuCode").toString());
                }
                mainProker.setStatus(new BigDecimal(params.get("status").toString()));
                mainProker.setUserCreated(new BigDecimal(session.getAttribute("userId").toString()));
                mainProker.setDeletedStatus(BigDecimal.ZERO);
                mainProker.setDateCreated(new Date());
                daoMainProker.savePrkMainProkerEntity(mainProker);
            } else if (isSave == 2) {
                BigDecimal isIku = new BigDecimal(params.get("isIku").toString());
                BigDecimal id = new BigDecimal(params.get("mainProkerId").toString());
                mainProker = new PrkMainProker();
                mainProker = daoMainProker.findById(id);
                mainProker.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                mainProker.setMainProkerYear(new BigDecimal(params.get("mainProkerYear").toString()));
                mainProker.setMainProkerName(params.get("mainProkerName").toString());
                mainProker.setIsIku(isIku);
                if (isIku.toString().equals("1")) {
                    mainProker.setIkuCode(params.get("ikuCode").toString());
                }
                mainProker.setStatus(new BigDecimal(params.get("status").toString()));
                mainProker.setUserUpdated(new BigDecimal(session.getAttribute("userId").toString()));
                mainProker.setDeletedStatus(BigDecimal.ZERO);
                mainProker.setDateUpdated(new Date());
                daoMainProker.savePrkMainProkerEntity(mainProker);
            } else {
                mainProker = new PrkMainProker();
                BigDecimal id = new BigDecimal(params.get("mainProkerId").toString());
                mainProker = daoMainProker.findById(id);
                mainProker.setDeletedStatus(BigDecimal.ONE);
                mainProker.setDateDeleted(new Date());
                mainProker.setUserDeleted(new BigDecimal(session.getAttribute("userId").toString()));
                daoMainProker.savePrkMainProkerEntity(mainProker);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/getbyid/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> editAppMstCity(
            @PathVariable(value = "id") BigDecimal mainProkerId) {
        Map<String, Object> dataResult = new HashMap<>();
        PrkMainProker dataBud = new PrkMainProker();
        dataBud = daoMainProker.findById(mainProkerId);
        dataResult.put("dataBud", dataBud);
        return dataResult;
    }

    @RequestMapping(value = {"/delete/{mainProkerId}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBudget(@PathVariable(value = "mainProkerId") BigDecimal mainProkerId) {
        Map<String, Object> dataResult = new HashMap<>();
        daoMainProker.deletePrkMainProkerEntity(mainProkerId);
        dataResult.put("status", Boolean.TRUE);
        return dataResult;
    }
}
