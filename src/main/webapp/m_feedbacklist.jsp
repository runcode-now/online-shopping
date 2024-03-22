<%-- 
    Document   : m_product-list
    Created on : Feb 5, 2024, 12:18:16 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.team3.onlineshopping.model.Account" %>
<%@ page import="com.team3.onlineshopping.model.Customer" %>
<%@ page import="com.team3.onlineshopping.model.Feedback" %>
<%@ page import="com.team3.onlineshopping.model.Product" %>
<%@ page import="com.team3.onlineshopping.dal.CustomerDAO" %>
<%@ page import="com.team3.onlineshopping.dal.AccountDAO" %>
<%@ page import="com.team3.onlineshopping.dal.FeedbackDAO" %>
<%@ page import="com.team3.onlineshopping.dal.ProductDAO" %>
<%@ page import="com.team3.onlineshopping.model.Cart" %>
<%@ page import="com.team3.onlineshopping.dal.CartDAO" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.HttpServlet" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Danh sách đánh giá</title>
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
        <!-- Boostrap web -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
        <style>
            .relative{
                position: relative;
            }

            .absolute{
                position: absolute;
            }

            .feedback__img{
                width: 60px;
                height: 60px;
                border-radius: 50%;
            }
            .text-justify {
                max-width: 200px; /* Đặt giá trị max-width để giữ cho ô không mở rộng */
                white-space: nowrap; /* Ngăn chặn ngắt dòng */
                overflow: hidden; /* Ẩn phần dư thừa */
                text-overflow: ellipsis; /* Hiển thị "..." */
            }

            .text-justify:hover {
                white-space: normal; /* Khi hover, cho phép ngắt dòng */
                overflow: visible; /* Hiển thị toàn bộ nội dung */
                text-overflow: unset; /* Hủy thiết lập text-overflow */
            }
            .review {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
            }

            .avatar {
                width: 80px;
                height: 80px;
                border-radius: 50%;
                object-fit: cover;
                margin-right: 20px;
            }

            .review-info {
                flex: 1;
            }

            .rating {
                font-size: 1.2em;
                font-weight: bold;
                margin-bottom: 10px;
            }

            h3 {
                margin: 0;
                margin-bottom: 5px;
                font-size: 13px;
            }

            p {
                margin: 0;
                color: #666;
            }
            .page{
                display: flex;
                justify-content: end;
                gap: 7px;
                margin-top: 20px;
            }
            .page__number, .page__number--chosen{
                width: 35px;
                height: 35px;
                border: 1px solid #dee2e6;
                padding: 10px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .page__number p{
                margin: 0;
                font-size: 14px;
                color: rgb(102, 102, 102);
            }
            .page__number:hover{
                background-color: #E9ECEF;
            }
            /* ======= CHOSEN ================= */
            .page__number--chosen{
                background-color: #FF8306;
            }
            .page__number--chosen p{
                margin: 0;
                font-size: 14px;
                color: #fff;
            }

            .filter {
                position: relative;
            }

            .filter__title {
                font-size: 100px;
                position: absolute;
                top: 10px;
                left: 100px;
                background-color: white;
                padding: 0 10px;
                color: rgb(26, 185, 26);
                font-weight: bold;
            }
            .filter__title--extra {
                font-size: 10px;
                position: absolute;
                top: 30px;
                right: 220px;
                background-color: white;
                padding: 0 10px;
                color: black;


            }
            .filter__body {
                margin: 40px;
                border: 1px solid rgb(231, 231, 231);
                padding: 20px 35px;
                border-radius: 5px;
                margin-top: 5px;
            }

            .filter__btn {
                position: absolute;
                left: 76%;
                top: 94%;
                padding: 3px 35px;
                border: 1px solid rgb(26, 185, 26);
                background-color: #fff;
                border-radius: 3px;
                color: rgb(11, 152, 11);
                font-weight: bold;
            }

            .filter__btn:hover {
                cursor: pointer;
                background-color: rgb(235, 235, 235);
            }

            .search {
                /* margin-right: 10px; */
                height: 100%;
                display: block;
                width: 100%;
                padding: 0.375rem 0.75rem;
                font-size: 1rem;
                font-weight: 400;
                line-height: 1.5;
                color: #212529;
                appearance: none;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #dee2e6;
                border-radius: 5px;
                transition: .15s ease-in-out, box-shadow .15s ease-in-out;
            }

            .search:focus {
                /*border-color: #007bff;*/
                box-shadow: 0px 0px 4px 4px rgba(0, 0, 0, 0.1);
                /*padding: 10px;*/
            }

            .search--container {
                position: relative;
            }

            .search--container i {
                font-size: 1.3em;
                /*font-weight: 1800;*/
            }

            .search--icon {
                position: absolute;
                font-size: 1px;
                top: 30%;
                right: 15px;
                transform: translateY(-50%);
            }

            .dropdown-item:hover {
                background-color: rgb(177, 177, 177);
                /* Màu nền thay đổi khi hover */
                color: #ffffff;
                /* Màu chữ thay đổi khi hover */
            }

            .button--update {
                padding: 5px 5px;
                width: 100%;
                background-color: #007fff;
                border-color: #00ec8d;
                border-radius: 4px;
                text-align: center;
                /*height: 30px;*/
                align-items: center;



            }
            .button--update a{
                padding: 15px 5px;
                color: #fff;

            }
            .button--update:hover {
                color: white;
                background-color: #210094;
            }
            .table td {
                padding: 0.75rem;
                vertical-align: unset;
                border-top: 1px solid #dee2e6;
                /*text-align: center;*/
                overflow: hidden;

            }
            .table th {
                font-size: 17px;
                background-color: #F89406;
            }
            .my-5 .long-text {
                max-width: 0; /* Đặt giá trị max-width để giữ cho ô không mở rộng */
                white-space: nowrap; /* Ngăn chặn ngắt dòng */
                overflow: hidden; /* Ẩn phần dư thừa */
                text-overflow: ellipsis; /* Hiển thị "..." */
            }
            .paging--fix {
                height: 170px;
            }

            #data .table td {
                white-space: nowrap; /* Ngăn chặn ngắt dòng */
                overflow: hidden; /* Ẩn phần dư thừa */
                text-overflow: ellipsis; /* Hiển thị "..." */
            }

            /* =========== SHOW ====================== */
            .ms-panel-body .show {
                display: flex;
                align-items: center;
                width: 20%;
                margin-bottom: 20px;
                gap: 10px;
            }

            .ms-panel-body .show__title {
                flex: 1;
                margin: 0px;
                margin-left: 1rem;
            }

            .ms-panel-body .show__select {
                flex: 2;
            }

            .feedback__product{
                text-align: center;
                text-decoration: none;
                position: absolute;
                top: 0;
                left:  50%;
                transform: translateX(-50%);
            }

        </style>
        <script>
            setTimeout(function () {
                document.getElementById("notification").style.display = "none"; // Ẩn thông báo sau 2 giây
                window.location.href = "ten_trang_chuyen_huong.jsp"; // Chuyển hướng trở lại màn hình người dùng
            }, 2000);
        </script>
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

            <!-- Product List Begin -->
            <div class="ms-content-wrapper">
                <div class="row">
                    <div class="col-md-12">
                        <!-- Breadcrumb Begin -->
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb pl-0">
                                <li class="breadcrumb-item"><a href="mkt_dashboard"><i class="material-icons">home</i> Thống kê</a></li>

                                <li class="breadcrumb-item active" aria-current="page">Danh sách phản hồi</li>
                            </ol>
                        </nav>
                        <!-- Breadcrumb End -->


                        <!-- Table Product -->
                        <div class="ms-panel">
                            <div class="ms-panel-header">
                                <h6>Danh sách phản hồi</h6>
                            </div>
                            <div class="ms-panel-body">
                                <div class="table-responsive">


                                    <div class="ms-panel">
                                        <form action="mkt_feedbacklist" id="user_list" method="post">
                                            <div class="ms-panel-header" style="border-bottom: 0;">
                                                <h6 style="color: #FF8306;">Danh sách phản hồi</h6>
                                            </div>
                                            <div class="ms-panel-header filter" style="border-bottom: 0; padding-bottom: 0;">


                                                <div class="filter__body">
                                                    <div class="row mt-2">
                                                        <div class="common mb-4 col-md-3">
                                                            <p class="common__title">Bài viết</p>
                                                            <input type="text" class="form-control" name="textSearch" oninput="movePage(1)"
                                                                   id="textSearch" value="${param.textSearch}" />
                                                        </div>

                                                        <div class="common mb-4 col-md-3">
                                                            <!-- <p class="common__title">Email</p> -->
                                                        </div>

                                                        <div class="common mb-4 col-md-3">
                                                            <p class="common__title">Từ ngày</p>
                                                            <input type="date" class="form-control" id="dateBegin" onchange="movePage(1)"
                                                                   name="dateBegin" value="${param.dateBegin}" />
                                                        </div>

                                                        <div class="common mb-4 col-md-3">
                                                            <p class="common__title">Đến ngày</p>
                                                            <input type="date" class="form-control" id="dateEnd" onchange="movePage(1)"
                                                                   name="dateEnd" value="${param.dateEnd}" />
                                                        </div>

                                                    </div>
                                                </div>
                                                <!--<input type="" class="filter__btn" value="Tìm kiếm" onclick="movePage(1)">-->
                                                <!--                                    <a href="#" class="filter__btn" onclick="movePage(1)"
                                                                                       style="color: rgb(11, 152, 11);">Tìm kiếm</a>-->
                                            </div>

                                            <div class="ms-panel-body">
                                                <div class="table-responsive">
                                                    <div class="show">
                                                        <p class="show__title">Đánh giá</p>
                                                        <select name="quantityPost" id="quantityPost" class="form-control show__select" onchange="movePage(1)">
                                                            <option value="0" <c:if test="${quantity == 0}">selected</c:if>>Tất cả</option>
                                                            <option value="1" <c:if test="${quantity == 1}">selected</c:if>>1 sao</option>
                                                            <option value="2" <c:if test="${quantity == 2}">selected</c:if>>2 sao</option>
                                                            <option value="3" <c:if test="${quantity == 3}">selected</c:if>>3 sao</option>
                                                            <option value="4" <c:if test="${quantity == 4}">selected</c:if>>4 sao</option>
                                                            <option value="5" <c:if test="${quantity == 5}">selected</c:if>>5 sao</option>

                                                            </select>
                                                        </div>

                                                        <div id="data">
                                                            <table class="table table-hover w-100 table-red thead-primary">


                                                                <div class="tab-pane" id="tabs-2" role="tabpanel">
                                                                    <h5>Đánh giá sản phẩm</h5>
                                                                <%
                                                                CustomerDAO cus_dao = new CustomerDAO();
                                                                AccountDAO acc_dao = new AccountDAO();
                                                                FeedbackDAO fb_dao=new FeedbackDAO();
                                                                ProductDAO pro_dao=new ProductDAO();
                                                                ArrayList<Feedback> feedList = (ArrayList<Feedback>) request.getAttribute("lFeedBack");
                                                                for (Feedback fback : feedList) {
                                                                    Customer cus = cus_dao.getById(fback.getCusId());
                                                                    Account accCustomer = acc_dao.getById(cus.getAccId()); 
                                                                Product pro=pro_dao.getById(fback.getProId()); %>
                                                                <div class="row d-flex py-3 w-100 relative">
                                                                    <div class="col-md-1 text-center">
                                                                        <img class="feedback__img" src="<%= accCustomer.getAccAvarUrl()%>" alt="alt"/>
                                                                    </div>
                                                                    <div class="col-md-11">
                                                                        <div class="mb-1 d-flex justify-content-between">
                                                                            <div>
                                                                                <%
                                                                                     for (int i = 0; i < fback.getFeedRating(); i++) {%>
                                                                                <i class="fa-solid fa-star fa-xs" style="color: #FFD43B;"></i>
                                                                                <%
                                                                                 }%>
                                                                            </div>
                                                                            <p class="font-italic" style="font-size: 12px"><%= fback.getFeedCreatedDate()%></p>
                                                                        </div>
                                                                        <h6 class="mb-2"><%= accCustomer.getAccName()%></h6>

                                                                        <p class="text-justify" style="font-size: 13px"><%= fback.getFeedContent()%></p>
                                                                        <a href="mkt_feedbackdeltai?proId=<%= fback.getProId()%>" class="feedback__product">
                                                                            <h6 class="pro_name"><%= pro.getProName()%></h6>
                                                                        </a>

                                                                    </div>


                                                                </div>

                                                                <%    
                                                                }
                                                                %>

                                                            </div>                                                           
                                                        </table>

                                                        <div class="page" id="page">
                                                            <c:forEach begin="1" end="${numberPage}" var="i">
                                                                <c:if test="${page == i}">
                                                                    <a href="#" class="page__number--chosen" onclick="movePage(${i})"><p>${i}</p></a>
                                                                        </c:if>

                                                                <c:if test="${page != i}">
                                                                    <a href="#" class="page__number" onclick="movePage(${i})"><p>${i}</p></a>
                                                                        </c:if>
                                                                    </c:forEach>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- Table Product -->

                    </div>

                </div>
            </div>
            <!-- Product List End -->


        </main>

        <!-- SCRIPTS -->
        <!-- Global Required Scripts Start -->
        <script>
            function movePage(page) {
                event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>

                var textSearch = document.getElementById('textSearch').value;
                var dateBegin = document.getElementById('dateBegin').value;
                var dateEnd = document.getElementById('dateEnd').value;
                var quantityPost = document.getElementById('quantityPost').value;

                $.ajax({
                    url: "mkt_feedbacklist",
                    type: 'POST',
                    data: {// send data      
                        option: 'split',
                        textSearch: textSearch,
                        dateBegin: dateBegin,
                        dateEnd: dateEnd,
                        quantityPost: quantityPost,

                        page: page
                    },
                    success: function (data) {
                        // Xóa hết nội dung trong phần tử có id là "data"
                        $("#data").empty();

                        // Thêm dữ liệu HTML mới vào phần tử có id là "data"
                        $("#data").append(data);
                    }
                });

                // Lấy tham chiếu đến tất cả các thẻ <a> trong phần tử có id là "page"
                var pageLinks = $("#page a");
                // Lặp qua tất cả các thẻ <a> để xóa lớp "page__number--chosen" khỏi thẻ hiện tại
                pageLinks.removeClass("page__number--chosen").addClass("page__number");

                // Thêm lớp "page__number--chosen" cho thẻ <a> được bấm
                pageLinks.eq(page - 1).addClass("page__number--chosen").removeClass("page__number");
            }
        </script>
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
        <script src="assets/js/widgets.js">
        </script>
        <script src="assets/js/clients.js">
        </script>
        <script src="assets/js/Chart.Financial.js">
        </script>
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
