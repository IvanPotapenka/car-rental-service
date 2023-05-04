<%--@elvariable id="car" type=""--%>
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
    <title>Booking</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" type="image/x-icon"
          href="https://e7.pngegg.com/pngimages/330/810/png-clipart-car-dealership-graphics-sports-car-computer-icons-car-blue-driving.png">
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th {
            font-size: 12px;
            padding: 10px;
            border: 1px solid lightblue;
        }

        box {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 50px;
        }

        flex {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        p.round {
            border-radius: 20px;

        }

        div {
            padding-top: 80px;
            padding-left: 20px;
        }
    </style>
</head>

<body>
<%@include file="header.jsp" %>
<div><a href="/">Home </a><a href="${pageContext.request.contextPath}/cars">/ cars</a><a href="/order">/ order</a><a
        href="/booking">/ booking</a></div>

<p class="w3-container w3-padding w3-margin"></p>
<box><h1 class="w3-text-blue">You have successfully booked a car!</h1>
    <box class="w3-card-4 w3-round-large w3-padding" style="width: 50%">
        <h2><a href=${pageContext.request.contextPath}/cars?id=${car.id}
               class="w3-text-blue"> ${car.brand} ${car.model} ${car.year}</a></h2>
        <h6> &#9989; ${car.body.placeQuantity} places &#9989; ${car.engine.transmission}
            &#9989;${car.body.doorQuantity} doors &#9989;fuel consumption ${car.engine.fuelConsumption} on 100 km</h6>

    </box>
    </br>
    <p style="font-size: 16px"><a href="/order?id=${car.id}">Back</a></p><br>
</box>
<%@include file="footer.jsp" %>
</body>
</html>