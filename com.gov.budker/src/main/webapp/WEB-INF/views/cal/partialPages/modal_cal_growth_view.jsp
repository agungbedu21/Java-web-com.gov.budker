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
<div data-backdrop="static" class="modal fade in" id="modalCalGrowthView" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h4 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    Pertumbuhan ${dataCalTrxGrowth.divName}
                    Triwulan <c:forEach items="${listTriwulan}" var="data">
                    <c:if test="${dataCalTrxGrowth.triwulan == data.twId}"> ${data.twName} </c:if>
                </c:forEach> Tahun ${dataCalTrxGrowth.year}
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
                                <td>${dataCalTrxGrowth.divName}</td>
                            </tr>
                            <tr>
                                <td>Tahun</td>
                                <td>:</td>
                                <td>${dataCalTrxGrowth.year}</td>
                            </tr>
                            <tr>
                                <td>Triwulan</td>
                                <td>:</td>
                                <td>
                                    <c:forEach items="${listTriwulan}" var="data">
                                        <c:if test="${dataCalTrxGrowth.triwulan == data.twId}"> ${data.twName} </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td>Total Pembiayaan Yoy</td>
                                <td>:</td>
                                <td>
                                    <fmt:formatNumber var="formaNum" type="number" minFractionDigits="2"
                                                      maxFractionDigits="3" value="${dataCalTrxGrowth.totalYoy}"/>

                                    <c:set var="billableTime" value="${formaNum}"/>
                                    <c:if test="${billableTime == null}">0</c:if>
                                    ${billableTime}
                                </td>
                            </tr>
                            <tr>
                                <td>Total Pembiayaan Bulan Berjalan</td>
                                <td>:</td>
                                <td>
                                    <fmt:formatNumber var="formaNum" type="number" minFractionDigits="2"
                                                      maxFractionDigits="2" value="${dataCalTrxGrowth.totalMonth}"/>

                                    <c:set var="billableTime" value="${formaNum}"/>
                                    <c:if test="${billableTime == null}">0</c:if>
                                    ${billableTime}
                                </td>
                            </tr>
                            <tr>
                                <td>Persentase Pertumbuhan</td>
                                <td>:</td>
                                <td>
                                    <fmt:parseNumber var="i" type="number" value="${dataCalTrxGrowth.totalYoy}"/>
                                    <fmt:parseNumber var="j" type="number" value="${dataCalTrxGrowth.totalMonth}"/>
                                    <c:set var="percen" value="${((j-i)/i)*100}"/>
                                    <fmt:formatNumber var="formaNu" type="number" minFractionDigits="2"
                                                      maxFractionDigits="2" value="${percen}"/>

                                    <c:set var="billableTime" value="${formaNu}"/>
                                    <c:if test="${billableTime == null}">0</c:if>
                                    ${billableTime} %
                                </td>
                            </tr>
                            <%--<tr>--%>
                            <%--<td>Capaian IKU</td>--%>
                            <%--<td>:</td>--%>
                            <%--<td>--%>
                            <%--<fmt:formatNumber var="formaNu" type="number" minFractionDigits="3"--%>
                            <%--maxFractionDigits="3" value="${(i/j)/dataCalTrxGrowth.ikuTarget}"/>--%>

                            <%--<c:set var="billableTime" value="${formaNu}"/>--%>
                            <%--<c:if test="${billableTime == null}">0</c:if>--%>
                            <%--${billableTime}--%>

                            <%--</td>--%>
                            <%--</tr>--%>
                        </table>
                    </div>
                </div>
                <input type="hidden" id="calGrowthId" name="calGrowthId" value="${dataCalTrxGrowth.calGrowthId}">
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped table-bordered table-hover"
                               width="100%" id="tableDetailGrowth">
                            <tr style="text-align: center; font-weight: bold">
                                <td>Nama Bank</td>
                                <td>Pertumbuhan RBB (%)</td>
                                <td>Target Pertumbuhan</td>
                                <td>Total Pembiayaan Desember Tahun Lalu</td>
                                <td>Pembiayaan (YOY)</td>
                                <td>Pembiayaan Bulan Berjalan</td>
                                <td>Pertumbuhan Bank</td>
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

                                        <input class="form-control" type="number" id="rbbGrowth${count+1}"
                                               name="rbbGrowth[]"
                                                <c:forEach items="${listDetail}" var="data1">
                                                    <c:if test="${data1.bankId == data.bankId}">
                                                        value="${data1.rbbGrowth}"
                                                    </c:if>
                                                </c:forEach>
                                        />
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <select class="select2 form-control"
                                                    name="growthTarget[]" id="growthTarget${count+1}">
                                                <option value="1"
                                                        <c:forEach items="${listDetail}" var="data1">
                                                            <c:if test="${data1.bankId == data.bankId  && data1.growthTarget == 1}">
                                                                selected
                                                            </c:if>
                                                        </c:forEach>
                                                >Ya
                                                </option>
                                                <option value="0"
                                                        <c:forEach items="${listDetail}" var="data1">
                                                            <c:if test="${data1.bankId == data.bankId && data1.growthTarget == 0}">
                                                                selected
                                                            </c:if>
                                                        </c:forEach>
                                                >Tidak
                                                </option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>

                                        <input class="form-control" type="number" id="lastYearValue${count+1}"
                                               name="lastYearValue[]"
                                                <c:forEach items="${listDetail}" var="data1">
                                                    <c:if test="${data1.bankId == data.bankId}">
                                                        value="${data1.lastYearValue}"
                                                    </c:if>
                                                </c:forEach>
                                        />
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <input class="form-control" type="number" id="yoyCost${count+1}"
                                                   name="yoyCost[]"
                                                    <c:forEach items="${listDetail}" var="data1">
                                                        <c:if test="${data1.bankId == data.bankId}">
                                                            value="${data1.yoyCost}"
                                                        </c:if>
                                                    </c:forEach>
                                            />
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <input class="form-control" type="number" id="monthCost${count+1}"
                                                   name="monthCost[]"
                                                    <c:forEach items="${listDetail}" var="data1">
                                                        <c:if test="${data1.bankId == data.bankId}">
                                                            value="${data1.monthCost}"
                                                        </c:if>
                                                    </c:forEach>
                                            />
                                        </div>
                                    </td>
                                    <td>
                                        <input type="hidden" id="growthValueInput${count+1}" name="growthValue[]"
                                                <c:forEach items="${listDetail}" var="data1">
                                                    <c:if test="${data1.bankId == data.bankId}">
                                                        value="${data1.growthValue}"
                                                    </c:if>
                                                </c:forEach>
                                        />
                                        <div id="growthValue${count+1}">
                                            <c:forEach items="${listDetail}" var="data1">
                                                <c:if test="${data1.bankId == data.bankId}">
                                                    ${data1.growthValue}
                                                    <c:if test=" ${data1.growthValue == null}">
                                                        -
                                                    </c:if>
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
                        <c:if test="${bankList == []}">disabled</c:if>
                        class="btn btn-primary">
                    Submit
                </button>
            </div>
        </div> <!--/.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- END MODAL ADD -->
<script type="text/javascript">
    $(document).ready(function () {
        $('#modalCalGrowthView')
            .bootstrapValidator({
                fields: {
                    "yoyCost[]": {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    "monthCost[]": {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    "rbbGrowth[]": {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    "growthTarget[]": {
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
        $("#yoyCost${cn+1}").keyup(function () {
            $('#monthCost${cn+1}').removeAttr("disabled");

            var yoyCost = $('#yoyCost${cn+1}').val();
            if (yoyCost == null || yoyCost === "") {
                $('#monthCost${cn+1}').attr("disabled", true);
            } else {
                var yoyCost = $('#yoyCost${cn+1}').val();
                var monthCost = $('#monthCost${cn+1}').val();
                var total = (monthCost - yoyCost) / yoyCost * 100;
                if (isFinite(total)) {

                } else {
                    total = 0;
                }
                $('#growthValue${cn+1}').html(parseFloat(total).toFixed(3));
                $('#growthValueInput${cn+1}').val(parseFloat(total).toFixed(3));
            }

        });

        <c:set var="cn" value="${cn=cn+1}"/>
        </c:forEach>

        <c:set var="cnn" value="${0}"/>
        <c:forEach items="${bankList}" var="data">
        $("#monthCost${cnn+1}").keyup(function () {

            var yoyCost = $('#yoyCost${cnn+1}').val();
            var monthCost = $('#monthCost${cnn+1}').val();
            var total = (monthCost - yoyCost) / yoyCost * 100;
            if (isFinite(total)) {

            } else {
                total = 0;
            }
            $('#growthValue${cnn+1}').html(parseFloat(total).toFixed(3));
            $('#growthValueInput${cnn+1}').val(parseFloat(total).toFixed(3));
        });
        <c:set var="cnn" value="${cnn=cnn+1}"/>
        </c:forEach>
    });

    function validate() {
        $('#modalCalGrowthView').bootstrapValidator('validate');
    }

    function submit() {
        var data = {
            "detailId": [],
            "rbbGrowth": [],
            "bankId": [],
            "growthValue": [],
            "growthTarget": [],
            "monthCost": [],
            "yoyCost": [],
            "calGrowthId": $("#calGrowthId").val()
        };

        $('#tableDetailGrowth').find('input[name="detailId[]"]').each(function () {
            data.detailId.push(
                $(this).val()
            );
        });
        $('#tableDetailGrowth').find('input[name="rbbGrowth[]"]').each(function () {
            data.rbbGrowth.push(
                $(this).val()
            );
        });
        $('#tableDetailGrowth').find('input[name="growthValue[]"]').each(function () {
            data.growthValue.push(
                $(this).val()
            );
        });
        $('#tableDetailGrowth').find('input[name="bankId[]"]').each(function () {
            data.bankId.push(
                $(this).val()
            );
        });
        $('#tableDetailGrowth').find('input[name="yoyCost[]"]').each(function () {
            data.yoyCost.push(
                $(this).val()
            );
        });
        $('#tableDetailGrowth').find('select[name="growthTarget[]"]').each(function () {
            data.growthTarget.push(
                $(this).val()
            );
        });
        $('#tableDetailGrowth').find('input[name="monthCost[]"]').each(function () {
            data.monthCost.push(
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
            'url': APP_PATH + '/cal/growth/detail/saveOrUpdate',
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalCalGrowthView').modal('toggle');
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