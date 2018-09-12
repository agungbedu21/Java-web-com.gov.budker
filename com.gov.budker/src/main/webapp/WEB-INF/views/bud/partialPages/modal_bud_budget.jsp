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
<div class="modal" id="modalBudBudget" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="wizardForm">
                <div id="rootwizard">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                            <c:if test="${budgetData.budgetId == null}">Tambah </c:if>
                            <c:if test="${budgetData.budgetId != null}">Ubah </c:if>
                            Anggaran
                            <c:if test="${budgetData.budgetId == null}"> Baru</c:if>
                            <input type="hidden" name="budgetId" value="${budgetData.budgetId}" id="budgetId"
                                   class="form-control">
                    </div>
                    <div class="modal-body clearfix">
                        <div class="col-sm-12 paddingLR0">
                            <ul class="nav-justified thumbnail wizard-list  shadow">
                                <li class="">
                                    <a href="#tab1" data-toggle="tab" class="disable">
                                        <h4 class="list-group-item-heading">Step 1</h4>
                                        <p class="list-group-item-text">Detail Anggaran</p>
                                    </a>
                                </li>
                                <li class="">
                                    <a href="#tab2" data-toggle="tab" class="disable">
                                        <h4 class="list-group-item-heading">Step 2</h4>
                                        <p class="list-group-item-text">Target Pencapaian</p>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane" id="tab1">
                                <div class="col-md-12 col-xs-12 form-content shadow">
                                    <div class="form-title form-group">Detail Anggaran
                                    </div>
                                    <div class="form-col">
                                        <%--<div class="form-group">--%>
                                        <%--<label>Pilih Direktorat</label>--%>
                                        <%--<select class="select2 form-control" name="budgetDirectorateId"--%>
                                        <%--id="budgetDirectorateId"--%>
                                        <%--onchange="getDivision(this)">--%>
                                        <%--<option value="">-- Pilih Direktorat --</option>--%>
                                        <%--<c:forEach items="${listDirectorate}" var="data">--%>
                                        <%--<option value="${data.directorateId}"--%>
                                        <%--<c:if test="${budgetData.budgetDirectorateId == data.directorateId}">selected</c:if>>--%>
                                        <%--${data.directorateName}--%>
                                        <%--</option>--%>
                                        <%--</c:forEach>--%>
                                        <%--</select>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label>Pilih Divisi</label>
                                            <select class="select2 form-control" name="budgetDivisionId"
                                                    id="budgetDivisionId">
                                                <option value="">-- Pilih Divisi --</option>
                                                <c:forEach items="${listDivision}" var="data">
                                                    <option value="${data.divisionId}"
                                                            <c:if test="${budgetData.budgetDivisionId == data.divisionId}">selected</c:if>>
                                                            ${data.divisionName}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Pilih Tahun</label>
                                                <select class="select2 form-control" name="budgetYear" id="budgetYear">
                                                    <option value="">-- Pilih Tahun --</option>
                                                    <c:forEach items="${listYears}" var="data">
                                                        <option value="${data.year}"
                                                                <c:if test="${budgetData.budgetYear == data.year}">selected</c:if>>
                                                                ${data.year}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <div class="group-input">
                                                    <label>Anggaran (Rp)</label>
                                                    <fmt:formatNumber var="budget" type="currency"
                                                                      pattern="#,##0;-#,##0"
                                                                      value="${budgetData.budgetValue}"/>
                                                    <input type="text" name="budgetValue" id="budgetValue"
                                                           class="form-control" value="${budget}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab2">
                                <div class="col-md-12 col-xs-12 form-content shadow">
                                    <div class="form-title form-group">Target Pencapaian
                                    </div>
                                    <div class="form-col">
                                        <div class="form-group">
                                            <div class="group-input">
                                                <%--<label class=""><b>Input Text</b></label>--%>
                                                <table width="100%" id="tableAch" border="1">
                                                    <thead style="font-weight: bold; text-align: center">
                                                    <td data-class="expand" style="text-align: center; padding:1%">TW
                                                    </td>
                                                    <td data-class="expand" style="padding:1%">Target Pencapaian (Rp)
                                                    </td>
                                                    <td data-class="expand" style="padding:1%">Persen Pencapaian (%)
                                                    </td>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td style="text-align: center">1</td>
                                                        <td style="padding:1%; text-align: right">
                                                            <fmt:formatNumber var="target" type="currency"
                                                                              pattern="#,##0;-#,##0"
                                                                              value="${budgetTarget[0].achTargetValue}"/>
                                                            <input type="text" id="achTargetValue1"
                                                                   name="achTargetValue[]" class="form-control"
                                                                   value="${target}">
                                                        </td>
                                                        <td style="padding:1%">
                                                            <input type="number" name="achTargetPercentage[]"
                                                                   class="form-control" disabled
                                                                   id="achTargetPercentage1"
                                                                   value="${budgetTarget[0].achTargetPercentage}">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="text-align: center">2</td>
                                                        <td style="padding:1%; text-align: right">
                                                            <fmt:formatNumber var="target" type="currency"
                                                                              pattern="#,##0;-#,##0"
                                                                              value="${budgetTarget[1].achTargetValue}"/>
                                                            <input type="text" name="achTargetValue[]"
                                                                   class="form-control" id="achTargetValue2"
                                                                   value="${target}">
                                                        </td>
                                                        <td style="padding:1%">
                                                            <input type="number" name="achTargetPercentage[]"
                                                                   class="form-control" disabled
                                                                   id="achTargetPercentage2"
                                                                   value="${budgetTarget[1].achTargetPercentage}">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="text-align: center">3</td>
                                                        <td style="padding:1%; text-align: right">
                                                            <fmt:formatNumber var="target" type="currency"
                                                                              pattern="#,##0;-#,##0"
                                                                              value="${budgetTarget[2].achTargetValue}"/>
                                                            <input type="text" name="achTargetValue[]"
                                                                   class="form-control" id="achTargetValue3"
                                                                   value="${target}">
                                                        </td>
                                                        <td style="padding:1%">
                                                            <input type="number" name="achTargetPercentage[]"
                                                                   class="form-control" disabled
                                                                   id="achTargetPercentage3"
                                                                   value="${budgetTarget[2].achTargetPercentage}">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="text-align: center">4</td>
                                                        <td style="padding:1%; text-align: right">
                                                            <fmt:formatNumber var="target" type="currency"
                                                                              pattern="#,##0;-#,##0"
                                                                              value="${budgetTarget[3].achTargetValue}"/>
                                                            <input type="text" name="achTargetValue[]"
                                                                   class="form-control" id="achTargetValue4"
                                                                   value="${target}">
                                                        </td>
                                                        <td style="padding:1%">
                                                            <input type="number" disabled name="achTargetPercentage[]"
                                                                   class="form-control" id="achTargetPercentage4"
                                                                   value="${budgetTarget[3].achTargetPercentage}">
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
                    budgetDivisionId: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    budgetYear: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    budgetValue: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    "achTargetValue[]": {
                        validators: {
                            notEmpty: {},
                            max: {}
                        }
                    },
                }
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
                    case 0:
                        $(".next").show();
                        $(".previous").hide();
                        $(".finish").hide();
                        break;
                    case 1:
                        $(".previous").show();
                        $(".next").hide();
                        $(".finish").show();
                        break;
                }
            }
        });

        $('#tableAch').DataTable({
            "bInfo": false,
            "bSort": false,
            "bPaginate": false,
            "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'><'col-sm-6 col-xs-12 hidden-xs'>>" +
            "t" +
            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'><'col-xs-12 col-sm-6'>>",
            "autoWidth": true,
            "oLanguage": {
                "sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>'
            }
        });

        $("#achTargetValue1").keyup(function () {
            var budgetVal = $('#budgetValue').val();
            budgetVal = budgetVal.replace(/\,/g,'');
            var bud = parseFloat(budgetVal);
            var ach = $("#achTargetValue1").val();
            ach = ach.replace(/\,/g,'');
            var ac = parseFloat(ach);
            var result = (ac / bud) * 100;
            $('#achTargetPercentage1').val(parseFloat(result).toFixed(2));

            var $this = $(this);
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt(input, 10) : 0;
            $this.val(function () {
                return ( input === 0 ) ? "" : input.toLocaleString("en-US");
            });
        });
        $("#achTargetValue2").keyup(function () {
            var budgetVal = $('#budgetValue').val();
            budgetVal = budgetVal.replace(/\,/g,'');
            var ach = $("#achTargetValue2").val();
            ach = ach.replace(/\,/g,'');
            var a = parseFloat($("#achTargetValue1").val().replace(/\,/g,''));
            var b = parseFloat($("#achTargetValue2").val().replace(/\,/g,''));
            if (b <= a) {
            } else {
                var result = (ach / budgetVal) * 100;
                $('#achTargetPercentage2').val(parseFloat(result).toFixed(2));
            }

            var $this = $(this);
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt(input, 10) : 0;
            $this.val(function () {
                return ( input === 0 ) ? "" : input.toLocaleString("en-US");
            });

        });
        $("#achTargetValue3").keyup(function () {
            var budgetVal = $('#budgetValue').val();
            budgetVal = budgetVal.replace(/\,/g,'');
            var ach = $("#achTargetValue3").val();
            ach = ach.replace(/\,/g,'');
            var a = parseFloat($("#achTargetValue2").val().replace(/\,/g,''));
            var b = parseFloat($("#achTargetValue3").val().replace(/\,/g,''));
            if (b <= a) {
            } else {
                var result = (ach / budgetVal) * 100;
                $('#achTargetPercentage3').val(parseFloat(result).toFixed(2));
            }

            var $this = $(this);
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt(input, 10) : 0;
            $this.val(function () {
                return ( input === 0 ) ? "" : input.toLocaleString("en-US");
            });
        });
        $("#achTargetValue4").keyup(function () {
            var budgetVal = $('#budgetValue').val();
            budgetVal = budgetVal.replace(/\,/g,'');
            var ach = $("#achTargetValue4").val();
            ach = ach.replace(/\,/g,'');
            var a = parseFloat($("#achTargetValue3").val().replace(/\,/g,''));
            var b = parseFloat($("#achTargetValue4").val().replace(/\,/g,''));
            if (b <= a) {
            } else {
                var result = (ach / budgetVal) * 100;
                $('#achTargetPercentage4').val(parseFloat(result).toFixed(2));
            }

            var $this = $(this);
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt(input, 10) : 0;
            $this.val(function () {
                return ( input === 0 ) ? "" : input.toLocaleString("en-US");
            });
        });

        $("#budgetValue").keyup(function () {
            var budgetVal = $('#budgetValue').val();
            budgetVal = budgetVal.replace(/\,/g,'');
            var a = parseFloat($("#achTargetValue1").val().replace(/\,/g,''));
            var b = parseFloat($("#achTargetValue2").val().replace(/\,/g,''));
            var c = parseFloat($("#achTargetValue3").val().replace(/\,/g,''));
            var d = parseFloat($("#achTargetValue4").val().replace(/\,/g,''));

            $('#achTargetPercentage1').val((a / budgetVal) * 100);
            $('#achTargetPercentage2').val((b / budgetVal) * 100);
            $('#achTargetPercentage3').val((c / budgetVal) * 100);
            $('#achTargetPercentage4').val((d / budgetVal) * 100);

            var $this = $(this);
            var input = $this.val();
            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt(input, 10) : 0;
            $this.val(function () {
                return ( input === 0 ) ? "" : input.toLocaleString("en-US");
            });
        });

    });

    function submitData() {
        var data = {
            "budgetId": $('#budgetId').val(),
//            "budgetDirectorateId": $('#budgetDirectorateId').val(),
            "budgetDivisionId": $('#budgetDivisionId').val(),
            "budgetYear": $('#budgetYear').val(),
            "budgetValue": $('#budgetValue').val(),
            "achTargetValue": [],
            "achTargetPercentage": [],
            "budTargetTw": [1, 2, 3, 4]

        };

        $('#tableAch').find('input[name="achTargetValue[]"]').each(function () {
            data.achTargetValue.push(
                $(this).val()
            );
        });

        $('#tableAch').find('input[name="achTargetPercentage[]"]').each(function () {
            data.achTargetPercentage.push(
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
            'url': APP_PATH + '/bud/budget/saveOrUpdate',
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $(".loading").hide();
                    $('#modalBudBudget').modal('toggle');
                    otable.ajax.reload(null, false);
                    number = 0;
                    success("Data Berhasil Disimpan!");
                } else {
                    danger("Data Gagal Disimpan");
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
                $('#budgetDivisionId').html(stringHtml);
            }
        });
    }

    function submit() {
        var data = $("#formRole").serializeJSON();
        var dataString = JSON.stringify(data);
        var idData = $("#budgetId").val();
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
                    $('#modalBudBudget').modal('toggle');
                    otable.ajax.reload(null, false);
                    showSmallInfo("Informasi", "Data berhasil disimpan", 5000);
                } else {
                    $(".loading").hide();
                    showSmallError("Kesalahan", "Data gagal disimpan", 5000);
                }
            }
        });
    }

    function submitDataAdd() {

        if ($('#tableMenu').find('input#checkMenu[type="checkbox"]:checked').length == 0) {
            danger("Pilih Menu Terlebih Dahulu!");
            return;
        }

        var Roledata = {
            "budgetId": $('input#budgetId').val(),
            "roleName": $('input#roleName').val(),
            "roleDescription": $('input#roleDescription').val(),
            "status": $('select#status').val()
        };

        var reqData = {
            "dataBudget": Roledata,
            "dataMenuRole": []
        };


        var idData = $("#budgetId").val();
        var is = null;
        if (idData === "") {
            is = 1;
        } else {
            is = 2;
        }

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
            'data': JSON.stringify(reqData),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $(".loading").hide();
                    $('#modalBudBudget').modal('toggle');
                    otable.ajax.reload(null, false);
//                    showSmallInfo("Informasi", "Data berhasil disimpan", 5000);
                } else {
                    $(".loading").hide();
//                    showSmallError("Kesalahan", "Data gagal disimpan", 5000);
                }
            }
        });
    }
</script>
