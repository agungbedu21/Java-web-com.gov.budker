package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.dao.*;
import com.gov.budker.model.*;
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
@RequestMapping("/cal/bus")
public class CalTrxCarBusController {

    @Autowired
    private CalTrxCarBusDao daoCalTrxCarBus;

    @Autowired
    private CalTrxBusDetailDao BusDetailDao;

    @Autowired
    private CalMstDeltaCarDao daoDeltaCar;

    @Autowired
    private AppMstDivisionDao daoDiv;

    @Autowired
    private CalMstRiskProfileDao daoRiskProfile;


    @Autowired
    private AppMstBankDao daoBank;

    @Autowired
    private MessageSource messageSource;

    private DateTimeGenerator dtg;

    /*
     * Add a new AppMstCity.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session,
                               HttpServletResponse response, HttpServletRequest request) {
        session.setAttribute("menuName", "CAR BUS");
        return "cal/pages/cal_car_bus";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable, HttpSession session) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, CalTrxCarBus.class);
        if ("1".equals(session.getAttribute("roleId").toString())) {
            return result;
        }
        List<CalTrxCarBus> listData = result.getData();
        List<CalTrxCarBus> listDatanew = new ArrayList<>();
        BigDecimal id = new BigDecimal(session.getAttribute("divisionId").toString());
        for (CalTrxCarBus prk : listData) {
            if (id.equals(prk.getDivisionId())) {
                listDatanew.add(prk);
            }
        }
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        for (int i = 0; i < listDivId.size(); i++) {
            for (CalTrxCarBus prk : listData) {
                if (listDivId.get(i).toString().equals(prk.getDivisionId().toString())) {
                    listDatanew.add(prk);
                }
            }
        }
        result.setData(listDatanew);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        List<AppMstDivision> listDivision = new ArrayList<>();

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
        if (params != null && params.containsKey("calCarBusId")) {
            BigDecimal id = new BigDecimal(params.get("calCarBusId").toString());
            CalTrxCarBus data = daoCalTrxCarBus.findById(id);
            Boolean isAllowed = Boolean.FALSE;
            if (data != null) {
                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataCalTrxCarBus", data);
                    isAllowed = Boolean.TRUE;
                } else {
                    if (data.getDivisionId().equals(divId)) {
                        isAllowed = Boolean.TRUE;
                    } else {
                        for (int i = 0; i < listDivId.size(); i++) {
                            if (listDivId.get(i).equals(data.getDivisionId())) {
                                isAllowed = Boolean.TRUE;
                                break;
                            }
                        }
                    }
                    if (isAllowed) {
                        mvc.addObject("dataCalTrxCarBus", data);
                    } else {
                        return mvc;
                    }
                }
            }
        }

        dtg = new DateTimeGenerator();
        mvc.addObject("listTriwulan", dtg.getTriwulan());
        mvc.addObject("listYears", dtg.getYear());

        mvc.setViewName("cal/partialPages/modal_cal_car_bus");
        return mvc;
    }

    @RequestMapping(value = {"/modalview"}, method = RequestMethod.POST)
    public ModelAndView getModalView(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        Boolean isAllowed = Boolean.FALSE;

        if (params != null && params.containsKey("calCarBusId")) {
            BigDecimal id = new BigDecimal(params.get("calCarBusId").toString());
            CalTrxCarBus data = daoCalTrxCarBus.findById(id);
            if (data != null) {
                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataCalTrxCarBus", data);
                    isAllowed = Boolean.TRUE;
                } else {
                    if (data.getDivisionId().equals(divId)) {
                        isAllowed = Boolean.TRUE;
                    } else {
                        for (int i = 0; i < listDivId.size(); i++) {
                            if (listDivId.get(i).equals(data.getDivisionId())) {
                                isAllowed = Boolean.TRUE;
                                break;
                            }
                        }
                    }
                    if (isAllowed) {
                        mvc.addObject("dataCalTrxCarBus", data);
                    } else {
                        return mvc;
                    }
                }
            }
            List<AppMstBank> listBank = new ArrayList<>();
            listBank = daoBank.findAppMstBankByDivisionId(data.getDivisionId());
            mvc.addObject("bankList", listBank);

            List<CalTrxBusDetail> listDetail = BusDetailDao.findCalTrxBusDetailByBusId(id);
            mvc.addObject("listDetail", listDetail);

            List<CalMstDeltaCar> listDeltaCar = new ArrayList<>();
            listDeltaCar = daoDeltaCar.findDeltaCarByYear(data.getYear());
            mvc.addObject("listDeltaCar", listDeltaCar);

            List<CalMstRiskProfile> listRisk = new ArrayList<>();
            listRisk = daoRiskProfile.findRiskProfileByYear(data.getYear());
            mvc.addObject("riskProfileList", listRisk);

        } else {
            return mvc;
        }
        dtg = new DateTimeGenerator();
        mvc.addObject("listTriwulan", dtg.getTriwulan());

        mvc.setViewName("/cal/partialPages/modal_cal_car_bus_view");
        return mvc;
    }


    /*
     * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(@RequestBody Map<String, Object> params, HttpSession session,
                                              @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        CalTrxCarBus CalTrxCarBus = new CalTrxCarBus();
        Boolean status = Boolean.FALSE;
        String message = "";
        try {
            if (isSave == 1) {
                CalTrxCarBus = new CalTrxCarBus();
                CalTrxCarBus.setCalCarBusId(null);
                CalTrxCarBus.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                CalTrxCarBus.setYear(params.get("year").toString());
                CalTrxCarBus.setTriwulan(new BigDecimal(params.get("triwulan").toString()));
                CalTrxCarBus.setIkuTarget(new BigDecimal(params.get("ikuTarget").toString()));
                CalTrxCarBus.setUserCreated(new BigDecimal(session.getAttribute("userId").toString()));
                CalTrxCarBus.setDeletedStatus(BigDecimal.ZERO);
                CalTrxCarBus.setDateCreated(new Date());
                if (daoCalTrxCarBus.cekAvailableData(CalTrxCarBus.getDivisionId(), CalTrxCarBus.getYear(),
                        CalTrxCarBus.getTriwulan())) {
                    daoCalTrxCarBus.saveCalTrxCarBusEntity(CalTrxCarBus);
                    status = Boolean.TRUE;
                } else {
                    message = "Data Telah Digunakan";
                }
            } else if (isSave == 2) {
                BigDecimal id = new BigDecimal(params.get("calCarBusId").toString());
                CalTrxCarBus = new CalTrxCarBus();
                CalTrxCarBus = daoCalTrxCarBus.findById(id);
                CalTrxCarBus.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                CalTrxCarBus.setYear(params.get("year").toString());
                CalTrxCarBus.setTriwulan(new BigDecimal(params.get("triwulan").toString()));
                CalTrxCarBus.setIkuTarget(new BigDecimal(params.get("ikuTarget").toString()));
                CalTrxCarBus.setUserUpdated(new BigDecimal(session.getAttribute("userId").toString()));
                CalTrxCarBus.setDeletedStatus(BigDecimal.ZERO);
                CalTrxCarBus.setDateUpdated(new Date());

                if (daoCalTrxCarBus.cekAvailableDataEdit(CalTrxCarBus.getDivisionId(), CalTrxCarBus.getYear(),
                        CalTrxCarBus.getTriwulan(), CalTrxCarBus.getCalCarBusId())) {
                    daoCalTrxCarBus.saveCalTrxCarBusEntity(CalTrxCarBus);
                    status = Boolean.TRUE;
                } else {
                    message = "Data Telah Digunakan";
                }
            } else {
                CalTrxCarBus = new CalTrxCarBus();
                BigDecimal id = new BigDecimal(params.get("carBusId").toString());
                CalTrxCarBus = daoCalTrxCarBus.findById(id);
                CalTrxCarBus.setDeletedStatus(BigDecimal.ONE);
                CalTrxCarBus.setDateDeleted(new Date());
                CalTrxCarBus.setUserDeleted(new BigDecimal(session.getAttribute("userId").toString()));
                daoCalTrxCarBus.saveCalTrxCarBusEntity(CalTrxCarBus);
            }
            result.put("status", status);
            result.put("message", message);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/detail/saveOrUpdate"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> savesectoralDetail(@RequestBody Map<String, Object> params, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        CalTrxBusDetail calTrxCarBusDetail = new CalTrxBusDetail();
        List<CalTrxBusDetail> listDetail = new ArrayList<>();
        try {
            BigDecimal calBusId = new BigDecimal(params.get("calCarBusId").toString());
            List detailId = new ArrayList();
            if (params.containsKey("detailId")) {
                detailId = (List) params.get("detailId");
            }
            List riskList = (List) params.get("risk");
            List carValue = (List) params.get("carValue");
            List bankId = (List) params.get("bankId");
            List ach = (List) params.get("ach");
            for (int i = 0; i < bankId.size(); i++) {
                calTrxCarBusDetail = new CalTrxBusDetail();
                if (detailId.size() > 0 && i < detailId.size()) {
                    calTrxCarBusDetail.setDetailId(new BigDecimal(detailId.get(i).toString()));
                }
                calTrxCarBusDetail.setRiskProfileId(new BigDecimal(riskList.get(i).toString()));
                calTrxCarBusDetail.setBankId(new BigDecimal(bankId.get(i).toString()));
                calTrxCarBusDetail.setCarBusId(calBusId);
                calTrxCarBusDetail.setCarBusValue(new BigDecimal(carValue.get(i).toString()));
                calTrxCarBusDetail.setAchievement(ach.get(i).toString());
                listDetail.add(calTrxCarBusDetail);
            }
            BusDetailDao.saveListBusDetail(listDetail);
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/delete/{CalTrxCarBusId}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBudget(HttpSession session, @PathVariable(value = "CalTrxCarBusId")
            BigDecimal CalTrxCarBusId) {
        Map<String, Object> dataResult = new HashMap<>();
        Boolean isAllowed = Boolean.FALSE;
        if (session.getAttribute("roleId").toString().equals("1")) {
            isAllowed = Boolean.TRUE;
        } else {
            BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
            List listDivId = (List) session.getAttribute("listDivisionAccess");
            CalTrxCarBus bud = daoCalTrxCarBus.findById(CalTrxCarBusId);
            if (divId.equals(bud.getDivisionId())) {
                isAllowed = Boolean.TRUE;
            } else {
                for (int i = 0; i < listDivId.size(); i++) {
                    if (listDivId.get(i).equals(bud.getDivisionId())) {
                        isAllowed = Boolean.TRUE;
                        break;
                    }
                }
            }
        }
        if (isAllowed) {
            daoCalTrxCarBus.deleteCalTrxCarBusEntity(CalTrxCarBusId,
                    new BigDecimal(session.getAttribute("userId").toString()));
            dataResult.put("status", Boolean.TRUE);
        } else {
            dataResult.put("status", Boolean.FALSE);
            dataResult.put("message", "Tidak Diizinkan!");
        }
        return dataResult;
    }
}
