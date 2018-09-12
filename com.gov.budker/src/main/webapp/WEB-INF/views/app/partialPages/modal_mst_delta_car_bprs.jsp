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
<div data-backdrop="static" class="modal fade in" id="modalDeltaCarBprs" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h3 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${dataList.bprsId == null}">Tambah </c:if>
                    <c:if test="${dataList.bprsId != null}">Ubah </c:if>
                    Delta CAR BPRS
                    <c:if test="${dataList.bprsId == null}"> Baru</c:if>
                </h3>
            </div>
            <div class="modal-body">
                <input type="hidden" id="bprsId" name="bprsId" value="${dataList.bprsId}">
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
                            <label><b>Nilai CAR BPRS Minimal</b></label>
                            <input name="carBprsMin" id="carBprsMin" type="text" class="form-control"
                                   placeholder=""
                                   value="${dataList.carBprsMin}">
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

        $('#modalDeltaCarBprs')
            .bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    carBprsMin: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    year: {
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
        $('#modalDeltaCarBprs').bootstrapValidator('validate');
    }

    function submit() {
        var data = {
            "bprsId": $('#bprsId').val(),
            "year": $('#year').val(),
            "carBprsMin": $('#carBprsMin').val(),
            "status": $('#status').val()
        };
        var idData = $("#bprsId").val();
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
            'url': APP_PATH + '/cal/deltabprs/saveOrUpdate/' + is,
            'data': JSON.stringify(data),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalDeltaCarBprs').modal('toggle');
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