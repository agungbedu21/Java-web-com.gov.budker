package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.AppMstMenuDao;
import com.gov.budker.dao.AppMstMenuRoleDao;
import com.gov.budker.dao.AppMstRoleDao;
import com.gov.budker.model.AppMstMenu;
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
@RequestMapping("/app/menurole")
public class AppMstMenuRoleController {

    @Autowired
    private AppMstMenuRoleDao daoMenuRole;

    @Autowired
    private AppMstRoleDao daoRole;

    @Autowired
    private AppMstMenuDao daoMenu;

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
        return "/app/pages/app_mst_menu_role";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstMenuRole.class);
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
            AppMstMenuRole data = daoMenuRole.findById(id);
            if (data != null) {
                mvc.addObject("data", data);
            }
        }
        List<AppMstRole> dataRole = daoRole.findAllAppMstRoleEntity();
        List<AppMstMenu> dataMenu = daoMenu.findAllAppMstMenuEntity();
        mvc.addObject("dataRole", dataRole);
        mvc.addObject("dataMenu", dataMenu);
        mvc.setViewName("/app/partialPages/modal_app_mst_menu_role");
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
        AppMstMenuRole dataMenuRole = new AppMstMenuRole();
        List<AppMstMenuRole> menuRole = new ArrayList<>();
        try {
            if (isSave == 1) {
                List menuId = new ArrayList();
                menuId = (List) params.get("menuId");
                for (int i = 0; i < menuId.size(); i++) {
                    dataMenuRole = new AppMstMenuRole();
                    dataMenuRole.setMenuRoleId(null);
                    dataMenuRole.setRoleId(new BigDecimal(params.get("roleId").toString()));
                    dataMenuRole.setMenuId(new BigDecimal(menuId.get(i).toString()));
                    dataMenuRole.setStatus(new BigDecimal(params.get("status").toString()));
                    dataMenuRole.setDeletedStatus(BigDecimal.ZERO);
                    dataMenuRole.setDateCreated(new Date());
                    menuRole.add(dataMenuRole);
                }
                daoMenuRole.saveListMenuRole(menuRole);
            } else if (isSave == 2) {
                BigDecimal id = new BigDecimal(params.get("roleId").toString());
                dataMenuRole = daoMenuRole.findById(id);
                dataMenuRole.setRoleId(new BigDecimal(params.get("roleId").toString()));
                dataMenuRole.setMenuId(new BigDecimal(params.get("menuId").toString()));
                dataMenuRole.setStatus(new BigDecimal(params.get("status").toString()));
                dataMenuRole.setStatus(new BigDecimal(params.get("status").toString()));
                dataMenuRole.setDeletedStatus(BigDecimal.ZERO);
                dataMenuRole.setDateUpdated(new Date());
                daoMenuRole.saveEditDeleteAppMstMenuRoleEntity(dataMenuRole);
            } else {
                dataMenuRole = new AppMstMenuRole();
                BigDecimal id = new BigDecimal(params.get("roleId").toString());
                dataMenuRole = daoMenuRole.findById(id);
                dataMenuRole.setDeletedStatus(BigDecimal.ONE);
                dataMenuRole.setDateDeleted(new Date());
                daoMenuRole.saveEditDeleteAppMstMenuRoleEntity(dataMenuRole);
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
//        AppMstEmployee dataMenuRole = new AppMstEmployee();
//        dataMenuRole = daoEmp.findById(id);
//        dataResult.put("dataMenuRole", dataMenuRole);
//        return dataResult;
//    }
}
