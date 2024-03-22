<%-- 
    Document   : a_register
    Created on : Feb 4, 2024, 3:48:47 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Đăng ký tài khoản quản trị</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png"> 
        <!-- Iconic Fonts -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="vendors/iconic-fonts/font-awesome/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="vendors/iconic-fonts/flat-icons/flaticon.css">
        <link rel="stylesheet" href="vendors/iconic-fonts/cryptocoins/cryptocoins.css">
        <link rel="stylesheet" href="vendors/iconic-fonts/cryptocoins/cryptocoins-colors.css">
        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery UI -->
        <link href="assets/css/jquery-ui.min.css" rel="stylesheet">
        <!-- Page Specific CSS (Slick Slider.css) -->
        <link href="assets/css/slick.css" rel="stylesheet">
        <link href="assets/css/datatables.min.css" rel="stylesheet">
        <!-- Foodtech styles -->
        <link href="assets/css/style.css" rel="stylesheet">
        <!-- Favicon -->
        <link rel="icon" type="image/png" sizes="32x32" href="favicon.ico">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            /* =================== BLOCK RIGHT ======================= */
            .block-right {
                width: 100%;
                background-color: white;
                margin: 25px 120px;
                padding: 25px 40px;
                display: flex;
                flex-direction: column;
                gap: 10px;
            }

            .block-right__title {
                color: rgb(168, 9, 9);
                font-weight: bolder;
                font-size: 28px;
                margin-bottom: 20px;
                text-align: center;
            }

            .common {
                width: 100%;
            }

            .common__title {
                font-size: 15px;
            }

            .btn-button {
                width: 100%;
                text-align: end;
                padding: none;
            }

            .btn-save:hover {
                cursor: pointer;
            }

            .btn-save {
                border-radius: 3px;
                min-width: 150px;
                border: none;
                background-color: #336cb1;
                padding: 10px 15px;
                color: #FFF;
                bottom: 30px;
                font-weight: 600;
                text-align: center;
            }

            p,
            h1,
            h2 {
                margin: 0px;
            }

            .error {
                color: red;
            }

            #toast {
                position: fixed;
                top: 80px;
                right: 20px;
            }

            .toast {
                max-width: 350px;
                max-height: 45px;
                display: flex;
                align-items: center;
                gap: 20px;
                background-color: #fff;
                padding: 10px 10px;
                border-radius: 2px;
                border-left: 4px solid #47d864;
                box-shadow: 0 5px 8px rgba(0, 0, 0, 0.08);
                animation: slideInLeft ease .3s, fadeOut linear 1s 3s forwards;
            }

            @keyframes slideInLeft {
                from {
                    opacity: 0;
                    transform: translateX(calc(100% + 32px));
                }

                to {
                    opacity: 1;
                    transform: translateX(0);
                }
            }

            @keyframes fadeOut {
                to {
                    opacity: 0;
                }
            }


            .toast__icon {
                font-size: 22px;
                color: #47d864;
            }

            .toast__body--msg {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 17px;
                color: #47d864;
            }
            .hide {
                display: none;
            }
        </style>
    </head>

    <body class="ms-body ms-aside-left-open ms-primary-theme ms-has-quickbar">
        <!-- Sidebar Navigation Left -->
        <aside id="ms-side-nav" class="side-nav fixed ms-aside-scrollable ms-aside-left">
            <!-- Logo -->
            <div class="logo-sn ms-d-block-lg">
                <img style="max-width: 255px;" src="https://scontent.fsgn2-7.fna.fbcdn.net/v/t1.15752-9/423472062_1513941472794031_1512193570186889795_n.png?_nc_cat=108&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=XRT4JijyDEAAX-eSnkR&_nc_oc=AQm7Ls7q3hNa9aYBBL0rALnbrxnhJTVTmyqQZmNbfKkE-l4E6aJMK9MC3cKzgIiFiPI&_nc_ht=scontent.fsgn2-7.fna&oh=03_AdTAZOY-GW5Nt09boLVoOBjnB7BNACIroAk--SEpLIMgYw&oe=65FA4C89" alt="logo">
            </div>
            <br>
            <!-- Navigation -->
            <ul class="accordion ms-main-aside fs-14" id="side-nav-accordion">
                <!-- Dashboard -->
                <li class="menu-item">
                    <a href="ad_dashboard"> <span><i class="fas fa-columns"
                                                        style="width: 14px; height: 14px;"></i>Thống kê</span>
                    </a>
                </li>
                <!-- /Dashboard -->

                <!-- manage account -->
                <li class="menu-item">
                    <a href="ad_userlist"> <span><i class="fas fa-user-circle"
                                                    style="width: 14px; height: 14px;"></i>Quản lí tài khoản</span>
                    </a>
                </li>
                <!-- /manage account -->

                <!-- register account -->
                <li class="menu-item">
                    <a href="ad_register"> <span><i class="fas fa-user-plus"
                                                       style="width: 14px; height: 14px;"></i>Đăng kí tài khoản quản trị</span>
                    </a>
                </li>
                <!-- /register account -->

                <!-- ========= PROFILE ============================= -->
                <p style="border-bottom: 1px solid rgb(177, 177, 177); width: 80%; margin: 20px 22px"></p>
                <li class="menu-item">
                    <a href="com_upprofile"> <span><i class="fas fa-file-invoice" style="width: 14px; height: 14px;"></i>Chỉnh
                            sửa thông tin</span>
                    </a>
                </li>
                <!-- change password  -->
                <!-- change password -->
                <li class="menu-item">
                    <a href="com_changepass"> <span><i class="fas fa-key" style="width: 14px; height: 14px;"></i>Đổi mật
                            khẩu</span>
                    </a>
                </li>
                <!-- change password  -->
                <!-- change password -->
                <li class="menu-item">
                    <a href="com_logout"> <span><i class="fas fa-sign-out-alt" style="width: 14px; height: 14px;"></i>Đăng
                            xuất</span>
                    </a>
                </li>
                <!-- change password  -->


            </ul>
        </aside>


        <!-- Main Content -->
        <main class="body-content">
            <!-- Navigation Bar -->
            <nav class="navbar ms-navbar">
                <div class="ms-aside-toggler ms-toggler pl-0" data-target="#ms-side-nav" data-toggle="slideLeft"> <span
                        class="ms-toggler-bar bg-primary"></span>
                    <span class="ms-toggler-bar bg-primary"></span>
                    <span class="ms-toggler-bar bg-primary"></span>
                </div>
                <div style="width: 25%; padding-top: 10px;">
                    <img style="width: 100%;"
                         src="https://scontent.fhan3-1.fna.fbcdn.net/v/t1.15752-9/422913501_374119282031008_2624180467215836246_n.png?_nc_cat=105&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=Qv6VonjNR50AX8G2nc_&_nc_ht=scontent.fhan3-1.fna&oh=03_AdRJCufJs3uUgbXd6mUFtWjrq5-GLQdKVLFt5ehtX0aDqg&oe=65E9FB2A"
                         alt="">
                </div>
                <ul class="ms-nav-list ms-inline mb-0" id="ms-nav-options">
                    <li class="ms-nav-item ms-nav-user dropdown">
                        <a href="#" id="userDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img class="ms-user-img ms-img-round float-right" style="width: 40px;height: 40px; object-fit: cover;border-radius: 50%;"
                                 src="${sessionScope.account.accAvarUrl}" alt="people">
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right user-dropdown" aria-labelledby="userDropdown">
                            <li class="dropdown-menu-header">
                                <h6 class="dropdown-header ms-inline m-0"><span class="text-disabled">${sessionScope.account.accName}</span>
                                </h6>
                            </li>
                            <li class="dropdown-divider"></li>
                            <li class="ms-dropdown-list">
                                <a class="media fs-14 p-2" href="com_upprofile"> <span><i class="flaticon-user mr-2"></i> Hồ sơ</span>
                                </a>
                                <a class="media fs-14 p-2" href="com_changepass"> <span><i class="flaticon-security mr-2"></i> Đổi mật
                                        khẩu</span>
                                </a>
                            </li>
                            <li class="dropdown-divider"></li>
                            <li class="dropdown-menu-footer">
                                <a class="media fs-14 p-2" href="com_logout"> <span><i class="flaticon-shut-down mr-2"></i>
                                        Đăng xuất</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>

            <div class="ms-content-wrapper">
                <div id="toast" class="${status == 'success' ? '' : 'hide'}">
                    <div class="toast">
                        <div class="toast__icon">
                            <div class="fas fa-check-circle"></div>
                        </div>
                        <div class="toast__body">
                            <p class="toast__body--msg">Đăng kí tài khoản thành công</p>
                        </div>
                    </div>
                </div>
                <!-- ================ FAILED ==============================-->
                <c:if test="${status != 'success'}">
                    <div class="col-md-9 block-right">
                        <p class="block-right__title">Đăng kí tài khoản quản trị viên</p>
                        <form action="ad_register" method="post">
                            <div class="row">
                                <div class="common mb-4 col-md-8">
                                    <p class="common__title">Họ và tên<span class="error">&nbsp;*</span></p>
                                    <input type="text" class="form-control" required name="fullname"
                                           value="${param.fullname}" placeholder="Nhập họ và tên" />
                                    <p class="error">${errFullName}</p>
                                </div>

                                <div class="common mb-4 col-md-4">
                                    <p class="common__title">Vị trí<span class="error">&nbsp;*</span></p>
                                    <select name="job" id="" class="form-control">
                                        <option value="1" class="form-control" ${param.job == '1' ? 'selected' : ''}>Admin</option>
                                        <option value="2" class="form-control" ${param.job == '2' ? 'selected' : ''}>Salesman</option>
                                        <option value="3" class="form-control" ${param.job == '3' ? 'selected' : ''}>Marketer</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="common mb-4 col-md-6">
                                    <p class="common__title">Địa chỉ Email<span class="error">&nbsp;*</span></p>
                                    <input type="email" class="form-control " required name="email"
                                           value="${param.email}" placeholder="Nhập địa chỉ email" />
                                    <p class="error">${errEmail}</p>
                                </div>

                                <div class="common mb-4 col-md-6">
                                    <p class="common__title">Số điện thoại</p>
                                    <input type="text" class="form-control " name="phone"
                                           value="${param.phone}" placeholder="Nhập số điện thoại" />
                                    <p class="error">${errPhone}</p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="common mb-4 col-md-6">
                                    <p class="common__title">Mật khẩu<span class="error">&nbsp;*</span></p>
                                    <input type="password" class="form-control" required name="pass"
                                           placeholder="Nhập mật khẩu" />
                                    <p class="error">${errPass}</p>
                                </div>

                                <div class="common mb-4 col-md-6">
                                    <p class="common__title">Nhập lại mật khẩu<span class="error">&nbsp;*</span></p>
                                    <input type="password" class="form-control" name="cfpass" placeholder="Nhập lại mật khẩu"
                                           required />
                                    <p class="error">${errCfPass}</p>
                                </div>
                            </div>

                            <div class="common mb-3 btn-button">
                                <button class="btn-save">Đăng kí tài khoản</button>
                            </div>
                        </form>

                    </div>    
                </c:if>


                <!-- =============== SUCCESS ================================-->
                <c:if test="${status == 'success'}">
                    <div class="col-md-9 block-right">
                        <p class="block-right__title">Đăng kí tài khoản quản trị viên</p>
                        <form action="ad_register" method="post">
                            <div class="row">
                                <div class="common mb-4 col-md-8">
                                    <p class="common__title">Họ và tên<span class="error">&nbsp;*</span></p>
                                    <input type="text" class="form-control" required name="fullname"
                                           placeholder="Nhập họ và tên" />
                                </div>

                                <div class="common mb-4 col-md-4">
                                    <p class="common__title">Vị trí<span class="error">&nbsp;*</span></p>
                                    <select name="job" id="" class="form-control">
                                        <option value="1" class="form-control" >Admin</option>
                                        <option value="2" class="form-control" >Salesman</option>
                                        <option value="3" class="form-control" >Marketer</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="common mb-4 col-md-6">
                                    <p class="common__title">Địa chỉ Email<span class="error">&nbsp;*</span></p>
                                    <input type="email" class="form-control " required name="email"
                                           placeholder="Nhập địa chỉ email" />
                                </div>

                                <div class="common mb-4 col-md-6">
                                    <p class="common__title">Số điện thoại</p>
                                    <input type="text" class="form-control " name="phone"
                                           placeholder="Nhập số điện thoại" />
                                </div>
                            </div>

                            <div class="row">
                                <div class="common mb-4 col-md-6">
                                    <p class="common__title">Mật khẩu<span class="error">&nbsp;*</span></p>
                                    <input type="password" class="form-control" required name="pass"
                                           placeholder="Nhập mật khẩu" />
                                </div>

                                <div class="common mb-4 col-md-6">
                                    <p class="common__title">Nhập lại mật khẩu<span class="error">&nbsp;*</span></p>
                                    <input type="password" class="form-control" name="cfpass" placeholder="Nhập lại mật khẩu"
                                           required />
                                </div>
                            </div>

                            <div class="common mb-3 btn-button">
                                <button class="btn-save">Đăng kí tài khoản</button>
                            </div>
                        </form>

                    </div>    
                </c:if>


            </div>

        </main>



        <!-- SCRIPTS -->
        <!-- Global Required Scripts Start -->
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/perfect-scrollbar.js">
        </script>
        <script src="assets/js/jquery-ui.min.js">
        </script>
        <!-- Global Required Scripts End -->
        <!-- Page Specific Scripts Start -->

        <script src="assets/js/Chart.bundle.min.js">
        </script>
        <script src="assets/js/widgets.js"></script>
        <script src="assets/js/clients.js"></script>
        <script src="assets/js/Chart.Financial.js"></script>
        <script src="assets/js/d3.v3.min.js">
        </script>
        <script src="assets/js/topojson.v1.min.js">
        </script>
        <script src="assets/js/datatables.min.js">
        </script>
        <script src="assets/js/data-tables.js">
        </script>
        <!-- Page Specific Scripts Finish -->
        <!-- Foodtech core JavaScript -->
        <script src="assets/js/framework.js"></script>
        <!-- Settings -->
        <script src="assets/js/settings.js"></script>
    </body>


</html>
