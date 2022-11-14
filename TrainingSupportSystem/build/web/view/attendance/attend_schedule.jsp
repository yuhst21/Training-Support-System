<%-- 
    Document   : subject_list
    Created on : Sep 21, 2022, 10:53:52 PM
    Author     : HAICAO
--%>
<a href="nbproject/project.xml"></a>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>TSS - Trainng Support System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="index.html" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="assets/images/logo.png">
        <!-- Bootstrap -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Select2 -->
        <link href="assets/css/select2.min.css" rel="stylesheet" />
        <!-- Date picker -->
        <link rel="stylesheet" href="assets/css/flatpickr.min.css">
        <!-- Icons -->
        <link href="assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <link href="assets/css/newcss.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/custom_attend.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <!-- Loader -->
        <div id="preloader">
            <div id="status">
                <div class="spinner">
                    <div class="double-bounce1"></div>
                    <div class="double-bounce2"></div>
                </div>
            </div>
        </div>
        <!-- Loader -->

        <div class="page-wrapper doctris-theme toggled">
            <!-- sidebar-wrapper  -->
            <jsp:include page="../navbar.jsp"/>
            <!-- sidebar-wrapper  -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <div class="top-header">
                    <div class="header-bar d-flex justify-content-between border-bottom">
                        <div class="d-flex align-items-center">
                            <a href="#" class="logo-icon">
                                <img src="assets/images/logo-icon.png" height="30" class="small" alt="">
                                <span class="big">
                                    <img src="assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
                                    <img src="assets/images/logo-light.png" height="24" class="logo-dark-mode" alt="">
                                </span>
                            </a>
                            <a id="close-sidebar" class="btn btn-icon btn-pills btn-soft-primary ms-2" href="#">
                                <i class="uil uil-bars"></i>
                            </a>
                        </div>

                        <ul class="list-unstyled mb-0">
                            <jsp:include page="../profilelogo.jsp"/>
                        </ul>
                    </div>
                </div>

                <div class="container-fluid">
                    <div class="layout-specing">


                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Schedule Attendance</h5>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="dashboard">Dashboard</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Attendance Tracking</li>
                                </ul>
                            </nav>
                        </div><br/>
                        <form action="attend" method="get">
                            <input type="hidden" name="action" value="schedule"/>
                            <select class="select-1" name="class">
                                <c:forEach items="${cs}" var="c">
                                    <option <c:if test="${c.class_id eq class_id}">selected=""</c:if>
                                                                                   value="${c.class_id}">${c.class_name}</option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="button-10">View</button>   
                        </form>
                        <div class="row">
                            <div class="p-4">
                                <div class="table-responsive shadow rounded">
                                    <table class="table table-center bg-white mb-0 table-sortable">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3 " style="min-width: 50px;">No</th>
                                                <th class="border-bottom p-3">Topic</th>
                                                <th class="border-bottom p-3 " style="min-width: 50px;">Slot</th>
                                                <th class="border-bottom p-3">Date</th>
                                                <th class="border-bottom p-3">From Time</th>
                                                <th class="border-bottom p-3">To Time</th>
                                                <th class="border-bottom p-3">Room</th>
                                                <th class="border-bottom p-3">Take Attendance</th>
                                                <th class="border-bottom p-3">Attendance Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${schedules}" var="s">
                                                <tr>
                                                    <td class="p-3 form-label" >${schedules.indexOf(s)+1}</td>
                                                    <td class="p-3 form-label" >${s.classs.subject.subject_name}</td>
                                                    <td class="p-3 form-label">${s.slot.slotname}</td>
                                                    <td class="p-3 form-label">${s.getDateString()}</td>
                                                    <td class="p-3 form-label">${s.from_time}</td>
                                                    <td class="p-3 form-label">${s.to_time}</td>
                                                    <td class="p-3 form-label">${s.room.room_name}</td>
                                                    <td class="p-3""> 
                                                        <c:if test="${s.is_attend eq true}">
                                                            <span class="badge bg-soft-success">Attended</span>
                                                        </c:if>
                                                        <c:if test="${s.is_attend eq false}">
                                                            <span class="badge bg-soft-warning">Future</span>
                                                        </c:if>   
                                                    </td>
                                                    <td class="p-3">

                                                        <c:if test="${s.is_attend eq false}">
                                                            <span class="badge bg-soft-warning">Not yet</span>
                                                        </c:if> 
                                                        <c:if test="${s.is_attend eq true}">
                                                            <c:if test="${stu.isAttend(s) eq true}">
                                                                <span class="badge bg-soft-success">Present</span>
                                                            </c:if>
                                                            <c:if test="${stu.isAttend(s) eq false}">
                                                                <span class="badge bg-soft-success">Absent</span>
                                                            </c:if>
                                                        </c:if> 
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div><br/>
                            </div>
                        </div><!--end row-->
                    </div>
                </div><!--end container-->

                <!-- Footer Start -->
                <footer class="bg-white shadow py-3">
                    <div class="container-fluid">
                        <div class="row align-items-center">
                            <div class="col">
                                <div class="text-sm-start text-center">
                                    <p class="mb-0 text-muted"><script>document.write(new Date().getFullYear())</script> © TSS. Design with <i class="mdi mdi-heart text-danger"></i> by <a href="index.html" target="_blank" class="text-reset">Shreethemes</a>.</p>
                                </div>
                            </div><!--end col-->
                        </div><!--end row-->
                    </div><!--end container-->
                </footer><!--end footer-->
                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->


        <!-- javascript -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="assets/js/simplebar.min.js"></script>
        <!-- Select2 -->
        <script src="assets/js/select2.min.js"></script>
        <script src="assets/js/select2.init.js"></script>
        <!-- Datepicker -->
        <script src="assets/js/flatpickr.min.js"></script>
        <script src="assets/js/flatpickr.init.js"></script>
        <!-- Icons -->
        <script src="assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="assets/js/app.js"></script>

    </body>

</html>