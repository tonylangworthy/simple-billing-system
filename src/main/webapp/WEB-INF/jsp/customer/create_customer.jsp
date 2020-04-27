<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
    <head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
    <c:url value="${pageContext.request.contextPath}/css/main.css" var="jstlCss" />
    <link type="text/css" href="${jstlCss}" rel="stylesheet" />
    </head>
    <body>

    	<div class="container">
        <div class="register-form mx-auto">
    		<div class="">
    			<h2>Create Customer</h2>
    		</div>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger" role="alert">${errorMessage}</div>
            </c:if>
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success" role="alert">${successMessage}</div>
            </c:if>
            <form:form action="/customers" modelAttribute="customer" method="post">
              <div class="form-group">
                <label for="first-name">first name</label>
                <form:input path="firstName" class="form-control ${firstName.hasErrors ? 'is-invalid':''}" id="first-name" aria-describedby="first-name-help" placeholder="First Name"/>
                <div class="invalid-feedback">${firstName.hasErrors ? firstName.errorMessage:''}</div>
                <small id="first-name-help" class="form-text text-muted">20 chars max</small>
              </div>
              <div class="form-group">
                <label for="last-name">last name</label>
                <form:input path="lastName" class="form-control ${lastName.hasErrors ? 'is-invalid':''}" id="last-name" aria-describedby="last-name-help" placeholder="Last Name"/>
                <div class="invalid-feedback">${lastName.hasErrors ? lastName.errorMessage:''}</div>
                <small id="last-name-help" class="form-text text-muted">20 chars max</small>
              </div>
              <div class="form-group">
                <label for="email-address">email</label>
                <form:input path="email" class="form-control ${email.hasErrors ? 'is-invalid':''}" id="email-address" aria-describedby="email-address-help" placeholder="Email Address"/>
                <div class="invalid-feedback">${email.hasErrors ? email.errorMessage:''}</div>
                <small id="email-address-help" class="form-text text-muted">50 chars max</small>
              </div>
              <div class="form-group">
                <label for="phone-number">phone</label>
                <form:input path="phone" class="form-control ${firstPassword.hasErrors ? 'is-invalid':''}" id="phone-number" aria-describedby="phone-help" placeholder="Phone"/>
                <div class="invalid-feedback">${phone.hasErrors ? phone.errorMessage:''}</div>
                <small id="phone-help" class="form-text text-muted">format xxx-xxx-xxxx</small>
              </div>
              <div class="form-group">
                <label for="company">company</label>
                <form:input path="companyName" class="form-control ${company.hasErrors ? 'is-invalid':''}" id="company" aria-describedby="company-help" placeholder="Company Name"/>
                <div class="invalid-feedback">${companyName.hasErrors ? companyName.errorMessage:''}</div>
                <small id="company-help" class="form-text text-muted">Customer's company name</small>
              </div>
              <button type="submit" class="btn btn-primary">Save</button>
            </form:form>
        </div>
    	</div>

    	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
