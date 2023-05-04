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

        div {
            padding-top: 80px;
            padding-left: 20px;
        }
    </style>
</head>

<body>
<%@include file="header.jsp" %>
<div><a href="/">Home </a><a href="${pageContext.request.contextPath}/catalog">/ catalog</a>/ order</div>
<p class="w3-container w3-padding w3-margin"></p>
<box>
    <box class="w3-card-4 w3-round-large w3-padding" style="width: 50%">
        <h2><a href=${pageContext.request.contextPath}/catalog/car?id=${car.id}
               class="w3-text-blue"> ${car.brand} ${car.model} ${car.year}</a></h2>
        <h6> &#9989; ${car.body.placeQuantity} places/ &#9989; ${car.engine.transmission}/
            &#9989;${car.body.doorQuantity} doors/ &#9989;${car.engine.fuelConsumption} on 100 km</h6><br>
        <form method="post">
            <table>
                <tr>
                    <th>1-3 days <input type="radio" name="day"></th>
                    <th>4-7 days <input type="radio" name="day"></th>
                    <th>7-15 days <input type="radio" name="day"></th>
                    <th>15-30 days <input type="radio" name="day"></th>
                </tr>
                <tr>
                    <th>${car.price*1.0}$</th>
                    <th>${car.price*0.9}$</th>
                    <th>${car.price*0.8}$</th>
                    <th>${car.price*0.7}$</th>
                </tr>
            </table>
        </form>
        <br>
        <flex>
            <h3 class="w3-padding w3-text-blue">Include:</h3>

            <h7 class="w3-padding w3-text-black ">&#9989; The car is insured against accidents</h7>
            <h7 class="w3-padding w3-text-black "> &#9989;Daily mileage up to 350 km</h7>
            <h7 class="w3-padding w3-text-black ">&#9989;24 hour roadside support</h7>
            <h7 class="w3-padding w3-text-black ">&#9989;The car is returned with the same fuel level as when it was
                issued
            </h7>
        </flex>
        <form method="post">
            <box>
                <h5 class="w3-padding w3-text-blue">Additional equipment</h5>
                <table>
                    <tr>
                        <th><input type="checkbox" name="gps">GPS</th>
                        <th><input type="checkbox" name="child">Child car sea</th>
                    </tr>
                    <tr>
                        <th>${car.price*0.1}$</th>
                        <th>${car.price*0.2}$</th>
                    </tr>
                </table>
                </br>
            </box>
        </form>

        <h5 class="w3-text-red">Total price ${car.price}$</h5><br>
        <c:if test="${sessionScope.user == null}">
            <h4 class="w3-text-blue w3-round-large"> To place an order, please <a href=/login>Sing in </a></h4>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <button class="w3-btn w3-round-large w3-right w3-red w3-padding"
                    onclick="location.href='/booking?id=${car.id}'">Booking
            </button>
        </c:if>

        <br>
    </box>
    </br>
    <p style="font-size: 16px"><a href="/catalog">Back</a></p><br>
</box>
<%@include file="footer.jsp" %>
</body>
</html>
