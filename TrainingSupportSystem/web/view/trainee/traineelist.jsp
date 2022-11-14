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
        <link href="assets/css/toggle_switch_v2.css" rel="stylesheet" type="text/css"/>
        <!-- Css table sort -->
        <link href="assets/css/sortcolumn.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/pagger_trainee_list.js" type="text/javascript"></script>
        <link href="assets/css/modal.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/custom_attend.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/confirm_trainee.js" type="text/javascript"></script>
        <script src="assets/js/import_submit.js" type="text/javascript"></script>
    </head>

    <script>
        function modal_close2() {

            window.location.href = "#";
        }
    </script>
    <body>
        <div id="demo-modal" class="modal">
            <div class="modal__content" id="model_content">
                <h2>Confirm</h2>
                <p>
                    Do you want to change trainee status ? 
                </p>
                <form id="test" action="traineelist">
                    <input type="hidden" name="action" value="view">
                    <div id="get_date">

                    </div> 
                </form>

                <div class="modal__footer">
                    <button type="button" onclick="modal_close()" class="button-10">Cancel</button>
                    <button type="button" onclick="modal_change()" class="button-10">Confirm</button>
                </div>


            </div>
        </div>
        <div id="demo-modal2" class="modal">
            <div class="modal__content" id="model_content">
                <h2></h2>
                <p>Import successfully!</p>
                <div class="modal__footer">
                    <button type="button" onclick="modal_close2()" class="button-10">Close</button>
                </div>
                <a href="#" class="modal__close">&times;</a>
            </div>
        </div>
        <div id="modal-import" class="modal">
            <div class="modal__content" id="model_content">
                <h2>Trainee Import</h2>
                <form method="post" action="traineelist" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="import"/>
                    <div style="margin-bottom: 5px">
                        Download Template : <a  href="view/trainee/reprort.jsp">Download</a>
                    </div>


                    <br/><!-- comment -->
                    <table>

                        <tr>
                            <td><h6>Class:</h6><br/> </td>
                            <td> 
                                <select style="margin-bottom: 40px;margin-left: 10px" class="select-1" name="class_choice" >
                                    <c:forEach items="${requestScope.list_class}" var="l">
                                        <option value="${l.class_id}">${l.class_name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td><h6>Select file:</h6></td>
                            <td>
                                <input style="margin-top:  10px;margin-left: 7px" id="file-upload" class="form-control" type="file" name="file" size="60" required=""/><br /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>                                
                            <td><button  type="submit" class="button-10">Upload</button></td>
                        </tr>
                    </table>
                </form>

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

            <!-- sidebar-wrapper  -->
            <jsp:include page="../navbar.jsp"/>
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
                            <jsp:include page="../profilelogo.jsp"/>
                        </ul>
                        <!-- USER PROFILE -->
                    </div>
                </div>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <h5 class="mb-0">Trainee List </h5>

                        <div id="search">
                            <!-- FILTER BAR -->
                            <form action="traineelist" method="get" >
                                <input type="hidden" name="action" value="view"/>
                                <!-- FILTER BAR --> 
                                <div class="row">  
                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            <br/>
                                            <select name="class_get"  class="select-1"  >
                                                <c:forEach items="${requestScope.list_class}" var="l">
                                                    <option <c:if test="${l.class_id == class_id}">selected=""</c:if>
                                                                                                      value="${l.class_id}">${l.class_name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div><!--end col-->
                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            <br/>
                                            <select class="select-1" name="status">
                                                <option value="-1">All Status</option>
                                                <option <c:if test="${status eq 1}">selected=""</c:if>  
                                                                                    value="1">Studying</option>
                                                    <option <c:if test="${status eq 0}">selected=""</c:if>                                                                                           
                                                                                        value="0">Dropped Out</option>  
                                                </select>                    
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-md-2">
                                            <div class="mb-3">
                                                <br/>
                                                <input style="width: 100%" type="text" name="trainee_name" class="form-control" value="${class_user}" placeholder="Enter Trainee Name :">
                                        </div>
                                    </div><!--end col-->
                                    <div class="col-md-2">
                                        <div class="mb-3">
                                            </br>
                                            <button  type="submit" class="button-10">Search</button>
                                        </div>
                                    </div><!--end col-->
                                </div>
                            </form>
                            <button  type="submit" class="button-10" onclick="modal_open1()">Import</button>
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
                                                            <th class="border-bottom p-3" style="min-width: 50px;">Name</th>
                                                            <th class="border-bottom p-3">Class</th>
                                                            <th class="border-bottom p-3" >Ongoing Eval</th>
                                                            <th class="border-bottom p-3">Final Eval</th>
                                                            <th class="border-bottom p-3">Topic Eval</th>
                                                            <td class="border-bottom p-3"><b>Status</b></td>                                         
                                                            <td class="border-bottom p-3"><b>Action</b></td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.class_user_list}" var="cl">
                                                            <tr>
                                                                <td class="p-3">${cl.user.user_id}</td>
                                                                <td class="p-3">${cl.user.full_name}</td>
                                                                <td class="p-3">${cl.class_id.class_name}</td>
                                                                <td class="p-3">${cl.ongoing_eval}</td>
                                                                <td class="p-3">${cl.final_eval}</td>
                                                                <td class="p-3">${cl.topic_eval}</td>                                         
                                                                <td class="p-3">
                                                                    <c:if test="${cl.status eq true}">
                                                                        <span class="badge bg-soft-success">Studying</span>
                                                                    </c:if>
                                                                    <c:if test="${cl.status eq false}">
                                                                        <span class="badge bg-soft-danger">Dropped Out</span>
                                                                    </c:if>

                                                                </td>           



                                                                <td class="p-3">
                                                                    <form id="status_form_${cl.user.user_id}_${cl.class_id.class_id}" action="traineelist">
                                                                        <input type="hidden" name="action" value="view">
                                                                        <input name="page" type="hidden" value="${page_index}">
                                                                        <input type="hidden" value="${class_id}">
                                                                        <input type="hidden" value="${class_user}">
                                                                        <input type="hidden" value="${status}">
                                                                        <input type="hidden" name="change_status" value="${cl.user.user_id}_${cl.class_id.class_id}_${cl.status}"/>
                                                                        <c:if test="${cl.status eq true}">
                                                                            <label class="switch-wrap" style="float: left">
                                                                                <input type="checkbox" id="status_check_${cl.user.user_id}" checked onChange="modal_open(${cl.user.user_id},${cl.class_id.class_id},${cl.status})"/>
                                                                                <div class="switch"></div>
                                                                            </label>  
                                                                        </c:if>
                                                                        <c:if test="${cl.status eq false}">                                                                         
                                                                            <label class="switch-wrap" style="float: left">
                                                                                <input type="checkbox" id="status_check_${cl.user.user_id}" onChange="modal_open(${cl.user.user_id},${cl.class_id.class_id},${cl.status})"/>
                                                                                <div class="switch"></div>
                                                                            </label>
                                                                        </c:if>

                                                                    </form>   
                                                                    <label style="float: right;">
                                                                        <a href="traineelist?action=edit&user_id=${cl.user.user_id}&class_id=${cl.class_id.class_id}"  class="btn btn-icon btn-pills btn-soft-primary" ><i class="uil uil-pen"></i></a>
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
                                                        render("only-pagger",${page_index},${total_page}, 2,${requestScope.class_id},${status}, "${class_user}",${sessionScope.userMan});
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
