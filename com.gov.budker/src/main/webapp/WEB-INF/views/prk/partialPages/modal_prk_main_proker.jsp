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
<div data-backdrop="static" class="modal fade in" id="modalMainProker" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h3 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${dataMainProker.mainProkerId == null}">Tambah </c:if>
                    <c:if test="${dataMainProker.mainProkerId != null}">Ubah </c:if>
                    Kategori Proker
                    <c:if test="${dataMainProker.mainProkerId == null}"> Baru</c:if>
                </h3>
            </div>
            <div class="modal-body">
                <input type="hidden" id="mainProkerId" name="mainProkerId" value="${dataMainProker.mainProkerId}">
                <%--<div class="row">--%>
                <%--<div class="col-md-12">--%>
                <%--<div class="form-group">--%>
                <%--<label>Pilih Direktorat</label>--%>
                <%--<select class="select2 form-control" name="directorateId"--%>
                <%--id="directorateId"--%>
                <%--onchange="getDivision(this)">--%>
                <%--<option value="">-- Pilih Direktorat --</option>--%>
                <%--<c:forEach items="${listDirectorate}" var="data">--%>
                <%--<option value="${data.directorateId}"--%>
                <%--<c:if test="${dataMainProker.directorateId == data.directorateId}">selected</c:if>>--%>
                <%--${data.directorateName}--%>
                <%--</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label>Pilih Divisi</label>
                            <select class="select2 form-control" name="divisionId"
                                    id="divisionId">
                                <option value="">-- Pilih Divisi --</option>
                                <c:forEach items="${listDivision}" var="data">
                                    <option value="${data.divisionId}"
                                            <c:if test="${dataMainProker.divisionId == data.divisionId}">selected</c:if>>
                                            ${data.divisionName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label>Pilih Tahun</label>
                        <select class="select2 form-control" name="mainProkerYear" id="mainProkerYear">
                            <option value="">-- Pilih Tahun --</option>
                            <c:forEach items="${listYear}" var="data">
                                <option value="${data.year}"
                                        <c:if test="${dataMainProker.mainProkerYear == data.year}">selected</c:if>>
                                        ${data.year}
                                </option>
                            </c:forEach>

                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label><b>Nama Proker Induk</b></label>
                            <input name="mainProkerName" id="mainProkerName" type="text" class="form-control"
                                   placeholder=""
                                   value="${dataMainProker.mainProkerName}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6" id="isIkuForm">
                        <label>Kode Iku</label>
                        <select class="select2 form-control" name="isIku" id="isIku"
                                onchange="enterIku(this)">
                            <option value="1"
                                    <c:if test="${dataMainProker.isIku == 1}">selected</c:if>>
                                Ada
                            </option>
                            <option value="0"
                                    <c:if test="${dataMainProker.isIku == 0}">selected</c:if>>
                                Tidak
                            </option>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <div class="group-input">
                            <div id="enterIkuId"
                                 <c:if test="${dataMainProker.isIku == 0}">hidden</c:if>
                            >
                                <label>Masukan Kode IKU</label>
                                <input type="text" name="ikuCode" id="ikuCode"
                                       class="form-control" value="${dataMainProker.ikuCode}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="status"><b>Status</b></label>
                            <select name="status" id="status" class="form-control">
                                <option value="1"
                                        <c:if test="${dataMainProker.status == 1}">selected</c:if>>Aktif
                                </option>
                                <option value="0"
                                        <c:if test="${dataMainProker.status == 0}">selected</c:if>>Tidak Aktif
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

        $('#modalMainProker')
            .bootstrapValidator({
                fields: {
//                    directorateId: {
//                        validators: {
//                            notEmpty: {}
//                        }
//                    },
                    divisionId: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    mainProkerYear: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    mainProkerName: {
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

    function checkAll() {
        var checkboxes = document.getElementsByTagName('input'), val = null;
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].type == 'checkbox') {
                if (val === null)
                    val = checkboxes[i].checked;
                checkboxes[i].checked = val;
            }
        }
    }

    function validate() {
        $('#modalMainProker').bootstrapValidator('validate');
    }

    function submit() {
        var data = {
            "mainProkerId": $('#mainProkerId').val(),
            "isIku" : $('#isIku').val(),
            "ikuCode" : $('#ikuCode').val(),
            "divisionId": $('#divisionId').val(),
            "mainProkerYear": $('#mainProkerYear').val(),
            "mainProkerName": $('#mainProkerName').val(),
            "status": $('#status').val()
        };
        var idData = $("#mainProkerId").val();
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
            'url': APP_PATH + '/prk/mainproker/saveOrUpdate/' + is,
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalMainProker').modal('toggle');
                    otable.ajax.reload(null, false);
                    number = 0;
                    success("Data Berhasil Disimpan!");
                } else {
                    danger("Data Gagal Disimpan!");
                }
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
</script>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>