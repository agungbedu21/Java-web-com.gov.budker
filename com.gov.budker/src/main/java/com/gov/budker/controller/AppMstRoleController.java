package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.AppMstMenuDao;
import com.gov.budker.dao.AppMstMenuRoleDao;
import com.gov.budker.dao.AppMstRoleDao;
import com.gov.budker.model.AppMstMenuRole;
import com.gov.budker.model.AppMstRole;
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
@RequestMapping("/app/role")
public class AppMstRoleController {

    @Autowired
    private AppMstRoleDao daoRole;

    @Autowired
    private AppMstMenuDao daoMenu;

    @Autowired
    private AppMstMenuRoleDao daoMenuRole;

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
        session.setAttribute("menuName", "Menu Role");
        return "/app/pages/app_mst_role";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstRole.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        mvc.addObject("viewFor", "Buat");
        if (params != null && params.containsKey("roleId")) {
            mvc.addObject("viewFor", "Ubah");
            BigDecimal id = new BigDecimal(params.get("roleId").toString());
            AppMstRole data = daoRole.findById(id);
            List<AppMstMenuRole> listMenuRole = new ArrayList<>();
            listMenuRole = daoMenuRole.getListMenuRoleByRoleId(id);
            if (data != null) {
                mvc.addObject("menuRole", listMenuRole);
                mvc.addObject("dataRole", data);
            }
        }
        mvc.addObject("menuList", daoMenu.findAllAppMstMenuEntity());
        mvc.setViewName("/app/partialPages/modal_app_mst_role");
        return mvc;
    }


    /*
     * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(@RequestBody Map<String, Object> reqData,
                                              @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        AppMstRole dataRole = new AppMstRole();
        Map <String, Object> params = new HashMap<>();
        params = (Map<String, Object>) reqData.get("dataRole");
        List menuId = (List) reqData.get("dataMenuRole");
        try {

            if (isSave == 1) {
                dataRole = new AppMstRole();
                dataRole.setRoleId(null);
                dataRole.setRoleName(params.get("roleName").toString());
                dataRole.setRoleDescription(params.get("roleDescription").toString());
                dataRole.setStatus(new BigDecimal(params.get("status").toString()));
                dataRole.setIsAdmin(new BigDecimal(params.get("isAdmin").toString()));
                dataRole.setDeletedStatus(BigDecimal.ZERO);
                dataRole.setDateCreated(new Date());
                daoRole.saveEditDeleteAppMstRoleEntity(dataRole, menuId);
            } else if (isSave == 2) {
                BigDecimal id = new BigDecimal(params.get("roleId").toString());
                dataRole = daoRole.findById(id);
                dataRole.setRoleId(id);
                dataRole.setRoleName(params.get("roleName").toString());
                dataRole.setIsAdmin(new BigDecimal(params.get("isAdmin").toString()));
                dataRole.setRoleDescription(params.get("roleDescription").toString());
                dataRole.setStatus(new BigDecimal(params.get("status").toString()));
                dataRole.setDeletedStatus(BigDecimal.ZERO);
                dataRole.setDateUpdated(new Date());
                daoRole.saveEditDeleteAppMstRoleEntity(dataRole, menuId);
            } else {
                dataRole = new AppMstRole();
                BigDecimal id = new BigDecimal(reqData.get("roleId").toString());
                dataRole = daoRole.findById(id);
                dataRole.setDeletedStatus(BigDecimal.ONE);
                dataRole.setDateDeleted(new Date());
                daoRole.deleteRole(dataRole);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
//
//    @RequestMapping(value = {"/getbyid"}, method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> editAppMstCity(
//            @RequestBody Map<String, Object> params, ModelMap model) {
//        BigDecimal id = new BigDecimal(params.get("employeeId").toString());
//        Map<String, Object> dataResult = new HashMap<>();
//        AppMstEmployee dataRole = new AppMstEmployee();
//        dataRole = daoEmp.findById(id);
//        dataResult.put("dataRole", dataRole);
//        return dataResult;
//    }
}
