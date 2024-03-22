<%-- 
    Document   : Cus_CartContact
    Created on : Jan 17, 2024, 8:31:09 PM
    Author     : minhb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.team3.onlineshopping.model.Account" %>
<%@ page import="com.team3.onlineshopping.model.Customer" %>
<%@ page import="com.team3.onlineshopping.dal.CustomerDAO" %>
<%@ page import="com.team3.onlineshopping.model.Cart" %>
<%@ page import="com.team3.onlineshopping.dal.CartDAO" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.HttpServlet" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ashion Template">
        <meta name="keywords" content="Ashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=5, user-scalable=1"
              name="viewport" />
        <title>Thanh toán</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
        <!-- script -->
        <script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.0.0/dist/tf.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@tensorflow-models/body-pix@1.0.0"></script>

        <script type="text/javascript" src="Content/pc/js/jquery.min.js"></script>
        <script type="text/javascript" src="Content/pc/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="Content/pc/js/popper.min.js"></script>
        <script type="text/javascript" src="Content/pc/js/bootstrap.min.js"></script>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-mQmPAF+4hvJl65v9xWW+BBsVsRDmZDJeW/h+qzZymEDUZOpH5K0pMb2o5MlDzcrB" crossorigin="anonymous">

        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <style type="text/css">
            html {
                scroll-behavior: smooth
            }

            body {
                text-size-adjust: 100%;
                position: relative;
                width: 100%;
                min-height: 100vh
            }

            *,
            ::after,
            ::before {
                box-sizing: border-box
            }

            a:not([class]) {
                text-decoration-skip-ink: auto
            }

            a,
            abbr,
            acronym,
            address,
            applet,
            article,
            aside,
            audio,
            b,
            big,
            blockquote,
            body,
            canvas,
            caption,
            center,
            cite,
            code,
            dd,
            del,
            details,
            dfn,
            div,
            dl,
            dt,
            em,
            embed,
            fieldset,
            figcaption,
            figure,
            footer,
            form,
            h1,
            h2,
            h3,
            h4,
            h5,
            h6,
            header,
            hgroup,
            html,
            i,
            iframe,
            img,
            ins,
            kbd,
            label,
            legend,
            li,
            mark,
            menu,
            nav,
            object,
            ol,
            output,
            p,
            pre,
            q,
            ruby,
            s,
            samp,
            section,
            small,
            span,
            strike,
            strong,
            sub,
            summary,
            sup,
            table,
            tbody,
            td,
            tfoot,
            th,
            thead,
            time,
            tr,
            tt,
            u,
            ul,
            var,
            video {
                font-size: 100%;
                font: inherit;
                margin: 0;
                padding: 0;
                border: 0;
                vertical-align: baseline
            }

            :focus {
                outline: 0
            }

            article,
            aside,
            details,
            figcaption,
            figure,
            footer,
            header,
            hgroup,
            main,
            menu,
            nav,
            section {
                display: block
            }

            ol,
            ul {
                list-style: none
            }

            blockquote,
            q {
                quotes: none
            }

            blockquote:after,
            blockquote:before,
            q:after,
            q:before {
                content: '';
                content: none
            }

            table {
                border-collapse: collapse;
                border-spacing: 0
            }

            td:empty,
            th:empty {
                opacity: 0
            }

            input,
            input:required {
                box-shadow: none
            }

            input:-webkit-autofill,
            input:-webkit-autofill:active,
            input:-webkit-autofill:focus,
            input:-webkit-autofill:hover {
                -webkit-box-shadow: 0 0 0 30px #fff inset
            }

            input[type=search]::-webkit-search-cancel-button,
            input[type=search]::-webkit-search-decoration,
            input[type=search]::-webkit-search-results-button,
            input[type=search]::-webkit-search-results-decoration {
                -webkit-appearance: none;
                -moz-appearance: none
            }

            input[type=search] {
                -webkit-appearance: none;
                -moz-appearance: none;
                -webkit-box-sizing: content-box;
                -moz-box-sizing: content-box;
                box-sizing: content-box
            }

            textarea {
                overflow: auto;
                vertical-align: top;
                resize: vertical
            }

            audio,
            canvas,
            video {
                display: inline-block;
                max-width: 100%
            }

            audio:not([controls]) {
                display: none;
                height: 0
            }

            [hidden] {
                display: none
            }

            a:active,
            a:hover {
                outline: 0
            }

            img {
                border: 0;
                max-width: 100%;
                display: inline-block;
                vertical-align: middle;
                height: auto
            }

            picture {
                display: inline-block
            }

            figure {
                margin: 0
            }

            button,
            input {
                line-height: normal
            }

            button,
            select {
                text-transform: none
            }

            button,
            html input[type=button],
            input[type=reset],
            input[type=submit] {
                -webkit-appearance: button;
                cursor: pointer;
                border: 0;
                background: 0 0
            }

            button::-moz-focus-inner {
                border: 0
            }

            button[disabled],
            html input[disabled] {
                cursor: default
            }

            input[type=checkbox],
            input[type=radio] {
                padding: 0
            }

            input[type=search] {
                -webkit-appearance: textfield;
                -moz-box-sizing: content-box;
                -webkit-box-sizing: content-box;
                box-sizing: content-box
            }

            input[type=search]::-webkit-search-cancel-button,
            input[type=search]::-webkit-search-decoration {
                -webkit-appearance: none
            }

            button::-moz-focus-inner,
            input::-moz-focus-inner {
                border: 0;
                padding: 0
            }

            button {
                border: 0;
                background: 0 0
            }

            textarea {
                overflow: auto;
                vertical-align: top;
                resize: vertical
            }

            table {
                border-collapse: collapse;
                border-spacing: 0
            }

            hr {
                box-sizing: content-box;
                overflow: visible;
                background: #000;
                border: 0;
                height: 1px;
                line-height: 0;
                margin: 0;
                padding: 0;
                page-break-after: always;
                width: 100%
            }

            pre {
                font-family: monospace, monospace;
                font-size: 100%
            }

            a {
                background-color: transparent
            }

            abbr[title] {
                border-bottom: none;
                text-decoration: none
            }

            code,
            kbd,
            samp {
                font-family: monospace, monospace;
                font-size: 100%
            }

            small {
                font-size: 75%
            }

            sub,
            sup {
                font-size: 75%;
                line-height: 0;
                position: relative;
                vertical-align: baseline
            }

            sub {
                bottom: -5px
            }

            sup {
                top: -5px
            }

            button,
            input,
            optgroup,
            select,
            textarea {
                font-family: inherit;
                font-size: 100%;
                line-height: 1;
                margin: 0;
                padding: 0
            }

            button,
            input {
                overflow: visible
            }

            button,
            select {
                text-transform: none
            }

            [type=button],
            [type=reset],
            [type=submit],
            button {
                -webkit-appearance: button
            }

            [type=button]::-moz-focus-inner,
            [type=reset]::-moz-focus-inner,
            [type=submit]::-moz-focus-inner,
            button::-moz-focus-inner {
                border-style: none;
                padding: 0;
                outline: 0
            }

            legend {
                color: inherit;
                white-space: normal;
                display: block;
                border: 0;
                max-width: 100%;
                width: 100%
            }

            fieldset {
                min-width: 0
            }

            body:not(:-moz-handler-blocked) fieldset {
                display: block
            }

            progress {
                vertical-align: baseline
            }

            [type=number]::-webkit-inner-spin-button,
            [type=number]::-webkit-outer-spin-button {
                height: auto
            }

            [type=search] {
                -webkit-appearance: textfield;
                outline-offset: -2px
            }

            [type=search]::-webkit-search-decoration {
                -webkit-appearance: none
            }

            ::-webkit-file-upload-button {
                -webkit-appearance: button;
                font: inherit
            }

            summary {
                display: list-item
            }

            template {
                display: none
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

            /*search*/
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

            #newAddressInput p{
                margin-bottom: 0;
            }
            /*==================pop-up====================*/
            #toast {
                position: fixed;
                top: 100px;
                right: 32px;
                z-index: 10;
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
        <link type="text/css" href="Content/pc/bootstrap.min.css" rel="stylesheet" />
        <link type="text/css" href="Content/pc/style.min.css?v=&lt;%=DateTime.Now.Ticks %>" rel="stylesheet" />
        <link type="text/css" href="Content/pc/custom.css?v=&lt;%=DateTime.Now.Ticks %>" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
        <link href='https://fonts.googleapis.com/css?family=Manrope' rel='stylesheet'>
        <script>
            window.onload = function () {
                var addressAddNew = "${addressAddNew}";

                if (addressAddNew === 'on') {
                    toggleNewAddressInput();
                }
            };
        </script>


    </head>
    <body style="font-family: Manrope">
        <!-- Header Section Begin -->
        <c:if test="${sessionScope.account != null}">
            <header id="header" class="header ">
                <div style="margin-top: 45px">&nbsp;</div>
                <div class="header_main fixed-top" id="headerMain" style="position: fixed">
                    <div class="container-full height-100">
                        <div class="height-100 d-flex justify-content-between align-items-center">                       
                            <div class="header_main__logo">
                                <h1 class="h1-logo">Thời trang nữ Ashion</h1>
                                <a href="pub_home">
                                    <img src="img/logo.png"
                                         alt="Ashion" />
                                </a>
                            </div>
                            <script type="text/javascript" src="Content/utils/threesixty/threesixty.js"></script>
                            <link type="text/css" href="Content/utils/threesixty/threesixty.css" rel="stylesheet" />

                            <div class="header_main__menu">
                                <ul class="nav_menu">
                                    <!--=========================product list=======================================-->
                                    <li class="has-full-menu ">
                                        <a href="javascript:void(0)">Sản Phẩm</a>

                                        <div class="submenu submenu-full">
                                            <div class="row">
                                                <div class="col-2">
                                                    <a href="https://aristino.com/san-pham.html">
                                                        <img src="Data/daisuthuonghieu.jpg" alt="Sản Phẩm"
                                                             style="border-radius: 5px" />
                                                    </a>
                                                </div>
                                                <div class="col-10" style="padding-left: 90px;">
                                                    <div class="row">

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=1">Áo</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=1&CategoryProductId=1">Áo sơ mi</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=2&CategoryProductId=1">Áo polo</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=3&CategoryProductId=1">Áo chống nắng</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=4&CategoryProductId=1">Áo len</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=5&CategoryProductId=1">Áo sweater</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=6&CategoryProductId=1">Áo phông</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=7&CategoryProductId=1">Áo khoác</a></p>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=2">Quần</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=8&CategoryProductId=2">Quần shorts</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=9&CategoryProductId=2">Quần jeans</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=10&CategoryProductId=2">Quần legging</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=11&CategoryProductId=2">Quần joggers</a></p>

                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=3">Váy</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=12&CategoryProductId=3">Chân váy</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=13&CategoryProductId=3">Đầm</a></p>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=4">Đồ lót</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=14&CategoryProductId=4">Áo lót</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=15&CategoryProductId=4">Quần lót</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=16&CategoryProductId=4">Quần tất</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=17&CategoryProductId=4">BIKINI</a></p>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=5">Set quần áo</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=18&CategoryProductId=5">Đồ ngủ</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=19&CategoryProductId=5">Đồ thể thao</a></p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <div style="padding: 15px 12px;">&nbsp;</div>
                                    </li>
                                    <!--=========================Accessory list=======================================-->
                                    <li class="has-full-menu ">
                                        <a href="javascript:void(0)">Phụ kiện</a>

                                        <div class="submenu submenu-full">
                                            <div class="row">
                                                <div class="col-2">
                                                    <a href="https://aristino.com/san-pham.html">
                                                        <img src="Image/Accessory/daisuthuonghieu.jpg" style="border-radius: 6px" alt="Sản Phẩm" />
                                                    </a>
                                                </div>
                                                <div class="col-10" style="padding-left: 90px;">
                                                    <div class="row">

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=6">Giày, dép</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=20&CategoryProductId=6">Giày thể thao</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=21&CategoryProductId=6">Bốt</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=22&CategoryProductId=6">Giày cao gót</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=23&CategoryProductId=6">Sục</a></p>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=7">Túi</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=24&CategoryProductId=7">Túi, ví</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=25&CategoryProductId=7">Balo</a></p>

                                                            </div>
                                                        </div>


                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=26&CategoryProductId=8">Mũ</a>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=27&CategoryProductId=9">Thắt lưng</a>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=28&CategoryProductId=10">Kính</a>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </li>

                                    <li>
                                        <div style="padding: 15px 12px;">&nbsp;</div>
                                    </li>
                                    <!--=========================Collection list=======================================-->
                                    <li class="has-full-menu ">
                                        <a href="javascript:void(0)">Bộ sưu tập</a>
                                        <div class="submenu submenu-full" 
                                             style="left: 25%; right: 35%; min-width: 100px; width: 18%; min-height: 300px; padding: 20px 0">
                                            <div class="row justify-content-center d-flex">
                                                <div class="col-10 ">
                                                    <div class="submenu__list justify-content-center d-flex" style="flex-direction: column">
                                                        <div class="title text-center">
                                                            <a href="javascript:void(0)">Các Bộ sưu tập</a>
                                                        </div>
                                                        <c:forEach items="${sessionScope.listCollection}" var="lCol">
                                                            <p class="text-center" ><a href="pub_collectionlist?collectionId=${lCol.collectionId}">${lCol.collectionTitle}</a></p>
                                                            </c:forEach>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>

                                    </li>
                                    <li>
                                        <div style="padding: 15px 12px;">&nbsp;</div>
                                    </li>
                                    <li>
                                        <a href="pub_viewpostlist">Tin tức</a>
                                    </li>

                                </ul>
                            </div>


                            <div class="header_main__myCart">
                                <ul>
                                    <li>
                                        <div class="search--container">
                                            <form action="pub_searchproductbyproductname" method="post">
                                                <input type="text" name="searching" class="search" placeholder="Tìm kiếm..." />
                                                <i class="search--icon bi bi-search"></i>
                                            </form>
                                        </div>
                                    </li>
                                    <li>
                                        <a href="pub_cart" class="myCart" title="Giỏ hàng">
                                            <img src="Content/pc/images/icon/Group 309.svg" alt="My cart" />
                                            <span class="numberCart">
                                                <%
                                                    if (session.getAttribute("account") != null) {
                                                        Account acc = (Account) session.getAttribute("account");

                                                        CustomerDAO cus_dao = new CustomerDAO();
                                                        Customer cus = cus_dao.getByAccountId(acc.getAccId());

                                                        CartDAO cart_dao = new CartDAO();
                                                        List<Cart> cartList = cart_dao.getByCusId(cus.getCusId());
                                                        out.print(cartList.size());
                                                    }
                                                %>
                                            </span>
                                        </a>
                                    </li>
                                </ul>

                                <div class="header_right">
                                    <div class="header_top__right">
                                        <a href="javascript:void(0)" class="" >
                                            <span>
                                                <c:if test="${sessionScope.account.accAvarUrl != null}">
                                                    <img src="${sessionScope.account.accAvarUrl}" alt="alt" style="width: 40px;height: 40px; object-fit: cover;border-radius: 50%;"/>
                                                </c:if>
                                                <c:if test="${sessionScope.account.accAvarUrl == null}">
                                                    <img src="Image/Avatar/Avatar_Default.png" alt="alt" style="width: 40px;height: 40px; object-fit: cover;border-radius: 50%;"/>
                                                </c:if>
                                            </span>
                                        </a>
                                        <div class="login-drop">
                                            <a href="com_upprofile" class="login-drop__User">${sessionScope.account.accName}</a>
                                            <a href="com_upprofile" >Thông tin tài khoản</a>
                                            <a href="cus_myorder" >Đơn hàng của tôi</a>
                                            <c:if test="${not empty sessionScope.account.accPass}">
                                                <a href="com_changepass">Đổi mật khẩu</a>
                                            </c:if>
                                            <c:if test="${empty sessionScope.account.accPass}">
                                                <a href="com_changepass">Đặt mật khẩu mới</a>                                
                                            </c:if>
                                            <a href="com_logout" >Đăng xuất</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </c:if>

        <c:if test="${sessionScope.account == null}">
            <header id="header" class="header ">
                <div style="margin-top: 45px">&nbsp;</div>
                <div class="header_main fixed-top" id="headerMain" style="position: fixed">
                    <div class="container-full height-100">
                        <div class="height-100 d-flex justify-content-between align-items-center">                       
                            <div class="header_main__logo">
                                <h1 class="h1-logo">Thời trang nữ Ashion</h1>
                                <a href="pub_home">
                                    <img src="img/logo.png"
                                         alt="Ashion" />
                                </a>
                            </div>


                            <script type="text/javascript" src="Content/utils/threesixty/threesixty.js"></script>
                            <link type="text/css" href="Content/utils/threesixty/threesixty.css" rel="stylesheet" />



                            <div class="header_main__menu">
                                <ul class="nav_menu">
                                    <!--=========================Sale=======================================-->
                                    <!--                                    <li>
                                                                            <a href="#">SALE</a>
                                                                        </li>-->

                                    <!--=========================product list=======================================-->
                                    <li class="has-full-menu ">
                                        <a href="javascript:void(0)">Sản Phẩm</a>

                                        <div class="submenu submenu-full">
                                            <div class="row">
                                                <div class="col-2">
                                                    <a href="https://aristino.com/san-pham.html">
                                                        <img src="Data/daisuthuonghieu.jpg" alt="Sản Phẩm"
                                                             style="border-radius: 5px" />
                                                    </a>
                                                </div>
                                                <div class="col-10" style="padding-left: 90px;">
                                                    <div class="row">

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=1">Áo</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=1&CategoryProductId=1">Áo sơ mi</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=2&CategoryProductId=1">Áo polo</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=3&CategoryProductId=1">Áo chống nắng</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=4&CategoryProductId=1">Áo len</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=5&CategoryProductId=1">Áo sweater</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=6&CategoryProductId=1">Áo phông</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=7&CategoryProductId=1">Áo khoác</a></p>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=2">Quần</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=8&CategoryProductId=2">Quần shorts</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=9&CategoryProductId=2">Quần jeans</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=10&CategoryProductId=2">Quần legging</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=11&CategoryProductId=2">Quần joggers</a></p>

                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=3">Váy</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=12&CategoryProductId=3">Chân váy</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=13&CategoryProductId=3">Đầm</a></p>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=4">Đồ lót</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=14&CategoryProductId=4">Áo lót</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=15&CategoryProductId=4">Quần lót</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=16&CategoryProductId=4">Quần tất</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=17&CategoryProductId=4">BIKINI</a></p>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=5">Set quần áo</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=18&CategoryProductId=5">Đồ ngủ</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=19&CategoryProductId=5">Đồ thể thao</a></p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <div style="padding: 15px 12px;">&nbsp;</div>
                                    </li>
                                    <!--=========================Accessory list=======================================-->
                                    <li class="has-full-menu ">
                                        <a href="javascript:void(0)">Phụ kiện</a>

                                        <div class="submenu submenu-full">
                                            <div class="row">
                                                <div class="col-2">
                                                    <a href="https://aristino.com/san-pham.html">
                                                        <img src="Image/Accessory/daisuthuonghieu.jpg" style="border-radius: 6px" alt="Sản Phẩm" />
                                                    </a>
                                                </div>
                                                <div class="col-10" style="padding-left: 90px;">
                                                    <div class="row">

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=6">Giày, dép</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=20&CategoryProductId=6">Giày thể thao</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=21&CategoryProductId=6">Bốt</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=22&CategoryProductId=6">Giày cao gót</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=23&CategoryProductId=6">Sục</a></p>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=7">Túi</a>
                                                                </div>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=24&CategoryProductId=7">Túi, ví</a></p>
                                                                <p><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=25&CategoryProductId=7">Balo</a></p>

                                                            </div>
                                                        </div>


                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=26&CategoryProductId=8">Mũ</a>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=27&CategoryProductId=9">Thắt lưng</a>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-2">
                                                            <div class="submenu__list">
                                                                <div class="title">
                                                                    <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=28&CategoryProductId=10">Kính</a>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </li>
                                    <li>
                                        <div style="padding: 15px 12px;">&nbsp;</div>
                                    </li>
                                    <!--=========================Collection list=======================================-->
                                    <li class="has-full-menu ">
                                        <a href="javascript:void(0)">Bộ sưu tập</a>
                                        <div class="submenu submenu-full" 
                                             style="left: 25%; right: 35%; min-width: 100px; width: 18%; min-height: 300px; padding: 20px 0">
                                            <div class="row justify-content-center d-flex">
                                                <div class="col-10 ">
                                                    <div class="submenu__list justify-content-center d-flex" style="flex-direction: column">
                                                        <div class="title text-center">
                                                            <a href="javascript:void(0)">Các Bộ sưu tập</a>
                                                        </div>
                                                        <c:forEach items="${sessionScope.listCollection}" var="lCol">
                                                            <p class="text-center" ><a href="pub_collectionlist?collectionId=${lCol.collectionId}">${lCol.collectionTitle}</a></p>
                                                            </c:forEach>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>

                                    </li>
                                    <li>
                                        <div style="padding: 15px 12px;">&nbsp;</div>
                                    </li>
                                    <!--=========================Shop=======================================-->
                                    <!--                                    <li>
                                                                            <a href="#">Cửa hàng</a>
                                                                        </li>-->

                                    <!--=========================Post=======================================-->
                                    <li>
                                        <a href="pub_viewpostlist">Tin tức</a>
                                    </li>

                                </ul>
                            </div>


                            <div class="header_main__myCart">
                                <ul>
                                    <li>
                                        <div class="search--container">
                                            <form action="pub_searchproductbyproductname" method="post">
                                                <input type="text" name="searching" class="search" placeholder="Tìm kiếm..." />
                                                <i class="search--icon bi bi-search"></i>
                                            </form>
                                        </div>
                                    </li>
                                    <li>
                                        <a href="com_login" class="header__login">Đăng nhập</a>
                                        / 
                                        <a href="com_register" class="header__login">Đăng ký</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </c:if>
        <!-- Header Section End -->

        <!-- Body Begin -->
        <section class="page_checkout">
            <form action="cus_cartcontact" method="post" name="checkout_form" id="checkout_form" novalidate="novalidate">
                <div class="container">
                    <div class="row">
                        <div class="col-8">
                            <div class="page_checkout__form">
                                <div class="heading_layout_other">
                                    <h2 class="mb-3 mt-3">GIAO HÀNG VÀ THANH TOÁN</h2>
                                </div>
                                <div class="form_checkout">
                                    <label class="label">
                                        Thông tin giao hàng
                                    </label>
                                    <div class="row">
                                        <div class="col-12" >
                                            <div class="form-group">                                                
                                                <select class="form-control" name="address" id="addressSelect" required="required">
                                                    <option value="" disabled selected >Chọn địa chỉ cụ thể</option>
                                                    <c:forEach items="${requestScope.cusAddress}" var="i" varStatus="loop">
                                                        <option value="${i.addRecName},${i.addRecPhone},${i.addId}" 
                                                                <c:if test="${choiceAdd == i.addId}">selected</c:if> >${i.addName}</option>
                                                    </c:forEach>
                                                    <option value="add">Thêm địa chỉ mới</option>
                                                    <!-- Thêm các option khác tương ứng với địa chỉ cụ thể khác nếu cần -->
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row" id="showInfo">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="addRecNameTextbox">Tên người nhận:</label>
                                                    <input type="text" class="form-control" id="addRecNameTextbox" value="${addressName}">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="addRecPhoneTextbox">Số điện thoại người nhận:</label>
                                                    <input type="text" class="form-control" id="addRecPhoneTextbox" value="${addressPhone}">
                                                </div>
                                            </div>
                                            <span style="color: red">${addressError}</span>
                                        </div>

                                        <div class="col-md-12 block-right" id="newAddressInput" style="display: none; ">
                                            <p class="block-right__title bold mb-3">Cập nhật địa chỉ</p>
                                            <input type="hidden" name="selectedValues" value="${selectValues}"/>
                                            <div class="row" style="width: 100%;">
                                                <div class="col-md-5">
                                                    <div class="common">
                                                        <p class="common__title">Họ tên</p>
                                                        <input type="text" class="form-control common__content" 
                                                               name="name" value="${addrName}"
                                                               placeholder="Nhập họ tên" style="height: 30px;" required/>
                                                        <div id="nameError" style="color: red;height: 1.5rem;"></div>
                                                    </div>

                                                    <div class="common">
                                                        <p class="common__title">Số điện thoại</p>
                                                        <input type="text" class="form-control common__content" 
                                                               name="phone" value="${addrPhone}"
                                                               placeholder="Nhập số điện thoại" style="height: 30px;" required/>
                                                        <div id="phoneError"  style="color: red;"></div>
                                                    </div>
                                                </div>
                                                <!-- SEND ADDRESS ID-->
                                                <input type="hidden" class="hide" name="addId" value="${address.addId}" />

                                                <div class="col-md-7">
                                                    <div class="common">
                                                        <p class="common__title">Tỉnh thành</p>
                                                        <input type="text" name = "city" class="form-control common__content" 
                                                               value="${city}" placeholder="Nhập tên tỉnh/thành phố" required/>
                                                        <div id="cityError"  style="color: red;height: 1.5rem;"></div>
                                                    </div>
                                                    <div class="common mb-3">
                                                        <p class="common__title">Quận huyện</p>
                                                        <input type="text" name = "district" class="form-control common__content" 
                                                               value="${district}" placeholder="Nhập tên quận/huyện" required/>
                                                        <div id="districtError" style="color: red;"></div>
                                                    </div>
                                                    <div class="common mb-3">
                                                        <p class="common__title">Phường xã</p>
                                                        <input type="text" name = "ward" class="form-control common__content" 
                                                               value="${ward}" placeholder="Nhập tên xã/phường" required/>
                                                        <div id="wardError"  style="color: red;"></div>
                                                    </div>
                                                    <div class="common mb-4">
                                                        <p class="common__title">Địa chỉ</p>
                                                        <input type="text" class="form-control common__content" name="detail"
                                                               value="${detail}" placeholder="Nhập địa chỉ" required/>
                                                        <div id="detailError" style="color: red;"></div>
                                                    </div>
                                                </div>

                                                <button type="submit" name="action" value="newAddress" class="btn btn-default-black btn_buy_now">Lưu thông tin</button>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="mt-2" id="paymentType">
                                        <label class="label">
                                            Chọn hình thức thanh toán
                                        </label>
                                        <ul class="list-group list-group-payment list-payment">
                                            <li class="list-group-item">
                                                <div class="payment-box">
                                                    <div class="form-check">
                                                        <input type="radio" id="PaymentID13419" class="form-check-input" name="Payment" data-id="13419" value="VNPAY"
                                                               <c:if test="${choicePay eq 'VNPAY'}">checked</c:if> >
                                                        </div>
                                                        <img class="ic" src="https://th.bing.com/th/id/OIP.pn3RUm1xk1HiAxWIgC6CIwHaHa?rs=1&pid=ImgDetMain" alt="Thanh toán qua VNPAY">
                                                        <label for="PaymentID13419">Thanh toán qua VNPAY</label>
                                                    </div>
                                                </li>
                                                <span style="color: red">${paymentError}</span>
                                        </ul>

                                        <label class="label">
                                            Chọn phương thức giao hàng
                                        </label>
                                        <ul class="list-group list-group-payment list-delivery">   
                                            <li class="list-group-item">
                                                <div class="payment-box">
                                                    <div class="form-check">
                                                        <input type="radio" id="DeliveryID13415" class="form-check-input" name="Delivery" data-fee="30000" value="delivery"
                                                               <c:if test="${choiceDeli eq 'delivery'}">checked</c:if>  >
                                                        </div>
                                                        <img class="ic" src="Content/pc/images/icon/delivery.png" alt="Giao hàng tiêu chuẩn">
                                                        <label for="DeliveryID13415">Giao hàng tiêu chuẩn</label>
                                                    </div>
                                                </li>
                                                <span style="color: red">${deliveryError}</span>
                                        </ul>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="col-4">
                            <div class="box_order_cart">
                                <div class="heading_cart">
                                    ĐƠN HÀNG <a href="pub_cart" class="btn_edit_cart" rel="nofollow">Sửa</a>
                                </div>
                                <ul class="list_cart_checkout list_cart_temp1">
                                    <jsp:useBean class="com.team3.onlineshopping.dal.ProductDAO" id="product"/>
                                    <jsp:useBean class="com.team3.onlineshopping.dal.CategorySizeDAO" id="productSize"/>
                                    <c:if test="${requestScope.buynow == null}">
                                        <c:forEach items="${requestScope.listcart}" var="i" varStatus="loop">
                                            <fmt:formatNumber value="${product.getById(i.proId).proPrice}" pattern="###,###" var="productPrice"/>
                                            <input type="hidden" class="hide" name="product" value="${i.proId}"/>
                                            <input type="hidden" class="hide" name="quantity" value="${i.cartQuantity}" />
                                            <input type="hidden" class="hide" name="size" value="${i.cateSizeId}" />
                                            <input type="hidden" class="hide" name="cart" value="${i.cartId}" />
                                            <li class="list_cart_temp1-item">
                                                <img src="${product.getById(i.proId).proImgDefault}" class="thumbnail" alt="">
                                                <div class="infoProduction">
                                                    <h3 class="infoProduction_name">${product.getById(i.proId).proName}</h3>
                                                    <div class="infoProduction_option">
                                                        Giá: ${productPrice}đ
                                                        <c:if test="${productSize.getById(i.cateSizeId).cateSizeName ne 'over'}">
                                                            <br>
                                                            Kích cỡ: ${productSize.getById(i.cateSizeId).cateSizeName}
                                                        </c:if>
                                                        <br>
                                                        Số lượng: ${i.cartQuantity}
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${requestScope.buynow == 'buynow'}">
                                        <fmt:formatNumber value="${products.proPrice}" pattern="###,###" var="productsPrice"/>
                                        <fmt:parseNumber value="${cateSizeId}" type="number" var="cateSize"/>
                                        <input type="hidden" class="hide" name="product" value="${products.proId}"/>
                                        <input type="hidden" class="hide" name="quantity" value="${quantity}" />
                                        <input type="hidden" class="hide" name="size" value="${cateSizeId}" />
                                        <input type="hidden" class="hide" name="buynow" value="${buynow}" />
                                        <li class="list_cart_temp1-item">
                                            <img src="${products.proImgDefault}" class="thumbnail" alt="">
                                            <div class="infoProduction">
                                                <h3 class="infoProduction_name">${products.proName}</h3>
                                                <div class="infoProduction_option">
                                                    Giá: ${productsPrice}đ
                                                    <c:if test="${productSize.getById(cateSize).cateSizeName ne 'over'}">
                                                        <br>
                                                        Kích cỡ: ${productSize.getById(cateSize).cateSizeName}
                                                    </c:if>
                                                    <br>
                                                    Số lượng: ${quantity}
                                                </div>
                                            </div>
                                        </li>
                                    </c:if>
                                </ul>

                                <div class="box_order_cart__price">
                                    <fmt:formatNumber value="${requestScope.totalPrice}" pattern="###,###" var="totalOrder"/>
                                    <ul> 
                                        <li>
                                            <span class="tille">Phí vận chuyển:</span>
                                            <span class="price delivery-value">30.000đ</span>
                                        </li>
                                        <li>
                                            <span class="tille"><strong>Thành tiền:</strong></span>
                                            <span class="price red total-order">${totalOrder}đ</span>
                                            <input type="hidden" class="hide" name="totalPrice" value="${requestScope.totalPrice}"/>
                                        </li>
                                    </ul>
                                </div>

                                <button type="submit" name="action" value="completion" class="btn btn-default-black btn_buy_now">
                                    XÁC NHẬN ĐẶT HÀNG
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </section>
        <!-- Body End -->


        <!-- Footer Section Begin -->
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <div class="footer__about">
                            <div class="footer__logo">
                                <a href="pub_home"><img src="img/logo.png" alt=""></a>
                            </div>

                            <div class="footer__payment">
                                <a class="m-0" href="#">
                                    <img src="https://th.bing.com/th/id/OIP.pn3RUm1xk1HiAxWIgC6CIwHaHa?rs=1&pid=ImgDetMain" alt=""
                                         style="width: 42px; height: 42px;">
                                </a>
                                <a class="m-0" href="#"><img src="https://trustsales.vn/image/ed3ea862190620191559038616.png?width=750" alt=""
                                                             style="width: 42px; height: 42px;"></a>
                                <a class="m-0" href="#"><img src="https://img.freepik.com/free-psd/google-icon-isolated-3d-render-illustration_47987-9777.jpg" alt=""
                                                             style="width: 42px; height: 42px;border-radius: 50%;"></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="footer__widget">
                            <h6>Thông tin</h6>
                            <ul class="list-menu">
                                <li class="fone d-flex">
                                    <div class="icon-footer d-flex align-items-center">
                                        <i class="fa-solid fa-phone" style="color: #bd0000;"></i>
                                    </div>
                                    &nbsp;
                                    <a href="javascript:void(0)">Hotline: 18008118</a>
                                </li>
                                <li class="fone d-flex">
                                    <div class="icon-footer d-flex align-items-center">
                                        <i class="fa-solid fa-envelope" style="color: #bd0000;"></i>
                                    </div>
                                    &nbsp;
                                    <a href="javascript:void(0)">Email: ashionshopping@gmail.com</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="footer__widget">
                            <h6>Về Ashion Shopping</h6>
                            <ul>
                                <li><a href="pub_home">Trang chủ</a></li>
                                <li><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=1">Sản phẩm</a></li>
                                <li><a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=6">Phụ kiện</a></li>
                                <li><a href="pub_viewpostlist">Tin tức</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="footer__newslatter">
                            <h6>Mạng xã hội</h6>
                            <div class="footer__social">
                                <a href="https://www.facebook.com/anh.hung.731135"><i class="fa-brands fa-facebook" style="color: #357af3;"></i></a>
                                <a href="#"><i class="fa-brands fa-twitter" style="color: #74C0FC;"></i></a>
                                <a href="#"><i class="fa-brands fa-youtube" style="color: #ff1a1a;"></i></a>
                                <a href="#"><i class="fa-brands fa-pinterest-p" style="color: #e14141;"></i></i></a>
                                <a href="#"><i class="fa-brands fa-tiktok" style="color: #3e3c3c;"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="footer__copyright__text" style="padding: 5px; margin: 0; font-size: 10px;">
                            <p>Copyright &copy;
                                <script>document.write(new Date().getFullYear());</script> by 
                                <a href="pub_home" target="_blank">AshionShop</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer Section End -->

        <!--pop-up notification-->
        <div id="toast" class="hide zindex-3">
            <div class="toast">
                <div class="toast__icon">
                    <div class="fas fa-check-circle"></div>
                </div>
                <div class="toast__body">
                    <p class="toast__body--msg"></p>
                </div>
            </div>
        </div>
        <!--pop-up notification-->

        <script>

            document.getElementById('addressSelect').addEventListener('change', function () {
                var selectedOption = this.value;
                if (selectedOption === 'add') {
                    toggleNewAddressInput();

                } else {
                    var selectedValues = selectedOption.split(',');
                    document.getElementById('addRecNameTextbox').value = selectedValues[0];
                    document.getElementById('addRecPhoneTextbox').value = selectedValues[1];
                }
            });

            function toggleNewAddressInput() {
                var newAddressInput = document.getElementById('newAddressInput');
                var addressSelect = document.getElementById('addressSelect');
                var showInfo = document.getElementById('showInfo');
                var paymentType = document.getElementById('paymentType');

                newAddressInput.style.display = 'block';

                addressSelect.style.display = 'none';
                showInfo.style.display = 'none';
                paymentType.style.display = 'none';
            }

            var nameError = "${nameError}";
            var phoneError = "${phoneError}";
            var cityError = "${cityError}";
            var districtError = "${districtError}";
            var wardError = "${wardError}";
            var detailError = "${detailError}";

            if (nameError) {
                document.getElementById("nameError").innerText = nameError;
            }
            if (phoneError) {
                document.getElementById("phoneError").innerText = phoneError;
            }
            if (cityError) {
                document.getElementById("cityError").innerText = cityError;
            }
            if (districtError) {
                document.getElementById("districtError").innerText = districtError;
            }
            if (wardError) {
                document.getElementById("wardError").innerText = wardError;
            }
            if (detailError) {
                document.getElementById("detailError").innerText = detailError;
            }



        </script>



    </body>
</html>
