<!--2-->
<%-- 
    Document   : m_product-details
    Created on : Feb 6, 2024, 7:32:05 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.team3.onlineshopping.model.Account" %>
<%@ page import="com.team3.onlineshopping.model.Customer" %>
<%@ page import="com.team3.onlineshopping.model.Feedback" %>
<%@ page import="com.team3.onlineshopping.dal.CustomerDAO" %>
<%@ page import="com.team3.onlineshopping.dal.AccountDAO" %>
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
        <title>Chi tiết đánh giá</title>
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
        <!-- Foodtech styles -->
        <link href="assets/css/style.css" rel="stylesheet">
        <!-- Favicon -->
        <link rel="icon" type="image/png" sizes="32x32" href="favicon.ico">

        <meta charset="UTF-8">
        <meta name="description" content="Ashion Template">
        <meta name="keywords" content="Ashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=5, user-scalable=1"
              name="viewport" />
        <!--<title>${requestScope.product.proName}</title>-->

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <!--<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">-->
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
        <style type="text/css">
            .feedback__img{
                width: 60px;
                height: 60px;
                border-radius: 50%;
            }
            /*breadcrumb*/
            .breadcrumb-option{
                /*margin-top: 72px;*/
            }

            /*product details*/
            .product-details {
                padding-top: 40px;
            }

            .product__details__text > *:not(:first-child){
                padding-bottom: 20px;
            }
            .product__details__text .rating i{
                color: #FFF;
                margin-right: 0px;
            }
            .product__details__text .rating{
                background-color: #FFA500;
                border-radius: 2rem;
                padding: 2px 5px;
                color: #FFF;
            }
            .product__details__text .sold{
                color: #7b7d85;
                font-size: 14px;
                font-weight: 500;
                line-height: 19px;
            }
            .product__details__text .product__details__price {
                margin-bottom: 0px;
                padding-bottom: 10px;
            }
            .product__details__text .quantity {
                width: 100%!important;
            }
            .product__details__text .quantity>span {
                font-size: 18px;
                width: 140px;
            }
            .product__details__text .product__details__widget {
                padding-bottom: 15px;
                padding-top: 25px;
            }

            .product__details__widget .size__btn label{
                padding: 2px 20px;
                border: 1px solid #EEE;
                border-radius: 3px;
            }

            .product__details__widget ul li span {
                margin-top: 2px;
                font-size: 18px;
            }

            .product__details__button {
                overflow: unset;
            }
            .product__details__button .product__cart-btn {
                position: relative;

            }

            .product__details__button .cart-btn{
                margin-top: 20px;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%,120%);
                width: 50%;
                text-align: center;
                font-size: 16px;
            }

            .product__details__tab .nav-tabs{
                margin-bottom: 40px;
            }

            .product__details__tab .nav-tabs,
            .product__details__tab .nav-tabs .nav-link{
                border: none;
            }

            .tab-content .tab-pane h5 {
                color: #666666;
                font-weight: bolder;
                margin-bottom: 24px;
            }

            .product__details__button .pro-quantity{
                height: 50px;
                width: 150px;
                border: 1px solid #ebebeb;
                border-radius: 50px;
                padding: 0 20px;
                overflow: hidden;
                display: inline-block;
            }

            .product__details__button .pro-quantity input{
                color: #666666;
                font-weight: 500;
                border: none;
                width: 84px!important;
                text-align: center;
                height: 48px!important;
            }
            .product__details__widget ul li .size__btn label.active {
                color: #FFF;
                background-color: #ca1515;
            }

            /*================================================================*/
            /*================================================================*/
            .search {
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
                transition: .15s ease-in-out,box-shadow .15s ease-in-out;

            }
            .search:focus {
                /*border-color: #007bff;*/
                box-shadow: 0px 0px 4px 4px rgba(0,0,0,0.1);

                /*padding: 10px;*/
            }
            .search--container {
                position: relative;

            }
            .search--container i{
                font-size: 1.3em;
                /*font-weight: 1800;*/
            }

            .search--icon {
                position:absolute;
                font-size: 1px;
                top: 30%;
                right: 15px;
                transform: translateY(-50%);
            }

            .header_top__right {
                position: relative;
            }

            .header_main__myCart .header__login{
                color: #000;
                font-style: italic;
                font-weight: 400;
            }

            .header_main__myCart .header__login:hover{
                color: #CCC;
            }

            /*==========user login=============*/
            .header_top__right{
                position: static!important;
                width: 40px;
                height: 40px;
                overflow: hidden;
                border: 1px solid #ccc;
                border-radius: 50%;
                margin-top: 1rem;
            }

            .header_right{
                position: relative;
                height: 70px;
            }

            .header_right:hover .login-drop{
                opacity: 1;
                visibility: unset;
            }
            .header_top__right .login-drop {
                position: absolute;
                top: 70px;
                right: 0;
                background: #fff;
                z-index: 90;
                min-width: 222px;
                box-shadow: 0 10px 20px rgb(0 0 0 / 15%);
                padding: 10px 0;
                opacity: 0;
                visibility: hidden;
            }
            .header_top__right .login-drop a {
                color: #000;
                padding: 0 20px;
                margin: 15px 0;
                font-size: 16px;
                display: block;
            }
            .header_top__right .login-drop a:hover {
                color: #999;
            }

            .header_top__right .login-drop .login-drop__User {
                border-bottom: 1px #CCC solid;
                font-weight: bold;
                padding-bottom: 10px;
                margin: 5px 0;
            }
            .custom-file-label::after {
                content: "Chọn ảnh"!important;
            }
            .owl-carousel .owl-item img {
                display: block;
                width: 100%;
                height: 336.23px;
            }
            .product__details__pic__left .pt img {
                min-width: 100%;
                height: 100.45px;
            }
            .product__details__widget ul li .size__btn label {
                font-size: 14px;
                color: #666666;
                text-transform: uppercase;
                cursor: pointer;
                margin-right: 10px;
                display: inline-block;
                margin-bottom: 10px;
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
                padding-bottom: 0px;
                border-radius: 2px;
                border-left: 4px solid #47d864;
                box-shadow: 0 5px 8px rgba(0, 0, 0, 0.08);
                animation: slideInLeft ease 2s, fadeOut linear 5s 5s forwards;
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

      <!-- Sidebar Navigation Left Begin -->
        <aside id="ms-side-nav" class="side-nav fixed ms-aside-scrollable ms-aside-left">
            <!-- Logo -->
            <div class="logo-sn ms-d-block-lg">
                <img style="max-width: 255px;" src="https://scontent.fsgn2-4.fna.fbcdn.net/v/t1.15752-9/423568413_220611387798284_6265991015599051178_n.png?_nc_cat=101&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=6xm1kK9w87wAX9TEvv_&_nc_ht=scontent.fsgn2-4.fna&oh=03_AdTwMqeyx_8lcqp09mOKsXlRojFzIL02Pvvb9Y3q1WDgZA&oe=65FA4050" alt="logo">
            </div>
            <br>
            <!-- Navigation -->
            <ul class="accordion ms-main-aside fs-14" id="side-nav-accordion" style="font-family: Roboto, sans-serif;">
                <!-- Dashboard -->
                <li class="menu-item">
                    <a href="mkt_dashboard"> <span><i class="fas fa-columns" style="width: 14px; height: 14px;"></i>Thống kê</span>
                    </a>
                </li>
                <!-- /Dashboard -->

                <!-- Product Begin -->
                <li class="menu-item">
                    <a href="#" class="has-chevron active" data-toggle="collapse" data-target="#product" aria-expanded="false" aria-controls="product1"> <span><i class="fa fa-archive fs-16"></i>Quản lý sản phẩm</span>
                    </a>
                    <ul id="product" class="collapse" aria-labelledby="product" data-parent="#side-nav-accordion">
                        <li> <a href="mkt_productlist">Danh sách sản phẩm</a>
                        </li>  
                        <li> <a href="mkt_addproduct">Thêm sản phẩm</a>
                        </li>
                        <li> <a href="mkt_feedbacklist" class="active">Đánh giá sản phẩm</a>
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


            <!-- Body Content Wrapper -->

            <div class="ms-content-wrapper">
                <div class="row">

                    <div class="col-md-12">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb pl-0">
                                <li class="breadcrumb-item"><a href="mkt_dashboard"><i class="material-icons">home</i> Thống kê</a></li>
                                <li class="breadcrumb-item"><a href="mkt_feedbacklist">Danh sách phản hồi</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Chi tiết phản hồi sản phẩm</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="my-3 mx-4" ><a href="mkt_feedbacklist"><i class="fa fa-chevron-circle-left" style="font-size: 2rem;" aria-hidden="true"></i></a></div>
                    <div class="col-md-12">
                        <div class="ms-panel">
                            <div class="ms-panel-header">
                                <h6>Chi tiết sản phẩm</h6>
                            </div>
                            <div class="container">

                                <div class="row">
                                    <div class="col-lg-3" >
                                        <h6 class="text-center mb-2 font-weight-bold">Ảnh mặc định</h6>
                                        <div style="width: 100%;">
                                            <img style="width: 100%; height: 336.23px" src="${product.proImgDefault}"/>
                                        </div>
                                    </div>
                                    <div class="col-lg-5" style="border-left: 1px solid #ccc">
                                        <h6 class="text-center mb-2 font-weight-bold">Ảnh bên lề</h6>

                                        <div class="product__details__pic">

                                            <div class="product__details__pic__left product__thumb nice-scroll">
                                                <c:forEach items="${requestScope.productImage}" var="proImg" varStatus="loop">
                                                    <c:set var="count" value="${loop.index + 1}"/>
                                                    <a class="pt active" href="#product-${loop.index + 1}">
                                                        <img src="${proImg.proImgUrl}" alt="">
                                                    </a>
                                                </c:forEach>
                                            </div>
                                            <div class="product__details__slider__content">
                                                <div class="product__details__pic__slider owl-carousel">
                                                    <c:forEach items="${requestScope.productImage}" var="proImg" varStatus="loop">
                                                        <c:set var="count" value="${loop.index + 1}"/>
                                                        <img data-hash="product-${count}" class="product__big__img" src="${proImg.proImgUrl}" alt="">
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--========================product information======================-->

                                    <div class="col-lg-4" style="border-left: 1px solid #ccc">
                                        <h6 class="text-center mb-2 font-weight-bold">Thông tin chi tiết</h6>
                                        <form action="pub_addcart" method="post" id="addToCartForm">
                                            <div class="product__details__text text-center">
                                                <h3>${requestScope.product.proName}</h3>
                                                <div class="product-details--info">
                                                    <span class="mr-3 rating"><i class="fa-solid fa-star"></i> ${requestScope.product.proRating}</span>
                                                    <span class="mr-3 sold"> Đã Bán ${requestScope.product.proSold} </span>
                                                </div>

                                                <fmt:formatNumber value="${requestScope.product.proPrice}" pattern="###,###" var="productPrice"/>
                                                <fmt:formatNumber value="${requestScope.product.proCost}" pattern="###,###" var="productCost"/>
                                                <div class="product__details__price"> ${productPrice}đ <span> ${productCost}đ</span></div>
                                            </div>

                                        </form>
                                    </div>  
                                </div>


                            </div>
                        </div>
                    </div>

                    <div class=" col-md-12">
                        <div class="ms-panel ms-panel-fh">
                            <div class="ms-panel-body">
                                <div class="tab-pane" id="tabs-2" role="tabpanel">
                                    <h5>Đánh giá sản phẩm</h5>
                                    <%
                                    CustomerDAO cus_dao = new CustomerDAO();
                                    AccountDAO acc_dao = new AccountDAO();
                                    ArrayList<Feedback> feedList = (ArrayList<Feedback>) request.getAttribute("feedback");
                                    for (Feedback fback : feedList) {
                                        Customer cus = cus_dao.getById(fback.getCusId());
                                        Account accCustomer = acc_dao.getById(cus.getAccId()); %>
                                    <div class="row d-flex px-5 py-3">
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
                                        </div>
                                    </div>
                                    <%    
                                    }
                                    %>

                                </div>
                            </div>
                        </div>
                    </div>

                    
                </div>                            
            </div>



        </main>

        <!--pop-up notification-->

        <!--pop-up notification-->

        <!-- SCRIPTS -->
        <!-- Global Required Scripts Start -->
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/perfect-scrollbar.js"></script>
        <script src="assets/js/jquery-ui.min.js"></script>
        <!-- Global Required Scripts End -->

        <!-- Page Specific Scripts Start -->
        <script src="assets/js/slick.min.js"></script>
        <script src="assets/js/moment.js"></script>
        <script src="assets/js/jquery.webticker.min.js"></script>
        <script src="assets/js/Chart.bundle.min.js"></script>
        <script src="assets/js/Chart.Financial.js"></script>
        <!-- Page Specific Scripts Finish -->

        <!-- Foodtech core JavaScript -->
        <script src="assets/js/framework.js"></script>

        <!-- Settings -->
        <script src="assets/js/settings.js"></script>
        <!------>
        <script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.0.0/dist/tf.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@tensorflow-models/body-pix@1.0.0"></script>

        <script type="text/javascript" src="Content/pc/js/jquery.min.js"></script>
        <script type="text/javascript" src="Content/pc/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="Content/pc/js/popper.min.js"></script>
        <script type="text/javascript" src="Content/pc/js/bootstrap.min.js"></script>

        <script src="js/jquery-3.3.1.min.js"></script>
        <!--<script src="js/bootstrap.min.js"></script>-->
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.nicescroll.min.js"></script>
        <script src="js/main.js"></script>

    </body>


</html>

