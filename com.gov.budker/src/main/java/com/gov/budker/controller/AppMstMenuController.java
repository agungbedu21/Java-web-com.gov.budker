package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.AppMstMenuDao;
import com.gov.budker.model.AppMstMenu;
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
@RequestMapping("/app/menu")
public class AppMstMenuController {

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
        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Manajemen Menu");
        mvc.addObject("menuName", "Manajemen Menu");
        return "/app/pages/app_mst_menu";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstMenu.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        List<AppMstMenu> listMenuParent = new ArrayList<>();
        listMenuParent = daoMenu.findAllAppMstMenuEntity();
        mvc.addObject("viewFor", "Buat");
        if (params != null && params.containsKey("menuId")) {
            mvc.addObject("viewFor", "Ubah");
            BigDecimal id = new BigDecimal(params.get("menuId").toString());
            AppMstMenu data = daoMenu.findById(id);
            if (data != null) {
                mvc.addObject("data", data);
            }
        }
        mvc.addObject("parentList", listMenuParent);
        mvc.setViewName("/app/partialPages/modal_app_mst_menu");
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
        AppMstMenu dataMenu = new AppMstMenu();
        try {
            if (isSave == 1) {
                dataMenu = new AppMstMenu();
                dataMenu.setMenuId(null);
                dataMenu.setMenuCode(params.get("menuCode").toString());
                dataMenu.setMenuName(params.get("menuName").toString());
                dataMenu.setMenuDescription(params.get("menuDescription").toString());
                dataMenu.setMenuIcon(params.get("menuIcon").toString());
                dataMenu.setMenuOrder(new BigDecimal(params.get("menuOrder").toString()));
                dataMenu.setMenuUrl(params.get("menuUrl").toString());
                try {
                    dataMenu.setParentId(new BigDecimal(params.get("parentId").toString()));
                }catch (Exception ex){

                }
                dataMenu.setStatus(new BigDecimal(params.get("status").toString()));
                dataMenu.setDeletedStatus(BigDecimal.ZERO);
                dataMenu.setDateCreated(new Date());
                daoMenu.saveAppMstMenuEntity(dataMenu);
            } else if (isSave == 2) {
                BigDecimal id = new BigDecimal (params.get("menuId").toString());
                dataMenu = daoMenu.findById(id);
                dataMenu.setMenuCode(params.get("menuCode").toString());
                dataMenu.setMenuName(params.get("menuName").toString());
                dataMenu.setMenuDescription(params.get("menuDescription").toString());
                dataMenu.setMenuIcon(params.get("menuIcon").toString());
                dataMenu.setMenuOrder(new BigDecimal(params.get("menuOrder").toString()));
                dataMenu.setMenuUrl(params.get("menuUrl").toString());
                try {
                    dataMenu.setParentId(new BigDecimal(params.get("parentId").toString()));
                }catch (Exception ex){

                }
                dataMenu.setStatus(new BigDecimal(params.get("status").toString()));
                dataMenu.setDeletedStatus(BigDecimal.ZERO);
                dataMenu.setDateUpdated(new Date());
                daoMenu.saveAppMstMenuEntity(dataMenu);
            } else {
                dataMenu = new AppMstMenu();
                BigDecimal id = new BigDecimal(params.get("menuId").toString());
                dataMenu = daoMenu.findById(id);
                dataMenu.setDeletedStatus(BigDecimal.ONE);
                dataMenu.setDateDeleted(new Date());
                daoMenu.saveAppMstMenuEntity(dataMenu);
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
//        AppMstEmployee dataMenu = new AppMstEmployee();
//        dataMenu = daoEmp.findById(id);
//        dataResult.put("dataMenu", dataMenu);
//        return dataResult;
//    }
}
