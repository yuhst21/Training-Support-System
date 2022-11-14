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
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
        <script>
            function ajax2(teamid) {

                $.ajax({
                    url: 'team_detail',
                    type: "Post",
                    data: {
                        team_id: teamid

                    },
                    success: function (data) {
                        if (data !== "") {
                            window.alert(data);
                        }
                    }
                });

            }
        </script>
        <script src="assets/js/confirm_subject.js" type="text/javascript"></script>
        <script src="assets/js/pagger_subject.js" type="text/javascript"></script>
        <link href="assets/css/teamlist.css" rel="stylesheet" type="text/css"/>
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
                        <!-- SEARCH BAR -->
                        <form action="" method="get">



                            <!-- SEARCH BAR -->

                            <select style="height:35px;margin-right: 20px;width: 150px;text-align: center;border-radius: 10px" name="class_id">

                                <c:forEach items="${requestScope.listClass}" var="c">

                                    <option <c:if test="${c.class_id== class_id}">selected=""</c:if> value="${c.class_id}" > ${c.class_name}</option>
                                </c:forEach>
                            </select>
                            <select style="height:35px;margin-right: 20px;width: 150px;text-align: center;border-radius: 10px" name="milestone">

                                <c:forEach items="${requestScope.listMileStone}" var="c">

                                    <option <c:if test="${c.milestone_id== milestone}">selected=""</c:if> value="${c.milestone_id}"> ${c.tittle}</option>
                                </c:forEach>
                            </select>
                            <select style="height:35px;margin-right: 20px;width: 150px;text-align: center;border-radius: 10px" name = "status" >
                                <option value="">All status</option>
                                <option  <c:if test="${status == '1'}">selected=""</c:if>   value="1" > Active</option>
                                <option   <c:if test="${status == '0'}">selected=""</c:if>  value="0" > Inactive</option>

                                </select>
                                
                            <input style="height:35px;margin-right: 2px;width: 230px;text-align: center;border-radius: 10px"   type = "text" value="" name ="team_code"/>


                            <button style="margin-left:20px;margin-right: 20px" type="submit" class="btn btn-primary">Search</button> 
                            <button class="btn btn-primary" onclick="location.href = 'add_team'" type="button">ADD</button>
                             
                            </form>

                            <div class="row">
                                <div class="col-12 mt-4">
                                    <div class="table-responsive shadow rounded">
                                        <c:if test="${requestScope.listTeam.size()==0}">
                                            <h6 style="text-align:center">Trainee have not been group  <a href="list_team?class=${requestScope.class_id}&milestone=${requestScope.milestone}">New team</a></h6>
                               
                                </c:if> 
                                    <c:if test="${requestScope.listTeam.size()>0}">
                                        <c:forEach  begin="0" end="${requestScope.listTeam.size()-1}" step="1"  var="i" >


                                            <div class="swanky_wrapper" style="width:970px">
                                                <input id="${requestScope.listTeam.get(i).getTeam_id()}"  name="radio" type="radio"></input>
                                                <label for="${requestScope.listTeam.get(i).getTeam_id()}">
                                                   
                                                    <span>${requestScope.listTeam.get(i).getTeam_code()}</span>
                                                    <a   href="team_detail?team_id=${requestScope.listTeam.get(i).getTeam_id()}" class="btn btn-icon btn-pills btn-soft-success" ><i class="uil uil-pen"></i></a>
                                                    <div class="lil_arrow"></div>
                                                    <div class="bar"></div>
                                                    <div class="swanky_wrapper__content">

                                                        <ul>
                                                          
                                                            <c:forEach items="${t.getTeamMemberByTeam(listTeam.get(i).getTeam_id())}" var ="c">
                                                                <li >${c.user.full_name}  <a 
                                                    <c:if test="${sessionScope.role == 15}">style="display: none;"</c:if>
                                                    href="edit_member?user_id=${c.user.user_id}" class="btn btn-icon btn-pills btn-soft-success" ><i class="uil uil-pen"></i></a></li>
                                                               

                                                                    
                                                                </c:forEach>








                                                        </ul>
                                                    </div>
                                                </label>

                                            </div>
                                        </c:forEach>
                                    </c:if>
























                                    <c:set var ="page" value="${requestScope.page}"/>
                                    <div class="pagination">
                                        <c:forEach begin="${1}" end ="${requestScope.num}" var = "i">

                                            <li class="page-item <c:if test="${i==page}">active</c:if>
                                                "><a class="page-link" href="list_of_team?page=${i}&class_id=${class_id}&status=${status}&team_code=${team_code}">${i}</a></li> 
                                            </c:forEach>
                                    </div>
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
