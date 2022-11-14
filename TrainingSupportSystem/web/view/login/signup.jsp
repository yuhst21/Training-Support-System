
<!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>TSS - Training Support System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="https://shreethemes.in" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="assets/images/logo.png">
        <!-- Bootstrap -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />

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
        
        <div class="back-to-home rounded d-none d-sm-block">
            <a href="index.html" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>

        <!-- Hero Start -->
        <section class="bg-half-150 d-table w-100 bg-light" style="background: url('assets/images/bg/bg-lines-one.png') center;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">
                        
                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <img style="width:150px;height:150px;border-radius: 100px; border-style: solid" src="assets/images/logo.png" height="24" class="mx-auto d-block" alt="">
                                <h4 class="text-center">Register</h4> 
                                <p style="color: red">${requestScope.error2}</p>
                                
                                <form action="signup" method="post" class="login-form mt-4">
                                    <div class="row">
                                        
                                        <div class="col-md-12">
                                            <div class="mb-3">                                                
                                                <label class="form-label">Full name <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control" placeholder="Full Name" name="name" value ="${name}" required="">
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Email <span class="text-danger">*</span></label>
                                                <input type="email" class="form-control" placeholder="Email" name="email" value="${email}" required="">
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Captcha <span class="text-danger">*</span></label>
                                                
                                            </div>
                                        </div>
                                        <h2 class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">${requestScope.captcha}<span class="text-danger"></span></label>
                                                <input oncopy="return false" onpaste="return false" oncut="return false" type="text" class="form-control" placeholder="Captcha" name="captcha" required="">
                                            </div>
                                        </h2>
                                                <p style="color: red">${requestScope.error}</p>                                
                                        <div class="col-md-12">
                                            <div class="d-grid">
                                                <button type="submit" class="btn btn-primary">Register</button>
                                            </div>
                                        </div>

                                        <div class="col-lg-12 mt-3 text-center">
                                            <h6 class="text-muted">Or</h6>
                                        </div><!--end col-->
                                        
                                       
                                        
                                        <div class="col-12 mt-3">
                                            <div class="d-grid">
                                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:9999/TrainingSupportSystem/GoogleSignInServlet&response_type=code&client_id=448431884720-edgchu8j2vmjjbsu9fc5mqqg6lqtuij5.apps.googleusercontent.com&approval_prompt=force" class="btn btn-soft-primary"><i class="uil uil-google"></i> Google</a>
                                            </div>
                                        </div><!--end col-->

                                        <div class="mx-auto">
                                            <p class="mb-0 mt-3"><small class="text-dark me-2">Already have an account ?</small> <a href="login" class="text-dark fw-bold">Sign in</a></p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div><!---->
                    </div> <!--end col-->
                </div><!--end row-->
            </div> <!--end container-->
        </section><!--end section-->
        <!-- Hero End -->
        
        <!-- javascript -->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <!-- Icons -->
        <script src="assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="assets/js/app.js"></script>
        
    </body>

</html>