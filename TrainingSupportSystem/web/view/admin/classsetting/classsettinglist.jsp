<%-- 
    Document   : settinglist
    Created on : Sep 25, 2022, 2:43:47 PM
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
        <!-- Select2 -->
        <link href="assets/css/select2.min.css" rel="stylesheet" />
        <!-- Icons -->
        <link href="assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- SLIDER -->
        <link href="assets/css/tiny-slider.css" rel="stylesheet" />
        <!-- Css -->
        <link href="assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <link href="assets/css/modal.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/custom_attend.css" rel="stylesheet" type="text/css"/>
        <!-- Css table sort -->
        <link href="assets/css/sortcolumn.css" rel="stylesheet" type="text/css"/>

        <link href="assets/css/toggle_switch_v2.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/pagger_classsetting.js" type="text/javascript"></script>
        <script src="assets/js/confirm_classsetting.js" type="text/javascript"></script>


    </head>

    <body>

        <div id="demo-modal" class="modal">
            <div class="modal__content" id="model_content">
                <h2>Confirm</h2>

                <p>
                    Do you want to change class setting status ? 
                </p>

                <div class="modal__footer">
                    <button type="button" onclick="modal_close()" class="button-10">Cancel</button>
                    <button type="button" onclick="modal_change()" class="button-10">Confirm</button>
                </div>

                <a href="#" class="modal__close">&times;</a>
            </div>
        </div>
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
                <div class="container-fluid">
                    <div class="layout-specing">
                        <h5 class="mb-0">Class Setting List</h5>        
                        <div id="search">
                            <!-- FILTER BAR -->
                            <form action="classsetting" method="get" >
                                <input type="hidden" name="action" value="view"/>
                                <!-- FILTER BAR --> 
                                <div class="row">  
                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            <br/>
                                            <select name="class_get" class="select-1">

                                                <c:forEach items="${requestScope.classlist}" var="s">
                                                    <option <c:if test="${s.class_id == cl_in}">selected=""</c:if>
                                                                                                value="${s.class_id}">${s.class_name}</option>
                                                </c:forEach>  
                                            </select>
                                        </div>
                                    </div><!--end col-->
                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            <br/>
                                            <select name="type" class="select-1">
                                                <option value="-1">All Type</option>
                                                <c:forEach items="${requestScope.typelist}" var="s">
                                                    <option <c:if test="${s.type.setting_id == settingtype}">selected=""</c:if>
                                                                                                             value="${s.type.setting_id}">${s.type.setting_tiltle}</option>
                                                </c:forEach>  
                                            </select>
                                        </div>
                                    </div><!--end col-->
                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            <br/>
                                            <select name="status" class="select-1">
                                                <option value="-1">All Status</option>
                                                <option <c:if test="${status eq 1}">selected=""</c:if>  
                                                                                    value="1">Active</option>
                                                    <option <c:if test="${status eq 0}">selected=""</c:if>  
                                                                                        value="0">Inactive</option> 
                                                </select>                  
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-md-2">
                                            <div class="mb-3">
                                                <br/>                                
                                                <input style="width: 100%"  type="text" class="form-control" name="title" value="${settingtitle}" placeholder="Enter Title">
                                        </div>
                                    </div><!--end col-->                             
                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            </br>
                                            <button  type="submit" class="button-10">Search</button>
                                            <button class="button-10" onclick="location.href = 'classsetting?action=add'" type="button">Add</button>    
                                        </div>
                                    </div><!--end col-->
                                </div>
                            </form>

                        </div>

                        <!--------------------------------end search---------------------------------------------------->

                        <div class="row">
                            <div class="col-xl-12 col-lg-8 mt-4">
                                <div class="card shadow border-0 p-4">
                                    <div class="row">
                                        <div class="col-12 mt-4">
                                            <div class="table-responsive bg-white shadow rounded">
                                                <table class="table table-center mb-0 table-sortable">
                                                    <thead>
                                                        <tr>
                                                            <th class="border-bottom p-3" style="min-width: 50px;">Id</th>
                                                            <th class="border-bottom p-3">Class</th>
                                                            <th class="border-bottom p-3">Setting Type</th>
                                                            <th class="border-bottom p-3">Title</th>
                                                            <th class="border-bottom p-3" >Order</th>
                                                            <th class="border-bottom p-3">Value</th>
                                                            <td class="border-bottom p-3"><b>Status</b></td>                                 
                                                            <td class="border-bottom p-3"><b>Action</b></td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.setting_fil}" var="w">
                                                            <tr>
                                                                <td class="p-3">${w.setting_id}</td>
                                                                <td class="p-3">${w.class_set.class_name}</td>
                                                                <td class="p-3">${w.type.setting_tiltle}</td>
                                                                <td class="p-3">${w.setting_title}</td>
                                                                <td class="p-3">${w.display_order}</td>
                                                                <td class="p-3">${w.setting_value}</td>
                                                                <td class="p-3">
                                                                    <c:if test="${w.status eq 1}">
                                                                        <span  class="badge bg-soft-success">Active</span>
                                                                    </c:if>
                                                                    <c:if test="${w.status eq 0}">
                                                                        <span class="badge bg-soft-danger">Inactive</span>
                                                                    </c:if>

                                                                </td>
                                                                <td class="p-3">
                                                                    <form id="status_form_${w.setting_id}" action="classsetting">
                                                                        <input type="hidden" name="action" value="view">
                                                                        <input name="page" type="hidden" value="${page_index}">
                                                                        <input type="hidden" value="${settingtitle}">
                                                                        <input type="hidden" value="${settingvalue}">                                                    
                                                                        <input type="hidden" value="${status}">
                                                                        <input type="hidden" value="${type}">
                                                                        <input type="hidden" name="change_status" value="${w.setting_id}_${w.status}"/>
                                                                        <c:if test="${w.status eq 1}">

                                                                            <label  class="switch-wrap"  style="float: left">
                                                                                <input type="checkbox" id="status_check_${w.setting_id}" checked onChange="modal_open(${w.setting_id})"/>
                                                                                <div class="switch"></div>
                                                                            </label>    

                                                                        </c:if>

                                                                        <c:if test="${w.status eq 0}">

                                                                            <label  class="switch-wrap"  style="float: left">
                                                                                <input type="checkbox" id="status_check_${w.setting_id}" onChange="modal_open(${w.setting_id})"/>
                                                                                <div class="switch"></div>
                                                                            </label>

                                                                        </c:if>
                                                                    </form>
                                                                    <label style="float: right;">
                                                                        <a href="classsetting?action=edit&setting_id=${w.setting_id}" class="btn btn-icon btn-pills btn-soft-primary" ><i class="uil uil-pen"></i></a>
                                                                    </label>


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
                                                        render("only-pagger",${page_index},${total_page}, 2,${requestScope.settingtype},${requestScope.status}, "${requestScope.settingtitle}",${requestScope.cl_in},${sessionScope.suOrTra});
                                                    </script>
                                                </ul>
                                            </div>
                                        </div><!--end col-->
                                        <!-- PAGINATION END -->
                                    </div><!--end row-->



                                </div>
                            </div><!--end col-->
                        </div><!--end row-->


                    </div>
                </div><!--end container-->

                <!-- Footer Start -->
                <footer class="bg-white shadow py-3">
                    <div class="container-fluid">
                        <div class="row align-items-center">
                            <div class="col">
                                <div class="text-sm-start text-center">
                                    <p class="mb-0 text-muted"><script>document.write(new Date().getFullYear())</script> © Doctris. Design with <i class="mdi mdi-heart text-danger"></i> by <a href="index.html" target="_blank" class="text-reset">Shreethemes</a>.</p>
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




    </div>
    <!-- Offcanvas End -->

    <!-- javascript -->
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <!-- simplebar -->
    <script src="assets/js/simplebar.min.js"></script>
    <!-- Chart -->
    <script src="assets/js/apexcharts.min.js"></script>
    <script src="assets/js/columnchart.init.js"></script>
    <!-- Icons -->
    <script src="assets/js/feather.min.js"></script>
    <!-- Main Js -->
    <script src="assets/js/app.js"></script>
    <!-- Tablesort Js -->
    <script src="assets/js/sortcolumn.js" type="text/javascript"></script>
</body>

</html>
