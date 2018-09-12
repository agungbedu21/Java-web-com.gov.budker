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
<div data-backdrop="static" class="modal fade in" id="modalDeltaCar" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h3 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${dataList.deltaCarId == null}">Tambah </c:if>
                    <c:if test="${dataList.deltaCarId != null}">Ubah </c:if>
                    Delta CAR BUS
                    <c:if test="${dataList.deltaCarId == null}"> Baru</c:if>
                </h3>
            </div>
            <div class="modal-body">
                <input type="hidden" id="deltaCarId" name="deltaCarId" value="${dataList.deltaCarId}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label> <b>Pilih Tahun</b></label>
                            <select class="select2 form-control" name="year"
                                    id="year">
                                <option value="">-- Pilih Tahun --</option>
                                <c:forEach items="${listYear}" var="data">
                                    <option value="${data.year}"
                                            <c:if test="${dataList.year == data.year}">selected</c:if>>
                                            ${data.year}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label><b>Nilai CAR Minimal</b></label>
                            <input name="carMinValue" id="carMinValue" type="text" class="form-control"
                                   placeholder=""
                                   value="${dataList.carMinValue}">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label><b>Nilai CAR Maksimal</b></label>
                            <input name="carMaxValue" id="carMaxValue" type="text" class="form-control"
                                   placeholder=""
                                   value="${dataList.carMaxValue}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label><b>Pencapaian</b></label>
                            <input name="achievement" id="achievement" type="text" class="form-control"
                                   placeholder=""
                                   value="${dataList.achievement}">
                        </div>
                    </div>
                    <%--<div class="col-md-6">--%>
                        <%--<div class="form-group">--%>
                            <%--<label><b>Is Bprs</b></label>--%>
                            <%--<select name="isBprs" id="isBprs" class="form-control">--%>
                                <%--<option value="1"--%>
                                        <%--<c:if test="${dataList.isBprs == 1}">selected</c:if>>Yes--%>
                                <%--</option>--%>
                                <%--<option value="0"--%>
                                        <%--<c:if test="${dataList.isBprs == 0}">selected</c:if>>No--%>
                                <%--</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="status"><b>Status</b></label>
                            <select name="status" id="status" class="form-control">
                                <option value="1"
                                        <c:if test="${dataList.status == 1}">selected</c:if>>Aktif
                                </option>
                                <option value="0"
                                        <c:if test="${dataList.status == 0}">selected</c:if>>Tidak Aktif
                                </option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-border-blue draft" data-dismiss="modal">
                    Batal
                </button>
                <button onclick="validate()" type="button" class="btn btn-primary">
                    Submit
                </button>
            </div>
        </div> <!--/.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- END MODAL ADD -->
<script type="text/javascript">
    $(document).ready(function () {

        $('#modalDeltaCar')
            .bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    carMaxValue: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    year: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    carMinValue: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    achievement: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    isBprs: {
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
    });


    function validate() {
        $('#modalDeltaCar').bootstrapValidator('validate');
    }

    function submit() {
        var data = {
            "deltaCarId": $('#deltaCarId').val(),
            "year": $('#year').val(),
            "carMaxValue": $('#carMaxValue').val(),
            "carMinValue": $('#carMinValue').val(),
            "status": $('#status').val(),
            "achievement": $('#achievement').val()
//            "isBprs": $('#isBprs').val()
        };
        var idData = $("#deltaCarId").val();
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
            'url': APP_PATH + '/cal/deltacar/saveOrUpdate/' + is,
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalDeltaCar').modal('toggle');
                    otable.ajax.reload(null, false);
                    number = 0;
                    success("Data Berhasil Disimpan!");
                } else {
                    danger("Data Gagal Disimpan!");
                }
            }
        });
    }
</script>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>