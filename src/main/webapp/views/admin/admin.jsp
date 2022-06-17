<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 3/17/22
  Time: 10:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ADMIN</title>

    <!-- Custom fonts for this template-->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>

<body>

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-success sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/User">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Admin</div>
        </a>

        <!-- Divider -->
        <c:if test="${sessionScope.sessionUser.isAdmin==1}">
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="/User">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Account</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Building
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link" href="/Building">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Building</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/Floor">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Floor</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/Room">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Room</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Contract
        </div>

        <!-- Nav Item - Pages Collapse Menu -->

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="/Contract">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Contract</span></a>
        </li>
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Customer
        </div>

        <!-- Nav Item - Pages Collapse Menu -->

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="/Customer">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Customer</span></a>
        </li>
        </c:if>
        <c:if test="${sessionScope.sessionUser.isAdmin==0}">
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Contract
        </div>

        <!-- Nav Item - Pages Collapse Menu -->

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="/Contract">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Contract</span></a>
        </li>
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Customer
        </div>

        <!-- Nav Item - Pages Collapse Menu -->

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="/Customer">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Customer</span></a>
        </li>
        </c:if>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>


    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->

                    <!-- Nav Item - Alerts -->

                    <!-- Nav Item - Messages -->

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.sessionUser.name}</span>
                            <img class="img-profile rounded-circle"
                                 src="../images/avatar/${sessionScope.sessionUser.avatar}">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                Settings
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Activity Log
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/signout">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <jsp:include page="${view}"/>
                <jsp:include page="${view1}"/>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2021</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="/signout">Logout</a>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="js/sb-admin-2.js"></script>
</body>

</html>