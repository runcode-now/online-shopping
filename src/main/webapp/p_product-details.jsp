<%-- 
    Document   : c_product-details
    Created on : Jan 17, 2024, 5:18:44 PM
    Author     : PC
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
        <title>${requestScope.product.proName}</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
        <link href='https://fonts.googleapis.com/css?family=Manrope' rel='stylesheet'>
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
                width: 47%;
                text-align: center;
                font-size: 14px;
            }

            .product__details__button .cart-btn.cart__btn--left{
                position: absolute;
                top: 6rem;
                left: 0;
            }

            .product__details__button .cart-btn.cart__btn--left:hover{
                opacity: 0.8;
            }

            .product__details__button .cart-btn.cart__btn--right{
                position: absolute;
                top: 6rem;
                right: 0;
                color: #ca1515;
                background-color: #FFF;
                border: 3px solid #ca1515;
                padding-top: 12px;
                padding-bottom: 12px;
            }

            .product__details__button .cart-btn.cart__btn--right:hover{
                color: #FFF;
                background-color: #ca1515;
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

            /*AnhTV*/
            .product__details__pic {
                overflow: hidden;
                margin-bottom: 80px;
            }
            .product__details__tab {
                padding-top: 0;
                margin-bottom: 65px;
            }
            .product__details__widget ul li .size__btn label {
                margin-bottom: 2px;
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
        <link type="text/css" href="Content/pc/bootstrap.min.css" rel="stylesheet" />
        <link type="text/css" href="Content/pc/style.min.css?v=&lt;%=DateTime.Now.Ticks %>" rel="stylesheet" />
        <link type="text/css" href="Content/pc/custom.css?v=&lt;%=DateTime.Now.Ticks %>" rel="stylesheet" />
        <style>
            .feedback__img{
                width: 60px;
                height: 60px;
                border-radius: 50%;
            }
        </style>
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

        <!-- Breadcrumb Begin -->
        <div class="breadcrumb-option">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb__links">
                            <a href="pub_home"><i class="fa fa-home"></i>Trang Chủ</a>
                            <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=0&CategoryProductId=${requestScope.catrgoryProduct.cateProId}"> ${requestScope.catrgoryProduct.cateProName} </a>
                            <a href="pub_viewproductlistbycategory?CategoryProductDetailsId=${catrgoryProductDetails.cateProDeId}&CategoryProductId=${requestScope.catrgoryProduct.cateProId}"> ${requestScope.catrgoryProductDetails.cateProDeName}</a>
                            <span> ${requestScope.product.proName} </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Breadcrumb End -->

        <!-- Product Details Section Begin -->
        <section class="product-details spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
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
                    <div class="col-lg-6">
                        <form action="pub_addcart" method="post" id="addToCartForm">
                            <jsp:useBean class="com.team3.onlineshopping.dal.ProductSizeDAO" id="proSize_dao"/>
                            <div class="product__details__text">
                                <h3>${requestScope.product.proName}</h3>
                                <div class="product-details--info">
                                    <span class="mr-3 rating"><i class="fa-solid fa-star"></i> ${requestScope.product.proRating}</span>
                                    <span class="mr-3 sold"> Đã Bán ${requestScope.product.proSold} </span>
                                    <span class="mr-3 sold"> Còn lại ${remaining} </span>
                                </div>

                                <fmt:formatNumber value="${requestScope.product.proPrice}" pattern="###,###" var="productPrice"/>
                                <fmt:formatNumber value="${requestScope.product.proCost}" pattern="###,###" var="productCost"/>
                                <div class="product__details__price"> ${productPrice}đ <span> ${productCost}đ</span></div>

                                <div class="product__details__widget">
                                    <ul>
                                        <li>
                                            <c:choose>
                                                <c:when test="${requestScope.catrgoryProduct.cateProId == 6}">
                                                    <span>Kích thước:</span>
                                                    <div class="size__btn">
                                                        <c:forEach begin="3" end="9" items="${requestScope.cateSize}" var="cateSize">
                                                            <c:if test="${proSize_dao.getByProCateSizeId(product.proId, cateSize.cateSizeId).proSizeQuantity > 0}">
                                                                <label for="btn-${cateSize.cateSizeName}">
                                                                    <input type="radio" name="size" id="btn-${cateSize.cateSizeName}" value="${cateSize.cateSizeName}">
                                                                    ${cateSize.cateSizeName}
                                                                </label>
                                                            </c:if>
                                                            <c:if test="${proSize_dao.getByProCateSizeId(product.proId, cateSize.cateSizeId).proSizeQuantity <= 0}">
                                                                <label style="pointer-events: none; background-color: #CCC;" for="btn-${cateSize.cateSizeName}">
                                                                    <input disabled="" type="radio" name="size" id="btn-${cateSize.cateSizeName}" value="">
                                                                    ${cateSize.cateSizeName}
                                                                </label>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </c:when>
                                                <c:when test="${requestScope.catrgoryProduct.cateProId ge 7 and requestScope.catrgoryProduct.cateProId le 10}">
                                                    <input type="hidden" name="size" value="over">
                                                </c:when>
                                                <c:otherwise>
                                                    <span>Kích thước:</span>
                                                    <div class="size__btn">
                                                        <c:forEach begin="0" end="2" items="${requestScope.cateSize}" var="cateSize">
                                                            <c:if test="${proSize_dao.getByProCateSizeId(product.proId, cateSize.cateSizeId).proSizeQuantity > 0}">
                                                                <label for="btn-${cateSize.cateSizeName}">
                                                                    <input type="radio" name="size" id="btn-${cateSize.cateSizeName}" value="${cateSize.cateSizeName}">
                                                                    ${cateSize.cateSizeName}
                                                                </label>
                                                            </c:if>
                                                            <c:if test="${proSize_dao.getByProCateSizeId(product.proId, cateSize.cateSizeId).proSizeQuantity <= 0}">
                                                                <label style="pointer-events: none; background-color: #CCC;" for="btn-${cateSize.cateSizeName}">
                                                                    <input disabled="" type="radio" name="size" id="btn-${cateSize.cateSizeName}" value="">
                                                                    ${cateSize.cateSizeName}
                                                                </label>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                    </ul>
                                </div>
                                <span class="" style="color: red;display: inline-block;">${requestScope.sizeError}</span>
                                <div class="product__details__button">
                                    <div class="quantity">
                                        <span>Số lượng:</span>
                                        <div class="pro-quantity">
                                            <button type="button" onclick="decreaseQuantity()">-</button>
                                            <input type="text" id="quantityInput" value="1" name="quantity" maxlength="4" minlength="1">
                                            <button type="button" onclick="increaseQuantity()">+</button>
                                        </div>
                                    </div>
                                    <c:if test="${sessionScope.account != null}" >
                                        <div class="product__cart-btn">
                                            <button name="action" value="addCart" class="cart-btn cart__btn--left" type="submit"><span class="icon_bag_alt"></span> Thêm vào giỏ hàng</button>
                                            <button name="action" value="pay" class="cart-btn cart__btn--right"  type="submit"><span class=""></span> Mua ngay</button>
                                        </div>
                                    </c:if>
                                    <c:if test="${sessionScope.account == null}" >
                                        <div class="product__cart-btn">
                                            <a href="com_login" class="cart-btn cart__btn--left"><span class="icon_bag_alt"></span> Thêm vào giỏ hàng </a>
                                            <a href="com_login" class="cart-btn cart__btn--right"><span class=""></span> Mua ngay </a>
                                        </div>
                                    </c:if>
                                    <input type="hidden" name="productId" value="${requestScope.product.proId}">
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--==========================description============================-->
                    <div class="col-lg-12 pt-5" style="margin-top: 3rem;">
                        <div class="product__details__tab">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab">Mô tả sản phẩm</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab">Đánh giá sản phẩm</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                    <h5>Mô tả sản phẩm</h5>
                                    <c:forEach items="${requestScope.productDescription}" var="pd">
                                        <p>${pd}</p>
                                    </c:forEach>
                                </div>
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
                    <div class="row">
                        <div class="col-lg-12 text-center">
                            <div class="related__title">
                                <h5>Sản phẩm liên quan</h5>
                            </div>
                        </div>
                        <c:forEach items="${requestScope.cateProDe}" var="cpd" varStatus="loop">
                            <c:if test="${loop.index lt 4}">
                                <fmt:formatNumber value="${cpd.proPrice}" pattern="###,###" var="productPrice"/>
                                <div class="col-lg-3 col-md-4 col-sm-6">
                                    <div class="product__item">
                                        <a href="pub_productdetails?proId=${cpd.proId}" style="display: block;">
                                            <div class="product__item__pic set-bg" data-setbg="${cpd.proImgDefault}"></div>
                                            <div class="product__item__text">
                                                <h6>${cpd.proName}</h6>
                                                <div class="product__price">${productPrice}đ</div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>


                    </div>
                </div>
        </section>
        <!-- Product Details Section End -->

        <!--pop-up notification-->
        <div id="toast" class="${status == 'success' ? '' : 'hide'}">
            <div class="toast">
                <div class="toast__icon">
                    <div class="fas fa-check-circle"></div>
                </div>
                <div class="toast__body">
                    <p class="toast__body--msg">Thêm vào giỏ hàng thành công</p>
                </div>
            </div>
        </div>
        <!--pop-up notification-->

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


        <script>
            function increaseQuantity() {
                var quantityInput = document.getElementById("quantityInput");
                var currentValue = parseInt(quantityInput.value, 10);
                if (currentValue < 9999) {
                    quantityInput.value = currentValue + 1;
                }
            }

            function decreaseQuantity() {
                var quantityInput = document.getElementById("quantityInput");
                var currentValue = parseInt(quantityInput.value, 10);
                if (currentValue > 1) {
                    quantityInput.value = currentValue - 1;
                }
            }

            var inputIds = ["quantityInput"];
            inputIds.forEach(function (inputId) {
                var input = document.getElementById(inputId);
                input.addEventListener("input", function (event) {
                    var value = event.target.value;
                    var cleanValue = value.replace(/[^0-9]/g, "");
                    if (cleanValue < 1 || cleanValue !== value || cleanValue > 10000) {
                        event.target.value = "";
                    }
                });
            });
        </script>

        <!-- script -->
        <script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.0.0/dist/tf.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@tensorflow-models/body-pix@1.0.0"></script>

        <script type="text/javascript" src="Content/pc/js/jquery.min.js"></script>
        <script type="text/javascript" src="Content/pc/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="Content/pc/js/popper.min.js"></script>
        <script type="text/javascript" src="Content/pc/js/bootstrap.min.js"></script>
        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
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
