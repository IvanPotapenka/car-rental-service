<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 11.04.2023
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${car.model}</title>
    <style>
        box {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 50px;
            margin-bottom: 20%;
        }
    </style>
</head>
    <body>
<%@include file="header.jsp" %> <br>
        <box>
            <box class="w3-container">
                <h2 class="w3-text-blue"> ${car.model} ${car.year}</h2></br>
                <h4>Transmission: ${car.transmission}</h4></br>
                <h4>Fuel: ${car.fuel}</h4></br>
                <h4>Engine: ${car.engineCapacity}</h4></br>
                <h2 class="w3-text-red">Price: ${car.price}$</h2></br>
                <c:if test="${sessionScope.user == null}">
                <h4 class="w3-text-blue w3-round-large"> To place an order, please <a href=/login>Sing in </a></h4>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                <button class="w3-btn w3-round-large w3-red" onclick="location.href='/order?id=${car.id}'">Order</button>
                </c:if>
            </box>
        </box>
<%@include file="footer.jsp" %>
    </body>
</html>
