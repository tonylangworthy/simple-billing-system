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
    			<h2>Add Products / Services to Invoice</h2>
    		</div>
            <form:form action="/invoices/create" modelAttribute="invoiceItems" method="post">
            <c:forEach var="product" items="${invoice.invoiceItems}" varStatus="loopCounter">
              <div class="custom-control custom-checkbox">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="${product.id}">
                <label class="custom-control-label" for="customCheck1"><c:out value="${product.productName}"/></label>
              </div>
              </c:forEach>
              <button type="submit" class="btn btn-primary">Save</buttoninvoiceItems>
            </form:form>
        </div>
    	</div>

    	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
