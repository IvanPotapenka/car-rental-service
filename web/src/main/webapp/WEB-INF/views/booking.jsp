<%--@elvariable id="car" type=""--%>
<%@ page import="by.potapenko.database.entity.CarEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 31.03.2023
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalDate" %>

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

        td {
            padding: 10px;
            border: 1px solid gray;
        }

        th {
            padding: 10px;
            border: 1px solid gray;
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
            align-items: center;
        }

        div {
            padding-top: 80px;
            padding-left: 20px;
        }

        select {
            padding: 10px;
            width: auto;
            height: 35px;
            font-size: 10px;
        }

        input {
            padding: 10px;
            width: auto;
            height: 35px;
            font-size: 10px;
        }

        flex {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-left: 10px;
        }
    </style>
</head>

<body>
<%@include file="header.jsp" %>
<div><a href="/">Home </a><a href="${pageContext.request.contextPath}/cars">/ cars</a><a href="/order">/ order</a>
        / booking</div>
<c:if test="${create_client == null}">
<p class="w3-container w3-padding w3-margin"></p>
<box><p style="font-size: 60px" class="w3-text-red">Booking</p>
    <box class="w3-card-4 w3-round-large w3-padding" style="width: 50%">
        <h2><a href=${pageContext.request.contextPath}/cars?id=${car.id}
               class="w3-text-blue"> ${car.brand} ${car.model} ${car.year}</a></h2>
        <h6> &#9989; ${car.body.placeQuantity} places &#9989; ${car.engine.transmission}
            &#9989;${car.body.doorQuantity} doors &#9989; ${car.engine.fuelType} </h6>
        <flex>
            <h5 class="w3-padding w3-text-black">Rental date:</h5><h5 class="w3-padding w3-text-red">${rental_date} at 12.00 PM</h5>
            <h5 class="w3-padding w3-text-black">Return date:</h5><h5 class="w3-padding w3-text-red"> ${return_date} at 12.00 PM</h5>
            <h4 class="w3-padding w3-text-black"> Rental ${rental_days} days</h4>

            <h3 class="w3-padding w3-text-blue"> You confirm compliance with the minimum rental conditions:</h3>

            <h7 class="w3-padding w3-text-black ">&#9989; Driving experience of at least 2 years.</h7>
            <h7 class="w3-padding w3-text-black ">&#9989; The minimum age is 20 years.</h7>
            <h7 class="w3-padding w3-text-black ">&#9989; The minimum rental period is 2 hours.</h7>
            <h7 class="w3-padding w3-text-black ">&#9989; Mileage limits - 300 km / day, every subsequent 300 km. for $ 10 (at the rate of the NBRB).</h7>
            <h7 class="w3-padding w3-text-black ">&#9989; Without the possibility of leaving the Republic of Belarus.</h7>
            <h7 class="w3-padding w3-text-black ">&#9989; The rental price includes CASCO insurance.</h7>
            <h7 class="w3-padding w3-text-black ">&#9989; Deposit (deposit) 500r.</h7>

            <h3 class="w3-padding w3-text-red"> Total price ${car.price * rental_days}$</h3>

        </flex>
    </box>
    </br>
    <form method="post" class="w3-card-4  w3-round-large w3-padding" style="width: 90%">
        <flex>

                <h1>Your data</h1><br>
                <table style="font-size: 10px">
                    <tr>
                        <th>*First name</th>
                        <th>*Last name</th>
                        <th>Middle name</th>
                        <th>*Date of birthday</th>
                    </tr>

                    <td><label for="first_name_id"></label>
                        <input class="w3-round-large"
                               maxlength="20"
                               type="text"
                               placeholder="Enter first name"
                               name="first_name"
                               id="first_name_id"
                               required/><br></td>

                    <td><label for="last_name_id"></label>
                        <input class="w3-round-large"
                               maxlength="20"
                               type="text"
                               placeholder="Enter last name"
                               name="last_name"
                               id="last_name_id"
                               required/><br></td>

                    <td><label for="middle_name_id"></label>
                        <input class="w3-round-large"
                               maxlength="20"
                               type="text"
                               value=" "
                               placeholder="Enter middle name"
                               name="middle_name"
                               id="middle_name_id"
                        /><br></td>

                    <td><label for="date_of_birthday_id"></label>
                        <input class="w3-round-large"
                               type="date"
                               min="1950-01-01"
                               max="2003-01-01"
                               name="date_of_birthday"
                               id="date_of_birthday_id"
                               required/><br></td>

                    <tr>
                        <th>*Phone number</th>
                        <th>Address</th>
                    </tr>

                    <td><label for="phone_id"></label>
                        <input class="w3-round-large"
                               maxlength="13"
                               type="tel"
                               placeholder="Enter your phone"
                               name="phone"
                               id="phone_id"
                        /><br></td>

                    <td><label for="address_id"></label>
                        <input class="w3-round-large"
                               maxlength="100"
                               type="text"
                               placeholder="Enter city,street,No."
                               name="address"
                               id="address_id"
                        /><br></td>
                </table>
            <input value="${car.price * rental_days}" name="total_price" hidden>
                <br>

            <table>
                <h1>Passport</h1><br>
                <table style="font-size: 10px">
                    <tr>
                        <th>*Serial No.</th>
                        <th>*No.</th>
                        <th>*Date of issue</th>
                        <th>*Who issued the passport</th>
                        <th>*Passport ID</th>
                    </tr>


                <td><label for="serial_id"></label>
                    <input class="w3-round-large"
                           maxlength="2"
                           type="text"
                           name="series"
                           id="serial_id"
                           required/><br></td>

                    <td><label for="no_id"></label>
                        <input class="w3-round-large"
                               maxlength="7"
                               type="number"
                               name="number"
                               id="no_id"
                               required/><br></td>

                    <td><label for="date_id"></label>
                        <input class="w3-round-large"
                               type="date"
                               name="date_of_issue"
                               id="date_id"
                               required/><br></td>

                    <td><label for="issued_id"></label>
                        <input class="w3-round-large"
                               maxlength="20"
                               type="text"
                               name="issued"
                               id="issued_id"
                               required/><br></td>

                    <td><label for="passport_id"></label>
                        <input class="w3-round-large"
                               maxlength="13"
                               type="text"
                               name="passportID"
                               id="passport_id"
                               required/><br></td>
            </table>
                <button class="w3-btn w3-white w3-round-large" type="submit">Send on check</button><br>
                </c:if>
        </flex>
    </form><br>
</box>
<%@include file="footer.jsp" %>
</body>
</html>