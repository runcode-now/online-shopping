<%-- 
    Document   : a_userdetails
    Created on : Feb 6, 2024, 11:46:02 PM
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
        <title>Thông tin người dùng</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
        <!-- Iconic Fonts -->
        <link rel="stylesheet" href="vendors/iconic-fonts/flat-icons/flaticon.css">
        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- Foodtech styles -->
        <link href="assets/css/style.css" rel="stylesheet">
        <!-- Favicon -->
        <link rel="icon" type="image/png" sizes="32x32" href="favicon.ico">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            .container {
                background-color: #F0F0FA;
            }
            .container__content {
                text-decoration: none;
                color: #FF8306;
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 15px;
                padding-left: 15px;
            }
            .error {
                color: red;
                margin-left: 2px;
                font-size: 13px;
            }


            /* ===================== BLOCK LEFT ========================== */
            .block-left {
            }
            .info {
                width: 100%;
                background-color: #fff;
                padding: 15px 15px;
                display: block;
                text-align: center;
                border-radius: 2px;
            }

            .info__img {
                width: 100px;
                height: 100px;
            }

            .info__img img {
                width: 100px;
                height: 100px;
                object-fit: cover;
                border-radius: 50%;
            }
            .infor__name p{
                margin: 0px;
                font-size: 20px;
                font-weight: bold;
            }

            /* =================== BLOCK RIGHT ======================= */
            .block-right {
                background-color: #fff;
                padding: 25px 35px;
                display: flex;
                flex-direction: column;
                gap: 10px;
                position: relative;
                border-radius: 2px;
            }
            .common {
                width: 100%;
            }
            .btn-save {
                border-radius: 3px;
                min-width: 120px;
                border: none;
                background-color: #629adf;
                padding: 10px 15px;
                color: #FFF;
                bottom: 30px;
                font-weight: 600;
            }
            .btn-save:hover{
                cursor: pointer;
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
                top: 85px;
                right: 20px;
                padding-right: 15px;
            }

            .toast {
                max-width: 350px;
                max-height: 45px;
                display: flex;
                align-items: center;
                gap: 20px;
                background-color: #fff;
                padding: 15px 10px;
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
                <div class="container" style="font-family: Arial, Helvetica, sans-serif;">
                    <div class="row">
                        <p class="container__content">
                            <a href="ad_userlist" style="text-decoration: none; font-size: 20px; font-weight: bold">
                                Danh sách người dùng &nbsp;
                            </a>
                            <i class="fas fa-backward" style="color: #74C0FC;"></i>&nbsp;
                            Thông tin người dùng
                        </p>    
                        <div id="toast" class="${status == 'success' ? '' : 'hide'}">
                            <div class="toast">
                                <div class="toast__icon">
                                    <div class="fas fa-check-circle"></div>
                                </div>
                                <div class="toast__body">
                                    <p class="toast__body--msg">Cập nhật thông tin thành công</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3 block-left">
                            <div class="info">
                                <label class="info__img">
                                    <img src="${account.accAvarUrl}" alt="Avatar">
                                </label>
                                <div class="infor__name">
                                    <!-- <p>${account.accName}</p> -->
                                    <p>${account.accName}</p>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-9 block-right">
                            <form action="ad_upuser">
                                <div class="row">
                                    <div class="common mb-4 col-md-6">
                                        <p class="common__title">Họ và tên</p>
                                        <input type="text" disabled class="form-control" name="name" value="${account.accName}" />
                                    </div>

                                    <div class="common mb-4 col-md-6">
                                        <p class="common__title">Email</p>
                                        <input type="text" disabled class="form-control" value="${account.accEmail}" />
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="common mb-4 col-md-6">
                                        <p class="common__title">Số điện thoại</p>
                                        <input type="text" disabled class="form-control" name="name" value="${account.accPhone}" />
                                    </div>

                                    <div class="common mb-4 col-md-6">
                                        <p class="common__title">Mật khẩu</p>
                                        <input type="password" disabled class="form-control" value="11111111" />
                                    </div>
                                </div>

                                <input type="hidden" name="choice" value="update" />
                                <input type="hidden" name="accId" value="${account.accId}" />
                                <input type="hidden" name="page" value="userdetails" />
                                <div class="row">
                                    <div class="common mb-4 col-md-6">
                                        <p class="common__title">Ngày sinh</p>
                                        <input type="date" disabled class="form-control" name="name" value="${account.accDoB}" />
                                    </div>

                                    <div class="common mb-4 col-md-6">
                                        <p class="common__title">Vai trò</p>
                                        <c:if test="${account.roleId == 1}">
                                            <input type="text" disabled class="form-control" name="name" value="Admin" />
                                        </c:if>
                                        <c:if test="${account.roleId == 2}">
                                            <input type="text" disabled class="form-control" name="name" value="Salesman" />
                                        </c:if>
                                        <c:if test="${account.roleId == 3}">
                                            <input type="text" disabled class="form-control" name="name" value="Marketer" />
                                        </c:if>
                                        <c:if test="${account.roleId == 4}">
                                            <input type="text" disabled class="form-control" name="name" value="Khách hàng" />
                                        </c:if>
                                    </div>

                                    <div class="common mb-4 col-md-6">
                                        <p class="common__title">Ngày tạo tài khoản</p>
                                        <input type="date" disabled class="form-control" name="name" value="${account.accCreatedDate}" />
                                    </div>

                                    <div class="common mb-4 col-md-6">
                                        <p class="common__title">Trạng thái</p>
                                        <select name="status" id="" class="form-control">
                                            <option value="off" class="" <c:if test="${account.accStatus == 'off'}">selected</c:if>>
                                                    Dừng hoạt động</option>
                                                <option value="on" class="" <c:if test="${account.accStatus == 'on'}">selected</c:if>>
                                                Đang hoạt động</option>
                                        </select>
                                    </div>
                                </div>

                                <input type="submit" class="btn-save mt-4" value="Lưu thông tin" />
                            </form>

                        </div>
                    </div>
                </div>
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