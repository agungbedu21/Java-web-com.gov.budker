<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="custom_css" fragment="true" %>
<%@attribute name="custom_js" fragment="true" %>
<%@attribute name="menu" fragment="true" %>
<%@attribute name="breadcrumb" fragment="true" %>
<%@attribute name="pagetitle" fragment="true" %>
<%@attribute name="bodycontent" fragment="true" %>
<%@attribute name="outsidecontent" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en-us">
<head>

    <script>
        <jsp:useBean id="now" class="java.util.Date"/>
        window.URL = "${pageContext.request.getAttribute("javax.servlet.forward.request_uri")}";
        window.APP_PATH = "${pageContext.request.contextPath}";
        window.USR = "${pageContext.session.getAttribute("userid")}";
        window.NOW = "<fmt:setLocale value="en_US" scope="session"/><fmt:formatDate value="${now}" pattern="MMMM dd, YYYY HH:mm:ss" timeZone="Asia/Jakarta" />";
        window.TIMEZONE = "<fmt:formatDate value="${now}" pattern="z" timeZone="Asia/Jakarta" />";
    </script>
    <meta charset="utf-8">
    <meta name="_csrf" content="${_csrf.token}">
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

    <title>
        <jsp:invoke fragment="pagetitle"/>
    </title>
    <meta name="description" content="">
    <meta name="author" content="">

    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="apple-touch-icon" sizes="57x57"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/favicon-16x16.png">
    <link rel="manifest" href="${pageContext.request.contextPath}/resources/img/ojk-ico/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/component/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/component/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/component/Ionicons/css/ionicons.min.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/component/select2-4.0.6/select2.min.css">
    <!-- Datetimepicker -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/component/datetimepicker-4.17.47/bootstrap-datetimepicker.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/component/iCheck/all.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/component/AdminLTE.min.css">
    <!-- Custom style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/component/custom.css">
    <!-- Bootstrap Validator style -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/component/bootstrapvalidator/css/bootstrapValidator.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
    folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/component/skins/_all-skins.min.css">
    <!-- DataTables -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/component/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <!-- Animasi -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/component/animate.css">
</head>

<body class="hold-transition skin-red fixed sidebar-mini custom-body">
<!-- Site wrapper -->
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <%--<a href="${pageContext.request.contextPath}/home" class="logo">--%>
        <%--<!-- mini logo for sidebar mini 50x50 pixels -->--%>
        <%--<span class="logo-mini"></span>--%>
        <%--<!-- logo for regular state and mobile devices -->--%>
        <%--<span class="logo-lg"><img class="img-responsive"--%>
        <%--src="${pageContext.request.contextPath}/resources/img/logo-OJK-banner.png"--%>
        <%--style="height: 50px!Important;margin: 0 auto;padding-top: 5px;padding-bottom: 5px"></span>--%>
        <%--</a>--%>
        <a href="${pageContext.request.contextPath}/home" class="logo" style="padding: 0px">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><img class="img-responsive"
                                       src="${pageContext.request.contextPath}/resources/img/logo-OJK-banner.png"
                                       style="height: 50px!Important;"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="${pageContext.request.contextPath}/resources/img/avatar-img.png"
                                 class="user-image" alt="User Image">
                            <span class="hidden-xs">${pageContext.session.getAttribute("employeeName")}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="${pageContext.request.contextPath}/resources/img/avatar-img.png"
                                     class="img-circle" alt="User Image">

                                <p>
                                    ${pageContext.session.getAttribute("employeeName")}
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <!-- <li class="user-body">
                                <div class="row">
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Followers</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Sales</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Friends</a>
                                    </div>
                                </div>
                            </li> -->
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <button onclick="changePassword()"
                                            class="btn btn-default btn-flat">Ubah Password
                                    </button>
                                </div>
                                <div class="pull-right">
                                    <a href="${pageContext.request.contextPath}/logout"
                                       class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <!-- <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li> -->
                </ul>
            </div>
        </nav>
    </header>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <div class="box-user">
                <!-- Sidebar user panel -->
                <div class="user-panel">
                    <h3 class="margin0">${pageContext.session.getAttribute("employeeDivision")} </h3>
                </div>
                <!-- search form -->
                <!-- <form action="#" method="get" class="sidebar-form">
                    <div class="input-group">
                        <input type="text" name="q" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                </form> -->
                <!-- /.search form -->
                <!-- sidebar menu: : style can be found in sidebar.less -->
            </div>
            ${pageContext.session.getAttribute("menuListSession")}
            <%--<ul class="sidebar-menu" data-widget="tree">--%>
            <%--<li class="header">MAIN NAVIGATION</li>--%>
            <%--<li class="treeview active">--%>
            <%--<a href="#">--%>
            <%--<i class="fa fa-dashboard"></i> <span>Dashboard</span>--%>
            <%--<span class="pull-right-container">--%>
            <%--<i class="fa fa-angle-left pull-right"></i>--%>
            <%--</span>--%>
            <%--</a>--%>
            <%--<ul class="treeview-menu">--%>
            <%--<li class="active"><a href="../../index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>--%>
            <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="treeview">--%>
            <%--<a href="#">--%>
            <%--<i class="fa fa-share"></i> <span>Multilevel</span>--%>
            <%--<span class="pull-right-container">--%>
            <%--<i class="fa fa-angle-left pull-right"></i>--%>
            <%--</span>--%>
            <%--</a>--%>
            <%--<ul class="treeview-menu">--%>
            <%--<li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>--%>
            <%--<li class="treeview">--%>
            <%--<a href="#"><i class="fa fa-circle-o"></i> Level One--%>
            <%--<span class="pull-right-container">--%>
            <%--<i class="fa fa-angle-left pull-right"></i>--%>
            <%--</span>--%>
            <%--</a>--%>
            <%--<ul class="treeview-menu">--%>
            <%--<li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>--%>
            <%--<li class="treeview">--%>
            <%--<a href="#"><i class="fa fa-circle-o"></i> Level Two--%>
            <%--<span class="pull-right-container">--%>
            <%--<i class="fa fa-angle-left pull-right"></i>--%>
            <%--</span>--%>
            <%--</a>--%>
            <%--<ul class="treeview-menu">--%>
            <%--<li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>--%>
            <%--<li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>--%>
            <%--</ul>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--</li>--%>
            <%--<li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>--%>
            <%--</ul>--%>
            <%--</li>--%>
            <%--</ul>--%>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="preloader" style="display: none">
        <div class="loading">
        </div>
    </div>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1 id="menuName">
                ${pageContext.session.getAttribute("menuName")}
            </h1>
            <ol class="breadcrumb">
                <jsp:invoke fragment="breadcrumb"/>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <jsp:invoke fragment="bodycontent"/>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
        </div>
        <strong>Copyright &copy; 2018</strong>
    </footer>
</div>
<!-- ./wrapper -->

<!--================================================== -->

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/resources/component/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/resources/component/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Bootstrap Validator -->
<script src="${pageContext.request.contextPath}/resources/component/bootstrapvalidator/js/bootstrapValidator.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/resources/component/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/resources/component/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/component/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/resources/component/demo.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/resources/component/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/component/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/resources/component/select2-4.0.6/select2.min.js"></script>
<!-- SERIALIZE json-->
<script src="${pageContext.request.contextPath}/resources/component/jquery.serializejson.js.js"></script>
<!-- Datetimepicker -->
<script src="${pageContext.request.contextPath}/resources/component/datetimepicker-4.17.47/moment.js"></script>
<script src="${pageContext.request.contextPath}/resources/component/datetimepicker-4.17.47/moment-with-locales.js"></script>
<script src="${pageContext.request.contextPath}/resources/component/datetimepicker-4.17.47/bootstrap-datetimepicker.min.js"></script>
<!-- iCheck 1.0.1 -->
<%--<script src="${pageContext.request.contextPath}/resources/component/iCheck/icheck.min.js"></script>--%>

<!-- iCheck 1.0.1 -->
<!-- <script src="component/iCheck/icheck.min.js"></script> -->
<!-- Wizard -->
<script src="${pageContext.request.contextPath}/resources/component/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>

<!-- notify -->
<script src="${pageContext.request.contextPath}/resources/component/notify.js"></script>
<!-- Bootstrap Notify -->
<script src="${pageContext.request.contextPath}/resources/component/bootstrap-notify-3.1.3/bootstrap-notify.js"></script>
<!-- highchart 1.0.1 -->
<script src="${pageContext.request.contextPath}/resources/component/highchart/code/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/resources/component/highchart/code/modules/exporting.js"></script>
<script src="${pageContext.request.contextPath}/resources/component/highchart/code/modules/export-data.js"></script>
<script src="${pageContext.request.contextPath}/resources/component/highchart/code/modules/export-data.js"></script>

<!-- format rupiah -->
<script src="${pageContext.request.contextPath}/resources/component/format-rupiah.js"></script>

<jsp:invoke fragment="custom_js"/>
<script>
    //            var today = new Date();
    //            var dd = today.getDate();
    //            var mm = today.getMonth() + 1; //January is 0!
    //            var yyyy = today.getFullYear();
    //
    //            if (dd < 10) {
    //                dd = '0' + dd
    //            }
    //
    //            if (mm < 10) {
    //                mm = '0' + mm
    //            }
    //
    //            today = mm + '/' + dd + '/' + yyyy;
    //            document.getElementById("current-date").innerHTML = today;

    function view_people() {
        window.location.href = APP_PATH + "/peo/selfService/peoProfileEmployee";
    }
</script>
<script type="text/javascript">
    //            $.extend(true, $.fn.dataTable.defaults.oLanguage, {
    //                "sUrl" :  "${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/Indonesian.json"
    //            });
    $(document).ready(function () {
        var uri = "${pageContext.request.getAttribute("javax.servlet.forward.request_uri")}";
        var c = uri.replace(/\//g, "");
        $("li#" + c).addClass("active");
//                $("li#" + c).parentsUntil(".menulist").addClass("open");
        $("li#" + c).parentsUntil(".menulist").find(".fa-plus-square-o").removeClass("fa-plus-square-o").addClass("fa-minus-square-o");
        $("li#" + c).parentsUntil(".menulist").css("display", "block");
    });

    function changePassword() {
        var json;
        $.ajax({
            cache: false,
            type: "POST",
            url: APP_PATH + "/app/employee/changepassword",
            data: JSON.stringify(json),
            contentType: 'application/json',
            success: function (data) {
                $('#modal').html(data);
                $('#modalChangePassword').modal('toggle');
            }
        });
    }
</script>
<div style="display: none;" class="loading">
    <div id="loader"></div>
    <div id="modal"></div>
</div>
</body>

</html>
