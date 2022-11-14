<%-- 
    Document   : weblist
    Created on : Sep 23, 2022, 2:14:10 PM
    Author     : win
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>Doctris - Doctor Appointment Booking System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="index.html" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <!-- Css table sort -->
        <link href="assets/css/weblist.css" rel="stylesheet" type="text/css"/>
        <!-- Pagger Js -->
        <script src="assets/js/pagger_weblist.js" type="text/javascript"></script>
        <link href="assets/css/custom_attend.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>


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
            <jsp:include page="../../navbar.jsp"/>

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
                            <!-- OLD SEARCH BAR -->

                            <!-- OLD SEARCH BAR -->
                        </div>

                        <!-- USER PROFILE -->
                        <ul class="list-unstyled mb-0">
                            <jsp:include page="../../profilelogo.jsp"/>
                        </ul>
                        <!-- USER PROFILE -->
                    </div>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------  -->

                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Contact List
                            </h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="index.html">Course</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Contact List</li>
                                </ul>
                            </nav>
                        </div>



                        <div id="search">
                            <!-- FILTER BAR -->
                            <form action="weblist" method="get" >
                                <input type="hidden" name="action" value="view"/>
                                <!-- FILTER BAR --> 
                                <div class="row">  
                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            <br/>
                                            <select  class="select-1" name="category">
                                                <option value="-1">All Category</option>
                                                <c:forEach items="${cate}" var="c">
                                                    <option <c:if test="${c.category_id eq catego}">selected=""</c:if>  
                                                                                                    value="${c.category_id}">${c.category_name}</option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                    </div><!--end col-->
                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            <br/>                                       
                                            <select class="select-1" name="satus_web">
                                                <option value="-1">All Status</option>
                                                <option <c:if test="${sta eq 1}">selected=""</c:if>  
                                                                                 value="1">Active</option>
                                                    <option <c:if test="${sta eq 0}">selected=""</c:if>  
                                                                                     value="0">Inactive</option> 
                                                </select>
                                            </div>
                                        </div><!--end col-->
                                        <div class="col-md-2">
                                            <div class="mb-3">
                                                <br/>
                                                <select  class="form-select" name="supporter">
                                                    <option value="-1">All Supporter</option>
                                                <c:forEach items="${supporter}" var="m">
                                                    <option <c:if test="${m.user_id == sup}">selected=""</c:if> 
                                                                                             value="${m.user_id}">${m.full_name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div><!--end col-->
                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            <br/>
                                            <input style="width: 100%" type="text" name="cusname" value="${requestScope.cusname}" class="form-control"  placeholder="Enter Customer Name :">
                                        </div>
                                    </div><!--end col-->

                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            </br>
                                            <button style="margin-left:50px"  type="submit" class="button-10">Search</button>
                                        </div>
                                    </div><!--end col-->
                                </div>
                            </form>

                        </div>

                        <!--------------------------------end search---------------------------------------------------->


                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive shadow rounded">
                                    <table class="table table-center bg-white mb-0 table-sortable">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3">Id</th>
                                                <th class="border-bottom p-3" style="min-width: 50px;">Name</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Category </th>
                                                <th class="border-bottom p-3">Email</th>
                                                <th class="border-bottom p-3">Mobile</th>
                                                <th class="border-bottom p-3">Supporter</th>
                                                <th class="border-bottom p-3">Status</th>                                                                                                             
                                                <td class="border-bottom p-3" ><b>Action</b></td>
                                            </tr>
                                        </thead>
                                        <tbody>   
                                            <c:forEach  items="${requestScope.webs}" var="w">
                                                <tr>
                                                    <td class="p-3">${w.contact_id}</td>
                                                    <td class="py-3">
                                                        <a href="#" class="text-dark">
                                                            <div class="d-flex align-items-center">
                                                                <span class="ms-2">${w.name}</span>
                                                            </div>
                                                        </a>
                                                    </td>
                                                    <td class="p-3">${w.category.category_name}</td>
                                                    <td class="p-3">${w.email}</td>
                                                    <td class="p-3">${w.mobile}</td>                            
                                                    <td class="p-3">${w.supporter.full_name}</td>
                                                    <td class="p-3">
                                                        <c:if test="${w.status eq true}">                                                                   
                                                            <span class="badge bg-soft-success">Answered</span>
                                                        </c:if>
                                                        <c:if test="${w.status eq false}">
                                                            <span class="badge bg-soft-danger">Not Answered</span>
                                                        </c:if>
                                                    </td>                                                                     
                                                    <td class="p-3">
                                                        <a href="weblist?action=edit&contact_id=${w.contact_id}" class="btn btn-icon btn-pills btn-soft-primary" ><i class="uil uil-pen"></i></a>

                                                    </td>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div><!--end row-->

                        <div class="row text-center">
                            <!-- PAGINATION START -->
                            <div class="col-12 mt-4">
                                <div class="d-md-flex align-items-center text-center justify-content-between">
                                    <span class="text-muted me-3"></span>
                                    <ul id="only-pagger" class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                        <script>
                                            render("only-pagger",${page_index},${total_page}, 2, "${cusname}",${catego},${sup},${sta});
                                        </script>
                                    </ul>

                                </div>
                            </div><!--end col-->
                            <!-- PAGINATION END -->
                        </div><!--end row-->
                    </div>
                </div><!--end container-->

                <!-- Footer Start -->
                <footer class="bg-white shadow py-3">
                    <div class="container-fluid">
                        <div class="row align-items-center">
                            <div class="col">
                                <div class="text-sm-start text-center">
                                    <p class="mb-0 text-muted"><script>document.write(new Date().getFullYear());</script> © Doctris. Design with <i class="mdi mdi-heart text-danger"></i> by <a href="index.html" target="_blank" class="text-reset">Shreethemes</a>.</p>
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

        <!-- Offcanvas Start -->

        <!-- Offcanvas End -->

        <!-- Modal start -->

        <!-- Profile Settings Start -->

        <!-- Profile Settings End -->


        <!-- Modal end -->

        <!-- javascript -->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="assets/js/simplebar.min.js"></script>
        <!-- Icons -->
        <script src="assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="assets/js/app.js"></script>
        <!-- Tablesort Js -->
        <script src="assets/js/weblsit.js" type="text/javascript"></script>

    </body>

</html>
