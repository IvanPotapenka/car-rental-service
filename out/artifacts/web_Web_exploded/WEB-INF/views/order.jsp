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
    <title>Order</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" type="image/x-icon"
          href="https://e7.pngegg.com/pngimages/330/810/png-clipart-car-dealership-graphics-sports-car-computer-icons-car-blue-driving.png">
    <style>
     box    {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top:50px;
            }

     flex   {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            }
     p.round {
            border-radius: 20px;
            }
     div    {
         padding-top: 80px;
         padding-left: 20px;
            }
    </style>
</head>

<body>
<%@include file="header.jsp" %>
    <div><a href="/">Home </a><a href="${pageContext.request.contextPath}/cars">/ cars</a><a href="/order">/ order</a></div>
        <p class="w3-container w3-padding w3-margin"></p>
            <box>
                <box class="w3-card-4 w3-round-large w3-padding" style="width: 50%">
                    <h2 class="w3-text-blue">${car.brand} ${car.model} ${car.yearOfRelease}</a></h2>
                    <h4>VIN code ${car.vinCode}</h4>
                    <h4>Engine capacity ${car.engine}l</h4>
                    <h3 class="w3-text-red">Price: ${car.price}$</h3>
                </box></br>
            </box>
    <box>
        <p><h3 class="w3-padding w3-text-blue">Include:</h3></p>
            <flex>
                <p><h7 class="w3-padding w3-text-black ">&#9989; The car is insured against accidents</h7></p>
                <p><h7 class="w3-padding w3-text-black ">&#9989;Daily mileage up to 350 km</h7></p>
                <p><h7 class="w3-padding w3-text-black ">&#9989;24 hour roadside support</h7></p>
                <p><h7 class="w3-padding w3-text-black ">&#9989;The car is returned with the same fuel level as when it was issued</h7></p>
            </flex>
    </box>
<div class="w3-container w3-padding">
        <p class="w3-card-4 w3-round-large w3-padding"></p>
</div>
    <form method="post">
        <box>
            <h5 class="w3-padding w3-text-blue">The cost of rent by day:</h5>
            <p><label>
                <input type="radio" name="day">
            </label>
                <h7 class="w3-padding w3-text-black">1-3 days of rental</h7></p>
            <p><h4 class="w3-right w3-text-red"> Price:${car.price*1.0}$</h4></p>
            <p><label>
                <input type="radio" name="day">
            </label>
                <h7 class="w3-padding w3-text-black"></h7>4-7 days of rental</p>
            <p><h4 class="w3-right w3-text-red"> Price:${car.price*0.9}$</h4></p>
            <p><label>
                <input type="radio" name="day">
            </label>
                <h7 class="w3-padding w3-text-black">8-15 days of rental</h7></p>
            <p><h4 class="w3-right w3-text-red"> Price:${car.price*0.8}$</h4></p>
            <p><label>
                <input type="radio" name="day">
            </label>
                <h7 class="w3-padding w3-text-black">15-30 days of rental</h7></p>
            <p><h4 class="w3-right w3-text-red"> Price:${car.price*0.7}$</h4></p>

        </box>
    </form>
<form method="post">
    <div class="w3-container w3-padding">
        <p class="w3-card-4 w3-round-large w3-padding"></p>
    </div>
        <box>
            <h5 class="w3-padding w3-text-blue">Additional equipment</h5>
            <label>
                <input type="checkbox"name="gps">
            </label>
            <h7 class="w3-padding w3-text-black">GPS</h7>
            <h4 class="w3-right w3-text-red"> Price:${car.price*0.1}$</h4>
            </br>
            <label>
                <input type="checkbox" name="child">
            </label>
            <h7 class="w3-padding w3-text-black">Child car sea</h7>
            <h4 class="w3-right w3-text-red"> Price:${car.price*0.2}$</h4>
            </br>

        </box>
</form>
    <div class="w3-container w3-padding">
        <button class="w3-btn w3-round-large w3-red w3-right w3-padding" onclick="location.href='/booking?id=${car.id}'">Booking</button>
    </div>
<%@include file="footer.jsp" %>
</body>
</html>
