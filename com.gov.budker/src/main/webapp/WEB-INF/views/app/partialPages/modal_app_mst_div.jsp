<%-- 
    Created on : mei 2018, 1:21:28 PM
    Author     : Agung Abdurohman
--%>

<%@page import="java.util.List" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- MODAL ADD -->
<style type="text/css">
    #checkBank {
        position: relative !important;
    }

    #checkDiv {
        position: relative !important;
    }
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<div data-backdrop="static" class="modal fade in" id="modalDiv" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h3 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${dataDiv.divisionId == null}">Tambah </c:if>
                    <c:if test="${dataDiv.divisionId != null}">Ubah </c:if>
                    Divisi
                    <c:if test="${dataDiv.divisionId == null}"> Baru</c:if>
                </h3>
            </div>
            <div class="modal-body">
                <input type="hidden" id="divisionId" name="divisionId" value="${dataDiv.divisionId}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label><b>Pilih Direktorat</b></label>
                            <select class="select2 form-control" name="directorateId"
                                    id="directorateId">
                                <option value="">-- Pilih Direktorat --</option>
                                <c:forEach items="${listDirectorate}" var="data">
                                    <option value="${data.directorateId}"
                                            <c:if test="${dataDiv.directorateId == data.directorateId}">selected</c:if>>
                                            ${data.directorateName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label><b>Nama Divisi</b></label>
                            <input name="divisionName" id="divisionName" type="text" class="form-control"
                                   placeholder=""
                                   value="${dataDiv.divisionName}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="status"><b>Status</b></label>
                            <select name="status" id="status" class="form-control">
                                <option value="1"
                                        <c:if test="${dataDiv.status == 1}">selected</c:if>>Aktif
                                </option>
                                <option value="0"
                                        <c:if test="${dataDiv.status == 0}">selected</c:if>>Tidak Aktif
                                </option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="status"><b>Pilih Tipe Bank</b></label>
                            <table id="tableBankType" class="tableRole table table-bordered table-hover dataTable"
                                   width="100%">
                                <thead>
                                <tr>
                                    <th style="text-align: center;width: 20%">
                                        <input onchange="checkAll()" id="checkAll" type="checkbox"/>
                                    </th>
                                    <th style="text-align: left;">Nama Tipe Bank</th>
                                </tr>
                                </thead>
                                <c:forEach items="${listBankType}" var="listItem">
                                    <tr>
                                        <td style="text-align: center;">
                                            <input data-bankid="${listItem.bankTypeId}" style="right : 0px !important;"
                                                   id="checkBank" type="checkbox" value="${listItem.bankTypeId}"
                                                    <c:forEach items="${listBankDiv}" var="data">
                                                        <c:if test="${listItem.bankTypeId == data.bankTypeId}">checked</c:if>
                                                    </c:forEach>
                                            />
                                        </td>
                                        <td>${listItem.bankTypeName}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label><b>Pilih Divisi Yang Bisa Di Akses</b></label>
                            <table id="tableDivision" class="tableRole table table-bordered table-hover dataTable"
                                   width="100%">
                                <thead>
                                <tr>
                                    <th style="text-align: center;width: 20%">
                                        <%--<input onchange="checkAll()" id="checkAll" type="checkbox"/>--%>
                                    </th>
                                    <th style="text-align: left;">Nama Divisi</th>
                                </tr>
                                </thead>
                                <c:forEach items="${listDivision}" var="listItem">
                                    <tr>
                                        <td style="text-align: center;">
                                            <input data-divid="${listItem.divisionId}" style="right : 0px !important;"
                                                   id="checkDiv" type="checkbox" value="${listItem.divisionId}"
                                                    <c:forEach items="${listRoleDiv}" var="data">
                                                        <c:if test="${listItem.divisionId == data.divisionGet}"> checked</c:if>
                                                    </c:forEach>
                                            />
                                        </td>
                                        <td>${listItem.divisionName}</td>
                                    </tr>
                                </c:forEach>
                            </table>
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

        $('#modalDiv')
            .bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    divisionName: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    directorateId: {
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
        $('#modalDiv').bootstrapValidator('validate');
    }

    function submit() {
//        if ($('#tableBankType').find('input#checkBank[type="checkbox"]:checked').length == 0) {
//            danger("Pilih Bank Terlebih Dahulu!");
//            return null;
//        }
        var data = {
            "divisionId": $('#divisionId').val(),
            "directorateId": $('#directorateId').val(),
            "divisionName": $('#divisionName').val(),
            "bankTypeId": [],
            "divisionGet": [],
            "status": $('#status').val()
        };

        $('#tableBankType').find('input#checkBank[type="checkbox"]:checked').each(function () {
            data.bankTypeId.push(
                $(this).data('bankid')
            );
        });
        $('#tableDivision').find('input#checkDiv[type="checkbox"]:checked').each(function () {
            data.divisionGet.push(
                $(this).data('divid')
            );
        });

        var idData = $("#divisionId").val();
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
            'url': APP_PATH + '/app/div/saveOrUpdate/' + is,
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalDiv').modal('toggle');
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