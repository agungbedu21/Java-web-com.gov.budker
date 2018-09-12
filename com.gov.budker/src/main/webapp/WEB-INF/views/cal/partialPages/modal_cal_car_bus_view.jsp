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
<div data-backdrop="static" class="modal fade in" id="modalCalBusView" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h4 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    CAR BUS ${dataCalTrxCarBus.divName}
                    Triwulan <c:forEach items="${listTriwulan}" var="data">
                    <c:if test="${dataCalTrxCarBus.triwulan == data.twId}"> ${data.twName} </c:if>
                </c:forEach> Tahun ${dataCalTrxCarBus.year}
                </h4>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <table id="datatable_fixed_column" class="table table-striped table-hover"
                               width="100%">
                            <tr>
                                <td>Nama Divisi</td>
                                <td>:</td>
                                <td>${dataCalTrxCarBus.divName}</td>
                            </tr>
                            <tr>
                                <td>Tahun</td>
                                <td>:</td>
                                <td>${dataCalTrxCarBus.year}</td>
                            </tr>
                            <tr>
                                <td>Triwulan</td>
                                <td>:</td>
                                <td>
                                    <c:forEach items="${listTriwulan}" var="data">
                                        <c:if test="${dataCalTrxCarBus.triwulan == data.twId}"> ${data.twName} </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td>Target IKU (%)</td>
                                <td>:</td>
                                <td>${dataCalTrxCarBus.ikuTarget}</td>
                            </tr>
                            <tr>
                                <td>Rata-Rata Capaian (%)</td>
                                <td>:</td>
                                <td>
                                    <fmt:formatNumber var="formaNum" type="number" minFractionDigits="2"
                                                      maxFractionDigits="2" value="${dataCalTrxCarBus.achAvg}"/>

                                    <c:set var="billableTime" value="${formaNum}"/>
                                    <c:if test="${billableTime == null}">0</c:if>
                                    ${billableTime}
                                </td>
                            </tr>
                            <%--<tr>--%>
                            <%--<td>Capaian IKU</td>--%>
                            <%--<td>:</td>--%>
                            <%--<td>--%>
                            <%--<fmt:parseNumber var="i" type="number" value="${dataCalTrxCarBus.achAvg}"/>--%>
                            <%--<fmt:parseNumber var="j" type="number" value="${dataCalTrxCarBus.ikuTarget}"/>--%>
                            <%--<c:set var="achievementTotal" value="${(i/j)}"/>--%>
                            <%--<fmt:formatNumber var="formaNu" type="number" minFractionDigits="3"--%>
                            <%--maxFractionDigits="3" value="${achievementTotal}" />--%>

                            <%--<c:set var="billableTime" value="${formaNu}" />--%>
                            <%--<c:if test="${billableTime == null}">0</c:if>--%>
                            <%--${billableTime}--%>

                            <%--</td>--%>
                            <%--</tr>--%>
                        </table>
                    </div>
                </div>
                <input type="hidden" id="calCarBusId" name="calCarBusId" value="${dataCalTrxCarBus.calCarBusId}">
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped table-bordered table-hover"
                               width="100%" id="tableDetailBus">
                            <tr style="font-weight: bold; text-align: center">
                                <td>Nama Bank</td>
                                <td>CAR BUS</td>
                                <td>Profil Resiko</td>
                                <td>CAR Minimum</td>
                                <td>Delta CAR</td>
                                <td>Pencapaian</td>
                            </tr>
                            <c:set var="count" value="0"/>
                            <c:forEach items="${bankList}" var="data">
                                <c:forEach items="${listDetail}" var="data1">
                                    <c:if test="${data1.bankId == data.bankId}">
                                        <input type="hidden" name="detailId[]" value="${data1.detailId}"/>
                                    </c:if>
                                </c:forEach>
                                <tr>
                                    <td>${data.bankName}
                                        <input name="bankId[]" id="bankId${count+1}" type="hidden"
                                               value="${data.bankId}"/>
                                    </td>
                                    <td>

                                        <input class="form-control" type="number" id="carBusValue${count+1}"
                                               name="carBusValue[]"
                                                <c:forEach items="${listDetail}" var="data1">
                                                    <c:if test="${data1.bankId == data.bankId}">
                                                        value="${data1.carBusValue}"
                                                    </c:if>
                                                </c:forEach>
                                        />
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <select onchange="changeCarMin('${count+1}', this)"
                                                    class="select2 form-control"
                                                    name="riskProfileId[]" id="riskProfileId${count+1}">
                                                <c:forEach items="${riskProfileList}" var="data1">
                                                    <option value="${data1.riskProfileId}" carMin="${data1.carMinValue}"
                                                            <c:forEach items="${listDetail}" var="dt">
                                                                <c:if test="${data1.riskProfileId == dt.riskProfileId
                                                                && dt.bankId == data.bankId}">
                                                                    selected
                                                                </c:if>
                                                            </c:forEach>
                                                    >
                                                            ${data1.riskProfileName}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div id="carMin${count+1}">
                                            <c:forEach items="${listDetail}" var="data1">
                                                <c:if test="${data1.bankId == data.bankId}">
                                                    <c:forEach items="${riskProfileList}" var="dt">
                                                        <c:if test="${dt.riskProfileId == data1.riskProfileId}">
                                                            ${dt.carMinValue}
                                                            <input type="hidden" id="cmin${count+1}"
                                                                   value="${dt.carMinValue}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
                                        <div id="deltaCar${count+1}">
                                            <script>
                                                var a = $('#carBusValue${count+1}').val();
                                                var b = $('#cmin${count+1}').val();
                                                var res = a - b;
                                                $('#deltaCar${count+1}').html(res);
                                                <c:forEach items="${listDeltaCar}" var="dt">
                                                if (res >= '${dt.carMinValue}' && res <= '${dt.carMaxValue}') {
                                                    $('#ach${count+1}').html("${dt.achievement}");
                                                    $('#achInput${count+1}').val("${dt.achievement}");
                                                }
                                                </c:forEach>
                                            </script>
                                        </div>
                                    </td>
                                    <td>
                                        <input type="hidden" id="achInput${count+1}" name="ach[]"
                                                <c:forEach items="${listDetail}" var="data1">
                                                    <c:if test="${data1.bankId == data.bankId}">
                                                        value="${data1.achievement}"
                                                    </c:if>
                                                </c:forEach>
                                        />
                                        <div id="ach${count+1}">
                                                <%--<c:forEach items="${listDetail}" var="data1">--%>
                                                <%--<c:if test="${data1.bankId == data.bankId}">--%>
                                                <%--${data1.achievement}--%>
                                                <%--<c:if test=" ${data1.achievement == null}">--%>
                                                <%-----%>
                                                <%--</c:if>--%>
                                                <%--</c:if>--%>
                                                <%--</c:forEach>--%>
                                        </div>
                                    </td>
                                </tr>
                                <c:set var="count" value="${count= count+1}"/>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-border-blue draft" data-dismiss="modal">
                    Batal
                </button>
                <button onclick="validate()" type="button"
                        <c:if test="${bankList == []}">disabled</c:if> class="btn btn-primary">
                    Submit
                </button>
            </div>
        </div> <!--/.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- END MODAL ADD -->
<script type="text/javascript">
    $(document).ready(function () {
        $('#modalCalBusView')
            .bootstrapValidator({
                fields: {
                    "carValue[]": {
                        validators: {
                            notEmpty: {}
                        }
                    }
                }
            })
            .off('success.form.bv')
            .on('success.form.bv', function (e) {
                e.preventDefault();
                submit();
            });

        $('.select2').select2();

        <c:set var="cn" value="${0}"/>
        <c:forEach items="${bankList}" var="data">

        $("#carBusValue${cn+1}").keyup(function () {
            var status = 0;
            var carValue = $('#riskProfileId${cn+1} option:selected').attr('carMin');
            $("#carMin${cn+1}").html(carValue);
            var deltaCar = ($("#carBusValue${cn+1}").val() - carValue);
            $("#deltaCar${cn+1}").html(deltaCar);

            <c:forEach items="${listDeltaCar}" var="dt">
            if (deltaCar >= '${dt.carMinValue}' && deltaCar <= '${dt.carMaxValue}') {
                $('#ach${cn+1}').html("${dt.achievement}");
                $('#achInput${cn+1}').val("${dt.achievement}");
                status = 1;
            }
            </c:forEach>
            if (status == 0) {
                $('#achInput${cn+1}').val("");
                $('#ach${cn+1}').html("-");
            }
        });
        <c:set var="cn" value="${cn=cn+1}"/>
        </c:forEach>

    });

    function changeCarMin(idNo, param) {
        var status = 0
        var option = $('option:selected', param).attr('carMin');
        $("#carMin" + idNo).html(option);
        var deltaCar = ($("#carBusValue" + idNo).val() - option);
        $("#deltaCar" + idNo).html(deltaCar);

        <c:forEach items="${listDeltaCar}" var="dt">
        if (deltaCar >= '${dt.carMinValue}' && deltaCar <= '${dt.carMaxValue}') {
            $('#ach' + idNo).html("${dt.achievement}");
            $('#achInput' + idNo).val("${dt.achievement}");
            status = 1;
        }
        </c:forEach>
        if (status == 0) {
            $('#achInput' + idNo).val("");
            $('#ach' + idNo).html("-");
        }
    }

    function validate() {
        $('#modalCalBusView').bootstrapValidator('validate');
    }

    function submit() {
        var data = {
            "detailId": [],
            "carValue": [],
            "bankId": [],
            "ach": [],
            "risk": [],
            "calCarBusId": $("#calCarBusId").val()
        };

        $('#tableDetailBus').find('input[name="detailId[]"]').each(function () {
            data.detailId.push(
                $(this).val()
            );
        });
        $('#tableDetailBus').find('input[name="ach[]"]').each(function () {
            data.ach.push(
                $(this).val()
            );
        });
        $('#tableDetailBus').find('input[name="bankId[]"]').each(function () {
            data.bankId.push(
                $(this).val()
            );
        });
        $('#tableDetailBus').find('input[name="carBusValue[]"]').each(function () {
            data.carValue.push(
                $(this).val()
            );
        });
        $('#tableDetailBus').find('select[name="riskProfileId[]"]').each(function () {
            data.risk.push(
                $(this).val()
            );
        });

        console.log(JSON.stringify(data));
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': APP_PATH + '/cal/bus/detail/saveOrUpdate',
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalCalBusView').modal('toggle');
                    otable.ajax.reload(null, false);
                    success("Data Berhasil Disimpan!");
                } else {
                    danger("Data Gagal Dismimpan!");
                }
            }
        });
    }
</script>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>