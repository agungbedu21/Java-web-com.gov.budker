package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.dao.AppMstDivisionDao;
import com.gov.budker.dao.AppMstRoleDao;
import com.gov.budker.dao.CalMstDeltaCarDao;
import com.gov.budker.model.CalMstDeltaCar;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cal/deltacar")
public class CalMstDeltaCarController {

    @Autowired
    private CalMstDeltaCarDao daoDeltaCar;

    @Autowired
    private MessageSource messageSource;

    /*
     * Add a new AppMstCity.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session,
                               HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Delta CAR");
        return "/app/pages/cal_mst_delta_car";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, CalMstDeltaCar.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("deltaCarId")) {
            BigDecimal id = new BigDecimal(params.get("deltaCarId").toString());
            CalMstDeltaCar data = daoDeltaCar.findById(id);
            if (data != null) {
                mvc.addObject("dataList", data);
            }
        }
        DateTimeGenerator dtg = new DateTimeGenerator();
        mvc.addObject("listYear", dtg.getYear());
        mvc.setViewName("/app/partialPages/modal_mst_delta_car");
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
        CalMstDeltaCar dataDeltaCar = new CalMstDeltaCar();
        try {
            if (isSave == 1) {
                dataDeltaCar = new CalMstDeltaCar();
                dataDeltaCar.setCarMaxValue(params.get("carMaxValue").toString());
                dataDeltaCar.setYear(new BigDecimal(params.get("year").toString()));
                dataDeltaCar.setCarMinValue(params.get("carMinValue").toString());
                dataDeltaCar.setStatus(new BigDecimal(params.get("status").toString()));
                dataDeltaCar.setAchievement(params.get("achievement").toString());
//                dataDeltaCar.setIsBprs(new BigDecimal(params.get("isBprs").toString()));
                dataDeltaCar.setDeletedStatus(BigDecimal.ZERO);
                daoDeltaCar.saveEditDeleteCalMstDeltaCarEntity(dataDeltaCar);
            } else if (isSave == 2) {
                dataDeltaCar = new CalMstDeltaCar();
                BigDecimal id = new BigDecimal(params.get("deltaCarId").toString());
                dataDeltaCar = daoDeltaCar.findById(id);
                dataDeltaCar.setCarMaxValue(params.get("carMaxValue").toString());
                dataDeltaCar.setYear(new BigDecimal(params.get("year").toString()));
                dataDeltaCar.setCarMinValue(params.get("carMinValue").toString());
                dataDeltaCar.setStatus(new BigDecimal(params.get("status").toString()));
                dataDeltaCar.setAchievement(params.get("achievement").toString());
//                dataDeltaCar.setIsBprs(new BigDecimal(params.get("isBprs").toString()));
                dataDeltaCar.setDeletedStatus(BigDecimal.ZERO);
                daoDeltaCar.saveEditDeleteCalMstDeltaCarEntity(dataDeltaCar);
            } else if (isSave == 3) {
                dataDeltaCar = new CalMstDeltaCar();
                BigDecimal id = new BigDecimal(params.get("deltaCarId").toString());
                dataDeltaCar = daoDeltaCar.findById(id);
                dataDeltaCar.setDeletedStatus(BigDecimal.ONE);
                daoDeltaCar.saveEditDeleteCalMstDeltaCarEntity(dataDeltaCar);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
