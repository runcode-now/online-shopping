<%-- 
    Document   : p_accessdenial
    Created on : Mar 7, 2024, 12:41:43 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Access Denied</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="style.css">
        <style>
            body{
                background-color: #E7EAF1;
            }

            .container{
                display: flex;
                justify-content: center;
                align-items: center;
                position: relative;
            }

            img {
                width: 85%;
                height: auto;
            }
            
            .goback{
                min-width: 300px;
                position: absolute;
                bottom: 20%;
                right: 30%;
                text-decoration: none;
                padding:10px 20px;
                border-radius: 60px;
                background: #CF3743;
                color: #fff;
                font-size: 20px;  
                text-align: center;
            }
            .goback:hover{
                opacity: 0.9;
            }
        </style>
    </head>
    <body style="font-family: sans-serif">
        <div class="container"> 
            <img src="Image/Avatar/403.png" alt="alt" />
            <c:if test="${sessionScope.account == null || sessionScope.account.roleId == 4}">
                <a href="pub_home" class="goback">Quay lại trang chủ</a>
            </c:if>
            <c:if test="${sessionScope.account.roleId == 1}">
                <a href="ad_dashboard" class="goback">Quay lại trang chủ</a>
            </c:if>
            <c:if test="${sessionScope.account.roleId == 2}">
                <a href="sale_dashboard" class="goback">Quay lại trang chủ</a>
            </c:if>
            <c:if test="${sessionScope.account.roleId == 3}">
                <a href="mkt_dashboard" class="goback">Quay lại trang chủ</a>
            </c:if>
        </div>
    </body>
</html>
