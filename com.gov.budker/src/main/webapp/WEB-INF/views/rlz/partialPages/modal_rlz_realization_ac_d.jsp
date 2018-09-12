<%-- 
    Created on : mei 2018, 1:21:28 PM
    Author     : Agung Abdurohman
--%>

<%@page import="java.util.List" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- MODAL ADD -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<!-- Datetimepicker -->
<script src="${pageContext.request.contextPath}/resources/component/datetimepicker-4.17.47/moment.js"></script>
<script src="${pageContext.request.contextPath}/resources/component/datetimepicker-4.17.47/moment-with-locales.js"></script>
<script src="${pageContext.request.contextPath}/resources/component/datetimepicker-4.17.47/bootstrap-datetimepicker.min.js"></script>
<div class="modal" id="modalRlzRealization" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="wizardForm">
                <div id="rootwizard">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                            Update Realisasi
                        </h4>
                        <input type="hidden" name="prokerId" value="${prokerData.prokerId}" id="prokerId"
                               class="form-control"/>
                        <input type="hidden" name="isIku" value="${prokerData.prokerId}" id="isIku"
                               class="form-control"/>
                    </div>
                    <div class="modal-body clearfix">
                        <div class="col-sm-12 paddingLR0">
                            <ul class="nav-justified thumbnail wizard-list  shadow">
                                <li class="">
                                    <a href="#tab1" data-toggle="tab" class="disable">
                                        <h4 class="list-group-item-heading">Step 1</h4>
                                        <p class="list-group-item-text">Detail Program Kerja</p>
                                    </a>
                                </li>
                                <li class="">
                                    <a href="#tab2" data-toggle="tab" class="disable">
                                        <h4 class="list-group-item-heading">Step 2</h4>
                                        <p class="list-group-item-text">Input Realisasi</p>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane" id="tab1">
                                <div class="col-md-12 col-xs-12 form-content shadow">
                                    <div class="form-title form-group">Detail Program Kerja
                                    </div>
                                    <div class="form-col">
                                        <div class="form-group">
                                            <label>Pilih Divisi</label>
                                            <select class="select2 form-control " name="divisionId"
                                                    id="divisionId" disabled>
                                                <option value="">-- Pilih Divisi --</option>
                                                <c:forEach items="${listDivision}" var="data">
                                                    <option value="${data.divisionId}"
                                                            <c:if test="${prokerData.divisionId == data.divisionId}">selected</c:if>>
                                                            ${data.divisionName}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Pilih Tahun</label>
                                                <select class="select2 form-control" name="year" id="year"
                                                        onchange="getProkerByDivAndYear(this)" disabled>
                                                    <option value="">-- Pilih Tahun --</option>
                                                    <c:forEach items="${listYear}" var="data">
                                                        <option value="${data.year}"
                                                                <c:if test="${prokerData.prokerYear == data.year}">selected</c:if>>
                                                                ${data.year}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <hr/>
                                        <%--<div class="row">--%>
                                            <%--<div class="form-group  col-md-6">--%>
                                                <%--<label>Pilih Proker Berdasarkan</label>--%>
                                                <%--<br/>--%>
                                                <%--<label class="label-check">--%>
                                                    <%--<input type="radio" name="isProker" value="1" checked> <span--%>
                                                        <%--class="label-text" disabled="true">Nama Proker</span>--%>
                                                <%--</label>--%>
                                                <%--<br/>--%>
                                                <%--<label class="label-check">--%>
                                                    <%--<input type="radio" name="isProker" value="2"> <span--%>
                                                        <%--class="label-text" disabled="true">Kode IKU</span>--%>
                                                <%--</label>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <div class="row">
                                            <div id="ifOne">
                                                <div class="form-group col-md-6">
                                                    <label>Pilih Proker</label>
                                                    <select class="select2 form-control" name="prokerName"
                                                            id="prokerNameSelect"
                                                            onchange="getBudgetBalanceByProker(this)" disabled>
                                                        <option value="">-- Pilih Proker --</option>
                                                        <c:forEach items="${listProker}" var="data">
                                                            <option value="${data.prokerId}" selected>
                                                                <c:if test="${data.mainProkerName != null}">
                                                                    ${data.mainProkerName}
                                                                </c:if>
                                                                <c:if test="${data.subMainProkerName != null}">
                                                                    ${data.subMainProkerName}
                                                                </c:if>
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div id="ifTwo" hidden>
                                                <div class="form-group col-md-6">
                                                    <label>Pilih Kode IKU</label>
                                                    <select class="select2 form-control" name="prokerIku"
                                                            id="ikuCodeChoose" disabled
                                                            onchange="getBudgetBalanceByProker(this)">
                                                        <option value="">-- Pilih Kode IKU --</option>
                                                        <c:forEach items="${listProker}" var="data">
                                                            <c:if test="${data.ikuCode != null}">
                                                                <option value="${data.prokerId}" selected>
                                                                        ${data.ikuCode}
                                                                </option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <table id="tblDet"
                                                       class="table table-striped table-hover"
                                                       width="100%">
                                                    <tbody>
                                                    <tr>
                                                        <td>PIC</td>
                                                        <td>:</td>
                                                        <td>
                                                            <div id="pic">${prokerData.picUserName}</div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Target Waktu Penyelesaian</td>
                                                        <td>:</td>
                                                        <td>
                                                            <div id="dateTarget">
                                                                <fmt:formatDate var="tgtDate" pattern="dd-MM-YYYY"
                                                                                value="${prokerData.dateTarget}"/>
                                                                ${tgtDate}
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Anggaran</td>
                                                        <td>:</td>
                                                        <td>
                                                            <fmt:formatNumber var="bud" type="currency"
                                                                              pattern="#,##0;-#,##0"
                                                                              value="${prokerData.prokerBudget}"/>
                                                            <div id="budget">Rp. ${bud}</div>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab2">
                                <div class="col-md-12 col-xs-12 form-content shadow">
                                    <div class="form-col">
                                        <div class="form-group">
                                            <div class="group-input">
                                                <div class="form-group col-md-12">
                                                    <table id="detailRel"
                                                           class="table table-striped table-hover"
                                                           width="50%">
                                                        <tbody>
                                                        <tr>
                                                            <td>Realisasi (Rp)</td>
                                                            <td>:</td>
                                                            <td>
                                                                <div id="realRp">
                                                                    <fmt:formatNumber var="rlz" type="currency"
                                                                                      pattern="#,##0;-#,##0"
                                                                                      value="${prokerData.totalRealization}"/>
                                                                    Rp. ${rlz}</div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Realisasi (%)</td>
                                                            <td>:</td>
                                                            <td>
                                                                <div id="realP">
                                                                    <c:set var="achievementTotal"
                                                                           value="${(prokerData.totalRealization*100)}"/>

                                                                    <fmt:formatNumber var="formaNu" type="number"
                                                                                      minFractionDigits="10"
                                                                                      maxFractionDigits="3"
                                                                                      value="${achievementTotal}"/>

                                                                    <c:set var="billableTime" value="${formaNu}"/>
                                                                    <c:if test="${billableTime == null}">0</c:if>
                                                                    <fmt:parseNumber var="i" type="number"
                                                                                     value="${billableTime}"/>
                                                                    ${i/prokerData.prokerBudget} %
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Saldo</td>
                                                            <td>:</td>
                                                            <td>
                                                                <div id="amount">
                                                                    <fmt:formatNumber var="sal" type="currency"
                                                                                      pattern="#,##0;-#,##0"
                                                                                      value="${prokerData.prokerBudget - prokerData.totalRealization}"/>
                                                                    Rp. ${sal}</div>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                    <div id="ikuTable"
                                         <c:if test="${prokerData.isIku == 0}">hidden</c:if> >
                                        <div class="form-title form-group">Target IKU Per TW
                                        </div>
                                        <div class="form-col">
                                            <div class="form-group">
                                                <div class="group-input">
                                                    <%--<label class=""><b>Input Text</b></label>--%>
                                                    <table width="100%" id="tableIku" border="1">
                                                        <thead  style="font-weight: bold; text-align: center">
                                                        <td data-class="expand" style="text-align:
                                                         center; padding:1%; width: 5%">
                                                            TW
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Target Pencapaian
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Periode
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Realisasi
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Pencapaian
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Penjelasan
                                                        </td>
                                                        </thead>
                                                        <tbody id="ikuTarget">
                                                        <%--<c:forEach items="${iku}" var="dt" varStatus="lp">--%>
                                                        <%--<tr>--%>
                                                        <%--<td style='text-align: center'>--%>
                                                        <%--${lp.index +1}--%>
                                                        <%--<input--%>
                                                        <%--type='hidden'--%>
                                                        <%--name='ikuTwId[]' value='${dt.ikuTwId}"'/>--%>
                                                        <%--</td>--%>
                                                        <%--<td style='padding:1%'><input type='number'--%>
                                                        <%--name='target[]'--%>
                                                        <%--maxlength="4"--%>
                                                        <%--class='form-control'--%>
                                                        <%--value="${dt.target}"/>--%>
                                                        <%--</td>--%>
                                                        <%--<td style='padding:1%'><input type='text'--%>
                                                        <%--name='period[]'--%>
                                                        <%--class='form-control'--%>
                                                        <%--value="${dt.periode}"/>--%>
                                                        <%--</td>--%>
                                                        <%--<td style='padding:1%'><input type='number'--%>
                                                        <%--maxlength="4"--%>
                                                        <%--name='ikuRealization[]'--%>
                                                        <%--class='form-control'--%>
                                                        <%--value="${dt.realization}"/>--%>
                                                        <%--</td>--%>
                                                        <%--<td style='padding:1%'><input type='number'--%>
                                                        <%--maxlength="4"--%>
                                                        <%--name='achievement[]'--%>
                                                        <%--class='form-control'--%>
                                                        <%--value="${dt.achievement}"/>--%>
                                                        <%--</td>--%>
                                                        <%--<td style='padding:1%'><input type='text'--%>
                                                        <%--name='explanation[]'--%>
                                                        <%--class='form-control'--%>
                                                        <%--value="${dt.explanation}"/>--%>
                                                        <%--</td>--%>
                                                        <%--</tr>--%>
                                                        <%--</c:forEach>--%>
                                                        <c:forEach items="${ikuX}" var="dt" varStatus="loop">
                                                            <tr>
                                                                <td style='text-align: center'>${dt.ikuTw}
                                                                    <input
                                                                            type='hidden'
                                                                            name='ikuTwId[]' value='${dt.ikuTwId}'/>
                                                                </td>
                                                                <td style='padding:1%'><input type='number'
                                                                                              maxlength="4"
                                                                                              name='target[]'
                                                                                              class='form-control'
                                                                                              value="${iku[loop.index].target}"/>
                                                                </td>
                                                                <td style='padding:1%'><input type='text'
                                                                                              name='period[]'
                                                                                              class='form-control'
                                                                                              value="${iku[loop.index].period}"/>
                                                                </td>
                                                                <td style='padding:1%'><input type='number'
                                                                                              name='ikuRealization[]'
                                                                                              maxlength="4"
                                                                                              class='form-control'
                                                                                              value="${iku[loop.index].realization}"/>
                                                                </td>
                                                                <td style='padding:1%'><input type='number'
                                                                                              maxlength="4"
                                                                                              name='achievement[]'
                                                                                              class='form-control'
                                                                                              value="${iku[loop.index].achievement}"/>
                                                                </td>
                                                                <td style='padding:1%'><input type='text'
                                                                                              name='explanation[]'
                                                                                              class='form-control'
                                                                                              value="${iku[loop.index].explanation}"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="monthTable">
                                        <div class="form-title form-group">Rencana Perbulan
                                        </div>
                                        <div class="form-col">
                                            <div class="form-group">
                                                <div class="group-input">
                                                    <%--<label class=""><b>Input Text</b></label>--%>
                                                    <table width="100%" id="tableMonth" border="1">
                                                        <thead  style="font-weight: bold; text-align: center">
                                                        <td data-class="expand" style="text-align: center; padding:1%">
                                                            Bulan
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Rencana
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Progres
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Realisasi (Rp)
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Subsequence (Rp)
                                                        </td>
                                                        </thead>
                                                        <tbody id="monthTarget">
                                                        <%--<c:forEach items="${monthTarget}" var="dt" varStatus="loop">--%>
                                                        <%--<tr>--%>
                                                        <%--<td style='text-align: center'>--%>
                                                        <%--${monthList[loop.index].monthName}--%>
                                                        <%--<input type='hidden' name='monthTargetId[]'--%>
                                                        <%--value="${dt.monthTgtId}"/>--%>
                                                        <%--</td>--%>
                                                        <%--<td style='text-align: left; padding : 1%'>${dt.target}</td>--%>
                                                        <%--<td style='padding:1%'>--%>
                                                        <%--<input type='text' name='progress[]'--%>
                                                        <%--class='form-control' value="${dt.progress}"/>--%>
                                                        <%--</td>--%>
                                                        <%--<td style='padding:1%'>--%>
                                                        <%--<input type='number' name='monthRealization[]'--%>
                                                        <%--class='form-control'--%>
                                                        <%--value="${dt.realization}"/>--%>
                                                        <%--</td>--%>
                                                        <%--<td style='padding:1%'>--%>
                                                        <%--<input type='number' name='subsequence[]'--%>
                                                        <%--class='form-control'--%>
                                                        <%--value="${dt.subsequence}"/>--%>
                                                        <%--</td>--%>
                                                        <%--</tr>--%>
                                                        <%--</c:forEach>--%>
                                                        <c:forEach items="${monthTargetX}" var="dt" varStatus="loop">
                                                            <tr>
                                                                <td style='text-align: center'>
                                                                        ${monthList[loop.index].monthName}
                                                                    <input type='hidden' name='monthTargetId[]'
                                                                           value="${dt.monthTargetId}"/>
                                                                </td>
                                                                <td style='text-align: left; padding : 1%'>${dt.target}</td>
                                                                <td style='padding:1%'>
                                                                    <input type='text' name='progress[]' class='
                                                                           form-control'
                                                                           value="${monthTarget[loop.index].progress}"/>
                                                                </td>
                                                                <td style='padding:1%'>
                                                                    <fmt:formatNumber var="rlz" type="currency"
                                                                                      pattern="#,##0;-#,##0"
                                                                                      value="${monthTarget[loop.index].realization}"/>
                                                                    <input type='text' name='monthRealization[]'
                                                                           class='form-control'
                                                                           pattern="([0-9]+.{0,1}[0-9]*,{0,1})*[0-9]"
                                                                           value="${rlz}"
                                                                           id="monthRealization${loop.index}"/>
                                                                </td>
                                                                <td style='padding:1%'>
                                                                    <fmt:formatNumber var="ssq" type="currency"
                                                                                      pattern="#,##0;-#,##0"
                                                                                      value="${monthTarget[loop.index].subsequence}"/>
                                                                    <input type='text' name='subsequence[]'
                                                                           pattern="([0-9]+.{0,1}[0-9]*,{0,1})*[0-9]"
                                                                           class='form-control' value="${ssq}"
                                                                           id="subsequence${loop.index}"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--end content-->
                    </div>
                    <div class="modal-footer">
                        <ul class="wizard list-inline pull-right">
                            <li class="first btn btn-default" style="display:none;"><a href="#">First</a></li>
                            <li class="previous">
                                <button type="button" class="btn btn-default">Kembali</button>
                            </li>
                            <li class="finish">
                                <button type="button" class="btn btn-primary" onclick="submitData()">Finish</button>
                            </li>
                            <li class="next">
                                <button type="button" class="btn btn-primary">Lanjut</button>
                            </li>
                        </ul>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#wizardForm')
            .bootstrapValidator({
                fields: {
                    divisionId: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    directorateId: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    year: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    prokerName: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    "period[]": {
                        validators: {
                            notEmpty: {}
                        }
                    },
                }
            });

        $('#targetDate').datetimepicker({
            ignoreReadonly: true,
            format: 'DD/MM/YYYY',
            showClear: true,
        }).on("dp.change", function (e) {
//            $('#wizardForm').bootstrapValidator('revalidateField', 'startdate');
        });

        $('.select2').select2();
        $('#rootwizard').bootstrapWizard({
            tabClass: 'nav nav-pills',
            onTabClick: function (tab, navigation, index) {
                return false;
            },
            onNext: function (tab, navigation, index) {
                $valid = true;
                $validator = $('#wizardForm').data('bootstrapValidator'); //the validator
                $wizard = $('#rootwizard').data('bootstrapWizard'); //the wizard

                $tab = $('#wizardForm').find('.tab-pane').eq(index - 1);
                $tab.find('input:text, input:password, input:file, select, textarea, input:not([type=hidden])')
                    .each(function () {
                        if ($validator.options.fields[$(this).attr('name')]) {
                            $validator.validateField($(this).attr('namec'));
                            if ($(this).closest('.form-group').hasClass('has-error')) {
                                $valid = false;
                            }
                        }
                    });
                return $valid;
            },
            onTabShow: function (tab, navigation, index) {
                switch (index) {
                    case 1:
                        $(".next").hide();
                        $(".previous").show();
                        $(".finish").show();
                        break;
                    default:
                        $(".previous").hide();
                        $(".next").show();
                        $(".finish").hide();
                        break;
                }
            }
        });

        $('input[type="radio"]').on('click change', function (e) {
            var value = $(this).val();
            if (value == 1) {

                $('#ifTwo').attr("hidden", "true");
                $('#ifOne').removeAttr("hidden");
            } else {
                $('#ifOne').attr("hidden", "true");
                $('#ifTwo').removeAttr("hidden");
            }
        });

        <c:forEach items="${monthTargetX}" var="dt" varStatus="loop">
        $("#monthRealization${loop.index}").keyup(function () {
            var $this = $(this);
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt(input, 10) : 0;
            $this.val(function () {
                return ( input === 0 ) ? "" : input.toLocaleString("en-US");
            });
        });
        $("#subsequence${loop.index}").keyup(function () {
            var $this = $(this);
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt(input, 10) : 0;
            $this.val(function () {
                return ( input === 0 ) ? "" : input.toLocaleString("en-US");
            });
        });
        </c:forEach>
    });

    function getBudgetBalanceByProker(param) {
        var selectedProker = param.value;
        $('#prokerId').val(selectedProker);
        if (selectedProker !== null || selectedProker !== '') {
            $('#tblDet').removeAttr("hidden");
            $.ajax({
                cache: false,
                type: "GET",
                url: APP_PATH + '/prk/proker/getprokerbyid/' + selectedProker,
                contentType: 'application/json',
                success: function (hasil) {
                    var res = hasil.data;
                    $('#realRp').html("Rp. " + res.totalRealization);
                    $('#realP').html((res.totalRealization * 100) / res.prokerBudget + " %");
                    $('#amount').html("Rp. " + (res.prokerBudget - res.totalRealization));
                    $('#pic').html(res.picUserName);
                    var timestamp = res.dateTarget;
                    var date = new Date(timestamp);
                    $('#dateTarget').html(moment(date).format('DD/MM/yyyy'));
                    $('#budget').html(formatRupiah2(res.prokerBudget));
                    var ikuData = hasil.dataIku;
                    var htmlIku = "";
                    $('#isIku').val(res.isIku);
                    if (res.isIku == 1) {
                        $('#ikuTable').removeAttr("hidden");
                        for (i = 0; i < ikuData.length; i++) {
                            htmlIku += "<tr>" +
                                "<td  style='text-align: center'> " + ikuData[i].ikuTw + " <input type='hidden'" +
                                " name='ikuTwId[]' value='" + ikuData[i].ikuTwId + "' /></td>" +
                                "<td style='padding : 1%'><input type='text' name='target[]' class='form-control'/></td>" +
                                "<td style='padding:1%'><input type='text' name='period[]' class='form-control'/></td>" +
                                "<td style='padding:1%'><input type='text' name='ikuRealization[]' class='form-control' /></td>" +
                                "<td style='padding:1%'><input type='text' name='achievement[]' class='form-control' /></td>" +
                                "<td style='padding:1%'><input type='text' name='explanation[]' class='form-control' /></td>" +
                                "</tr>";
                        }
                        $('#ikuTarget').html(htmlIku);
                    } else {
                        $('#ikuTable').attr("hidden", true);
                    }

                    var monthData = hasil.dataMonth;
                    var htmlMonth = "";
                    var month = hasil.month;
                    for (i = 0; i < monthData.length; i++) {
                        htmlMonth += "<tr>" +
                            "<td  style='text-align: center'>" + month[i].monthName + " <input type='hidden'" +
                            " name='monthTargetId[]' value='" + monthData[i].monthTargetId + "' /></td>" +
                            "<td style='text-align: left; padding : 1%'>" + monthData[i].target + "</td>" +
                            "<td style='padding:1%'><input type='text' name='progress[]' class='form-control'/></td>" +
                            "<td style='padding:1%'><input type='number' name='monthRealization[]' class='form-control' /></td>" +
                            "<td style='padding:1%'><input type='number' name='subsequence[]' class='form-control' /></td>" +
                            "</tr>";
                    }
                    $('#monthTarget').html(htmlMonth);
                }
            });

        } else {
            $('#tblDet').attr("hidden", true);
        }

    }

    function getBudgetBalance(divisionId) {
        $("#budgetBalance").html('950000');
    }

    function submitData() {
        var data = {
            "prokerId": $('#prokerId').val(),
            "ikuPeriod": [],
            "ikuRealization": [],
            "ikuAchievement": [],
            "ikuExplanation": [],
            "ikuTarget": [],
            "isIku": $('#isIku').val(),
            "ikuId": [],
            "monthId": [],
            "monthProgress": [],
            "monthRealization": [],
            "monthSubsequence": []
        };

        $('#ikuTable').find('input[name="ikuTwId[]"]').each(function () {
            data.ikuId.push(
                $(this).val()
            );
        });
        $('#ikuTable').find('input[name="target[]"]').each(function () {
            data.ikuTarget.push(
                $(this).val()
            );
        });
        $('#ikuTable').find('input[name="period[]"]').each(function () {
            data.ikuPeriod.push(
                $(this).val()
            );
        });
        $('#ikuTable').find('input[name="ikuRealization[]"]').each(function () {
            data.ikuRealization.push(
                $(this).val()
            );
        });
        $('#ikuTable').find('input[name="achievement[]"]').each(function () {
            data.ikuAchievement.push(
                $(this).val()
            );
        });
        $('#ikuTable').find('input[name="explanation[]"]').each(function () {
            data.ikuExplanation.push(
                $(this).val()
            );
        });

        $('#monthTable').find('input[name="monthTargetId[]"]').each(function () {
            data.monthId.push(
                $(this).val()
            );
        });
        $('#monthTable').find('input[name="progress[]"]').each(function () {
            data.monthProgress.push(
                $(this).val()
            );
        });
        $('#monthTable').find('input[name="monthRealization[]"]').each(function () {
            data.monthRealization.push(
                $(this).val()
            );
        });
        $('#monthTable').find('input[name="subsequence[]"]').each(function () {
            data.monthSubsequence.push(
                $(this).val()
            );
        });

        var dataString = JSON.stringify(data);
        console.log(dataString);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': APP_PATH + '/rlz/realization/saveOrUpdate',
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalRlzRealization').modal('toggle');
                    otable.ajax.reload(null, false);
                    number = 0;
                    success("Data Berhasil Disimpan!");
                } else {
                    danger("Data Gagal Disimpan!");
                }
            }
        });
    }

    function getDivision(directorateId) {
        $.ajax({
            cache: false,
            type: "GET",
            url: APP_PATH + '/app/div/getbydirid/' + directorateId.value,
            contentType: 'application/json',
            success: function (hasil) {
                var dataResult = [];
                dataResult = hasil.data;
                var stringHtml = "<option value=''>-- Pilih Divisi --</option>";
                for (i = 0; i < dataResult.length; i++) {
                    stringHtml += "<option value='" + dataResult[i].divisionId + "'>" + dataResult[i].divisionName + "</option>";
                }
                $('#divisionId').html(stringHtml);
            }
        });
    }

    function getProkerByDivAndYear(thisvar) {
        var divId = $('#divisionId').val();
        $.ajax({
            cache: false,
            type: "GET",
            url: APP_PATH + '/prk/proker/getprokerbydivid/' + divId + '/' + thisvar.value,
            contentType: 'application/json',
            success: function (hasil) {
                var dataResult = [];
                dataResult = hasil.data;
                var stringHtml = "<option value=''>-- Pilih Program Kerja -- </option>";
                for (i = 0; i < dataResult.length; i++) {
                    if (dataResult[i].mainProkerName !== null || dataResult[i].mainProkerName !== '') {
                        stringHtml += "<option value='" + dataResult[i].prokerId + "'>" + dataResult[i].mainProkerName + "</option>";
                    } else {
                        stringHtml += "<option value='" + dataResult[i].prokerId + "'>" + dataResult[i].subMainProkerName + "</option>";
                    }

                }
                $('#prokerNameSelect').html(stringHtml);
                var stringHtml = "<option value=''>-- Pilih Kode IKU -- </option>";
                for (i = 0; i < dataResult.length; i++) {
                    if (dataResult[i].isIku == 1) {
                        stringHtml += "<option value='" + dataResult[i].prokerId + "'>" + dataResult[i].ikuCode + "</option>";
                    }
                }
                $('#ikuCodeChoose').html(stringHtml);
            }
        });
    }

    function submit() {
        var data = $("#formRole").serializeJSON();
        var dataString = JSON.stringify(data);
        var idData = $("#prokerId").val();
        var is = null;
        if (idData === "") {
            is = 1;
        } else {
            is = 2;
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': APP_PATH + '/app/role/saveOrUpdate/' + is,
            'data': dataString,
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $(".loading").hide();
                    $('#modalRlzRealization').modal('toggle');
                    otable.ajax.reload(null, false);
                    showSmallInfo("Informasi", "Data berhasil disimpan", 5000);
                } else {
                    $(".loading").hide();
                    showSmallError("Kesalahan", "Data gagal disimpan", 5000);
                }
            }
        });
    }
</script>
