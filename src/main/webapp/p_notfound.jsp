<%-- 
    Document   : p_notfound
    Created on : Jan 22, 2024, 10:09:49 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Không tìm thấy địa chỉ</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
        <style>
            body {
                margin: 0;
                overflow: hidden; /* Ngăn chặn việc cuộn trang */
            }

            .not-found {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .not-found img {
                max-width: 100%;
                max-height: 100%;
            }
        </style>
    </head>
    <body>
        <div class="not-found">
            <img src="https://epal.vn/wp-content/uploads/2023/04/nguyen-nhan-gay-ra-404.webp" alt="alt"/>
        </div>
    </body>
</html>

