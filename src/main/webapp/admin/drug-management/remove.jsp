<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Drug management</title>
    <%@ include file="/layout/header-p1.jsp" %>
    <!-- App favicon -->
    <link rel="shortcut icon" href="/assets/images/favicon.ico">
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

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body table-responsive">
                                <h4 class="m-t-0 header-title mb-4"><b>Default Example</b></h4>

                                <table id="datatable" class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Drug Name</th>
                                        <th>Drug content (mg)</th>
                                        <th>Dosage Form</th>
                                        <th>Quantity</th>
                                        <th>Price (VND)</th>
                                        <th>Usage</th>
                                        <th>Production Date</th>
                                        <th>Expiration Date</th>
                                        <th>Note</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                        <c:set var="i" value="0"></c:set>
                                        <c:forEach var="drug" items="${drugDTOList}">
                                            <td>${i=i+1}</td>
                                            <td>${drug.getDrugName()}</td>
                                            <td>${drug.getDrugContent()}</td>
                                            <td>${drug.getDosageForm}</td>
                                            <td>${drug.geQuantity()}</td>
                                            <td>${drug.getPricePerPill()}</td>
                                            <td>${drug.getUsage()}</td>
                                            <td>${drug.getProductionDate()}</td>
                                            <td>${drug.getExpirationDate()}</td>
                                            <td>${drug.getNote()}</td>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="header-title mb-4">Basic Form</h4>

                                <form class="parsley-examples" action="#">
                                    <div class="form-group">
                                        <label for="userName">User Name<span class="text-danger">*</span></label>
                                        <input type="text" name="nick" parsley-trigger="change" required="" placeholder="Enter user name" class="form-control" id="userName">
                                    </div>
                                    <div class="form-group">
                                        <label for="emailAddress">Email address<span class="text-danger">*</span></label>
                                        <input type="email" name="email" parsley-trigger="change" required="" placeholder="Enter email" class="form-control" id="emailAddress">
                                    </div>
                                    <div class="form-group">
                                        <label for="pass1">Password<span class="text-danger">*</span></label>
                                        <input id="pass1" type="password" placeholder="Password" required="" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="passWord2">Confirm Password <span class="text-danger">*</span></label>
                                        <input data-parsley-equalto="#pass1" type="password" required="" placeholder="Password" class="form-control" id="passWord2">
                                    </div>
                                    <div class="form-group">
                                        <div class="checkbox checkbox-purple">
                                            <input id="checkbox6a" type="checkbox">
                                            <label for="checkbox6a">
                                                Remember me
                                            </label>
                                        </div>

                                    </div>

                                    <div class="form-group text-right mb-0">
                                        <button class="btn btn-primary waves-effect waves-light mr-1" type="submit">
                                            Submit
                                        </button>
                                        <button type="reset" class="btn btn-secondary waves-effect waves-light">
                                            Cancel
                                        </button>
                                    </div>

                                </form>
                            </div>
                        </div>
                        <!-- end card -->
                    </div>
                    <!-- end col -->

                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="header-title mb-4">Horizontal Form</h4>

                                <form class="parsley-examples">
                                    <div class="form-group row">
                                        <label for="inputEmail3" class="col-md-4 col-form-label">Email<span class="text-danger">*</span></label>
                                        <div class="col-md-7">
                                            <input type="email" required="" parsley-type="email" class="form-control" id="inputEmail3" placeholder="Email">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="hori-pass1" class="col-md-4 col-form-label">Password<span class="text-danger">*</span></label>
                                        <div class="col-md-7">
                                            <input id="hori-pass1" type="password" placeholder="Password" required="" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="hori-pass2" class="col-md-4 col-form-label">Confirm Password
                                            <span class="text-danger">*</span></label>
                                        <div class="col-md-7">
                                            <input data-parsley-equalto="#hori-pass1" type="password" required="" placeholder="Password" class="form-control" id="hori-pass2">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="webSite" class="col-md-4 col-form-label">Web Site<span class="text-danger">*</span></label>
                                        <div class="col-md-7">
                                            <input type="url" required="" parsley-type="url" class="form-control" id="webSite" placeholder="URL">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-md-8 offset-md-4">
                                            <div class="checkbox checkbox-purple">
                                                <input id="checkbox6" type="checkbox">
                                                <label for="checkbox6">
                                                    Remember me
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row mb-0">
                                        <div class="col-md-8 offset-md-4">
                                            <button type="submit" class="btn btn-primary waves-effect waves-light mr-1">
                                                Register
                                            </button>
                                            <button type="reset" class="btn btn-secondary waves-effect waves-light">
                                                Cancel
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!-- end card -->
                    </div>
                    <!-- end col -->
                </div>

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


<!-- Right Sidebar -->
<%@ include file="/layout/sidebar-right.jsp" %>
<!-- /Right-bar -->

<!-- Right bar overlay-->
<div class="rightbar-overlay"></div>

<!-- Vendor js -->
<script src="/assets/js/vendor.min.js"></script>

<!-- Plugin js-->
<script src="/assets/libs/parsleyjs/parsley.min.js"></script>

<!-- Validation init js-->
<script src="/assets/js/pages/form-validation.init.js"></script>

<!-- App js -->
<script src="/assets/js/app.min.js"></script>
</body>
</html>