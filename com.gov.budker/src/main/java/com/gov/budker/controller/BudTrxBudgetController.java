package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.dao.AppMstDirectorateDao;
import com.gov.budker.dao.AppMstDivisionDao;
import com.gov.budker.dao.BudTrxAchTargetDao;
import com.gov.budker.dao.BudTrxBudgetDao;
import com.gov.budker.model.AppMstDirectorate;
import com.gov.budker.model.AppMstDivision;
import com.gov.budker.model.BudTrxAchTarget;
import com.gov.budker.model.BudTrxBudget;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
@RequestMapping("/bud/budget")
public class BudTrxBudgetController {

    @Autowired
    private BudTrxBudgetDao daoBudget;

    @Autowired
    private BudTrxAchTargetDao daoTarget;

    @Autowired
    private AppMstDirectorateDao daoDir;

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
        try {
            session.getAttribute("menuList");
        } catch (Exception e) {
            return "redirect:/login";
        }
        session.setAttribute("menuName", "Anggaran");
        return "/bud/pages/bud_budget";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, BudTrxBudget.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        List<AppMstDivision> listDivision = new ArrayList<>();
        BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
        List listDivId = (List) session.getAttribute("listDivisionAccess");

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

        if (params != null && params.containsKey("budgetId")) {
            BigDecimal id = new BigDecimal(params.get("budgetId").toString());
            BudTrxBudget data = daoBudget.findById(id);
            Boolean isAllowed = Boolean.FALSE;
            if (data != null) {
                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("budgetData", data);
                    isAllowed = Boolean.TRUE;
                } else {
                    if (data.getBudgetDivisionId().equals(divId)) {
                        isAllowed = Boolean.TRUE;
                    } else {
                        for (int i = 0; i < listDivId.size(); i++) {
                            if (listDivId.get(i).equals(data.getBudgetDivisionId())) {
                                isAllowed = Boolean.TRUE;
                                break;
                            }
                        }
                    }
                    if (isAllowed) {
                        mvc.addObject("budgetData", data);
                    } else {
                        return mvc;
                    }
                }

            }

            List<BudTrxAchTarget> listTargetAch = new ArrayList<>();
            listTargetAch = daoTarget.findBudTrxAchTargetByBudgetId(id);
            mvc.addObject("budgetTarget", listTargetAch);
        }

        DateTimeGenerator dg = new DateTimeGenerator();
        mvc.addObject("listYears", dg.getYear());
//        List<AppMstDirectorate> listDirectorate = new ArrayList<>();
//        listDirectorate = daoDir.findAllAppMstDirectorateEntity();
//        mvc.addObject("listDirectorate", listDirectorate);


        mvc.setViewName("/bud/partialPages/modal_bud_budget");
        return mvc;
    }


    /*
     * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        BudTrxBudget budget = new BudTrxBudget();
        BudTrxAchTarget achTarget = new BudTrxAchTarget();
        List<BudTrxAchTarget> listachTarget = new ArrayList<>();
        List value = (List) params.get("achTargetValue");
        List percentage = (List) params.get("achTargetPercentage");
        try {
            if (params.get("budgetId").toString() != "") {
                budget = daoBudget.findById(new BigDecimal(params.get("budgetId").toString()));
                listachTarget = daoTarget.findBudTrxAchTargetByBudgetId(budget.getBudgetId());
            }
//            budget.setBudgetDirectorateId(new BigDecimal(params.get("budgetDirectorateId").toString()));
            budget.setBudgetDivisionId(new BigDecimal(params.get("budgetDivisionId").toString()));
            budget.setBudgetYear(new BigDecimal(params.get("budgetYear").toString()));
            budget.setBudgetValue(params.get("budgetValue").toString().replace("," , ""));
            budget.setStatus(BigDecimal.ONE);
            budget.setDeletedStatus(BigDecimal.ZERO);

            if (params.get("budgetId").toString() == "") {
                for (int i = 0; i < 4; i++) {
                    achTarget = new BudTrxAchTarget();
                    achTarget.setAchTargetValue(value.get(i).toString().replace(",",""));
                    achTarget.setAchTargetPercentage(percentage.get(i).toString());
                    achTarget.setBudTargetTw(new BigDecimal(i + 1));
                    listachTarget.add(achTarget);
                }
                daoBudget.saveBudgetAndAchievement(budget, listachTarget);
            } else if (params.get("budgetId").toString() != null) {
                List<BudTrxAchTarget> listachTargetNew = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    achTarget = new BudTrxAchTarget();
                    achTarget = listachTarget.get(i);
                    achTarget.setAchTargetValue(value.get(i).toString().replace(",",""));
                    achTarget.setAchTargetPercentage(percentage.get(i).toString());
                    achTarget.setBudTargetTw(new BigDecimal(i + 1));
                    listachTargetNew.add(achTarget);
                }
                daoBudget.saveBudgetAndAchievement(budget, listachTargetNew);
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
        BigDecimal id = new BigDecimal(params.get("divisionId").toString());
        BigDecimal year = new BigDecimal(params.get("year").toString());
        Map<String, Object> dataResult = new HashMap<>();
        BudTrxBudget dataBud = new BudTrxBudget();
        dataBud = daoBudget.findBudTrxBudgetEntity(id, year);
        dataResult.put("dataBud", dataBud);
        return dataResult;
    }

    @RequestMapping(value = {"/delete/{budgetId}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBudget(@PathVariable(value = "budgetId") BigDecimal budgetId, HttpSession session) {
        Map<String, Object> dataResult = new HashMap<>();

        Boolean isAllowed = Boolean.FALSE;
        if (session.getAttribute("roleId").toString().equals("1")) {
            isAllowed = Boolean.TRUE;
        } else {
            BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
            List listDivId = (List) session.getAttribute("listDivisionAccess");
            BudTrxBudget bud = daoBudget.findById(budgetId);
            if (divId.equals(bud.getBudgetDivisionId())) {
                isAllowed = Boolean.TRUE;
            } else {
                for (int i = 0; i < listDivId.size(); i++) {
                    if (listDivId.get(i).equals(bud.getBudgetDivisionId())) {
                        isAllowed = Boolean.TRUE;
                        break;
                    }
                }
            }
        }

        if (isAllowed) {
            daoBudget.deleteBudTrxBudgetEntity(budgetId);
            dataResult.put("status", Boolean.TRUE);
        } else {
            dataResult.put("status", Boolean.FALSE);
            dataResult.put("message", "Tidak Diizinkan!");
        }

        return dataResult;
    }
}
