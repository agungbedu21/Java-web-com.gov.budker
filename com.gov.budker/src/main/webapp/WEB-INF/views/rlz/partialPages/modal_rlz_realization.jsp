<%-- 
    Created on : mei 2018, 1:21:28 PM
    Author     : Agung Abdurohman
--%>

<%@page import="java.util.List" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                                            <label>Pilih Direktorat</label>
                                            <select class="select2 form-control" name="directorateId"
                                                    id="directorateId"
                                                    onchange="getDivision(this)">
                                                <option value="">-- Pilih Direktorat --</option>
                                                <c:forEach items="${listDirectorate}" var="data">
                                                    <option value="${data.directorateId}"
                                                            <c:if test="${prokerData.directorateId == data.directorateId}">selected</c:if>>
                                                            ${data.directorateName}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Pilih Divisi</label>
                                            <select class="select2 form-control" name="divisionId"
                                                    id="divisionId">
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
                                                        onchange="getProkerByDivAndYear(this)">
                                                    <option value="">-- Pilih Tahun --</option>
                                                    <option value="2018"
                                                            <c:if test="${prokerData.prokerYear == 2018}">selected</c:if>>
                                                        2018
                                                    </option>
                                                    <option value="2019"
                                                            <c:if test="${prokerData.prokerYear == 2019}">selected</c:if>>
                                                        2019
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="form-group  col-md-6">
                                                <label>Pilih Proker Berdasarkan</label>
                                                <br/>
                                                <label class="label-check">
                                                    <input type="radio" name="isProker" value="1" checked> <span
                                                        class="label-text">Nama Proker</span>
                                                </label>
                                                <br/>
                                                <label class="label-check">
                                                    <input type="radio" name="isProker" value="2"> <span
                                                        class="label-text">Kode IKU</span>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div id="ifOne">
                                                <div class="form-group col-md-6">
                                                    <label>Pilih Proker</label>
                                                    <select class="select2 form-control" name="prokerName"
                                                            id="prokerNameSelect"
                                                            onchange="getBudgetBalanceByProker(this)">
                                                        <option value="">-- Pilih Proker --</option>
                                                        <c:forEach items="${listProker}" var="data">
                                                            <option value="${data.prokerId}">
                                                                <c:if test="${data.mainProkerName != null}">
                                                                    ${data.mainProkerName}
                                                                </c:if>
                                                                <c:if test="${data.subMainProkerName != null}">
                                                                    (${data.subMainProkerName})
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
                                                            id="ikuCodeChoose"
                                                            onchange="getBudgetBalanceByProker(this)">
                                                        <option value="">-- Pilih Kode IKU --</option>
                                                        <c:forEach items="${listProker}" var="data">
                                                            <c:if test="${data.ikuCode != null}">
                                                                <option value="${data.prokerId}">
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
                                                       width="100%" hidden>
                                                    <tbody>
                                                    <tr>
                                                        <td>PIC</td>
                                                        <td>:</td>
                                                        <td>
                                                            <div id="pic"></div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Target Waktu Penyelesaian</td>
                                                        <td>:</td>
                                                        <td>
                                                            <div id="dateTarget"></div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Anggaran</td>
                                                        <td>:</td>
                                                        <td>
                                                            <div id="budget"></div>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Bentuk Dokumen</label>
                                                <select class="select2 form-control" name="docType" id="docType">
                                                    <option value="">-- Pilih Bentuk Dokumen --</option>
                                                    <c:forEach items="${listDoc}" var="data">
                                                        <option value="${data.docId}">
                                                                ${data.docName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
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
                                                           name="documentDate" id="documentDate" readonly/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Jumlah Hari</label>
                                                <input type="number" name="sumDay" id="sumDay"
                                                class="form-control">
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
                                                <input type="decimal" name="surveiValue" id="surveiValue"
                                                       class="form-control">
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
                                                                <div id="realRp">Rp. xxxxx</div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Realisasi (%)</td>
                                                            <td>:</td>
                                                            <td>
                                                                <div id="realP">xxxx %</div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Saldo</td>
                                                            <td>:</td>
                                                            <td>
                                                                <div id="amount">Rp. XXXX</div>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                    <div id="ikuTable">
                                        <div class="form-title form-group">Target IKU Per TW
                                        </div>
                                        <div class="form-col">
                                            <div class="form-group">
                                                <div class="group-input">
                                                    <%--<label class=""><b>Input Text</b></label>--%>
                                                    <table width="100%" id="tableIku" border="1">
                                                        <thead>
                                                        <td data-class="expand" style="text-align: center; padding:1%">
                                                            TW
                                                        </td>
                                                        <td data-class="expand" style="padding:1%">
                                                            Target Pencapaian
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
                                                        <thead>
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
                    DivisionId: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    DirectorateId: {
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
    });

    function getBudgetBalanceByProker(param) {
        var selectedProker = param.value;
        if (selectedProker !== null || selectedProker !== '') {
            $('#tblDet').removeAttr("hidden");
            $.ajax({
                cache: false,
                type: "GET",
                url: APP_PATH + '/prk/proker/getprokerbyid/' + selectedProker,
                contentType: 'application/json',
                success: function (hasil) {
                    var res = hasil.data;
                    $('#pic').html(res.picUserName);
                    var timestamp = res.dateTarget;
                    var date = new Date(timestamp);
                    $('#dateTarget').html(date);
                    $('#budget').html(res.prokerBudget);
                    var ikuData = hasil.dataIku;
                    var htmlIku = "";
                    if (res.isIku == 1) {
                        $('#ikuTable').removeAttr("hidden");
                        for (i = 0; i < ikuData.length; i++) {
                            htmlIku += "<tr>" +
                                "  <td  style='text-align: center'> " + ikuData[i].ikuTw + "</td>" +
                                "<td style='text-align: left; padding : 1%'>" + ikuData[i].ikuTarget + "</td>" +
                                "<td style='padding:1%'><input type='text' name='realzation[]' class='form-control' /></td>" +
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
                            "  <td  style='text-align: center'> " + month[i].monthName + "</td>" +
                            "<td style='text-align: left; padding : 1%'>" + monthData[i].target + "</td>" +
                            "<td style='padding:1%'><input type='text' name='progress[]' class='form-control'/></td>" +
                            "<td style='padding:1%'><input type='text' name='realzation[]' class='form-control' /></td>" +
                            "<td style='padding:1%'><input type='text' name='subsequence[]' class='form-control' /></td>" +
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
            "directorateId": $('#directorateId').val(),
            "divisionId": $('#divisionId').val(),
            "prokerYear": $('#year').val(),
            "isIku": $('#isIku').val(),
            "ikuCode": $('#ikuCode').val(),
            "userDivisionId": $('#userDivisionId').val(),
            "picUserName": $('#picUserId').text(),
            "targetDate": $('#targetDate').val(),
            "prokerBudget": $('#prokerBudget').val(),
            "ikuTw": [1, 2, 3, 4],
            "ikuTarget": [],
            "month": [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            "target": [],
            "mainProkerName": "",
            "subProkerName": "",
        };

        if ($("#isMainProker option:selected").val() == 1) {
            data['mainProkerName'] = ($('#prokerName').val());
        } else {
            data['mainProkerName'] = ($('#prokerNameSelect option:selected').text());
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
        console.log(dataString);
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
                    stringHtml += "<option value='" + dataResult[i].prokerId + "'>" + dataResult[i].ikuCode + "</option>";
                }
                $('#ikuCodeChoose').html(stringHtml);
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

    function changeProkerInput(isProker) {
        if (isProker.value == 1) {
            $('#ifZero').attr("hidden", true);
            $('#ifOne').removeAttr("hidden");
        } else {
            $('#ifOne').attr("hidden", true);
            $('#ifZero').removeAttr("hidden");
        }
    }
</script>
