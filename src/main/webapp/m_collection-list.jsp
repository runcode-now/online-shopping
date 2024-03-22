<!--m_collection-list-->
<%-- 
    Document   : m_product-list
    Created on : Feb 5, 2024, 12:18:16 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Danh sách bộ sưu tập</title>
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
        <style>
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
            .long-text {
                width: 20%;
                overflow: hidden;

                white-space: pre-wrap;
                overflow-wrap: break-word;
            }
            .paging--fix {
                height: 170px;
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
                margin: 0;
            }
            .hide {
                display: none;
            }
            .title{
                padding: 10px 0;
                width: 100%;
                height: 100%;
                display: flex;
                gap: 30px;
            }
            .title__img{
                flex:2;
                display: flex;
                align-items: center;
            }
            .title__img img{
                width: 100%;
                margin: 0;
                border-radius: 2px;
                max-width: 100%;
            }
            .title_content{
                flex: 3;
                display: flex;
                justify-content: left;
                align-items: center;
            }
            .view--product img{
                border-radius: 0!important;
                width: 250px;
                height: 100px;
                max-height: 100px;
                max-width: 250px;

            }
            .icon--button {
                background: none;
                border:0;
                padding: 0;
                cursor:pointer;
                text-align: center;
                align-items: center;
                justify-content: center;
            }
            .icon--button i{
                margin-left: 8px;
            }

            .icon--button i{
                margin-left: 8px;
            }
        </style>
        <script>
            function change() {
                document.getElementById("status").submit();
            }
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

            <!-- Collection List Begin -->
            <div class="ms-content-wrapper">
                <div class="row">
                    <div class="col-md-12">
                        <!-- Breadcrumb Begin -->
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb pl-0">
                                <li class="breadcrumb-item"><a href="mkt_dashboard"><i class="material-icons">home</i> Thống kê</a></li>

                                <li class="breadcrumb-item active" aria-current="page">Danh sách bộ sưu tập</li>
                            </ol>
                        </nav>
                        <!-- Breadcrumb End -->


                        <!-- Table Collection -->
                        <div class="ms-panel">
                            <div class="ms-panel-header">
                                <h6>Danh sách bộ sưu tập</h6>
                            </div>
                            <div class="ms-panel-body">
                                <div class="table-responsive">
                                    <div>
                                        <c:if test="${back ne null}">
                                            <a href="mkt_collectionlist"><i class="fa fa-chevron-left" aria-hidden="true" style="font-size:1rem;"></i> ${back}</a>
                                        </c:if>
                                        <div class="d-flex justify-content-between  mx-2">

                                            <div class="dropdown" style="display: flex">


                                                <div class="mx-3">
                                                    <a class="btn btn-info dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        Trạng thái
                                                    </a>
                                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                                        <a class="dropdown-item" href="mkt_collectionlist?collectionStatus=&searching=${searching}">Tất cả</a>  
                                                        <a class="dropdown-item" href="mkt_collectionlist?collectionStatus=on&searching=${searching}" style="color: green;">Đang hoạt động</a>  
                                                        <a class="dropdown-item" href="mkt_collectionlist?collectionStatus=off&searching=${searching}" style="color: #dc3545;">Đã dừng</a>  

                                                    </div>
                                                    <div>
                                                        <h6 class="text-success">${msgOn}</h6>
                                                        <h6 class="text-danger">${msgOff}</h6>

                                                    </div>

                                                </div>


                                            </div>

                                            <div class="search--container my-3">
                                                <form action="mkt_collectionlist" method="get">
                                                    <input type="text" name="searching" class="search" placeholder="Tìm kiếm..." value="${searching}" />

                                                </form>
                                            </div>

                                        </div>


                                        <table class="table table-hover w-100 table-red text-center">

                                            <thead>

                                                <tr>
                                                    <th scope="col" style="width: 5%">STT</th>

                                                    <th  scope="col" style="width: 25%">Slider</th>
                                                    <th scope="col" style="width: 20%">Tên bộ sưu tập</th>
                                                    <th scope="col" style="width: 20%">Người tạo</th>
                                                    <th scope="col" style="width: 10%">Ngày tạo</th>
                                                    <th scope="col" style="width: 10%">Trạng thái</th>
                                                    <th scope="col" style="width: 10%">Chi tiết</th>


                                                </tr>
                                            </thead>
                                            <tbody>
                                            <div>
                                                <h5 style="text-align: center">${msgNotFound}</h5>
                                                <h5 style="text-align: center">${msgFound}</h5>
                                            </div>

                                            <% int cnt = (Integer) request.getAttribute("startNumOr");                                        
                                            %>
                                            <form action="mkt_collectionlist" method="post">
                                                <c:forEach items="${requestScope.listCollection}" var="lc">
                                                    <!--<input type="hidden" name="collectionId" value="${lc.collectionId}"/>-->

                                                    <jsp:useBean class="com.team3.onlineshopping.dal.AccountDAO" id="AccCus"/>
                                                    <jsp:useBean class="com.team3.onlineshopping.dal.NewsDAO" id="News"/>

                                                    <tr class="my-5">
                                                        <% cnt++; %>
                                                        <td class="align-middle"><%= cnt %></td>

                                                        <td style="max-width: 0;">
                                                            <div class="view--product">
                                                                <c:set var="accountId" value="${lc.newsId}" />
                                                                <fmt:parseNumber var="IdParsed" type="number" value="${lc.newsId}" />
                                                                <c:set var="newsImgUrl1" value="${News.getById(IdParsed).newsImgUrl}" />

                                                                <img src="${newsImgUrl1}" alt="alt"/>
                                                            </div>
                                                        </td>

                                                        <td class="text-left">${lc.collectionTitle}</td>

                                                        <td class="align-middle font-italic">
                                                            <c:set var="accountId" value="${lc.employeeId}" />
                                                            <fmt:parseNumber var="IdParsed" type="number" value="${lc.employeeId}" />
                                                            <c:set var="accountName" value="${AccCus.getAccByEmpId(IdParsed).accName}" />
                                                            <c:out value="${accountName}" />
                                                        </td>

                                                        <td class="align-middle">${lc.collectionCreatedDate}</td>

                                                        <c:set var="check" value="on" />
                                                        <c:choose>  
                                                            <c:when test="${lc.collectionStatus == check}">
                                                                <td class="text-success bold align-middle">Hoạt động</td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td class="bold text-danger align-middle">Đã dừng</td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <td class="text-center align-middle">
                                                            <span>
                                                                <button type="submit" name="collectionId" value="${lc.collectionId}" class="icon--button">
                                                                    <i class="fas fa-eye" style="color: #b1a9a9; font-size: 14px;"></i>
                                                                </button>
                                                                <input type="hidden" name="handleColDe" value="viewColDe"/>
                                                            </span>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </form>
                                            </tbody>
                                        </table>

                                        <nav aria-label="..." class="paging--fix">

                                            <ul class="pagination justify-content-lg-end  mt-5">


                                                <c:choose>
                                                    <c:when test="${numberPage == 1}">
                                                        <li class="page-item disabled">
                                                            <a class="page-link" href="#" tabindex="-1">Trang trước</a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li class="page-item">
                                                            <a class="page-link" href="mkt_collectionlist?numberPage=${(numberPage - 1)}&collectionStatus=${collectionStatus}&searching=${searching}">Trang trước</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:if test="${endPage == 0}">
                                                    <c:set var="endPage" value="${endPage + 1}" />
                                                </c:if>
                                                <c:if test="${endPage > 0}">
                                                    <li class="page-item ${numberPage == 1?"active":""}">
                                                        <a class="page-link" href="mkt_collectionlist?numberPage=1&collectionStatus=${collectionStatus}&searching=${searching}">1</a>
                                                    </li>
                                                </c:if>

                                                <c:if test="${endPage > 1}">
                                                    <li class="page-item ${numberPage == 2?"active":""}">
                                                        <a class="page-link" href="mkt_collectionlist?numberPage=2&collectionStatus=${collectionStatus}&searching=${searching}">2</a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${endPage > 2}">

                                                    <li class="page-item ${numberPage == 3?"active":"}"}">

                                                        <a class="page-link" href="mkt_collectionlist?numberPage=3&collectionStatus=${collectionStatus}&searching=${searching}>3</a>
                                                           </li>
                                                        </c:if>
                                                        <c:if test="${numberPage > 3}">


                                                            <div style="position: relative; top: 20px; margin: 0 5px;">...</div>
                                                            <li class="page-item ${numberPage == numberPage?"active":""}">
                                                                <a class="page-link" href="mkt_collectionlist?numberPage=${numberPage}&collectionStatus=${sliderStatus}&searching=${searching}">${numberPage}</a>
                                                            </li>
                                                        </c:if>


                                                        <c:choose>
                                                            <c:when test="${numberPage == endPage}">
                                                                <li class="page-item disabled">
                                                                    <a class="page-link" href="#" tabindex="-1">Trang sau</a>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="page-item">
                                                                    <a class="page-link" href="mkt_collectionlist?numberPage=${(numberPage + 1)}&collectionStatus=${collectionStatus}&searching=${searching}">Trang sau</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>

                                            </ul>
                                            <h6>Hiển thị trang ${numberPage} trên ${endPage} trang</h6>
                                        </nav>
                                        <hr style="font-weight: 900;">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Table Collection end -->

                    </div>

                </div>
            </div>
            <!-- Product List End -->

            <!--pop-up notification-->
            <div id="toast" class="${status != null ? '' : 'hide'}">
                <div class="toast">
                    <div class="toast__icon">
                        <div class="fas fa-check-circle"></div>
                    </div>
                    <div class="toast__body">
                        <p class="toast__body--msg">
                            <c:if test="${status eq 'successDel'}">Đã xóa thành công</c:if>
                            <c:if test="${status eq 'successAdd'}">Đã thêm thành công</c:if>
                        </p>
                    </div>
                </div>
            </div>
            <!--pop-up notification-->


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
