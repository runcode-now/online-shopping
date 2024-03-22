<%-- 
    Document   : m_product-details
    Created on : Feb 6, 2024, 7:32:05 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.HttpServlet" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Thêm sản phẩm</title>
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



        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <style type="text/css">

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
            label {
                font-weight: bold;
            }
            .ms-quick-stats p {
                margin-bottom: 0;
                font-size: 1rem;
                font-weight: 700;
            }

        </style>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Bạn có chắc chắn xóa sản phẩm này?")) {
                    window.location = "mkt_delete?productId=" + id;
                }
            }
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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


            <!-- Body Content Wrapper -->
            <div class="ms-content-wrapper">
                <div class="row">

                    <div class="col-md-12">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb pl-0">
                                <li class="breadcrumb-item"><a href="mkt_dashboard"><i class="material-icons">home</i> Thống kê</a></li>
                                <li class="breadcrumb-item active" aria-current="page"><a href="mkt_addproduct">Thêm sản phẩm</a></li>
                            </ol>
                        </nav>
                    </div>



                    <div class=" col-md-6">
                        <div class="ms-panel ms-panel-fh">
                            <div class="ms-panel-header">
                                <h6>Điền thông tin sản phẩm</h6>
                            </div>
                            <div class="ms-panel-body">      
                                <div class="form-row">

                                    <div class="col-md-12 mb-3">
                                        <form id="myForm" action="mkt_addproduct">
                                            <label for="categoryProduct">Thể loại</label>
                                            <div class="input-group">
                                                <select class="form-control" id="categoryProduct" name="getCateProductId" required>
                                                    <option value="0">Chọn Thể loại</option>   
                                                    <c:forEach items="${requestScope.listCateProduct}" var="lcp">
                                                        <option value="${lcp.cateProId}" ${lcp.cateProId == compareCateProId?'selected':''}>${lcp.cateProName}</option>   
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </form>
                                    </div>

                                    <div class="col-md-12 mb-3">
                                        <form action="mkt_addproduct" method="post" enctype="multipart/form-data">
                                            <label for="categoryProductDetails">Chi tiết thể loại</label>
                                            <div class="input-group">
                                                <select class="form-control" id="categoryProductDetails"  name="cateProDetails" required>
                                                    <option>Chọn chi tiết thể loại</option>   
                                                    <c:forEach items="${requestScope.listCateProDetails}" var="lcpd">
                                                        <option value="${lcpd.cateProDeId}" ${lcpd.cateProDeId == cateProDetails?'selected':''}>${lcpd.cateProDeName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                    </div>

                                    <div class="col-md-12 mb-3">
                                        <label for="ProductName">Tên sản phẩm</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="ProductName" name="proName" placeholder="Nhập tên sản phẩm" value="${getProName}" required>
                                        </div>
                                    </div>



                                    <div class="col-md-6 mb-3">
                                        <label for="numberInput1">Giá bán(VNĐ)</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="numberInput1" name="proPrice" placeholder="Nhập giá bán" value="${proPrice}" required>
                                        </div>
                                        <h6 style="color:red">${errorInputPrice}</h6>

                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="numberInput2">Giá gốc(VNĐ)</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="numberInput2" name="proCost" placeholder="Nhập giá gốc" value="${proCost}" required>
                                        </div>
                                        <h6 style="color:red">${errorInputCost}</h6>

                                    </div>

                                    <div class="col-md-12 mb-4">
                                        <label for="ProImgDefault1">Ảnh mặc định</label>
                                        <div class="custom-file">
                                            <input type="file" accept="image/*" class="custom-file-input" name="proImgDefault" onchange="previewImage(event, 'imagePreview0')"  required>

                                            <br>
                                            <label class="custom-file-label" for="ProImgDefault1">Tải lên ảnh mặc định</label>
                                        </div>
                                    </div>
                                    <div class="col-md-12 mb-4">
                                        <label for="ProImage1">Ảnh bên lề 1</label>
                                        <div class="custom-file">
                                            <input type="file" accept="image/*" class="custom-file-input" name="proImg1" onchange="previewImage(event, 'imagePreview1')" required>
                                            <br>
                                            <label class="custom-file-label" for="ProImage1">Tải lên ảnh bên lề 1</label>
                                        </div>
                                    </div>
                                    <div class="col-md-12 mb-4">
                                        <label for="ProImage2">Ảnh bên lề 2</label>
                                        <div class="custom-file">
                                            <input type="file" accept="image/*" class="custom-file-input" name="proImg2" onchange="previewImage(event, 'imagePreview2')" required>
                                            <br>
                                            <label class="custom-file-label" for="ProImage2">Tải lên ảnh bên lề 2</label>
                                        </div>
                                    </div>
                                    <div class="col-md-12 mb-4">
                                        <label for="ProImage3">Ảnh bên lề 3</label>
                                        <div class="custom-file">
                                            <input type="file" accept="image/*" class="custom-file-input" name="proImg3" onchange="previewImage(event, 'imagePreview3')" required>
                                            <br>
                                            <label class="custom-file-label" for="ProImage3">Tải lên ảnh bên lề 3</label>
                                        </div>
                                    </div>
                                </div>



                            </div>
                        </div>
                    </div>


                    <div class="col-xl-6 col-md-12">

                        <div class="ms-panel ms-panel-fh">
                            <div class="row">
                                <div class="col-md-12">

                                    <div class="ms-panel-header">
                                        <h6>Ảnh sản phẩm</h6>
                                    </div>
                                    <div class="ms-panel-body">
                                        <div id="imagesSlider" class="ms-image-slider carousel slide" data-ride="carousel">
                                            <div class="carousel-inner">
                                                <div class="carousel-item active">
                                                    <img class="d-block w-100" id="imagePreview0" src="#" alt="Ảnh mặc định" style="height: 300px;">
                                                </div>
                                                <div class="carousel-item">
                                                    <img class="d-block w-100" id="imagePreview1" src="#" alt="Ảnh bên lề 1" style="height: 300px;">
                                                </div>
                                                <div class="carousel-item">
                                                    <img class="d-block w-100" id="imagePreview2" src="#" alt="Ảnh bên lề 2" style="height: 300px;">
                                                </div>
                                                <div class="carousel-item">
                                                    <img class="d-block w-100" id="imagePreview3" src="#" alt="Ảnh bên lề 3" style="height: 300px;">
                                                </div>

                                            </div>
                                            <ol class="carousel-indicators" style="overflow: hidden;">
                                                <li data-target="#imagesSlider" data-slide-to="0"><img id="imagePreview0" src="#" value="${requestScope.filePartD}"  style="max-width: 100%;"></li>
                                                <li data-target="#imagesSlider" data-slide-to="1"><img id="imagePreview1" src="#"  style="max-width: 100%;"></li>
                                                <li data-target="#imagesSlider" data-slide-to="2"><img id="imagePreview2" src="#" style="max-width: 100%;"></li>
                                                <li data-target="#imagesSlider" data-slide-to="3"><img id="imagePreview3" src="#"  style="max-width: 100%;"></li>
                                            </ol>
                                        </div>
                                    </div>

                                    <div class="col-md-12 mb-3">
                                        <label for="validationCustom12">Kích thước</label>
                                        <div class="ms-quick-stats">
                                            <div class="row">
                                                <%
                                    int cnt = 2;
                                                %>
                                                <c:forEach items="${requestScope.listProductSize}" var="lps">
                                                    <% cnt++; %>
                                                    <c:if test="${lps.cateSizeId <= 3}">
                                                        <div class="ms-stats-grid col-lg-4" style="border:none;">
                                                            <p style="color:#ff1d1d">${lps.cateSizeName}</p>
                                                            <p class="ms-text-dark"><input class="form-control my-1" name="proSizeQuantity${lps.cateSizeName}" style="width: 40%;height: 25px; margin: 0 auto; text-align: center;font-weight: bold" type="text" id="numberInput<%= cnt %>" placeholder="" value="" required> </p>
                                                            <span class="badge badge-pill badge-gradient-secondary" style="font-size: 8px;">Số lượng</span>
                                                            <input type="hidden" name="cateSizeId${lps.cateSizeId}" value="${lps.cateSizeId}">
                                                        </div>
                                                        <input type="hidden" name="checkCateSize" value="fontsize"/>

                                                    </c:if>
                                                    <c:if test="${lps.cateSizeId >= 4 && lps.cateSizeId <= 10}">
                                                        <div class="ms-stats-grid col-lg-3" style="border:none;">
                                                            <p style="color:#ff1d1d">${lps.cateSizeName}</p>
                                                            <p class="ms-text-dark"><input class="form-control my-1" name="proSizeQuantity${lps.cateSizeName}" style="width: 50%;height: 25px; margin: 0 auto; text-align: center;font-weight: bold" type="text" id="numberInput<%= cnt %>" placeholder="" value="" required> </p>
                                                            <span class="badge badge-pill badge-gradient-secondary" style="font-size: 8px;">Số lượng</span>
                                                            <input type="hidden" name="cateSizeId${lps.cateSizeId}" value="${lps.cateSizeId}">
                                                        </div>
                                                        <input type="hidden" name="checkCateSize" value="sizenumber"/>

                                                    </c:if>
                                                    <c:if test="${lps.cateSizeId > 10}">
                                                        <div class="ms-stats-grid col-lg-12" style="border:none;">
                                                            <p style="color:#ff1d1d; text-transform: uppercase;">${lps.cateSizeName}</p>
                                                            <p class="ms-text-dark"><input class="form-control my-1" name="proSizeQuantity${lps.cateSizeName}" style="width: 50%;height: 25px; margin: 0 auto; text-align: center;font-weight: bold;" type="text" id="numberInput<%= cnt %>" placeholder="" value="" required> </p>
                                                            <span class="badge badge-pill badge-gradient-secondary" style="font-size: 8px;">Số lượng</span>
                                                            <input type="hidden" name="cateSizeId${lps.cateSizeId}" value="${lps.cateSizeId}">
                                                        </div>
                                                        <input type="hidden" name="checkCateSize" value="none"/>

                                                    </c:if>
                                                </c:forEach>
                                                <h6 style="color:red" class="col-lg-12 text-center">${errorInputSize}</h6>

                                            </div>
                                        </div>
                                        <div class="col-md-12 mb-3">
                                            <label for="description">Mô tả</label>
                                            <div class="input-group">
                                                <textarea rows="5" id="description" name="proDescription" class="form-control" placeholder="Thêm mô tả" value="${getProDescription}" required></textarea>
                                            </div>
                                        </div>

                                        <div class="col-md-12 mb-3">
                                            <label for="status">Trạng thái</label>
                                            <div class="input-group">

                                                <select class="form-control" name="proStatus" id="status">
                                                    <option style="color: #20c914;" value="on" selected>Hoạt động</option>            
                                                    <option style="color: #838383;" value="off">Dừng bán</option>
                                                </select>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>     
                </div>
                <div class="col-lg-12 text-center">
                    <input type="hidden" name="checkCateSize" value="${checkCateSize}"/>
                    <button type="submit" class="btn btn-success mx-5">Thêm sản phẩm</button>
                </div>
                <input type="hidden" name="cateProId" value="${compareCateProId}"/>
                </form>
        </main>



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

        <script>
                                                function previewImage(event, previewId) {
                                                    var reader = new FileReader();
                                                    reader.onload = function () {
                                                        var preview = document.getElementById(previewId);
                                                        preview.src = reader.result;
                                                    };
                                                    reader.readAsDataURL(event.target.files[0]);
                                                }

                                                document.getElementById("categoryProduct").addEventListener("change", function () {
                                                    var form = document.getElementById("myForm"); // Lấy đối tượng form
                                                    form.submit(); // Gửi yêu cầu POST đến servlet
                                                });

                                                // Mảng chứa các ID của các trường input cần xử lý
                                                var inputIds = ["numberInput1", "numberInput2", "numberInput3", "numberInput4", "numberInput5", "numberInput6", "numberInput7", "numberInput8", "numberInput9"];

                                                // Lặp qua mảng các ID và gán hàm xử lý sự kiện cho từng trường input
                                                inputIds.forEach(function (inputId) {
                                                    var input = document.getElementById(inputId);

                                                    // Lắng nghe sự kiện khi người dùng nhập vào trường input
                                                    input.addEventListener("input", function (event) {
                                                        // Lấy giá trị hiện tại của trường input
                                                        var value = event.target.value;

                                                        // Loại bỏ tất cả các ký tự không phải số
                                                        var cleanValue = value.replace(/[^0-9]/g, "");

                                                        // Kiểm tra nếu giá trị là số âm hoặc chứa chữ, gán giá trị rỗng cho trường input
                                                        if (cleanValue < 1 || cleanValue !== value || cleanValue > 100000000) {
                                                            event.target.value = "";

                                                        }
                                                    });
                                                });
        </script>
    </body>


</html>

