<%@ page import="by.potapenko.database.entity.CarEntity" %>
<%@ page import="java.util.Optional" %>
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
  <title>Create new car</title>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
    }

    td {
      padding: 10px;
      border: 1px solid white;
    }

    th {
      padding: 10px;
      border: 1px solid white;
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
      width: auto;
      height: 30px;
      font-size: 10px;
    }

    input {
      width: auto;
      height: 30px;
      font-size: 10px;
    }
  </style>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<div><a href="/admin">Home </a>/ update new client</div>
<box>
  <form method="post" class="w3-card-4 w3-blue-grey w3-round-large w3-padding" style="width: 90%">
    <flex>
      <h1>${client.firstName} ${client.lastName}</h1><br>
      <c:if test="${update_client_error == false}">
        <p style="font-size: 20px" class="w3-text-green"> Client save successfully!</p>
        <p style="font-size: 16px"><a href="/admin/clients/client?id=${client.id}">Back</a></p>
      </c:if><br>
      <c:if test="${update_client_error == null}">
        <table style="font-size: 10px">
          <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Middle name</th>
            <th>Date of birthday</th>
            <th>Phone number</th></tr>
          </tr>

          <td><label for="first_name_id"></label>
            <input class="w3-round-large"
                   maxlength="20"
                   type="text"
                   placeholder="Enter first name"
                   name="first_name"
                   id="first_name_id"
                   value="${client.firstName}"
                   required/><br></td>

          <td><label for="last_name_id"></label>
            <input class="w3-round-large"
                   maxlength="20"
                   type="text"
                   placeholder="Enter last name"
                   name="last_name"
                   id="last_name_id"
                   value="${client.lastName}"
                   required/><br></td>

          <td><label for="middle_name_id"></label>
            <input class="w3-round-large"
                   maxlength="20"
                   type="text"
                   placeholder="Enter middle name"
                   name="middle_name"
                   id="middle_name_id"
                   value="${client.lastName}"
            /><br></td>

          <td><label for="date_of_birthday_id"></label>
            <input class="w3-round-large"
                   maxlength="20"
                   type="date"
                   placeholder="Enter date of birthday"
                   name="date_of_birthday"
                   id="date_of_birthday_id"
                   value="${client.dateOfBirthday}"
                   required/><br></td>

          <td><label for="phone_id"></label>
            <input class="w3-round-large"
                   maxlength="20"
                   type="tel"
                   placeholder="Enter your phone"
                   name="phone"
                   id="phone_id"
                   value="${client.contact.phone}"
            /><br></td>
        </table>

        <table style="font-size: 10px">
          <tr>
            <th>City</th>
            <th>Street</th>
            <th>House</th>
            <th>Apartment</th>
<%--            <th>Passport</th>--%>
            <th>Driver licence</th>
            <th>Date of creation</th>
          </tr>

          <td><label for="city_id"></label>
            <select class="w3-round-large"
                    id="city_id"
                    name="city"
                    required>
              <option value="">--City--</option>
              <option value="London">London</option>
              <option value="Liverpool">Liverpool</option>
              <option value="Bristol">Bristol</option>
              <option value="Manchester">Manchester</option>
              <option value="Plymouth">Plymouth</option>
              <option value="Southampton">Southampton</option>
              <option value="Norwich">Norwich</option>
              <option selected>${client.contact.city}</option>
            </select><br></td>

          <td><label for="street_id"></label>
            <select class="w3-round-large"
                    id="street_id"
                    name="street"
                    required>
              <option value="">--Street--</option>
              <option value="Albion Way">Albion Way</option>
              <option value="Back Alley ">Back Alley</option>
              <option value="Ball Court">Ball Court</option>
              <option value="Camomile Street">Camomile Street</option>
              <option value="Dean's Court">Dean's Court</option>
              <option value="Eastcheap ">Eastcheap</option>
              <option value="Hare Place">Hare Place</option>
              <option selected>${client.contact.street}</option>
            </select><br></td>

          <td><label for="build_id"></label>
            <input class="w3-round-large"
                   maxlength="3"
                   type="number"
                   placeholder="Enter house number"
                   name="house_number"
                   id="build_id"
                   value="${client.contact.houseNumber}"
            /><br></td>

          <td><label for="apartment_id"></label>
            <input class="w3-round-large"
                   maxlength="3"
                   type="number"
                   placeholder="Enter apartment number"
                   name="apartment_number"
                   id="apartment_id"
                   value="${client.contact.apartmentNumber}"
            /><br></td>

<%--          <td><label for="passport_id"></label>--%>
<%--            <input class="w3-round-large"--%>
<%--                   maxlength="10"--%>
<%--                   type="text"--%>
<%--                   placeholder="Enter passport number"--%>
<%--                   name="passport"--%>
<%--                   id="passport_id"--%>
<%--                   value="${client.document.passport}"--%>
<%--            /><br></td>--%>

          <td><label for="driver_license_id"></label>
            <input class="w3-round-large"
                   maxlength="10"
                   type="text"
                   placeholder="Enter driver license"
                   name="driver_license"
                   id="driver_license_id"
                   value="${client.document.driverLicense}"
            /><br></td>

          <td><label for="date_of_creation_id"></label>
            <input class="w3-round-large"
                   type="text"
                   name="date_of_creation"
                   id="date_of_creation_id"
                   value="${client.dateOfCreation}"
                   readonly/><br></td>
        </table>
        <input hidden type="number" name="client_id" value="${client.id}"/><br>
        <br>
        <button class="w3-btn w3-white w3-round-large" type="submit">Save</button>
        </br>

        <p style="font-size: 12px"><a href="/admin/clients/client/update_client?id=${client.id}">Back</a></p>
      </c:if>
      <c:if test="${update_cleint_error == true}">
        <p class="w3-text-red"> Client wasn't saved!</p>
        <a href="/admin/clients/client" class="w3-text-blue">Try again</a></p>
      </c:if><br>
    </flex>
  </form>
</box>
</body>
</html>