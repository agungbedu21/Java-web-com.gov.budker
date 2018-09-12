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
<div class="modal" id="modalPrkProker" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="wizardForm">
                <div id="rootwizard">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                            <c:if test="${prokerData.prokerId == null}">Tambah </c:if>
                            <c:if test="${prokerData.prokerId != null}">Ubah </c:if>
                            Program Kerja
                            <c:if test="${prokerData.prokerId == null}"> Baru</c:if>
                            <input type="hidden" name="prokerId" value="${prokerData.prokerId}" id="prokerId"
                                   class="form-control">
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
                                        <p class="list-group-item-text">Target Dan Rencana Program Kerja</p>
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
                                                    id="divisionId" onchange="isIkuAvailable(this)"
                                                    <c:if test="${prokerData.prokerId != null}">disabled </c:if>
                                            >
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
                                                        onchange="getBudgetBalance(this)"
                                                        <c:if test="${prokerData.prokerId != null}">disabled </c:if>
                                                >
                                                    <option value="">-- Pilih Tahun --</option>
                                                    <c:forEach items="${listYear}" var="data">
                                                        <option value="${data.year}"
                                                                <c:if test="${prokerData.prokerYear == data.year}">selected</c:if>>
                                                                ${data.year}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <div class="group-input">
                                                    <label></label>
                                                    <div>Saldo Rencana Anggaran Rp. <b id="budgetBalance">0</b>.00</div>
                                                    <%--<input type="number" name="Value" id="Value"--%>
                                                    <%--class="form-control" value="${prokerData.Value}">--%>

                                                </div>
                                                <hr/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Apakah Memiliki subproker?</label>
                                                <select class="select2 form-control" name="isMainProker"
                                                        id="isMainProker" onchange="changeProkerInput(this)"
                                                        <c:if test="${prokerData.prokerId != null}">disabled </c:if>
                                                >
                                                    <option value="1"
                                                            <c:if test="${prokerData.isMainProker == 1}">selected</c:if>>
                                                        Tidak
                                                    </option>
                                                    <option value="0"
                                                            <c:if test="${prokerData.isMainProker == 0}">selected</c:if>>
                                                        Ya
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div id="ifOne">
                                                <div class="group-input">
                                                    <label>Nama Program</label>
                                                    <input type="text" name="prokerName" id="prokerName" id="prokerName"
                                                           class="form-control"
                                                    <c:if test='${prokerData.mainProkerName == null}'>
                                                           value="${prokerData.subMainProkerName}"
                                                    </c:if>
                                                    <c:if test='${prokerData.mainProkerName != null}'>
                                                           value="${prokerData.mainProkerName}"
                                                    </c:if>
                                                    <c:if test="${prokerData.prokerId != null}"> disabled </c:if>
                                                    >
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div id="ifZero" hidden>
                                                <div class="form-group col-md-6">
                                                    <label>Pilih Proker Induk</label>
                                                    <select class="select2 form-control" name="prokerNameSelect"
                                                            id="prokerNameSelect"
                                                            <c:if test="${prokerData.prokerId != null}">disabled </c:if>
                                                            onchange="getIkuOrNot(this)">
                                                        <option value="0">-- Pilih Proker Induk --</option>
                                                        <c:forEach items="${listMainProker}" var="data">
                                                            <option value="${data.mainProkerId}"
                                                                    <c:if test="${data.mainProkerName == prokerData.mainProkerName}">selected</c:if>
                                                            >${data.mainProkerName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <div class="group-input">
                                                        <label>Nama Sub Proker</label>
                                                        <input type="text" name="subProkerName" id="subProkerName"
                                                               class="form-control"
                                                               <c:if test="${prokerData.prokerId != null}">disabled </c:if>
                                                               value="${prokerData.subMainProkerName}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6" id="isIkuForm"
                                                 <c:if test="${prokerData.isIku == 0}">hidden</c:if>
                                            >
                                                <label>Kode Iku</label>
                                                <select class="select2 form-control" name="isIku" id="isIku"
                                                        onchange="enterIku(this)"
                                                        <c:if test="${prokerData.prokerId != null}">disabled </c:if>
                                                >
                                                    <option value="1"
                                                            <c:if test="${prokerData.isIku == 1}">selected</c:if>>
                                                        Ada
                                                    </option>
                                                    <option value="0"
                                                            <c:if test="${prokerData.isIku == 0}">selected</c:if>>
                                                        Tidak
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <div class="group-input">
                                                    <div id="enterIkuId"
                                                         <c:if test="${prokerData.isIku == 0}">hidden</c:if>
                                                    >
                                                        <label>Masukan Kode IKU</label>
                                                        <input type="text" name="ikuCode" id="ikuCode"
                                                               class="form-control" value="${prokerData.ikuCode}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-title form-group">PIC
                                        </div>
                                        <div class="row">
                                            <%--<div class="form-group col-md-6">--%>
                                            <%--<label>Pilih Divisi</label>--%>
                                            <%--<select class="select2 form-control" name="divisionId"--%>
                                            <%--id="userDivisionId" onchange="getUserByDivision(this)">--%>
                                            <%--<option value="">-- Pilih Divisi --</option>--%>
                                            <%--<c:forEach items="${listDivision}" var="data">--%>
                                            <%--<option value="${data.divisionId}"--%>
                                            <%--<c:if test="${prokerData.divisionId == data.divisionId}">selected</c:if>>--%>
                                            <%--${data.divisionName}--%>
                                            <%--</option>--%>
                                            <%--</c:forEach>--%>
                                            <%--</select>--%>
                                            <%--</div>--%>
                                            <div class="form-group col-md-6">
                                                <label>Pilih User</label>
                                                <select class="select2 form-control" name="picUserId"
                                                        id="picUserId" multiple="multiple">
                                                    <option value="">-- Pilih User --</option>
                                                    <c:forEach items="${userList}" var="data">
                                                        <option value="${data.employeeId}"
                                                                <c:forEach items="${picUser}" var="pic">
                                                                    <c:if test="${data.employeeId == pic.userId}">selected</c:if>
                                                                </c:forEach>

                                                        >
                                                                ${data.employeeName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Target Waktu Penyelesaian</label>
                                                <div class='input-group' id='datetimepicker1'>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                                    <input type='text'
                                                           class="form-control startdate"
                                                           name="targetDate" id="targetDate" readonly/>
                                                </div>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <div class="group-input">
                                                    <label>Anggaran Proker</label>
                                                    <fmt:formatNumber var="budget" type="currency"
                                                                      pattern="#,##0;-#,##0"
                                                                      value="${prokerData.prokerBudget}"/>
                                                    <input type="text" name="prokerBudget" id="prokerBudget"
                                                           class="form-control" value="${budget}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab2">
                                <div class="col-md-12 col-xs-12 form-content shadow">
                                    <div id="ikuTable"
                                         <c:if test="${prokerData.isIku == 0}">hidden</c:if> >
                                        <div class="form-title form-group">Target IKU Per TW
                                        </div>
                                        <div class="form-col">
                                            <div class="form-group">
                                                <div class="group-input">
                                                    <%--<label class=""><b>Input Text</b></label>--%>
                                                    <table width="100%" id="tableIku" border="1">
                                                        <thead style="font-weight: bold; text-align: center">
                                                        <td data-class="expand" style="text-align: center; padding:1%">
                                                            TW
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Target Pencapaian
                                                        </td>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td style="text-align: center">1</td>
                                                            <td style="padding:1%">
                                                                <textarea type="text"
                                                                       name="ikuTarget[]" class="form-control">${iku[0].ikuTarget}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">2</td>
                                                            <td style="padding:1%">
                                                                <textarea type="text"
                                                                          name="ikuTarget[]" class="form-control">${iku[1].ikuTarget}</textarea>
                                                                <%--<input type="text"--%>
                                                                       <%--name="ikuTarget[]" class="form-control"--%>
                                                                       <%--value="${iku[1].ikuTarget}">--%>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">3</td>
                                                            <td style="padding:1%">
                                                                <textarea type="text"
                                                                          name="ikuTarget[]" class="form-control">${iku[2].ikuTarget}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">4</td>
                                                            <td style="padding:1%">
                                                                <textarea type="text"
                                                                          name="ikuTarget[]" class="form-control">${iku[3].ikuTarget}</textarea>
                                                            </td>
                                                        </tr>
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
                                                        <thead style="font-weight: bold; text-align: center">
                                                        <td data-class="expand" style="text-align: center; padding:1%">
                                                            Bulan
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Rencana
                                                        </td>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td style="text-align: center">Januari</td>
                                                            <td style="padding:1%">
                                                                <textarea name="target[]" class="form-control"
                                                                >${monthTarget[0].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">Februari</td>
                                                            <td style="padding:1%">
                                                                <textarea name="target[]" class="form-control"
                                                                >${monthTarget[1].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">Maret</td>
                                                            <td style="padding:1%">
                                                                <textarea name="target[]" class="form-control"
                                                                >${monthTarget[2].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">April</td>
                                                            <td style="padding:1%">
                                                                <textarea name="target[]" class="form-control"
                                                                >${monthTarget[3].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">Mei</td>
                                                            <td style="padding:1%">
                                                                <textarea name="target[]" class="form-control"
                                                                >${monthTarget[4].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">Juni</td>
                                                            <td style="padding:1%">
                                                                <textarea
                                                                        name="target[]" class="form-control"
                                                                >${monthTarget[5].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">Juli</td>
                                                            <td style="padding:1%">
                                                                <textarea
                                                                        name="target[]" class="form-control"
                                                                >${monthTarget[6].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">Agustus</td>
                                                            <td style="padding:1%">
                                                                <textarea
                                                                        name="target[]"
                                                                        class="form-control">${monthTarget[7].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">September</td>
                                                            <td style="padding:1%">
                                                                <textarea
                                                                        name="target[]" class="form-control"
                                                                >${monthTarget[8].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">Oktober</td>
                                                            <td style="padding:1%">
                                                                <textarea
                                                                        name="target[]" class="form-control"
                                                                >${monthTarget[9].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">November</td>
                                                            <td style="padding:1%">
                                                                <textarea
                                                                        name="target[]" class="form-control"
                                                                >${monthTarget[10].target}</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center">Desember</td>
                                                            <td style="padding:1%">
                                                                <textarea
                                                                        name="target[]" class="form-control"
                                                                >${monthTarget[11].target}</textarea>
                                                            </td>
                                                        </tr>
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
        var $validator = $('#wizardForm')
            .bootstrapValidator({
                fields: {
                    divisionId: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    isIku: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    ikuCode: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    prokerName: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    prokerNameSelect: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    subProkerName: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    isMainProker: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    year: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    prokerBudget: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    targetDate: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    picUserId: {
                        validators: {
                            notEmpty: {}
                        }
                    }
                }
            });

        $("#prokerBudget").keyup(function () {
            var budgetVal = $('#prokerBudget').val();
            var $this = $(this);
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt(input, 10) : 0;
            $this.val(function () {
                return ( input === 0 ) ? "" : input.toLocaleString("en-US");
            });
        });

        $('#targetDate').datetimepicker({
            ignoreReadonly: true,
            format: 'DD/MM/YYYY',
            showClear: true,
        }).on("dp.change", function (e) {
            $('#wizardForm').bootstrapValidator('revalidateField', 'targetDate');
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
                            $validator.validateField($(this).attr('name'));
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
        if ('${prokerData.dateTarget}' === null || '${prokerData.dateTarget}' === '') {
            $('#targetDate').datetimepicker({
                ignoreReadonly: true,
                format: 'DD/MM/YYYY',
                showClear: true,
            }).on("dp.change", function (e) {

            });
        } else {
            $('#targetDate').datetimepicker({
                ignoreReadonly: true,
                format: 'DD/MM/YYYY',
                showClear: true,
            }).on("dp.change", function (e) {

            }).val(moment('${prokerData.dateTarget}').format('DD/MM/YYYY'));
        }

        getBudgetBalanceLunch();

    });

    function isIkuAvailable(params) {
        var divId = params.value;
        if (divId === "7") {
            $('#ikuTable').attr("hidden", true);
            $('#enterIkuId').attr("hidden", true);
            $('#isIkuForm').attr("hidden", true);
        } else {
            $('#ikuTable').removeAttr('hidden');
            $('#enterIkuId').removeAttr("hidden");
            $('#isIkuForm').removeAttr("hidden");
        }
    }

    function getBudgetBalance(year) {
        var data = {
            "divisionId": $("#divisionId").val(),
            "year": year.value
        };
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': APP_PATH + '/bud/budget/getbyid',
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                var data = hasil.dataBud;
                $('#budgetBalance').html(formatRupiah2(data.budgetValue));
            }
        });
    }

    function getBudgetBalanceLunch() {
        var data = {
            "divisionId": '${prokerData.divisionId}',
            "year": '${prokerData.prokerYear}'
        };
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': APP_PATH + '/bud/budget/getbyid',
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                var data = hasil.dataBud;
                $('#budgetBalance').html(formatRupiah2(data.budgetValue));
            }
        });
    }

    function getIkuOrNot(params) {
        var mainProkerId = params.value;
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'GET',
            'url': APP_PATH + '/prk/mainproker/getbyid/' + mainProkerId,
            'data': null,
            'dataType': 'json',
            success: function (hasil2) {
                var hasil = hasil2.dataBud;
                if (hasil.isIku == 0) {
                    $('#isIku').removeAttr("disabled");
                    $('#ikuCode').removeAttr("disabled");
                    $('#ikuCode').val("");
                    $('#ikuTable').attr("hidden", true);
                    $('#enterIkuId').attr('hidden', true);
                    $('#isIkuForm').attr('hidden', true);
                    $("select[name='isIku'] option[value='0']").attr('selected', 'selected');
                } else {
                    $('#ikuCode').val(hasil.ikuCode);
                    $('#isIku').attr("disabled", true);
                    $('#ikuCode').attr("disabled", true);
                    $('#ikuTable').removeAttr('hidden');
                    $('#isIkuForm').removeAttr('hidden');
                    $('#enterIkuId').removeAttr('hidden');
                    $("select[name='isIku'] option[value='1']").attr('selected', 'selected');
                }
            }
        });

    }

    function submitData() {
        var data = {
            "prokerId": $('#prokerId').val(),
            "divisionId": $('#divisionId').val(),
            "prokerYear": $('#year').val(),
            "isIku": $('#isIku').val(),
            "ikuCode": $('#ikuCode').val(),
            "targetDate": $('#targetDate').val(),
            "prokerBudget": $('#prokerBudget').val(),
            "ikuTw": [1, 2, 3, 4],
            "ikuTarget": [],
            "month": [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            "target": [],
            "mainProkerName": "",
            "subProkerName": "",
            "picUser": []
        };

        $('#picUserId :selected').each(function (i, sel) {
            data.picUser.push($(sel).val());
        });


        if ($("#isMainProker option:selected").val() == 1 && $('#prokerName').val() !== '') {
            data['mainProkerName'] = ($('#prokerName').val());
        } else if ($("#isMainProker option:selected").val() == 0 && $('#prokerNameSelect option:selected').val() != 0) {
            data['mainProkerName'] = ($('#prokerNameSelect option:selected').text());
            data['subProkerName'] = ($('#subProkerName').val());
        } else if ($("#isMainProker option:selected").val() == 0 && $('#prokerNameSelect option:selected').val() == 0) {
            data['mainProkerName'] = ($('#prokerName').val());
            data['subProkerName'] = ($('#subProkerName').val());
        }

        $('#tableIku').find('input[name="ikuTarget[]"]').each(function () {
            data.ikuTarget.push(
                $(this).val()
            );
        });

        $('#tableMonth').find('textarea[name="target[]"]').each(function () {
            data.target.push(
                $(this).val()
            );
        });

        var dataString = JSON.stringify(data);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': APP_PATH + '/prk/proker/saveOrUpdate',
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalPrkProker').modal('toggle');
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

    function getUserByDivision(divisionId) {
        $.ajax({
            cache: false,
            type: "GET",
            url: APP_PATH + '/app/employee/getbydivid/' + divisionId.value,
            contentType: 'application/json',
            success: function (hasil) {
                var dataResult = [];
                dataResult = hasil.data;
                var stringHtml = "";
                for (i = 0; i < dataResult.length; i++) {
                    stringHtml += "<option value='" + dataResult[i].employeeId + "'>" + dataResult[i].employeeName + "</option>";
                }
                $('#picUserId').html(stringHtml);
            }
        });
    }

    function enterIku(isIku) {
        if (isIku.value == 1) {
            $('#ikuTable').removeAttr('hidden');
            $('#enterIkuId').removeAttr('hidden');
        } else {
            $('#ikuTable').attr("hidden", true);
            $('#enterIkuId').attr('hidden', true);
        }
    }

    function changeProkerInput(isProker) {
        $('#isIku').removeAttr("disabled");
        $('#ikuCode').removeAttr("disabled");
        if (isProker.value == 1) {
            $('#ifZero').attr("hidden", true);
            $('#ifOne').removeAttr("hidden");
        } else {
            $('#ifOne').attr("hidden", true);
            $('#ifZero').removeAttr("hidden");
        }
    }
</script>
