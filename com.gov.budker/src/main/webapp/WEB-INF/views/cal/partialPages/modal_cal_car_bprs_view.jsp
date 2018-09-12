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
<div data-backdrop="static" class="modal fade in" id="modalCalBprsView" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h4 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    CAR BPRS ${dataCalTrxCarBprs.divName}
                    Triwulan <c:forEach items="${listTriwulan}" var="data">
                    <c:if test="${dataCalTrxCarBprs.triwulan == data.twId}"> ${data.twName} </c:if>
                </c:forEach> Tahun ${dataCalTrxCarBprs.year}
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
                                <td>${dataCalTrxCarBprs.divName}</td>
                            </tr>
                            <tr>
                                <td>Tahun</td>
                                <td>:</td>
                                <td>${dataCalTrxCarBprs.year}</td>
                            </tr>
                            <tr>
                                <td>Triwulan</td>
                                <td>:</td>
                                <td>
                                    <c:forEach items="${listTriwulan}" var="data">
                                        <c:if test="${dataCalTrxCarBprs.triwulan == data.twId}"> ${data.twName} </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td>Target IKU (%)</td>
                                <td>:</td>
                                <td>${dataCalTrxCarBprs.ikuTarget}</td>
                            </tr>
                            <tr>
                                <td>Rata-Rata Capaian (%)</td>
                                <td>:</td>
                                <td>
                                    <c:set var="mynum"><%=Math.ceil(1.2d)%>
                                    </c:set>
                                    <fmt:formatNumber var="formaNu" type="number" minFractionDigits="2"
                                                      maxFractionDigits="2"
                                                      value="${dataCalTrxCarBprs.achAvg}"/>

                                    <c:set var="billableTime" value="${formaNu}"/>
                                    <c:if test="${billableTime == null}">0</c:if>
                                    ${billableTime}
                                </td>
                            </tr>
                            <%--<tr>--%>
                            <%--<td>Capaian IKU</td>--%>
                            <%--<td>:</td>--%>
                            <%--<td>--%>
                            <%--<c:set var="achievementTotal"--%>
                            <%--value="${(dataCalTrxCarBprs.achAvg/dataCalTrxCarBprs.ikuTarget)}"/>--%>

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
                <input type="hidden" id="calCarBprsId" name="calCarBprsId" value="${dataCalTrxCarBprs.calCarBprsId}">
                <input type="hidden" id="minValue" name="minValue" value="${carMin}">
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped table-bordered table-hover"
                               width="100%" id="tableDetailBprs">
                            <tr style="font-weight: bold; text-align: center">
                                <td>Nama Bank</td>
                                <td>Status Pengawasan</td>
                                <td>CAR</td>
                                <td>Sesuai</td>
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
                                        <div class="form-group">
                                            <select onchange="changeBalance('${count+1}')"
                                                    class="select2 form-control"
                                                    name="supervisionStatus[]" id="supervisionStatus${count+1}">
                                                <option value="1"
                                                        <c:forEach items="${listDetail}" var="data1">
                                                            <c:if test="${data1.bankId == data.bankId  && data1.supervisionStatus == 1}">
                                                                selected
                                                            </c:if>
                                                        </c:forEach>
                                                >Normal
                                                </option>
                                                <option value="2"
                                                        <c:forEach items="${listDetail}" var="data1">
                                                            <c:if test="${data1.bankId == data.bankId  && data1.supervisionStatus == 2}">
                                                                selected
                                                            </c:if>
                                                        </c:forEach>
                                                >Intensif
                                                </option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <input name="carValue[]" id="carValue${count+1}" type="number"
                                                   class="form-control" <c:forEach items="${listDetail}" var="data1">
                                                <c:if test="${data1.bankId == data.bankId}"> value="${data1.carValue}" </c:if>
                                            </c:forEach>/>
                                        </div>
                                    </td>
                                    <td>
                                        <input type="hidden" id="isBalanceInput${count+1}" name="isBalance[]"
                                                <c:forEach items="${listDetail}" var="data1">
                                                    <c:if test="${data1.bankId == data.bankId}">
                                                        value="${data1.isBalance}"
                                                    </c:if>
                                                </c:forEach>
                                        />
                                        <div id="isBalance${count+1}">
                                            <c:forEach items="${listDetail}" var="data1">
                                                <c:if test="${data1.bankId == data.bankId}">
                                                    <c:if test="${data1.isBalance == 1}"> Ya</c:if>
                                                    <c:if test="${data1.isBalance == 0}"> Tidak</c:if>
                                                </c:if>
                                            </c:forEach>
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
        $('#modalCalBprsView')
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
    });

    <c:set var="cn" value="${0}"/>
    <c:forEach items="${bankList}" var="data">
    $("#carValue${cn+1}").keyup(function () {
        var min = $("#minValue").val();
        var val = $("#carValue${cn+1}").val();
        var supStatus = $("#supervisionStatus${cn+1}").val();
        if ((supStatus == 2 && val !== "") || (supStatus == 1 && val > min)) {
            $('#isBalance${cn+1}').html(
                "Ya"
            );

            $('#isBalanceInput${cn+1}').val(1);
        } else {
            $('#isBalance${cn+1}').html(
                "Tidak"
            );
            $('#isBalanceInput${cn+1}').val(0);
        }
    });
    <c:set var="cn" value="${cn=cn+1}"/>
    </c:forEach>

    function changeBalance(idNo) {
        var min = $("#minValue").val();
        var val = $("#carValue" + idNo).val();
        var supStatus = $("#supervisionStatus" + idNo).val();
        if ((supStatus == 2 && val !== "") || (supStatus == 1 && val > min)) {
            $("#isBalance" + idNo).html(
                "Ya"
            );
            $('#isBalanceInput' + idNo).val(1);
        } else {
            $("#isBalance" + idNo).html(
                "Tidak"
            );
            $('#isBalanceInput' + idNo).val(0);
        }
    }

    function validate() {
        $('#modalCalBprsView').bootstrapValidator('validate');
    }

    function submit() {
        var data = {
            "detailId": [],
            "isBalance": [],
            "carValue": [],
            "bankId": [],
            "supervisionStatus": [],
            "minValue": $('#minValue').val(),
            "calCarBprsId": $("#calCarBprsId").val()
        };

        $('#tableDetailBprs').find('input[name="detailId[]"]').each(function () {
            data.detailId.push(
                $(this).val()
            );
        });
        $('#tableDetailBprs').find('input[name="bankId[]"]').each(function () {
            data.bankId.push(
                $(this).val()
            );
        });
        $('#tableDetailBprs').find('input[name="carValue[]"]').each(function () {
            data.carValue.push(
                $(this).val()
            );
        });
        $('#tableDetailBprs').find('input[name="isBalance[]"]').each(function () {
            data.isBalance.push(
                $(this).val()
            );
        });
        $('#tableDetailBprs').find('select[name="supervisionStatus[]"]').each(function () {
            data.supervisionStatus.push(
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
            'url': APP_PATH + '/cal/bprs/detail/saveOrUpdate',
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalCalBprsView').modal('toggle');
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