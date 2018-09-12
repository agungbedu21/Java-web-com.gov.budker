package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.dao.AppMstDivisionDao;
import com.gov.budker.dao.CalMstRiskProfileDao;
import com.gov.budker.dao.AppMstRoleDao;
import com.gov.budker.model.AppMstDivision;
import com.gov.budker.model.CalMstRiskProfile;
import com.gov.budker.model.AppMstRole;
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
@RequestMapping("/cal/riskprofile")
public class CalMstRiskProfileController {

    @Autowired
    private CalMstRiskProfileDao daoEmp;

    @Autowired
    private MessageSource messageSource;

    /*
     * Add a new AppMstCity.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session,
                               HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Profile Resiko");
        return "app/pages/cal_mst_risk_profile";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, CalMstRiskProfile.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("riskProfileId")) {
            BigDecimal id = new BigDecimal(params.get("riskProfileId").toString());
            CalMstRiskProfile data = daoEmp.findById(id);
            if (data != null) {
                mvc.addObject("dataList", data);
            }
        }
        DateTimeGenerator dtg = new DateTimeGenerator();
        mvc.addObject("listYear", dtg.getYear());
        mvc.setViewName("/app/partialPages/modal_mst_risk_profile");
        return mvc;
    }


    /*
     * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(@RequestBody Map<String, Object> params,
                                              @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        CalMstRiskProfile dataRiskProfile = new CalMstRiskProfile();
        try {
            if (isSave == 1) {
                dataRiskProfile = new CalMstRiskProfile();
                dataRiskProfile.setRiskProfileName(params.get("riskProfileName").toString());
                dataRiskProfile.setYear(new BigDecimal (params.get("year").toString()));
                dataRiskProfile.setCarMinValue(params.get("carMinValue").toString());
                dataRiskProfile.setStatus(new BigDecimal(params.get("status").toString()));
                dataRiskProfile.setDeletedStatus(BigDecimal.ZERO);
                daoEmp.saveEditDeleteCalMstRiskProfileEntity(dataRiskProfile);
            } else if (isSave == 2) {
                dataRiskProfile = new CalMstRiskProfile();
                BigDecimal id = new BigDecimal(params.get("riskProfileId").toString());
                dataRiskProfile = daoEmp.findById(id);
                dataRiskProfile.setRiskProfileName(params.get("riskProfileName").toString());
                dataRiskProfile.setYear(new BigDecimal (params.get("year").toString()));
                dataRiskProfile.setCarMinValue(params.get("carMinValue").toString());
                dataRiskProfile.setStatus(new BigDecimal(params.get("status").toString()));
                daoEmp.saveEditDeleteCalMstRiskProfileEntity(dataRiskProfile);
            } else {
                dataRiskProfile = new CalMstRiskProfile();
                BigDecimal id = new BigDecimal(params.get("riskProfileId").toString());
                dataRiskProfile = daoEmp.findById(id);
                dataRiskProfile.setDeletedStatus(BigDecimal.ONE);
                daoEmp.saveEditDeleteCalMstRiskProfileEntity(dataRiskProfile);
            }
        result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
