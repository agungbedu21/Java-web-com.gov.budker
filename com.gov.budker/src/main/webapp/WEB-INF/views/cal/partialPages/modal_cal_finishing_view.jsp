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
<div data-backdrop="static" class="modal fade in" id="modalCalFinsihingView" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h4 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    Penyelesaian ${dataCalTrxFinishing.divName} ${dataCalTrxFinishing.finishingTypeName}
                    Bulan <c:forEach items="${listMonth}" var="data">
                    <c:if test="${dataCalTrxFinishing.month == data.monthId}"> ${data.monthName} </c:if>
                </c:forEach> Tahun ${dataCalTrxFinishing.year}
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
                                <td>${dataCalTrxFinishing.divName}</td>
                            </tr>
                            <tr>
                                <td>Jenis</td>
                                <td>:</td>
                                <td>${dataCalTrxFinishing.finishingTypeName}</td>
                            </tr>
                            <tr>
                                <td>Tahun</td>
                                <td>:</td>
                                <td>${dataCalTrxFinishing.year}</td>
                            </tr>
                            <tr>
                                <td>Bulan</td>
                                <td>:</td>
                                <td>
                                    <c:forEach items="${listMonth}" var="data">
                                        <c:if test="${dataCalTrxFinishing.month == data.monthId}"> ${data.monthName} </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td>Target IKU (%)</td>
                                <td>:</td>
                                <td>${dataCalTrxFinishing.ikuTarget}</td>
                            </tr>
                            <%--<tr>--%>
                            <%--<td>Total Capaian (%)</td>--%>
                            <%--<td>:</td>--%>
                            <%--<td>--%>

                            <%--<c:if test="${dataCalTrxFinishing.achTotal == null}">0</c:if>--%>
                            <%--<fmt:formatNumber var="formaNu" type="number" minFractionDigits="3"--%>
                            <%--maxFractionDigits="3" value="${dataCalTrxFinishing.achTotal}" />--%>

                            <%--<c:set var="billableTime" value="${formaNu}" />--%>
                            <%--<c:if test="${billableTime == null}">0</c:if>--%>
                            <%--${billableTime}--%>

                            <%--</td>--%>
                            <%--</tr>--%>
                            <tr>
                                <td>Capaian IKU</td>
                                <td>:</td>
                                <td>
                                    <c:set var="achievementTotal"
                                           value="${(dataCalTrxFinishing.achTotal/dataCalTrxFinishing.ikuTarget)*100}"/>
                                    <c:if test="${achievementTotal == null}">0</c:if>
                                    <fmt:formatNumber var="formaNu" type="number" minFractionDigits="2"
                                                      maxFractionDigits="2" value="${achievementTotal}"/>

                                    <c:set var="billableTime" value="${formaNu}"/>
                                    <c:if test="${billableTime == null}">0</c:if>
                                    ${billableTime}
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <input type="hidden" id="finishingId" name="finishingId" value="${dataCalTrxFinishing.finishingId}">
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped table-bordered table-hover"
                               width="100%" id="tableDetail">
                            <tr style="font-weight: bold; text-align: center">
                                <td>Nama Bank</td>
                                <td>Jumlah Permasalahan</td>
                                <td>Jumlah Realisasi</td>
                                <td>Capaian (%)</td>
                                <td>Keterangan</td>
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
                                            <input name="problems[]" id="problems${count+1}" type="number"
                                                   class="form-control" <c:forEach items="${listDetail}" var="data1">
                                                <c:if test="${data1.bankId == data.bankId}"> value="${data1.problems}" </c:if>
                                            </c:forEach>/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <input name="realization[]" id="realization${count+1}" type="number"
                                                   class="form-control" <c:forEach items="${listDetail}" var="data1">
                                                <c:if test="${data1.bankId == data.bankId}"> value="${data1.realization}" </c:if>
                                            </c:forEach>/>
                                        </div>
                                    </td>
                                    <td>
                                        <div id="ach${count+1}">
                                            <c:forEach items="${listDetail}" var="data1">
                                                <c:if test="${data1.bankId == data.bankId}"> ${(data1.realization/data1.problems)*100} %</c:if>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                        <textarea name="explanation[]" id="explanation" type="text"
                                                  class="form-control"><c:forEach items="${listDetail}"
                                                                                  var="data1"><c:if
                                                test="${data1.bankId == data.bankId}">${data1.explanation}</c:if></c:forEach></textarea>
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
        $('#modalCalFinsihingView')
            .bootstrapValidator({
                fields: {
//                    "problems[]": {
//                        validators: {
//                            notEmpty: {}
//                        }
//                    },
//                    "realization[]": {
//                        validators: {
//                            notEmpty: {}
//                        }
//                    },
//                    "explanation[]": {
//                        validators: {
//                            notEmpty: {}
//                        }
//                    }
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
    $("#realization${cn+1}").keyup(function () {
        var rlz = $("#realization${cn+1}").val();
        var prob = $("#problems${cn+1}").val();
        if(rlz == 0 && prob == 0){
            $('#ach${cn+1}').html(
                "100 %"
            );
        }else{
            $('#ach${cn+1}').html(
                (( rlz / prob) * 100) + " %"
            );
        }

    });
    <c:set var="cn" value="${cn=cn+1}"/>
    </c:forEach>

    function validate() {
        $('#modalCalFinsihingView').bootstrapValidator('validate');
    }

    function submit() {
        var data = {
            "detailId": [],
            "finishingId": $('#finishingId').val(),
            "bankId": [],
            "problems": [],
            "realization": [],
            "explanation": []
        };
        $('#tableDetail').find('input[name="detailId[]"]').each(function () {
            data.detailId.push(
                $(this).val()
            );
        });
        $('#tableDetail').find('input[name="bankId[]"]').each(function () {
            data.bankId.push(
                $(this).val()
            );
        });
        $('#tableDetail').find('input[name="problems[]"]').each(function () {
            data.problems.push(
                $(this).val()
            );
        });

        $('#tableDetail').find('input[name="realization[]"]').each(function () {
            data.realization.push(
                $(this).val()
            );
        });

        $('#tableDetail').find('textarea[name="explanation[]"]').each(function () {
            data.explanation.push(
                $(this).val()
            );
        });

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': APP_PATH + '/cal/finishing/detail/saveOrUpdate',
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalCalFinsihingView').modal('toggle');
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