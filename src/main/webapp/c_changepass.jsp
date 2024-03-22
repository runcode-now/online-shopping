<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Thay đổi mật khẩu</title>
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
                background-color: white;
                margin: 25px 120px;
                padding: 25px 45px;
                display: flex;
                flex-direction: column;
                gap: 10px;
            }

            .block-right__title {
                color: #629adf;
                font-weight: bolder;
                font-size: 28px;
                margin-bottom: 20px;
            }

            .common {
                width: 100%;
            }

            .common__title {
                font-size: 17px;
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

            .error {
                color: red;
            }

            /*pop-up*/
            #toast {
                position: fixed;
                top: 100px;
                right: 32px;
            }

            #toast .toast {
                max-width: 300px;
                max-height: 120px;
                display: flex;
                align-items: center;
                gap: 20px;
                background-color: #fff;
                padding: 10px 10px;
                border-radius: 2px;
                border-left: 4px solid #47d864;
                box-shadow: 0 5px 8px rgba(0, 0, 0, 0.08);
                animation: slideInLeft ease 1s, fadeOut linear 2s 2s forwards;
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
                color: rgb(77, 75, 75);
            }
            .hide {
                display: none;
            }
        </style>
    </head>

    <body class="ms-body ms-aside-left-open ms-primary-theme ms-has-quickbar">
        <!-- Sidebar Navigation Left -->
        <aside id="ms-side-nav" class="side-nav fixed ms-aside-scrollable ms-aside-left">
            <c:if test="${sessionScope.account.roleId == 1}">
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

                </c:if>

                <c:if test="${sessionScope.account.roleId == 2}">
                    <!-- Logo -->
                    <div class="logo-sn ms-d-block-lg">
                        <img style="max-width: 255px;" src="https://scontent.fhan18-1.fna.fbcdn.net/v/t1.15752-9/423422529_931280068324876_5402123020227114441_n.png?_nc_cat=108&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=GJIOVC0k1PsAX_l36k0&_nc_ht=scontent.fhan18-1.fna&oh=03_AdT2vGnLNVUhiU4H96X6QdvfqioLWRYCd9NIi8nXo46hXg&oe=65FA97B9" alt="logo">
                    </div>
                    <br>
                    <!-- Navigation -->
                    <ul class="accordion ms-main-aside fs-14" id="side-nav-accordion">
                        <!-- Dashboard -->
                        <li class="menu-item">
                            <a href="sale_dashboard"> <span><i class="fas fa-columns" style="width: 14px;
                                                               height: 14px;"></i>Thống kê</span>
                            </a>
                        </li>
                        <!-- /Dashboard -->

                        <!-- order -->
                        <li class="menu-item">
                            <a href="#" class="has-chevron" data-toggle="collapse" data-target="#order" aria-expanded="false"
                               aria-controls="order">  <span><i class="fas fa-clipboard-list fs-16"></i>Đơn hàng</span>
                            </a>
                            <ul id="order" class="collapse" aria-labelledby="order" data-parent="#side-nav-accordion">
                                <li> <a href="sale_order" class="unactive">Tất cả</a>
                                </li>
                                <li> <a href="sale_order?type=delivered" class="unactive">Đơn hàng đã giao</a>
                                </li>
                                <li> <a href="sale_order?type=cancelled" class="unactive">Đơn hàng đã hủy</a>
                                </li>
                                <li> <a href="sale_order?type=pending" class="unactive">Đơn hàng đang chờ xử lý</a>
                                </li>
                                <li> <a href="sale_order?type=delivering" class="unactive">Đơn hàng đang giao</a>
                                </li>
                            </ul>
                        </li>
                        <!-- order -->
                    </c:if>

                    <c:if test="${sessionScope.account.roleId == 3}">
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
                        </c:if>

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
                            <div class="col-md-8 block-right">
                                <p class="block-right__title">Đổi mật khẩu</p>
                                <form action="com_changepass" class="parents" method="post">
                                    <div class="common mb-4">
                                        <p class="common__title">Mật khẩu cũ</p>
                                        <input type="password" class="form-control " required name="oldpass"
                                               placeholder="Nhập mật khẩu cũ" />
                                        <p class="error">${errOldPass}</p>
                                    </div>
                                    <div class="row">
                                        <div class="common mb-4 col-md-6">
                                            <p class="common__title">Nhập mật khẩu mới</p>
                                            <input type="password" class="form-control" required name="newpass"
                                                   placeholder="Nhập mật khẩu mới" />
                                            <p class="error">${errNewPass}</p>
                                        </div>

                                        <div class="common mb-4 col-md-6">
                                            <p class="common__title">Nhập lại mật khẩu mới</p>
                                            <input type="password" class="form-control" name="cfpass" placeholder="Nhập lại mật khẩu mới"
                                                   required />
                                            <p class="error">${errCfPass}</p>
                                        </div>
                                    </div>

                                    <input type="hidden" id="check" value="${status}">
                                    <div class="common mb-3 btn-button">
                                        <button class="btn-save">Lưu lại</button>
                                    </div>
                                </form>

                            </div>
                        </div>

                    </main>

                    <!--pop-up notification-->
                    <div id="toast" class="${status == 'success' ? '' : 'hide'}">
                        <div class="toast">
                            <div class="toast__icon">
                                <div class="fas fa-check-circle"></div>
                            </div>
                            <div class="toast__body">
                                <p class="toast__body--msg">Đổi mật khẩu thành công</p>
                            </div>
                        </div>
                    </div>
                    <!--pop-up notification-->

                    <!-- SCRIPTS -->
                    <script>
                        function updateImage() {
                            // Truy cập form và gọi phương thức submit
                            const form = document.getElementById('uploadForm');
                            form.submit();
                        }

                        <%
                            String showPopup = (String) request.getAttribute("status");
                            if (showPopup != null && showPopup == "success") {
                        %>

                        setTimeout(function () {
                            document.getElementById('toast').classList.remove('hide');
                            setTimeout(function () {
                        <% session.removeAttribute("account"); %>
                                window.location.href = 'com_login';
                            }, 1000);
                        }, 500);
                        <%
                        }
                        %>

                    </script>
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