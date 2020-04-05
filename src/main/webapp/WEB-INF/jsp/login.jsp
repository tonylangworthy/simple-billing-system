<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
    <c:url value="${pageContext.request.contextPath}/css/main.css" var="jstlCss" />
    <link type="text/css" href="${jstlCss}" rel="stylesheet" />
    </head>
    <body>

    	<div class="container">
            <div class="login-form mx-auto">

                <div class="starter-template">
                    <h1>Simple Billing System Login</h1>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" role="alert">${errorMessage}</div>
                </c:if>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success" role="alert">${successMessage}</div>
                </c:if>
                <form method="post" action="/login">
                  <div class="form-group">
                    <label for="user-email">Email address</label>
                    <input type="text" name="username" class="form-control" id="user-email" aria-describedby="email-help" placeholder="Enter email">
                  </div>
                  <div class="form-group">
                    <label for="user-password">Password</label>
                    <input type="password" name="password" class="form-control" id="user-password" placeholder="Password">
                  </div>
                  <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="remember-me">
                    <label class="form-check-label" for="remember-me">Remember Me</label>
                  </div>
                  <div class="btn btn-link float-right"><a href="/register">Register here</a></div>
                  <button type="submit" class="btn btn-primary">Login</button>
                </form>
                </div>
            </div>

     	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
     	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
