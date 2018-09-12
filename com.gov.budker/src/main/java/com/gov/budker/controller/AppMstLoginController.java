package com.gov.budker.controller;

import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.common.MenuUtils;
import com.gov.budker.dao.*;
import com.gov.budker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AppMstLoginController {

    @Autowired
    private AppMstEmployeeDao daoEmp;

    @Autowired
    private AppMstMenuDao daoMenu;

    @Autowired
    private AppMstRoleDao daoRole;

    @Autowired
    private AppMstDivisionDao daoDiv;

    @Autowired
    private AppMstRoleDivisionDao daoRoleDiv;

    @Autowired
    private BudTrxBudgetDao daoBudget;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initial(ModelMap model, HttpSession session,
                          HttpServletResponse response, HttpServletRequest request) {
        return "redirect:/login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(ModelMap model, HttpSession session,
                          HttpServletResponse response, HttpServletRequest request) {
        session.setAttribute("menuName", "Home");
        return "redirect:/home/"+session.getAttribute("empId").toString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String listAppMstUsers(ModelMap model) {
        return "/app/pages/login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(ModelMap model, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        try {
//            int userId = Integer.parseInt(session.getAttribute("userid").toString());
//            daoSession.deleteAppTrxSessionByuserId(userId);
            session.invalidate();
        } catch (Exception e) {
            session.invalidate();
            return "redirect:/login";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> printWelcome(
            @RequestBody Map<String, Object> params, HttpSession session,
            HttpServletResponse response, HttpServletRequest request,
            ModelMap model) {
        String uname, pwd;
        uname = params.get("employeeUserName").toString();
        pwd = params.get("employeePassword").toString();
        Map<String, Object> dataLogin = new HashMap<>();

        try {
            AppMstEmployee resultLogin = daoEmp.login(uname, pwd);
            if (resultLogin != null) {
                session.setAttribute("divisionId", resultLogin.getEmployeeDivision());
                session.setAttribute("employeeId", resultLogin.getEmployeeId());
                session.setAttribute("employeeName", resultLogin.getEmployeeName());
                session.setAttribute("employeeDivision", resultLogin.getDivisionName());
                AppMstRole role = new AppMstRole();
                role = daoRole.findById(resultLogin.getRoleId());
                session.setAttribute("roleId", role.getIsAdmin());
                session.setAttribute("empId", resultLogin.getEmployeeId());
                List<AppMstRoleDivision> listRoleDiv = new ArrayList<>();
                listRoleDiv = daoRoleDiv.findAppMstRoleDivisionEntity(resultLogin.getEmployeeDivision());
                List listDIvId = new ArrayList();
                for (int i = 0; i < listRoleDiv.size(); i++) {
                    listDIvId.add(listRoleDiv.get(i).getDivisionGet());
                }
                session.setAttribute("listDivisionAccess", listDIvId);
                dataLogin.put("empId", resultLogin.getEmployeeId());
                dataLogin.put("status", Boolean.TRUE);
            } else {
                dataLogin.put("status", Boolean.FALSE);
            }
        } catch (Exception e) {
            dataLogin.put("status", Boolean.FALSE);
        }
        session.setAttribute("menuName", "Home");
        return dataLogin;
    }

    @RequestMapping(value = "/home/{empId}", method = RequestMethod.GET)
    public String getHomePage(@PathVariable(value = "empId") BigDecimal empId, ModelMap model, HttpServletRequest req,
                              HttpSession session) {
        List<AppMstMenu> menuEmp = new ArrayList<>();
        List<AppMstMenu> menuChild = new ArrayList<>();
        menuEmp = daoMenu.getMenuEmployee(empId);

        List<Map> menuList = new ArrayList<>();
        List<Map> menuListChild = new ArrayList<>();
        Map<String, Object> menuMap = new HashMap<>();
        Map<String, Object> menuMapChild = new HashMap<>();

        for (AppMstMenu appMstMenu : menuEmp) {
            menuChild = new ArrayList<>();
            menuMap = new HashMap<>();
            menuChild = daoMenu.getMenuByParentId(appMstMenu.getMenuId());
            menuMap = getMapConstractedMenu(appMstMenu);
            if (menuChild.size() <= 0) {
                menuMap.put("childs", new ArrayList<>());
            } else {
                for (AppMstMenu appMstMenuChild : menuChild) {
                    menuMapChild = getMapConstractedMenu(appMstMenuChild);
                    menuMapChild.put("childs", new ArrayList<>());
                    menuListChild.add(menuMapChild);
                }
                menuMap.put("childs", menuListChild);
                menuListChild = new ArrayList<>();
            }

            menuList.add(menuMap);
        }
        String menuUtil = MenuUtils.menuList2HTML(menuList, req);

        model.addAttribute("menuList", menuUtil);
        session.setAttribute("menuListSession", menuUtil);
        session.setAttribute("userId", empId);
        DateTimeGenerator dg = new DateTimeGenerator();

        model.addAttribute("listTriwulan", dg.getTriwulan());
        model.addAttribute("listMonth", dg.getMonth());
        model.addAttribute("listYear", dg.getYear());
        model.addAttribute("listDiv", daoDiv.findAllAppMstDivisionEntity());
        session.setAttribute("menuName", "Home");
        return "/app/pages/app_dashboard";

    }

    public Map<String, Object> getMapConstractedMenu(AppMstMenu appMstMenu) {
        Map<String, Object> result = new HashMap<>();
        result.put("menuName", appMstMenu.getMenuName());
        result.put("menuUrl", appMstMenu.getMenuUrl());
        result.put("menuIcon", appMstMenu.getMenuIcon());
        return result;
    }

}
