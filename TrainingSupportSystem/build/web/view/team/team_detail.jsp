<%-- 
    Document   : subject_list
    Created on : Sep 21, 2022, 10:53:52 PM
    Author     : HAICAO
--%>
<a href="nbproject/project.xml"></a>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>TSS - Training Support System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="../../index.html" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="assets/images/logo.png">
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
        <link href="assets/css/newcss.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/sortcolumn.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/toggle_switch.css" rel="stylesheet" type="text/css"/>

        <!-- JS -->

        <script>
            function change_confirm(id) {
                var status = document.getElementById("status_check_" + id);
                if (confirm("Press OK to change this subject status!") === true) {
                    document.getElementById("status_form_" + id).submit();
                } else {
                    status.checked = !status.checked;
                }
            }
        </script>

        <script src="assets/js/confirm_subject.js" type="text/javascript"></script>
        <script src="assets/js/pagger_subject.js" type="text/javascript"></script>
        <style>
            .filter123{
                margin-right: 50px
            }

            .pagination{
                color: black;
                font-size: 50px;
                float: left;
                padding: 20px 30px;
                margin: 20px;
                text-decoration: none;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }
            .pagination a:hover:not(.active){
                background-color: chocolate;
            }
            .topnav{
                overflow: hidden;
                background-color: #e9e9e9;
            }
            .topnav input[type = text]{
                margin-left: 100px;
                font-size: 20px;
            }

        </style>
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
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0"><a href="list_ass">Team List</a></h5>


                        </div>


                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive shadow rounded">


                                    <table class="table table-center bg-white mb-0 table-sortable">
                                        <thead>
                                            <tr>

                                                <th class="border-bottom p-3" >Name</th>
                                                <th class="border-bottom p-3" >Leader</th>

                                                <th class="border-bottom p-3">Active</th>

                                                <th <c:if test="${sessionScope.role == 15}">style="display: none;"</c:if> class="border-bottom p-3">Action</th>


                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.listMember}" var="s" >
                                                <tr>

                                                    <td class="p-3 form-label">${s.user.full_name}</td>

                                                    <c:if test="${s.is_leader eq true}">
                                                        <td class="p-3">
                                                            <span class="badge bg-soft-success">true</span>

                                                        </td>
                                                    </c:if>

                                                    <c:if test="${s.is_leader eq false}">
                                                        <td class="p-3">
                                                            <span class="badge bg-soft-success">false</span>

                                                        </td>
                                                    </c:if>
                                            <form id="status_form_${s.user.user_id}" action="">




                                                <input type="hidden" name="change_status" value="${s.user.user_id}_${s.is_active}"/>
                                                <c:if test="${s.is_active eq true}">
                                                    <td class="p-3">
                                                        <span class="badge bg-soft-success">Active</span>
                                                        <label 
                                                            <c:if test="${sessionScope.role == 15}">style="display: none;"</c:if>
                                                            class="switch-wrap">
                                                            <input type="checkbox" class="open-model" id="status_check_${s.user.user_id}" checked onChange="change_confirm(${s.user.user_id})"/>

                                                            <div class="switch"></div>
                                                        </label>    
                                                    </td>
                                                </c:if>

                                                <c:if test="${s.is_active eq false}">
                                                    <td class="p-3">
                                                        <span class="badge bg-soft-success">Inactive</span>
                                                        <label 
                                                            <c:if test="${sessionScope.role == 15}">style="display: none;"</c:if>
                                                            class="switch-wrap">
                                                            <input type="checkbox" id="status_check_${s.user.user_id}" onChange="change_confirm(${s.user.user_id})"/>
                                                            <div class="switch"></div>
                                                        </label>
                                                    </td>
                                                </c:if>
                                            </form>

                                            <td class="text-end p-3">
                                                <a 
                                                    <c:if test="${sessionScope.role == 15}">style="display: none;"</c:if>
                                                    href="edit_member?user_id=${s.user.user_id}" class="btn btn-icon btn-pills btn-soft-success" ><i class="uil uil-pen"></i></a>

                                            </td>



                                            </tr> 
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                        <form  action="team_detail" method="post" class="mt-4">
                                            <input type="hidden" name="id" value="${team_id}">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Class id</label>
                                                    <br>
                                                    <input
                                                        required="" readonly="" name="class_id" id="name2" type="text" class="form-control"  value="${class_id}">
                                                    
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Team Code</label>
                                                    <input
                                                        required="" maxlength="30" name="team_code" id="name2" type="text" class="form-control" placeholder="team_code :" value="${team_code}">
                                                    
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Topic name </label>
                                                    <input name="topic_name"  required="" id="name" type="text" class="form-control" value="${topic_name}" placeholder="topic name">
                                                    
                                                </div>
                                            </div><!--end col-->

                                            
                                            

                                            

                                            
                                             
                                             <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Description</label>
                                                    <input 
                                                        name="description" maxlength="30" type="text" class="form-control" placeholder="description :" value="${description}"/>
                                                </div>
                                            </div><!--end col-->
                                            
                                             
                                             <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label>
                                                    <br>
                                                        
                                                            <input type="radio" name = "status" <c:if test="${status == true}">
                                                                        checked=""
                                                                    </c:if> value="1" > Active</option>
                                                            <input type="radio" name = "status" <c:if test="${status == false}">
                                                                        checked=""
                                                                    </c:if>  value="0" > Inactive</option>
                                                        
                                                    
                                                </div>
                                            </div><!--end col-->
                                             
                                            
                                                    
                                        </div><!--end row-->

                                        <button type="submit" class="btn btn-primary">Update</button>
                                        <p style="color:#4BB543;">${requestScope.success_mess}</p>
                                    </form>        

                                </div>
                            </div>
                        </div><!--end row-->





                        <div class="row">
                            <div class="col-12 mt-4">

                            </div>
                        </div><!--end row-->



                        <div class="row text-center">
                            <!-- PAGINATION START -->
                            <div class="col-12 mt-4">
                                <div class="d-md-flex align-items-center text-center justify-content-between">
                                    <ul id="only-pagger" class="pagination justify-content-center mb-0 mt-3 mt-sm-0">

                                    </ul>
                                </div>
                            </div><!--end col-->
                            <!-- PAGINATION END -->
                        </div><!--end row-->

                    </div>
                </div><!--end container-->

                <!-- Footer Start -->

                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->


        <!-- javascript -->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="assets/js/simplebar.min.js"></script>
        <!-- Icons -->
        <script src="assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="assets/js/app.js"></script>
        <script src="assets/js/sortcolumn.js" type="text/javascript"></script>

    </body>




</html>
