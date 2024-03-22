<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.team3.onlineshopping.model.Account" %>
<%@ page import="com.team3.onlineshopping.model.Customer" %>
<%@ page import="com.team3.onlineshopping.dal.AccountDAO" %>
<%@ page import="com.team3.onlineshopping.dal.CustomerDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Chi tiết khách hàng</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
        <!-- Iconic Fonts -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="vendors/iconic-fonts/font-awesome/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="vendors/iconic-fonts/flat-icons/flaticon.css">
        <link rel="stylesheet" href="vendors/iconic-fonts/cryptocoins/cryptocoins.css">
        <link rel="stylesheet" href="vendors/iconic-fonts/cryptocoins/cryptocoins-colors.css">
        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- Page Specific Css (Datatables.css) -->
        <link href="assets/css/datatables.min.css" rel="stylesheet">

        <!-- jQuery UI -->
        <link href="assets/css/jquery-ui.min.css" rel="stylesheet">
        <!-- Page Specific CSS (Slick Slider.css) -->
        <link href="assets/css/slick.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <!-- Foodtech styles -->
        <link href="assets/css/style.css" rel="stylesheet">
        <!-- Favicon -->
        <link rel="icon" type="image/png" sizes="32x32" href="favicon.ico">
        <style>
            /* =================== BLOCK RIGHT ======================= */
            .block-right {
                background-color: white;
                margin: 15px 120px;
                padding: 25px 45px;
                display: flex;
                flex-direction: column;
                gap: 10px;
            }

            .block-right__title {
                color: rgb(16, 16, 182);
                font-weight: bolder;
                font-size: 20px;
            }

            .info {
                text-align: center;
            }

            .info__img {
                max-width: 200px;
            }

            .info__img img {
                width: 150px;
                height: 150px;
                object-fit: cover;
                /* Cắt và căn giữa ảnh */
                border-radius: 50%;
            }

            .info__name p {
                font-weight: bold;
                font-size: 20px;
            }

            .common {
                width: 100%;
            }
            .common__title{
                font-weight: bold;
            }
            .common__content {
                min-height: 35px;
                font-size: 14px;
                border: 1px solid rgb(199, 196, 196);
                border-radius: 2px;
            }

            .common__content:focus {
                outline: none;
                box-shadow: none;
                border: none;
                border: 1px solid rgb(199, 196, 196);
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
                background-color: #629adf;
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
            .error{
                color: red;
            }

            /*============================== TABLE =============================*/
            .table-user {
                width: 100%;
                border-collapse: collapse;
            }

            .table-user__header {
                color: #fff;
                background-color: rgb(55, 55, 206);
                border-top: 1px solid rgb(194, 194, 194);
                border-bottom: 1px solid rgb(194, 194, 194);
            }

            .table-user__header th {
                padding: 10px 0;
                padding-left: 10px;
            }

            .table-user__body {
            }

            .table-user__body:hover {
                background-color: rgb(241, 241, 241);
            }

            .table-user__body td {
                border-top: 1px solid rgb(228, 228, 228);
                border-bottom: 1px solid rgb(228, 228, 228);
                padding: 15px 0;
                padding-left: 10px;
                padding-right: 20px;
            }


            .table-user__body--select {
                /* max-height: 40px; */
                width: 100%;
                padding: 5px;
            }
        </style>
    </head>

    <body class="ms-body ms-aside-left-open ms-primary-theme ms-has-quickbar">

 <!-- Sidebar Navigation Left Begin -->
        <aside id="ms-side-nav" class="side-nav fixed ms-aside-scrollable ms-aside-left">
            <!-- Logo -->
            <div class="logo-sn ms-d-block-lg">
                <img style="max-width: 255px;" src="https://scontent.fsgn2-4.fna.fbcdn.net/v/t1.15752-9/423568413_220611387798284_6265991015599051178_n.png?_nc_cat=101&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=6xm1kK9w87wAX9TEvv_&_nc_ht=scontent.fsgn2-4.fna&oh=03_AdTwMqeyx_8lcqp09mOKsXlRojFzIL02Pvvb9Y3q1WDgZA&oe=65FA4050" alt="logo">
            </div>
            <br>
            <!-- Navigation -->
            <ul class="accordion ms-main-aside fs-14" id="side-nav-accordion">
                <!-- Dashboard -->
                <li class="menu-item">
                    <a href="mkt_dashboard"> <span><i class="fas fa-columns" style="width: 14px; height: 14px;"></i>Thống kê</span>
                    </a>
                </li>
                <!-- /Dashboard -->

                <!-- Product Begin -->
                <li class="menu-item">
                    <a href="#" class="has-chevron" data-toggle="collapse" data-target="#product" aria-expanded="false" aria-controls="product1"> <span><i class="fa fa-archive fs-16"></i>Quản lý sản phẩm</span>
                    </a>
                    <ul id="product" class="collapse" aria-labelledby="product" data-parent="#side-nav-accordion">
                        <li> <a href="mkt_productlist">Danh sách sản phẩm</a>
                        </li>  
                        <li> <a href="mkt_addproduct">Thêm sản phẩm</a>
                        </li>
                        <li> <a href="mkt_feedbacklist">Đánh giá sản phẩm</a>
                        </li>
                    </ul>
                </li>
                <!-- Product End -->

                <!-- News Begin -->
                <li class="menu-item">
                    <a href="#" class="has-chevron" data-toggle="collapse" data-target="#slider" aria-expanded="false" aria-controls="slider"> <span><i class="fas fa-newspaper"></i>Tin tức</span>
                    </a>
                    <ul id="slider" class="collapse" aria-labelledby="slider" data-parent="#side-nav-accordion">
                        <li> <a href="mkt_postlist">Danh sách bài đăng</a>
                        </li>
                        <li> <a href="mkt_postcreate"> Thêm bài đăng</a>
                        </li>
                        <li> <a href="mkt_sliderlist?cateNewsId=2" >Danh sách Slider</a>
                        </li>
                        <li> <a href="mkt_addslider?cateNewsId=2" >Thêm Slider</a>
                        </li>
                        <li> <a href="mkt_collectionlist">Danh sách bộ sưu tập</a>
                        </li>
                        <li> <a href="mkt_addcollection" >Thêm bộ sưu tập</a>
                        </li>

                    </ul>
                </li>
                <!-- News End -->

                <!-- Customer Begin -->
                <li class="menu-item">
                    <a href="mkt_cuslist"> <span><i class="fas fa-user-friends fs-16"></i>Danh sách khách hàng</span>
                    </a>
                </li>
                <!-- Customer End -->



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


            </ul>
        </aside>
        <!-- Sidebar Navigation Left End -->


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
                <div class="block-right">
                    <p class="block-right__title">
                        <a href="mkt_cuslist"><i class="fas fa-chevron-circle-left fa-lg" style="color: rgb(55, 55, 206)"></a></i>
                        &nbsp; Thông tin cá nhân khách hàng</p>
                    <div class="info">
                        <label class="info__img" for="move">
                            <img src="${account.accAvarUrl}" alt="Avatar"> 
                        </label>
                        <div class="info__name">
                            <p>${account.accName}</p> 
                        </div>
                    </div>

                    <div class="row mt-3">
                        <div class="common col-md-2">
                        </div>

                        <div class="common col-md-5">
                            <p class="common__title">Họ và tên</p>
                            <div class="form-group form-control d-flex align-items-center">
                                <p>${account.accName}</p>
                            </div>
                        </div>

                        <div class="common col-md-3">
                            <p class="common__title">Số điện thoại</p>
                            <div class="form-group form-control d-flex align-items-center">
                                <p>${account.accPhone}</p>
                            </div>
                        </div>

                        <div class="common col-md-2">
                        </div>
                    </div>

                    <div class="row">
                        <div class="common col-md-2">
                        </div>
                        <div class="common col-md-5">
                            <p class="common__title">Email</p>
                            <div class="form-group form-control d-flex align-items-center">
                                <p>${account.accEmail}</p>
                            </div>
                        </div>

                        <div class="common col-md-3">
                            <p class="common__title">Trạng thái</p>
                            <c:if test="${account.accStatus == 'off'}">
                                <div class="form-group form-control d-flex align-items-center">
                                    <p class="text-danger font-weight-bold">Dừng hoạt động</p>
                                </div>
                            </c:if>
                            <c:if test="${account.accStatus != 'off'}">
                                <div class="form-group form-control d-flex align-items-center">
                                    <p class="text-success font-weight-bold">Đang hoạt động</p>
                                </div>
                            </c:if>
                        </div>
                        <div class="common col-md-2">
                        </div>
                    </div>

                    <div class="row">
                        <div class="common col-md-2">
                        </div>
                        <div class="common col-md-5">
                            <p class="common__title">Số tiền đã mua</p>
                            <div class="form-group form-control d-flex align-items-center">
                                <p id="formattedTotalPurchase">${customer.cusTotalPurchase}</p>

                                <script>
                                    // Lấy giá trị của số từ phần tử HTML
                                    var totalPurchase = document.getElementById('formattedTotalPurchase').innerHTML;

                                    // Định dạng số thành chuỗi với dấu phân tách là ","
                                    var formattedTotalPurchase = parseFloat(totalPurchase).toLocaleString('en-US');

                                    // Hiển thị lại số đã định dạng vào phần tử HTML
                                    document.getElementById('formattedTotalPurchase').innerHTML = formattedTotalPurchase;
                                </script>
                            </div>
                        </div>
                        <div class="common col-md-3">
                            <p class="common__title">Ngày tháng năm sinh</p>
                            <div class="form-group form-control d-flex align-items-center">
                                <p>${account.accDoB}</p>
                            </div>
                        </div>

                        <div class="common col-md-2">
                        </div>
                    </div>

                    <div class="mt-3">
                        <table class="table-user" >
                            <tr class="table-user__header">
                                <th style="width: 5%;">STT</th>
                                <th style="width: 20%;">Tên người nhận</th>
                                <th style="width: 20%;">Số điện thoại nhận hàng</th>
                                <th style="width: 55%;">Địa chỉ nhận hàng</th>
                            </tr>

                            <c:forEach items="${addList}" var="add" varStatus="loop">
                                <tr class="table-user__body">
                                    <td>${loop.index + 1}</td>
                                    <td>${add.addRecName}</td>
                                    <td>${add.addRecPhone}</td>
                                    <td>${add.addName}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                </div>
            </div>
        </main>



        <!-- SCRIPTS -->

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
