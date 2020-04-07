<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.langworthytech.simplebillingsystem.util.ValidationError"%>

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
    			<h2>Add Product / Service</h2>
    		</div>
            <form:form action="/products/create" modelAttribute="product" method="post">
              <div class="form-group">
                <label for="product-name">name</label>
                <form:input path="name" class="form-control ${name.hasErrors ? 'is-invalid':''}" id="product-name" aria-describedby="product-name-help" placeholder="Product or Service"/>
                <div class="invalid-feedback">${name.hasErrors ? name.errorMessage:''}</div>
                <small id="product-name-help" class="form-text text-muted">The name of your product of service (100 chars max)</small>
              </div>
              <div class="form-group">
                <label for="product-description">description</label>
                <form:textarea path="description" class="form-control ${description.hasErrors ? 'is-invalid':''}" id="product-description" aria-describedby="description-help" placeholder="Product description"/>
                <div class="invalid-feedback">${description.hasErrors ? description.errorMessage:''}</div>
                <small id="last-name-help" class="form-text text-muted">Describe the product or service</small>
              </div>
              <div class="form-group">
                <label for="product-price">price</label>
                <form:input path="price" class="form-control ${price.hasErrors ? 'is-invalid':''}" id="product-price" aria-describedby="product-price-help" placeholder="Price"/>
                <div class="invalid-feedback">${price.hasErrors ? price.errorMessage:''}</div>
                <small id="product-price-help" class="form-text text-muted">Price format 0.00</small>
              </div>
              <button type="submit" class="btn btn-primary">Save</button>
            </form:form>
        </div>
    	</div>

    	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
