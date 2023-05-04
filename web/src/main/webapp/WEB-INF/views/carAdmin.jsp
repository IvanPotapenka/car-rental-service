<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 12.04.2023
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Car current</title>
    <style>
        table{
            border-collapse: collapse;
            width: 100%;
        }
        td {
            padding: 10px;
            border: 1px solid white;
        }
        th {
            padding: 10px;
            border:  1px solid white;
        }
        box {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 100px;

        }
        flex {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        div{
            padding-top: 80px;
            padding-left: 20px;
        }

    </style>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<div><a href="/admin">Home </a><a href="${pageContext.request.contextPath}/admin/cars/car?id=${car.id}">/ edit</a></div>
<box>
    <form method="post" class="w3-card-4 w3-blue-grey w3-round-large w3-padding" style="width: 70%">
        <a style="font-size: 12px" href="${pageContext.request.contextPath}/admin/cars/create_car">Create new car</a>
        <flex>
            <p style="font-size: 16px">
            <h1>${car.brand} ${car.model}</h1>
            <table>
                <tr>
                    <th>Car_id</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Year of release</th>
                    <th>VIN code</th>
                    <th>Engine, l</th>
                    <th>Price, $</th>
                    <th>Delete</th>
                    <th>Edit</th>
                </tr>

                    <tr>
                        <td>${car.id}</td>
                        <td>${car.brand}</td>
                        <td>${car.model}</td>
                        <td>${car.yearOfRelease}</td>
                        <td>${car.vinCode}</td>
                        <td>${car.engine}</td>
                        <td>${car.price}</td>
                        <td><a href="/admin/cars/car/delete_car?id=${car.id}" class="w3-text-red" >DELETE</a></td>
                        <td><a href="/admin/cars/car/update_car?id=${car.id}" class="w3-text-red" >EDITE</a></td>
                    </tr>

            </table></br>
            <p style="font-size: 16px"><a href="/admin/cars">Back</a></p>
        </flex>
    </form>
</box>
</body>
</html>