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
         <link href="assets/css/modal.css" rel="stylesheet" type="text/css"/>
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
        <div id="demo-modal" class="modal">
            <div class="modal__content" id="model_content">
                <h2>Confirm</h2>

                <p>
                    Do you want to change subject status ? 
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
                            <h5 class="mb-0"><a href="list_ass">Assignment List</a></h5>


                        </div>
                        <!-- SEARCH BAR -->
                        <form action="" method="get">



                            <!-- SEARCH BAR -->

                            <select style="height:35px;margin-right: 70px;width: 150px;text-align: center;border-radius: 10px"     name = "subject" >

                                <c:forEach items="${requestScope.list2}" var="c">

                                    <option <c:if test="${c.subject_id == subject}">
                                            selected=""
                                        </c:if> value="${c.subject_id}" > ${c.subject_code}</option>
                                </c:forEach>
                            </select>

                            <select style="height:35px;margin-right: 70px;width: 150px;text-align: center;border-radius: 10px"     name = "status" >
                                <option value ="">All status</option>
                                <option   <c:if test="${status == '1'}">
                                        selected=""
                                    </c:if> value="1" > Active</option>
                                <option  <c:if test="${status == '0'}">
                                        selected=""
                                    </c:if> value="0" > Inactive</option>

                            </select>
                            <input  style="height:35px;margin-right: 10px;width: 250px;text-align: center;border-radius: 10px" type = "text" value="${key}" name ="key"/>
                            <button  type="submit" class="btn btn-primary">Search</button>   

                            <button class="btn btn-primary" onclick="location.href = 'add_ass'" type="button">ADD</button>
                        </form>







                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive shadow rounded">


                                    <table class="table table-center bg-white mb-0 table-sortable">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" style="min-width: 50px;">Id</th>
                                                <th class="border-bottom p-3" >Subject Code</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Title</th>

                                                <th class="border-bottom p-3">Eval weight</th>
                                                <th class="border-bottom p-3">Team work</th>
                                                <th class="border-bottom p-3">On going</th>
                                                <th class="border-bottom p-3">Action</th>



                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.data}" var="s" >
                                                <tr>

                                                    <td class="p-3 form-label">${s.ass_id}</td>
                                                    <td class="p-3 form-label">${s.subject_id.subject_code}</td>
                                                    <td class="p-3 form-label">${s.title}</td>

                                                    <td class="p-3 form-label">${s.eval_weight}%</td>
                                                    <td class="p-3 form-label">${s.is_team_work}</td>
                                                    <td class="p-3 form-label">${s.is_ongoing}</td>

                                            <form id="status_form_${s.ass_id}" action="">




                                                <input type="hidden" name="change_status" value="${s.ass_id}_${s.status}"/>
                                                <c:if test="${s.status eq true}">
                                                    <td class="p-3">
                                                        <span class="badge bg-soft-success">Active</span>
                                                        <label 

                                                            class="switch-wrap">
                                                            <input type="checkbox" class="open-model" id="status_check_${s.ass_id}" checked onChange="change_confirm(${s.ass_id})"/>

                                                            <div class="switch"></div>
                                                        </label>    
                                                    </td>
                                                </c:if>

                                                <c:if test="${s.status eq false}">
                                                    <td class="p-3">
                                                        <span class="badge bg-soft-success">Inactive</span>
                                                        <label 

                                                            class="switch-wrap">
                                                            <input type="checkbox" id="status_check_${s.ass_id}" onChange="change_confirm(${s.ass_id})"/>
                                                            <div class="switch"></div>
                                                        </label>
                                                    </td>
                                                </c:if>
                                            </form>


                                            <td class="text-end p-3">
                                                <a 

                                                    href="edit_ass?ass_id=${s.ass_id}" class="btn btn-icon btn-pills btn-soft-success" ><i class="uil uil-pen"></i></a>

                                            </td>
                                            </tr> 
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                    <c:set var ="k" value="${requestScope.key}"/> 
                                    <c:set var ="page" value="${requestScope.page}"/>
                                    <div class="pagination">
                                        <c:forEach begin="${1}" end ="${requestScope.num}" var = "i">

                                            <li class="page-item <c:if test="${i==page}">active</c:if>
                                                "><a class="page-link" href="list_ass?page=${i}&key=${key}&subject=${subject}&status=${status}">${i}</a></li> 
                                            </c:forEach>
                                    </div>
                                </div>
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
