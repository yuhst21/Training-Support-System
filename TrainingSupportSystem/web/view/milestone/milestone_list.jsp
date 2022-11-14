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
        <script src="assets/js/confirm_schedule.js" type="text/javascript"></script>
        <script src="assets/js/pagger_milestone.js" type="text/javascript"></script>


        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>

        <!-- JS -->
        <script>


            function f1() {
                var e = document.getElementById('test');
                var value = e.value;
                $.ajax({
                    url: 'milestone?action=load2',
                    type: "GET",
                    data: {
                        classid: value
                    },
                    success: function (data) {
                        var content = document.getElementById('test2');
                        content.innerHTML = data;

                    }
                });

            }

            function render(id, pageindex, totalpage, gap, classid, assid, status, tittle)
            {
                var container = document.getElementById(id);
                var content = "";
                if (pageindex > gap + 1)
                    content += "<li class=\"page-item\"><a class=\"page-link\" href=\"milestone?action=list&class=" + classid + "&ass=" + assid + "&status=" + status + "&tittle=" + tittle + "&page=1\" aria-label=\"Previous\">First</a></li>";

                for (var i = pageindex - gap; i < pageindex; i++)
                    if (i > 0)
                        content += "<li class=\"page-item\"><a class=\"page-link\" href=\"milestone?action=list&class=" + classid + "&ass=" + assid + "&page=" + i + "\">" + i + "</a></li>";

                content += "<li class=\"page-item active\"><a class=\"page-link\" href=\"milestone?action=list&class=" + classid + "&ass=" + assid + "&status=" + status + "&tittle=" + tittle + "&page=" + pageindex + "\">" + pageindex + "</a></li>";

                for (var i = pageindex + 1; i <= pageindex + gap; i++)
                    if (i <= totalpage)
                        content += "<li class=\"page-item\"><a class=\"page-link\" href=\"milestone?action=list&class=" + classid + "&ass=" + assid + "&status=" + status + "&tittle=" + tittle + "&page=" + i + "\">" + i + "</a></li>";

                if (pageindex < totalpage - gap)
                    content += "<li class=\"page-item\"><a class=\"page-link\" href=\"milestone?action=list&class=" + classid + "&ass=" + assid + "&status=" + status + "&tittle=" + tittle + "&page=" + totalpage + "\" aria-label=\"Previous\">Last</a></li>";
                container.innerHTML = content;
            }



        </script>

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
        <div id="demo-modal2" class="modal">
            <div class="modal__content" id="model_content">
                <h2></h2>

                <p>
                    Add successfully!
                </p>

                <div class="modal__footer">
                    <button type="button" onclick="modal_close()" class="button-10">Close</button>
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

                        <!-- SEARCH BAR -->
                        <div class="search-bar p-0 d-none d-lg-block ms-2">

                            <!-- SEARCH BAR -->


                            <div class="d-md-flex justify-content-between">


                                <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                    <ul id="content" class="breadcrumb bg-transparent rounded mb-0 p-0">
                                        <li class="breadcrumb-item"><a href="index.html">TSS</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Milestone</li>
                                    </ul>

                                </nav>
                            </div>
                            <div id="search">
                                <!-- FILTER BAR -->
                                <form action="milestone"  method="GET"  class="searchform" >
                                    <input type="hidden" name="action" value="list">
                                    <!-- FILTER BAR --> 
                                    <div class="row">  
                                        <div class="col-md-2">
                                            <div class="mb-3">
                                                </br>
                                                <select onchange="f1()" id="test"  style="width: 120px;text-align: center" class="form-select"   style="margin-right: 80px;" class="s_filter" name="class" > 

                                                    <c:forEach items="${clist}" var="c">
                                                        <option 
                                                            <c:if test="${class_id == c.class_id}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="${c.class_id}" ">${c.class_name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div><!--end col-->
                                        <div class="col-md-2">
                                            <div class="mb-3">
                                                </br>
                                                <select  id="test2"  style="width: 120px;text-align: center" class="form-select"   style="margin-right: 80px;" class="s_filter" name="ass" > 
                                                    <option 

                                                        value="-1">All Assignment</option>
                                                    <c:forEach items="${alist}" var="a">
                                                        <option 
                                                            <c:if test="${ass_id == a.ass_id}">
                                                                selected="selected"
                                                            </c:if>

                                                            value="${a.ass_id}">${a.title}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div><!--end col-->


                                        <div class="col-md-2">
                                            <div class="mb-3">
                                                </br>
                                                <select style="width: 120px;text-align: center" class="form-select" style="margin-right: 80px;" class="s_filter" name="status">                                       
                                                    <option value="all">All status</option>
                                                    <option value="active"<c:if test="${status eq active}">
                                                            selected="selected"
                                                        </c:if>>Active</option>
                                                    <option value="inactive" <c:if test="${status eq inactive}">
                                                            selected="selected"
                                                        </c:if> >Inactive</option>
                                                </select>
                                            </div>
                                        </div><!--end col-->



                                        <div class="col-md-2">
                                            <div class="mb-3">
                                                </br>
                                                <input style="width: 120px" 

                                                       name="tittle" id="name2" type="text" class="form-control" placeholder="Tittle:"

                                                       <c:if test="${tittle ne notext}">
                                                           value="${tittle}"
                                                       </c:if>

                                                       >
                                            </div>
                                        </div><!--end col-->





                                        <div class="col-md-2">
                                            <div class="mb-3">
                                                </br>
                                                <button  type="submit" class="btn btn-primary">Search</button>
                                            </div>
                                        </div><!--end col-->




                                        <div class="col-md-2">
                                            <div class="mb-3">
                                                </br>
                                                <button    class="btn btn-primary"><a style="color:white" href="milestone?action=add">Add</a></button>

                                            </div>
                                        </div><!--end col-->







                                    </div>
                                </form>
                            </div>

                            <div class="row">
                                <div class="col-12 mt-4">
                                    <div class="table-responsive shadow rounded">
                                        <table class="table table-center bg-white mb-0 table-sortable">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" style="min-width: 50px;">Id</th>
                                                    <th class="border-bottom p-3" style="min-width: 180px;">Tittle</th>
                                                    <th class="border-bottom p-3">Assingment</th>
                                                    <th class="border-bottom p-3">FromDate</th>
                                                    <th class="border-bottom p-3">ToDate</th>

                                                    <th class="border-bottom p-3">Status</th>
                                                    <th class="border-bottom p-3" style="min-width: 100px;">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${subs}" var="s">
                                                    <tr>
                                                        <td class="p-3 form-label">${s.milestone_id}</td>
                                                        <td class="py-3 form-label">
                                                            <a href="#" class="text-dark">
                                                                <div class="d-flex align-items-center">
                                                                    <span class="ms-2 form-label">${s.tittle}</span>
                                                                </div>
                                                            </a>
                                                        </td>
                                                        <td class="p-3 form-label">${s.assignment.title}</td>
                                                        <td class="p-3 form-label">${s.from_date.toLocalDate().getDayOfMonth()}/${s.from_date.toLocalDate().getMonthValue()}/${s.from_date.toLocalDate().getYear()}</td>
                                                        <td class="p-3 form-label">${s.to_date.toLocalDate().getDayOfMonth()}/${s.to_date.toLocalDate().getMonthValue()}/${s.to_date.toLocalDate().getYear()}</td>

                                                <form id="status_form_${s.milestone_id}" action="milestone" method="get" >
                                                    <input type="hidden" name="action" value="list">
                                                    <input name="page" type="hidden" value="${page_index}">
                                                    <input name="class" type="hidden" value="${class_id}">
                                                    <input name="tittle" type="hidden" value="${tittle}">
                                                    <input name="status" type="hidden" value="${status}">

                                                    <input type="hidden" name="change_status" value="${s.milestone_id}_${s.status}"/>

                                                    <c:if test="${s.status eq true}">
                                                        <td class="p-3">
                                                            <span class="badge bg-soft-success">Active</span>
                                                            <label 

                                                                class="switch-wrap">
                                                                <input type="checkbox" class="open-model" id="status_check_${s.milestone_id}" checked onChange="modal_open(${s.milestone_id})" />

                                                                <div class="switch"></div>
                                                            </label>    
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${s.status eq false}">
                                                        <td class="p-3">
                                                            <span style="color: red" class="badge bg-soft-danger">Inactive</span>
                                                            <label 

                                                                class="switch-wrap">
                                                                <input type="checkbox" class="open-model"  id="status_check_${s.milestone_id}" onChange="modal_open(${s.milestone_id})"/>
                                                                <div class="switch"></div>
                                                            </label>
                                                        </td>
                                                    </c:if>
                                                </form>
                                                <td class="text-end p-3">
                                                    <a style="align-items:  center"  href="milestone?action=edit&id=${s.milestone_id}" class="btn btn-icon btn-pills btn-soft-primary" ><i class="uil uil-pen"></i></a>

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
                                        <span class="text-muted me-3">Showing ${(page_index-1) * page_size + 1} - ${page_index * page_size} out of ${count}</span>
                                        <ul id="only-pagger" class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                            <script>
                                                render("only-pagger",${page_index},${total_page}, 2,${class_id},${ass_id}, '${status}', '${tittle}');

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
                </div>
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->

    </script>


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
