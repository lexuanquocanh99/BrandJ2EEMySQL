<%--
  Created by IntelliJ IDEA.
  User: AaronLX
  Date: 7/1/2021
  Time: 9:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Brand Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> Brand Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/listbrand" class="nav-link">Brands</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Brands</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/newbrand" class="btn btn-success">Add New Brand</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Is_Enabled</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="brand" items="${listBrand}">

                <tr>
                    <td>
                        <c:out value="${brand.id}" />
                    </td>
                    <td>
                        <c:out value="${brand.name}" />
                    </td>
                    <td>
                        <c:if test="${brand.is_enabled == 1}">
                            <c:out value="Enable" />
                        </c:if>
                        <c:if test="${brand.is_enabled == 0}">
                            <c:out value="Disable" />
                        </c:if>
                    </td>
                    <td>
                        <c:out value="${brand.description}" />
                    </td>
                    <td><a href="editbrand?id=<c:out value='${brand.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="deletebrand?id=<c:out value='${brand.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>

</html>
