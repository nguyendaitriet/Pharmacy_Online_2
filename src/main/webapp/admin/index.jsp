<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Admin Dashboard</title>
    <%@ include file="/layout/header-p1.jsp" %>

    <link href="/assets/libs/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="/assets/libs/datatables/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="/assets/libs/datatables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="/assets/libs/datatables/select.bootstrap4.min.css" rel="stylesheet" type="text/css">

    <%@ include file="/layout/header-p2.jsp" %>
</head>

<body>

<!-- Begin page -->
<div id="wrapper">


    <!-- Topbar Start -->
    <%@ include file="/layout/topbar.jsp" %>
    <!-- end Topbar --> <!-- ========== Left Sidebar Start ========== -->

    <div class="left-side-menu">

        <div class="slimscroll-menu">

            <!--- Sidemenu -->
            <div id="sidebar-menu">
                <%@ include file="/layout/sidebar-left.jsp" %>
            </div>
            <!-- End Sidebar -->

            <div class="clearfix"></div>

        </div>
        <!-- Sidebar -left -->

    </div>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="container-fluid">

                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box">
                            <h4 class="page-title">Welcome to Administrator Page</h4>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-xl-3 col-sm-6">
                        <div class="card bg-pink">
                            <div class="card-body widget-style-2">
                                <div class="text-white media">
                                    <div class="media-body align-self-center">
                                        <p class="mb-2">Total Account</p>
                                        <h2 class="my-0 text-white"><span data-plugin="counterup">${totalAccounts}</span></h2>
                                    </div>
                                    <i class="ion ion-ios-people"></i>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-3 col-sm-6">
                        <div class="card bg-primary">
                            <div class="card-body widget-style-2">
                                <div class="text-white media">
                                    <div class="media-body align-self-center">
                                        <p class="mb-0 my-0 text-white">User</p>
                                        <p class="mb-0 ">Active
                                            <span class="my-0 text-white" data-plugin="counterup">${activeUsers}</span>
                                        </p>
                                        <p class="mb-0 ">Blocked
                                            <span class="my-0 text-white" data-plugin="counterup">${blockedUsers}</span>
                                        </p>
                                    </div>
                                    <i class="ion ion-ios-person"></i>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-3 col-sm-6">
                        <div class="card bg-info">
                            <div class="card-body widget-style-2">
                                <div class="text-white media">
                                    <div class="media-body align-self-center">
                                        <div class="media-body align-self-center">
                                            <p class="mb-0 my-0 text-white">Admin</p>
                                            <p class="mb-0 ">Active
                                                <span class="my-0 text-white" data-plugin="counterup">${activeAdmins}</span>
                                            </p>
                                            <p class="mb-0 ">Blocked
                                                <span class="my-0 text-white" data-plugin="counterup">${blockedAdmins}</span>
                                            </p>
                                        </div>
                                    </div>
                                    <i class="ion ion-ios-star"></i>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-3 col-sm-6">
                        <div class="card bg-secondary">
                            <div class="card-body widget-style-2">
                                <div class="text-white media">
                                    <div class="media-body align-self-center">
                                        <p class="mb-0">Total drug</p>
                                        <h2 class="my-0 text-white"><span data-plugin="counterup">${totalDrugs}</span></h2>
                                    </div>
                                    <i class="ion ion-md-medkit"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End row -->
            </div>
            <!-- end container-fluid -->
        </div>
        <!-- end content -->

        <!-- Footer Start -->
        <%@ include file="/layout/footer.jsp" %>
        <!-- end Footer -->

    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->


<!-- Vendor js -->
<%@ include file="/layout/script.jsp" %>
</body>
</html>