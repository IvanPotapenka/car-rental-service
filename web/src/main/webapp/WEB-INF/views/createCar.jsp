<%@ page import="by.potapenko.database.entity.Car" %>
<%@ page import="java.util.Optional" %>
<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 12.04.2023
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Add new car</title>
  <style>
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

     select {
       width: 250px;
       height: 40px;
       font-size: 16px;
     }
     input{
       width: 250px;
       height: 40px;
       font-size: 16px;
     }

  </style>

</head>
<body>
<div><a href="/">Home </a>
  <a href="${pageContext.request.contextPath}/add">/ add new car</a></div>
<box>

  <form method="post" class="w3-card-4 w3-blue-grey w3-round-large w3-padding" style="width: 70%">
    <flex>
      <c:if test="${param.vin_code == null}">
      <h1>Car</h1><br>

      <label for="brand_id">Brand</label>
      <select class="w3-round-large"
              id="brand_id"
              name="brand"
              required>
        <option value="" >--Choose a brand car--</option>
        <option value="Audy">Audy</option>
        <option value="BMW">BMW</option>
        <option value="Citroen">Citroen</option>
        <option value="Ford">Ford</option>
        <option value="Renault">Renault</option>
        <option value="Hyundai">Hyundai</option>
      </select><br>
          <label for="model">Model</label>
          <input class="w3-round-large"
                 maxlength="20"
                 type="text"
                 placeholder="Enter model car"
                 name="model"
                 id="model"
                 required/><br>
       <label for="year">Year</label>
        <select class="w3-round-large"
                id="year"
                name="year"
                required >
          <option value="">--Choose year of release--</option>
          <script>
            let y = 2010
            while(y<2020)
            {
              y++
              document.getElementById('year').innerHTML += '<option>'+y+'</option>'
            }
          </script>
        </select><br>
     <label for="vin_code">VIN code</label>
        <input class="w3-round-large"
             maxlength="17"
             type="text"
             placeholder="Enter VIN-code"
             name="vin_code"
             id="vin_code"
             required/>
             </input><br>
     <label for="engine">Engine</label>
      <input class="w3-round-large"
             maxlength="3"
             type="number"
             placeholder="Enter engine capacity"
             name="engine"
             step="0.1"
             id="engine"
             required/><br>
      <label for="price">Price</label>
      <input class="w3-round-large"
             maxlength="4"
             type="number"
             placeholder="Enter price rental"
             name="price"
             id="price"
             required/><br>
          <button class="w3-btn w3-white w3-round-large" type="submit">Add</button></br>
          <p style="font-size: 12px">If this car is already exist,</p>
          <p style="font-size: 16px"><a href="/cars">Choose</a></p>
      </c:if>
      <c:if test="${param.vin_code == true}">
         <p class="w3-text-red"> Car wasn't added!</p>
        <p class="w3-text-red"> Car with this VIN-code already exists!</p><p><a href="/add_car"class="w3-text-blue" >Try again</a></p>
      </c:if><br>
      <c:if test="${param.vin_code == false}">
          <p class="w3-text-green"> Car added successfully!</p><p><a href="/cars"class="w3-text-blue" >Look all cars</a></p>
      </c:if>

    </flex>
  </form>
</box>
</body>
</html>