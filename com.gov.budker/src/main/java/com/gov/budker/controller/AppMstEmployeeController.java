package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.AppMstDivisionDao;
import com.gov.budker.dao.AppMstEmployeeDao;
import com.gov.budker.dao.AppMstRoleDao;
import com.gov.budker.model.AppMstDivision;
import com.gov.budker.model.AppMstEmployee;
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
@RequestMapping("/app/employee")
public class AppMstEmployeeController {

    @Autowired
    private AppMstEmployeeDao daoEmp;

    @Autowired
    private AppMstRoleDao daoRole;

    @Autowired
    private AppMstDivisionDao daoDiv;

    @Autowired
    private MessageSource messageSource;

    /*
     * Add a new AppMstCity.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session,
                               HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mvc = new ModelAndView();
        mvc.addObject("menuName", "Manajemen User");
        session.setAttribute("menuName", "Manajemen User");
        return "/app/pages/app_mst_employee";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstEmployee.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("employeeId")) {
            BigDecimal id = new BigDecimal(params.get("employeeId").toString());
            AppMstEmployee data = daoEmp.findById(id);
            if (data != null) {
                mvc.addObject("userList", data);
            }
        }
        List<AppMstDivision> listDiv = new ArrayList<>();
        listDiv = daoDiv.findAllAppMstDivisionEntity();
        mvc.addObject("listDiv", listDiv);
        List<AppMstRole> listRole = new ArrayList<>();
        listRole = daoRole.findAllAppMstRoleEntity();
        mvc.addObject("listRole", listRole);
        mvc.setViewName("/app/partialPages/modal_app_user");
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
        AppMstEmployee dataEmp = new AppMstEmployee();
        try {
            if (isSave == 1) {
                dataEmp = new AppMstEmployee();
                dataEmp.setEmployeeName(params.get("employeeName").toString());
                dataEmp.setEmployeeUserName(params.get("employeeUserName").toString());
                dataEmp.setEmployeePassword(params.get("employeePassword").toString());
                dataEmp.setEmployeeDivision(new BigDecimal(params.get("employeeDivision").toString()));
                dataEmp.setRoleId(new BigDecimal(params.get("employeeRoleId").toString()));
                dataEmp.setDeletedStatus(BigDecimal.ZERO);
                dataEmp.setDateCreated(new Date());
                daoEmp.saveAppMstEmployeeEntity(dataEmp);
            } else if (isSave == 2) {
                dataEmp = new AppMstEmployee();
                BigDecimal id = new BigDecimal(params.get("employeeId").toString());
                dataEmp = daoEmp.findById(id);
                dataEmp.setEmployeeName(params.get("employeeName").toString());
                dataEmp.setEmployeeUserName(params.get("employeeUserName").toString());
                dataEmp.setEmployeePassword(params.get("employeePassword").toString());
                dataEmp.setEmployeeDivision(new BigDecimal(params.get("employeeDivision").toString()));
                dataEmp.setRoleId(new BigDecimal(params.get("employeeRoleId").toString()));
                dataEmp.setDeletedStatus(BigDecimal.ZERO);
                dataEmp.setDateUpdated(new Date());
                daoEmp.saveAppMstEmployeeEntity(dataEmp);
            } else {
                dataEmp = new AppMstEmployee();
                BigDecimal id = new BigDecimal(params.get("employeeId").toString());
                dataEmp = daoEmp.findById(id);
                dataEmp.setDeletedStatus(BigDecimal.ONE);
                dataEmp.setDateDeleted(new Date());
                daoEmp.saveAppMstEmployeeEntity(dataEmp);
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
        AppMstEmployee dataEmp = new AppMstEmployee();
//        dataEmp = daoEmp.findById(id);
        dataResult.put("dataEmp", dataEmp);
        return dataResult;
    }

    @RequestMapping(value = {"/getbydivid/{divId}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmpByDivId(@PathVariable(value = "divId") BigDecimal divId) {
        Map<String, Object> dataResult = new HashMap<>();
        List<AppMstEmployee> listEmployee = new ArrayList<>();
        listEmployee = daoEmp.findAppMstEmployeeEntity(divId);
        dataResult.put("data", listEmployee);
        return dataResult;
    }

    @RequestMapping(value = {"/changepassword"}, method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView changePassword(HttpSession session) {
        BigDecimal empId = new BigDecimal(session.getAttribute("empId").toString());
        AppMstEmployee dataEmployee = new AppMstEmployee();
        dataEmployee = daoEmp.findById(empId);
        ModelAndView mvc = new ModelAndView();
        mvc.addObject("employeeData", dataEmployee);
        mvc.setViewName("/app/partialPages/modal_change_password");
        return mvc;
    }

    @RequestMapping(value = {"/savechange"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changePasswordSave(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        AppMstEmployee dataEmp = new AppMstEmployee();
        try {
            dataEmp = new AppMstEmployee();
            BigDecimal id = new BigDecimal(params.get("employeeId").toString());
            dataEmp = daoEmp.findById(id);
            dataEmp.setEmployeePassword(params.get("employeePassword").toString());
            dataEmp.setDeletedStatus(BigDecimal.ZERO);
            dataEmp.setDateUpdated(new Date());
            daoEmp.saveAppMstEmployeeEntity(dataEmp);
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
