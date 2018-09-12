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
<div data-backdrop="static" class="modal fade in" id="modalCalbprs" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h3 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${dataCalTrxCarBprs.calCarBprsId == null}">Tambah </c:if>
                    <c:if test="${dataCalTrxCarBprs.calCarBprsId != null}">Ubah </c:if>
                    CAR BPRS
                    <c:if test="${dataCalTrxCarBprs.calCarBprsId == null}"> Baru</c:if>
                </h3>
            </div>
            <div class="modal-body">
                <input type="hidden" id="calCarBprsId" name="calCarBprsId" value="${dataCalTrxCarBprs.calCarBprsId}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label><b>Pilih Divisi</b></label>
                            <select class="select2 form-control" name="divisionId"
                                    id="divisionId" <c:if test="${dataCalTrxCarBprs.calCarBprsId != null}">disabled </c:if>>
                                <option value="">-- Pilih Divisi --</option>
                                <c:forEach items="${listDivision}" var="data">
                                    <option value="${data.divisionId}"
                                            <c:if test="${dataCalTrxCarBprs.divisionId == data.divisionId}">selected</c:if>>
                                            ${data.divisionName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label><b>Pilih Tahun</b></label>
                        <select class="select2 form-control" name="year" id="year"
                                <c:if test="${dataCalTrxCarBprs.calCarBprsId != null}"> disabled </c:if>
                        >
                            <option value="">-- Pilih Tahun --</option>
                            <c:forEach items="${listYears}" var="data">
                                <option value="${data.year}"
                                        <c:if test="${dataCalTrxCarBprs.year == data.year}"> selected</c:if>>
                                        ${data.year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label><b>Pilih Triwulan</b></label>
                        <select class="select2 form-control" name="triwulan" id="triwulan"
                                <c:if test="${dataCalTrxCarBprs.calCarBprsId != null}"> disabled </c:if>
                        >
                            <option value="">-- Pilih Triwulan --</option>
                            <c:forEach items="${listTriwulan}" var="data">
                                <option value="${data.twId}"
                                        <c:if test="${dataCalTrxCarBprs.triwulan == data.twId}">selected</c:if>>
                                        ${data.twName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label><b>Target IKU (%)</b></label>
                            <input name="ikuTarget" id="ikuTarget" type="number" class="form-control"
                                   placeholder=""
                                   value="${dataCalTrxCarBprs.ikuTarget}">
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
        $('#modalCalbprs')
            .bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {

                    divisionId: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    year: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    triwulan: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    ikuTarget: {
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
        $('#modalCalbprs').bootstrapValidator('validate');
    }

    function submit() {
        var data = {
            "calCarBprsId": $('#calCarBprsId').val(),
            "divisionId": $('#divisionId').val(),
            "year": $('#year').val(),
            "triwulan": $('#triwulan').val(),
            "ikuTarget": $('#ikuTarget').val()
        };
        var idData = $("#calCarBprsId").val();
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
            'url': APP_PATH + '/cal/bprs/saveOrUpdate/' + is,
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $(".loading").hide();
                    $('#modalCalbprs').modal('toggle');
                    otable.ajax.reload(null, false);
                    number = 0;
                    success("Data Berhasil Disimpan!");
                } else {
                    danger("Data Gagal Dismimpan!");
                }
            }
        });
    }
</script>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>