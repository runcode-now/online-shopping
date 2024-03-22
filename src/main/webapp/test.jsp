<%-- 
    Document   : test.jsp
    Created on : Jan 17, 2024, 5:30:51 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1></h1>


        <c:forEach items="${orderDetails}" var="orde">
            <jsp:useBean class="com.team3.onlineshopping.dal.ProductDAO" id="pro_dao"/>
            <jsp:useBean class="com.team3.onlineshopping.dal.CategorySizeDAO" id="cateSize_dao"/>
            <div class="bill__product d-flex">
                <div class="" style="width: calc(100% - 149px)!important;">
                    <div style="font-size: 16px;">
                        Phân loại hàng: ${cateSize_dao.getById(orde.CateSizeId).cateSizeName}
                    </div>
                    <div style="font-size: 14px;">Số lượng: ${orde.orDeQuantity}</div>
                </div>
            </div>
        </c:forEach>

        gewagarga
    </body>
</html>
