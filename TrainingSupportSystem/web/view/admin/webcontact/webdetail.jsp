<%-- 
    Document   : webdetail
    Created on : Sep 24, 2022, 7:11:52 PM
    Author     : win
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="assets/css/modal.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/custom_attend.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/confirm_web_send.js" type="text/javascript"></script>
    </head>

    <body>
        <div id="demo-modal" class="modal">
            <div class="modal__content" id="model_content">
                <h2>Confirm</h2>
                <p>
                    Do you want to send!
                </p>
                <div class="modal__footer">
                    <button type="button" onclick="modal_close()" class="button-10">Close</button>
                    <button type="button" onclick="modal_change()" class="button-10">Confirm</button>
                </div>
                <a href="#" class="modal__close">&times;</a>
            </div>
        </div>
        <div id="demo-modal2" class="modal">
            <div class="modal__content" id="model_content">
                <h2></h2>
                <p>Send successfully!</p>
                <div class="modal__footer">
                    <button type="button" onclick="modal_close2()" class="button-10">Close</button>
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
            <jsp:include page="../../navbar.jsp"/>
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
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Contact Profile</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="">Course</a></li>
                                    <li class="breadcrumb-item"><a href="">Contact</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Contact Profile</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-lg-3 col-md-5 mt-4">
                                <div class="bg-white rounded shadow overflow-hidden">
                                    <div class="card border-0">
                                        <img src="assets/images/bg/bg-profile.jpg" class="img-fluid" alt="">
                                    </div>

                                    <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
                                        <img src="assets/images/client/09.jpg" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                        <h5 class="mt-3 mb-1">${maincontact.name}</h5>
                                        <p class="text-muted mb-0"></p>
                                    </div>

                                    <div class="list-unstyled p-4">
                                        <div class="progress-box mb-4">
                                            <h6 class="title"></h6>
                                            <div class="progress">
                                                <div class="progress-bar position-relative bg-primary" style="width:100%;">
                                                    <div class="progress-value d-block text-muted h6"></div>
                                                </div>
                                            </div>
                                        </div><!--end process box-->

                                        <div class="d-flex align-items-center mt-2">

                                        </div>

                                        <div class="d-flex align-items-center mt-2">
                                            <i class="uil uil-envelope align-text-bottom text-primary h5 mb-0 me-2"></i>
                                            <h6 class="mb-0">Email</h6>
                                            <p class="text-muted mb-0 ms-2">${maincontact.email}</p>
                                        </div>

                                        <div class="d-flex align-items-center mt-2">
                                            <i class="uil uil-book-open align-text-bottom text-primary h5 mb-0 me-2"></i>
                                            <h6 class="mb-0">Phone</h6>
                                            <p class="text-muted mb-0 ms-2">${maincontact.mobile}</p>
                                        </div>



                                    </div>
                                </div>
                            </div><!--end col-->

                            <div class="col-lg-6 col-md-7 mt-4">
                                <div class="card border-0 shadow overflow-hidden">
                                    <ul class="nav nav-pills nav-justified flex-column flex-sm-row rounded-0 shadow overflow-hidden bg-white mb-0" id="pills-tab" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link rounded-0 active" id="overview-tab" data-bs-toggle="pill" href="#pills-overview" role="tab" aria-controls="pills-overview" aria-selected="false">
                                                <div class="text-center pt-1 pb-1">
                                                    <h4 class="title fw-normal mb-0">Overview</h4>
                                                </div>
                                            </a><!--end nav link-->
                                        </li><!--end nav item-->


                                    </ul>

                                    <div class="tab-content p-4" id="pills-tabContent">
                                        <div class="tab-pane fade show active" id="pills-overview" role="tabpanel" aria-labelledby="overview-tab">
                                            <h5 class="mb-0">Message</h5>

                                            <p class="text-muted mt-4 mb-0"></p>
                                            <textarea readonly="" class="form-control" rows="3">${maincontact.message}</textarea>
                                            <br/><!-- comment -->
                                            <h5 class="mb-0">Date Send</h5>
                                            <input type="date" class="form-control" value="${maincontact.dateSend}" readonly="">
                                            <div class="row">
                                                <div class="col-lg-12 col-12 mt-4">
                                                    <h5>Response</h5>

                                                    <form id="frm" action="weblist" method="post">
                                                        <input type="hidden" name="action" value="edit"/>
                                                        <input type="hidden" name="contact_id_per" value="${maincontact.contact_id}">
                                                        <label class="form-label">Send Response <span class="text-danger">*</span></label>
                                                        <textarea  name="message_per" " id="textid" rows="4" class="form-control" 
                                                                   onkeydown="limit(this);" onkeyup="limit(this);"
                                                                   maxlength="201"
                                                                   >${maincontact.respones}</textarea>
                                                        <div class="mb-12" id="textvalidate"></div>

                                                    </form><!--end form-->
                                                    <button onclick="modal_open2()" style="width: 100px ;margin-left:  80%;margin-top: 20px" type="submit" class="button-10">Send</button>
                                                </div>                                             
                                            </div>
                                        </div>                                   
                                    </div>                     
                                </div>
                            </div><!--end col-->

                            <div class="col-lg-3 mt-4">
                                <div class="card rounded border-0 shadow">
                                    <div class="p-4 border-bottom">
                                        <h5 class="mb-0">Note</h5>
                                    </div>

                                    <ul class="list-unstyled mb-0 p-4" data-simplebar style="height: 200px;">

                                        <li class="d-md-flex align-items-center text-center text-md-start mt-4">                                  
                                            <p class="text-muted my-1"> 
                                                <c:if test="${maincontact.respones ne null}">
                                                    <label class="form-label">Supporter</label>                                          
                                                    <span style="margin-left: 20px;margin-top: 5px" class="badge bg-soft-success">Checked</span>
                                                    <br/>
                                                    <label class="form-label">Date Send</label>                                          
                                                    <span> <input type="date" class="form-control" value="${maincontact.dateResponse}" readonly=""></span>



                                                </c:if>

                                                <c:if test="${maincontact.respones eq null}">

                                                    <label class="form-label">Supporter</label>                                                                                                                                     
                                                    <span class="badge bg-soft-danger">Unchecked</span>


                                                </c:if></p>                            
                                        </li>
                                    </ul>
                                </div>
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
        <div class="offcanvas offcanvas-end bg-white shadow" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header p-4 border-bottom">
                <h5 id="offcanvasRightLabel" class="mb-0">
                    <img src="assets/images/logo-dark.png" height="24" class="light-version" alt="">
                    <img src="assets/images/logo-light.png" height="24" class="dark-version" alt="">
                </h5>
                <button type="button" class="btn-close d-flex align-items-center text-dark" data-bs-dismiss="offcanvas" aria-label="Close"><i class="uil uil-times fs-4"></i></button>
            </div>
            <div class="offcanvas-body p-4 px-md-5">
                <div class="row">
                    <div class="col-12">
                        <!-- Style switcher -->
                        <div id="style-switcher">
                            <div>
                                <ul class="text-center list-unstyled mb-0">
                                    <li class="d-grid"><a href="javascript:void(0)" class="rtl-version t-rtl-light" onclick="setTheme('style-rtl')"><img src="assets/images/layouts/light-dash-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="ltr-version t-ltr-light" onclick="setTheme('style')"><img src="assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-rtl-version t-rtl-dark" onclick="setTheme('style-dark-rtl')"><img src="assets/images/layouts/dark-dash-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-ltr-version t-ltr-dark" onclick="setTheme('style-dark')"><img src="assets/images/layouts/dark-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-version t-dark mt-4" onclick="setTheme('style-dark')"><img src="assets/images/layouts/dark-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Dark Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="light-version t-light mt-4" onclick="setTheme('style')"><img src="assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Light Version</span></a></li>
                                    <li class="d-grid"><a href="landing/index.html" target="_blank" class="mt-4"><img src="assets/images/layouts/landing-light.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Landing Demos</span></a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end Style switcher -->
                    </div><!--end col-->
                </div><!--end row-->
            </div>

            <div class="offcanvas-footer p-4 border-top text-center">
                <ul class="list-unstyled social-icon mb-0">
                    <li class="list-inline-item mb-0"><a href="https://1.envato.market/doctris-template" target="_blank" class="rounded"><i class="uil uil-shopping-cart align-middle" title="Buy Now"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://dribbble.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-dribbble align-middle" title="dribbble"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://www.facebook.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-facebook-f align-middle" title="facebook"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://www.instagram.com/shreethemes/" target="_blank" class="rounded"><i class="uil uil-instagram align-middle" title="instagram"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://twitter.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-twitter align-middle" title="twitter"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="mailto:support@shreethemes.in" class="rounded"><i class="uil uil-envelope align-middle" title="email"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="index.html" target="_blank" class="rounded"><i class="uil uil-globe align-middle" title="website"></i></a></li>
                </ul><!--end icon-->
            </div>
        </div>
        <!-- Offcanvas End -->

        <!-- View Invoice Start -->

        <!-- View Invoice End -->

        <!-- javascript -->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="assets/js/simplebar.min.js"></script>
        <!-- Icons -->
        <script src="assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="assets/js/app.js"></script>

    </body>

</html>