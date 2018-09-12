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
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<div data-backdrop="static" class="modal fade in" id="modalBankType" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h3 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${dataList.bankTypeId == null}">Tambah </c:if>
                    <c:if test="${dataList.bankTypeId != null}">Ubah </c:if>
                    Tipe Bank
                    <c:if test="${dataList.bankTypeId == null}"> Baru</c:if>
                </h3>
            </div>
            <div class="modal-body">
                <input type="hidden" id="bankTypeId" name="bankTypeId" value="${dataList.bankTypeId}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label><b>Nama Tipe Bank</b></label>
                            <input name="bankTypeName" id="bankTypeName" type="text" class="form-control"
                                   placeholder=""
                                   value="${dataList.bankTypeName}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="status"><b>Pilih Bank</b></label>
                            <table id="tableBank" class="tableRole table table-bordered table-hover dataTable"
                                   width="100%">
                                <thead>
                                <tr>
                                    <th style="text-align: center;width: 20%">
                                        <input onchange="checkAll()" id="checkAll" type="checkbox"/>
                                    </th>
                                    <th style="text-align: left;">Nama Bank</th>
                                </tr>
                                </thead>
                                <c:forEach items="${listBank}" var="listItem">
                                    <tr>
                                        <td style="text-align: center;">
                                            <input data-bankid="${listItem.bankId}" style="right : 0px !important;"
                                                   id="checkBank" type="checkbox" value="${listItem.bankId}"
                                                    <c:forEach items="${listBankType}" var="data">
                                                        <c:if test="${listItem.bankId == data.bankId}">checked</c:if>
                                                    </c:forEach>
                                            />
                                        </td>
                                        <td>${listItem.bankName}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
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

        $('#modalBankType')
            .bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    bankTypeName: {
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


    function validate() {
        $('#modalBankType').bootstrapValidator('validate');
    }

    function submit() {
        if ($('#tableBank').find('input#checkBank[type="checkbox"]:checked').length == 0) {
            danger("Pilih Bank Terlebih Dahulu!");
            return null;
        }

        var data = {
            "bankTypeId": $('#bankTypeId').val(),
            "bankTypeName": $('#bankTypeName').val(),
            "bankList": [],
            "status": $('#status').val()
        };

        $('#tableBank').find('input#checkBank[type="checkbox"]:checked').each(function () {
            data.bankList.push(
                $(this).data('bankid')
            );
        });

        var idData = $("#bankTypeId").val();
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
            'url': APP_PATH + '/app/banktype/saveOrUpdate/' + is,
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalBankType').modal('toggle');
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