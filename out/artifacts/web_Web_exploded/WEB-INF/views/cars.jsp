<%@ page import="by.potapenko.database.entity.Car" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 31.03.2023
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Cars</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" type="image/x-icon"
          href="https://e7.pngegg.com/pngimages/330/810/png-clipart-car-dealership-graphics-sports-car-computer-icons-car-blue-driving.png">
    <style>
        box {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 50px;
        }
        div{
            padding-top: 80px;
            padding-left: 20px;
        }

    </style>
</head>
<body>
<%@include file="header.jsp" %>
    <div><a href="/">Home </a><a href="${pageContext.request.contextPath}/cars">/ cars</a></div></nav1>
        <c:forEach var="car" items="${cars}">
            <box class="w3-container w3-padding">
                <box class="w3-card-4 w3-round-large w3-padding" style="width: 50%">
                    <h2><a href=${pageContext.request.contextPath}/cars?id=${car.id} class="w3-text-blue"> ${car.brand} ${car.model} ${car.yearOfRelease}</a></h2>
                    <h4>VIN code ${car.vinCode}</h4>
                    <h4>Engine capacity ${car.engine}l</h4>

                    <c:if test="${sessionScope.user == null}">
                    <button class="w3-btn w3-round-large w3-red" onclick="location.href='/login'">Order</button>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                    <h2 class="w3-text-red">Price ${car.price}$</h2>
                    <button class="w3-btn w3-round-large w3-red" onclick="location.href='/order?id=${car.id}'">Order</button>
                    <button class="w3-btn w3-round-large w3-red" onclick="location.href='/delete?id=${car.id}'">Delete</button>
                    </c:if>
                </box></br>
            </box>
        </c:forEach>
<%@include file="footer.jsp" %>
    </body>
</html>
