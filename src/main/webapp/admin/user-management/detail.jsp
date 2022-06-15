<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>User Detail</title>
    <%@ include file="/layout/header-p1.jsp" %>
    <%@ include file="/layout/header-p2.jsp" %>
    <link href="/assets/libs/toastr/css/iziToast.min.css" rel="stylesheet" type="text/css">
    <script src="/assets/libs/toastr/js/iziToast.min.js"></script>
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

    <div class="content-page">
        <div class="content">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-md-9">
                        <div class="page-title-box">
                            <h2 class="text-secondary">USER DETAIL</h2>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="page-title-box" style="text-align: right">
                            <a title="Back to list" href="/users" class="btn btn-outline-info">
                                <i class="fas fa-arrow-left"></i>
                                <span> Back</span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table mb-0">
                                        <thead class="table-active">
                                            <tr>
                                                <th style="width: 5%">#</th>
                                                <th style="width: 20%">Title</th>
                                                <th>Detail</th>
                                            </tr>
                                        </thead>
                                        <tbody class="bg-dark text-white">
                                            <tr>
                                                <th scope="row">1</th>
                                                <td class="font-weight-bold">Full Name</td>
                                                <td>${user.getFullName()}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">2</th>
                                                <td class="font-weight-bold">Gender</td>
                                                <td>${user.getGender()}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">3</th>
                                                <td class="font-weight-bold">Date of Birth</td>
                                                <td>
                                                    <fmt:formatDate pattern = "dd/MM/yyyy" value = "${user.getDateOfBirth()}" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <th scope="row">4</th>
                                                <td class="font-weight-bold">Email Address</td>
                                                <td>${user.getEmail()}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">5</th>
                                                <td class="font-weight-bold">Phone Number</td>
                                                <td>${user.getPhoneNumber()}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">6</th>
                                                <td class="font-weight-bold">Address</td>
                                                <td>${user.getAddress()}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">7</th>
                                                <td class="font-weight-bold">Username</td>
                                                <td>${user.getUsername()}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">8</th>
                                                <td class="font-weight-bold">Role</td>
                                                <td>${user.getRole()}</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">9</th>
                                                <td class="font-weight-bold">Creation Date</td>
                                                <td>${user.getCreationDate()}</td>
                                            </tr>
                                            <c:choose>
                                                <c:when test = "${user.isBlocked() == false}">
                                                    <tr class="bg-success text-white">
                                                        <th scope="row">10</th>
                                                        <td class="font-weight-bold ">Status</td>
                                                        <td>Active</td>
                                                    </tr>
                                                </c:when>
                                                <c:when test = "${user.isBlocked() == true}">
                                                    <tr class="bg-danger text-white">
                                                        <th scope="row">10</th>
                                                        <td class="font-weight-bold ">Status</td>
                                                        <td>Blocked</td>
                                                    </tr>
                                                </c:when>
                                            </c:choose>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="mt-3" >
                                    <a id="btn-block"  title="Block this user" href="/users?action=block&id=${user.getId()}" class="btn btn-outline-danger">
                                            <i class="fas fa-user-lock"></i>
                                             Block
                                    </a>
                                    <a id="btn-unblock" style="visibility: hidden" title="Unblock this user" href="/users?action=unblock&id=${user.getId()}" class="btn btn-outline-purple">
                                            <i class=" fas fa-user-check"></i>
                                             Unblock
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="/layout/footer.jsp" %>

    </div>

</div>

<c:if test="${user.isBlocked()}">
    <script>
        document.querySelector("#btn-unblock").style.visibility="visible";
        document.querySelector("#btn-block").style.display="none";
    </script>
</c:if>

<c:if test="${error != null}">
    <script>
        iziToast.error({
            title: 'Error',
            message: "${error}"
        });
    </script>
</c:if>
<c:if test="${failed != null}">
    <script>
        iziToast.error({
            title: 'Error',
            message: "${failed}"
        });
    </script>
</c:if>
<c:if test="${successfully != null}">
    <script>
        iziToast.success({
            title: 'Done',
            message: "${successfully}"
        });
    </script>
</c:if>

<script src="assets\js\vendor.min.js"></script>

<!-- App js -->
<script src="assets\js\app.min.js"></script>

</body>

</html>