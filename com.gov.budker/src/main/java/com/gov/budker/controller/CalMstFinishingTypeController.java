package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.AppMstFinishingTypeDao;
import com.gov.budker.model.AppMstFinishingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cal/finkind")
public class CalMstFinishingTypeController {

    @Autowired
    private AppMstFinishingTypeDao daofinishingTypeal;

    @Autowired
    private MessageSource messageSource;

    /*
     * Add a new Menu.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
//        try {
//            int userId = Integer.parseInt(session.getAttribute("userid").toString());
//        } catch (Exception e) {
//            return "redirect:/login";
//        }
        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Jenis Penyelesaian");
        return "app/pages/cal_mst_finkind";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstFinishingType.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("finishingTypeId")) {
            BigDecimal id = new BigDecimal(params.get("finishingTypeId").toString());
            AppMstFinishingType data = daofinishingTypeal.findById(id);
            if (data != null) {
                mvc.addObject("dataList", data);
            }
        }
        mvc.setViewName("/app/partialPages/modal_cal_mst_finkind");
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
        AppMstFinishingType dataFinishingType = new AppMstFinishingType();
        try {
            if (isSave == 1) {
                dataFinishingType = new AppMstFinishingType();
                dataFinishingType.setFinishingTypeName(params.get("finishingTypeName").toString());
                dataFinishingType.setStatus(new BigDecimal(params.get("status").toString()));
                dataFinishingType.setDeletedStatus(BigDecimal.ZERO);
                daofinishingTypeal.saveEditDeleteAppMstFinishingTypeEntity(dataFinishingType);
            } else if (isSave == 2) {
                dataFinishingType = new AppMstFinishingType();
                dataFinishingType = daofinishingTypeal.findById(new BigDecimal(params.get("finishingTypeId").toString()));
                dataFinishingType.setFinishingTypeName(params.get("finishingTypeName").toString());
                dataFinishingType.setStatus(new BigDecimal(params.get("status").toString()));
                dataFinishingType.setDeletedStatus(BigDecimal.ZERO);
                daofinishingTypeal.saveEditDeleteAppMstFinishingTypeEntity(dataFinishingType);
            } else {
                dataFinishingType = new AppMstFinishingType();
                BigDecimal id = new BigDecimal(params.get("finishingTypeId").toString());
                dataFinishingType = daofinishingTypeal.findById(id);
                dataFinishingType.setDeletedStatus(BigDecimal.ONE);
                daofinishingTypeal.saveEditDeleteAppMstFinishingTypeEntity(dataFinishingType);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
