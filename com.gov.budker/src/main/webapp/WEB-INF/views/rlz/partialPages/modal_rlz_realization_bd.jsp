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
                            <input type="hidden" name="prokerId" value="${prokerData.prokerId}" id="prokerId"
                                   class="form-control"/>
                            <input type="hidden" name="isIku" value="${prokerData.prokerId}" id="isIku"
                                   class="form-control"/>
                        </h4>
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
                                        <%--<div class="form-group">--%>
                                        <%--<label>Pilih Direktorat</label>--%>
                                        <%--<select class="select2 form-control" name="directorateId"--%>
                                        <%--id="directorateId"--%>
                                        <%--onchange="getDivision(this)">--%>
                                        <%--<option value="">-- Pilih Direktorat --</option>--%>
                                        <%--<c:forEach items="${listDirectorate}" var="data">--%>
                                        <%--<option value="${data.directorateId}"--%>
                                        <%--<c:if test="${prokerData.directorateId == data.directorateId}">selected</c:if>>--%>
                                        <%--${data.directorateName}--%>
                                        <%--</option>--%>
                                        <%--</c:forEach>--%>
                                        <%--</select>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label>Pilih Divisi</label>
                                            <select class="select2 form-control" name="divisionId"
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
                                                        <%--class="label-text"  disabled="true">Nama Proker</span>--%>
                                                <%--</label>--%>
                                                <%--<br/>--%>
                                                <%--<label class="label-check">--%>
                                                    <%--<input type="radio" name="isProker" value="2"> <span--%>
                                                        <%--class="label-text"  disabled="true">Kode IKU</span>--%>
                                                <%--</label>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <div class="row">
                                            <div id="ifOne">
                                                <div class="form-group col-md-6">
                                                    <label>Pilih Proker</label>
                                                    <select class="select2 form-control" name="prokerName"
                                                            id="prokerNameSelect" disabled
                                                            onchange="getBudgetBalanceByProker(this)">
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
                                                        <%--<c:forEach items="${listProker}" var="data">--%>
                                                        <%--<c:if test="${data.ikuCode != null && prokerData.isIku != 1}">--%>
                                                        <%--<option value="${data.prokerId}">--%>
                                                        <%--${data.ikuCode}--%>
                                                        <%--</option>--%>
                                                        <%--</c:if>--%>
                                                        <%--</c:forEach>--%>
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
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Tanggal Dokumen Lengkap</label>
                                                <div class='input-group' id='datetimepicker1'>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                                    <input type='text'
                                                           class="form-control startdate"
                                                           name="documentDate" id="documentDate" readonly
                                                           value="${rlzHeader.documentDate}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Bentuk Dokumen</label>
                                                <select class="select2 form-control" name="docType" id="docType">
                                                    <option value="">-- Pilih Bentuk Dokumen --</option>
                                                    <c:forEach items="${listDoc}" var="data">
                                                        <option value="${data.docId}"
                                                                <c:if test="${data.docId == rlzHeader.documentType}">
                                                                    selected
                                                                </c:if>
                                                        >
                                                                ${data.docName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Tanggal Surat Persetujuan</label>
                                                <div class='input-group'>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                                    <input type='text'
                                                           class="form-control startdate"
                                                           name="approveDate" id="approveDate"
                                                           readonly value="${rlzHeader.approveDate}"/>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Jumlah Hari</label>
                                                <input type="hidden" name="realizationId" id="realizationId"
                                                       class="form-control" value="${rlzHeader.realizationId}">
                                                <input type="number" name="sumDay" id="sumDay"
                                                       class="form-control" value="${rlzHeader.sumDay}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group  col-md-6">
                                                <label>Tepat Waktu</label>
                                                <br/>
                                                <label class="label-check">
                                                    <input type="radio" name="isOnTime" value="1" checked> <span
                                                        class="label-text">Ya</span>
                                                </label>
                                                <br/>
                                                <label class="label-check">
                                                    <input type="radio" name="isOnTime" value="0"> <span
                                                        class="label-text">Tidak</span>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Nilai Rata-Rata Survei</label>
                                                <input type="number" name="surveiValue" id="surveiValue"
                                                       class="form-control" step=".001"
                                                       value="${rlzHeader.averageValue}">
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
                                                                    <fmt:formatNumber var="real" type="currency"
                                                                                      pattern="#,##0;-#,##0"
                                                                                      value="${prokerData.totalRealization}"/>
                                                                    Rp. ${real}</div>
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
                                    <%--<div id="ikuTable">--%>
                                    <%--<div class="form-title form-group">Target IKU Per TW--%>
                                    <%--</div>--%>
                                    <%--<div class="form-col">--%>
                                    <%--<div class="form-group">--%>
                                    <%--<div class="group-input">--%>
                                    <%--&lt;%&ndash;<label class=""><b>Input Text</b></label>&ndash;%&gt;--%>
                                    <%--<table width="100%" id="tableIku" border="1">--%>
                                    <%--<thead>--%>
                                    <%--<td data-class="expand" style="text-align: center; padding:1%">--%>
                                    <%--TW--%>
                                    <%--</td>--%>
                                    <%--<td data-class="expand" style="padding:1%">--%>
                                    <%--Target Pencapaian--%>
                                    <%--</td>--%>
                                    <%--<td data-class="expand" style="padding:1%">--%>
                                    <%--Realisasi--%>
                                    <%--</td>--%>
                                    <%--<td data-class="expand" style="padding:1%">--%>
                                    <%--Pencapaian--%>
                                    <%--</td>--%>
                                    <%--<td data-class="expand" style="padding:1%">--%>
                                    <%--Penjelasan--%>
                                    <%--</td>--%>
                                    <%--</thead>--%>
                                    <%--<tbody id="ikuTarget">--%>
                                    <%--</tbody>--%>
                                    <%--</table>--%>
                                    <%--</div>--%>
                                    <%--</div>--%>
                                    <%--</div>--%>
                                    <%--</div>--%>
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
                                                        <%--<input type='number' name='realization[]'--%>
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
                                                                    <input type='text' name='progress[]'
                                                                           class='form-control'
                                                                           value="${monthTarget[loop.index].progress}"/>
                                                                </td>
                                                                <td style='padding:1%'>
                                                                    <fmt:formatNumber var="rlz" type="currency"
                                                                                      pattern="#,##0;-#,##0"
                                                                                      value="${monthTarget[loop.index].realization}"/>
                                                                    <input type='text' name='monthRealization[]' id="monthRealization${loop.index}"
                                                                           class='form-control' step="any" lang="en"
                                                                           value="${rlz}"/>
                                                                </td>
                                                                <td style='padding:1%'>
                                                                    <fmt:formatNumber var="ssq" type="currency"
                                                                                      pattern="#,##0;-#,##0"
                                                                                      value="${monthTarget[loop.index].subsequence}"/>
                                                                    <input type='text' name='subsequence[]'  id="subsequence${loop.index}"
                                                                           class='form-control' step="any" lang="en"
                                                                           value="${ssq}"/>
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
                    Value: {
                        validators: {
                            notEmpty: {}
                        }
                    }
                }
            });

        $('#documentDate').datetimepicker({
            ignoreReadonly: true,
            format: 'DD/MM/YYYY',
            showClear: true,
        }).on("dp.change", function (e) {
//            $('#wizardForm').bootstrapValidator('revalidateField', 'startdate');
        }).val(moment('${rlzHeader.documentDate}').format('DD/MM/YYYY'));

        $('#approveDate').datetimepicker({
            ignoreReadonly: true,
            format: 'DD/MM/YYYY',
            showClear: true,
        }).on("dp.change", function (e) {
//            $('#wizardForm').bootstrapValidator('revalidateField', 'startdate');
        }).val(moment('${rlzHeader.approveDate}').format('DD/MM/YYYY'));

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

        $('input[name="isProker"]').on('click change', function (e) {
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
            input = input ? parseInt( input, 10 ) : 0;
            $this.val( function() {
                return ( input === 0 ) ? "" : input.toLocaleString( "en-US" );
            } );
        });
        $("#subsequence${loop.index}").keyup(function (e) {
            var $this = $(this);
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt( input, 10 ) : 0;
            $this.val( function() {
                return ( input === 0 ) ? "" : input.toLocaleString( "en-US" );
            } );
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
                    $('#realRp').html("Rp. " + formatRupiah2(res.totalRealization));
                    $('#realP').html((res.totalRealization * 100) / res.prokerBudget + " %");
                    $('#amount').html("Rp. " + formatRupiah2(res.prokerBudget - res.totalRealization));
                    $('#pic').html(res.picUserName);
                    var timestamp = res.dateTarget;
                    var date = new Date(timestamp);
                    $('#dateTarget').html(moment(date).format('DD/MM/YYYY'));
                    $('#budget').html(formatRupiah2(res.prokerBudget));
                    var ikuData = hasil.dataIku;
                    var htmlIku = "";
                    if (res.isIku == 1) {
                        $('#ikuTable').removeAttr("hidden");
                        for (i = 0; i < ikuData.length; i++) {
                            htmlIku += "<tr>" +
                                "  <td  style='text-align: center'> " + ikuData[i].ikuTw + "</td>" +
                                "<td style='text-align: left; padding : 1%'>" + ikuData[i].ikuTarget + "</td>" +
                                "<td style='padding:1%'><input type='text' name='realization[]' class='form-control' /></td>" +
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
                            "  <td  style='text-align: center'> " + month[i].monthName + "<input type='hidden'" +
                            " name='monthTargetId[]' value='" + monthData[i].monthTargetId + "' /></td>" +
                            "<td style='text-align: left; padding : 1%'>" + monthData[i].target + "</td>" +
                            "<td style='padding:1%'><input type='text' name='progress[]' class='form-control'/></td>" +
                            "<td style='padding:1%'><input type='number' name='realization[]' class='form-control' /></td>" +
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

    function submitData() {

        var headerRlz = {
            "documentDate": $("#documentDate").val(),
            "documentType": $("#docType").val(),
            "approveDate": $("#approveDate").val(),
            "sumDay": $("#sumDay").val(),
            "isOnTime": $("input[name='isOnTime']:checked").val(),
            "averageValue": $("#surveiValue").val(),
            "realizationId": $('#realizationId').val()
        };
        var data = {
            "prokerId": $('#prokerId').val(),
            "headRlz": {},
            "isIku": 0,
            "ikuId": [],
            "monthId": [],
            "monthProgress": [],
            "monthRealization": [],
            "monthSubsequence": []
        };

        data.headRlz = headerRlz;

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
                    if (dataResult[i].isIku == 0) {
                        if (dataResult[i].mainProkerName !== null || dataResult[i].mainProkerName !== '') {
                            stringHtml += "<option value='" + dataResult[i].prokerId + "'>" + dataResult[i].mainProkerName + "</option>";
                        } else {
                            stringHtml += "<option value='" + dataResult[i].prokerId + "'>" + dataResult[i].subMainProkerName + "</option>";
                        }
                    }
                }
                $('#prokerNameSelect').html(stringHtml);
//                var stringHtml = "<option value=''>-- Pilih Kode IKU -- </option>";
//                for (i = 0; i < dataResult.length; i++) {
//                    stringHtml += "<option value='" + dataResult[i].prokerId + "'>" + dataResult[i].ikuCode + "</option>";
//                }
//                $('#ikuCodeChoose').html(stringHtml);
            }
        });
    }

</script>
