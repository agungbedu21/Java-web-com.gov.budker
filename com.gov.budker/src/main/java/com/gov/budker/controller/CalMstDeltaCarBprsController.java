package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.dao.CalMstDeltaCarBprsDao;
import com.gov.budker.model.CalMstCarBprs;
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
@RequestMapping("/cal/deltabprs")
public class CalMstDeltaCarBprsController {

    @Autowired
    private CalMstDeltaCarBprsDao daoDeltaCar;

    @Autowired
    private MessageSource messageSource;

    /*
     * Add a new AppMstCity.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session,
                               HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Delta CAR BPRS");
        return "/app/pages/cal_mst_delta_car_bprs";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, CalMstCarBprs.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("bprsId")) {
            BigDecimal id = new BigDecimal(params.get("bprsId").toString());
            CalMstCarBprs data = daoDeltaCar.findById(id);
            if (data != null) {
                mvc.addObject("dataList", data);
            }
        }
        DateTimeGenerator dg = new DateTimeGenerator();
        mvc.addObject("listYear", dg.getYear());
        mvc.setViewName("/app/partialPages/modal_mst_delta_car_bprs");
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
        CalMstCarBprs dataDeltaCar = new CalMstCarBprs();
        try {
            if (isSave == 1) {
                dataDeltaCar = new CalMstCarBprs();
                dataDeltaCar.setCarBprsMin(params.get("carBprsMin").toString());
                dataDeltaCar.setYear(params.get("year").toString());
                dataDeltaCar.setStatus(new BigDecimal(params.get("status").toString()));
                dataDeltaCar.setDeletedStatus(BigDecimal.ZERO);
                if (daoDeltaCar.cekAvailableData(dataDeltaCar.getYear())) {
                    daoDeltaCar.saveEditDeleteCalMstCarBprsEntity(dataDeltaCar);
                    result.put("status", Boolean.TRUE);
                } else {
                    result.put("status", Boolean.FALSE);
                }
            } else if (isSave == 2) {
                dataDeltaCar = new CalMstCarBprs();
                BigDecimal id = new BigDecimal(params.get("bprsId").toString());
                dataDeltaCar = daoDeltaCar.findById(id);
                dataDeltaCar.setCarBprsMin(params.get("carBprsMin").toString());
                dataDeltaCar.setYear(params.get("year").toString());
                dataDeltaCar.setStatus(new BigDecimal(params.get("status").toString()));
                dataDeltaCar.setDeletedStatus(BigDecimal.ZERO);
                if (daoDeltaCar.cekAvailableDataEdit(dataDeltaCar.getYear(), dataDeltaCar.getBprsId())) {
                    daoDeltaCar.saveEditDeleteCalMstCarBprsEntity(dataDeltaCar);
                    result.put("status", Boolean.TRUE);
                } else {
                    result.put("status", Boolean.FALSE);
                }

            } else if (isSave == 3) {
                dataDeltaCar = new CalMstCarBprs();
                BigDecimal id = new BigDecimal(params.get("bprsId").toString());
                dataDeltaCar = daoDeltaCar.findById(id);
                dataDeltaCar.setDeletedStatus(BigDecimal.ONE);
                daoDeltaCar.saveEditDeleteCalMstCarBprsEntity(dataDeltaCar);
                result.put("status", Boolean.TRUE);
            }

        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
