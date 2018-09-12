package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.AppMstDirectorateDao;
import com.gov.budker.model.AppMstDirectorate;
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
@RequestMapping("/app/dir")
public class AppMstDirectorateController {

    @Autowired
    private AppMstDirectorateDao daoDir;

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
        session.setAttribute("menuName", "Manajemen Direktorat");
        mvc.addObject("menuName", "Manajemen Direktorat");
        return "/app/pages/app_mst_directorate";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstDirectorate.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("directorateId")) {
            BigDecimal id = new BigDecimal(params.get("directorateId").toString());
            AppMstDirectorate data = daoDir.findById(id);
            if (data != null) {
                mvc.addObject("datDir", data);
            }
        }
        mvc.setViewName("/app/partialPages/modal_app_mst_dir");
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
        AppMstDirectorate datDir = new AppMstDirectorate();
        try {
            if (isSave == 1) {
                datDir = new AppMstDirectorate();
                datDir.setDirectorateId(null);
                datDir.setDirectorateName(params.get("directorateName").toString());
                datDir.setStatus(new BigDecimal(params.get("status").toString()));
                datDir.setDeletedStatus(BigDecimal.ZERO);
                datDir.setDateCreated(new Date());
                daoDir.saveAppMstDirectorateEntity(datDir);
            } else if (isSave == 2) {
                datDir = new AppMstDirectorate();
                datDir = daoDir.findById(new BigDecimal(params.get("directorateId").toString()));
                datDir.setDirectorateName(params.get("directorateName").toString());
                datDir.setStatus(new BigDecimal(params.get("status").toString()));
                datDir.setDeletedStatus(BigDecimal.ZERO);
                datDir.setDeletedStatus(BigDecimal.ZERO);
                datDir.setDateUpdated(new Date());
                daoDir.saveAppMstDirectorateEntity(datDir);
            } else {
                datDir = new AppMstDirectorate();
                BigDecimal id = new BigDecimal(params.get("directorateId").toString());
                datDir = daoDir.findById(id);
                datDir.setDeletedStatus(BigDecimal.ONE);
                datDir.setDateDeleted(new Date());
                daoDir.saveAppMstDirectorateEntity(datDir);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
