<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%

%>
<!DOCTYPE html>
<html lang="en">
    <head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
    </head>
    <body>

    	<div class="container">

    		<div class="starter-template">
    			<h2>Please Register</h2>
    		</div>
            <form:form action="/register" modelAttribute="registration" method="post">
              <div class="form-group">
                <label for="first-name">first name</label>
                <form:input path="firstName" class="form-control ${firstName.hasErrors ? 'is-invalid':''}" id="first-name" aria-describedby="first-name-help" placeholder="Please enter your first name"/>
                <div class="invalid-feedback">${firstName.hasErrors ? firstName.errorMessage:''}</div>
                <small id="first-name-help" class="form-text text-muted">20 chars max</small>
              </div>
              <div class="form-group">
                <label for="last-name">last name</label>
                <form:input path="lastName" class="form-control ${lastName.hasErrors ? 'is-invalid':''}" id="last-name" aria-describedby="last-name-help" placeholder="Please enter your last name"/>
                <div class="invalid-feedback">${lastName.hasErrors ? lastName.errorMessage:''}</div>
                <small id="last-name-help" class="form-text text-muted">20 chars max</small>
              </div>
              <div class="form-group">
                <label for="email-address">email</label>
                <form:input path="email" class="form-control ${email.hasErrors ? 'is-invalid':''}" id="email-address" aria-describedby="email-address-help" placeholder="Please enter a valid email address"/>
                <div class="invalid-feedback">${email.hasErrors ? email.errorMessage:''}</div>
                <small id="email-address-help" class="form-text text-muted">50 chars max</small>
              </div>
              <div class="form-group">
                <label for="first-password">password</label>
                <form:password path="firstPassword" class="form-control ${firstPassword.hasErrors ? 'is-invalid':'' || isPasswordMatch.hasErrors ? 'is-invalid':''}" id="first-password" aria-describedby="first-password-help" placeholder="Please create a password"/>
                <div class="invalid-feedback">${firstPassword.hasErrors ? firstPassword.errorMessage:''}</div>
                <small id="first-password-help" class="form-text text-muted">minimum of 8 characters</small>
              </div>
              <div class="form-group">
                <label for="confirm-password">confirm password</label>
                <form:password path="confirmPassword" class="form-control ${confirmPassword.hasErrors ? 'is-invalid':'' || isPasswordMatch.hasErrors ? 'is-invalid':''}" id="confirm-password" placeholder="Please confirm your password"/>
                <div class="invalid-feedback">${confirmPassword.hasErrors ? confirmPassword.errorMessage:''}</div>
                <div class="invalid-feedback">${isPasswordMatch.hasErrors ? isPasswordMatch.errorMessage:''}</div>
              </div>
              <button type="submit" class="btn btn-primary">Register</button>
            </form:form>
    	</div>

    	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </body>
</html>
