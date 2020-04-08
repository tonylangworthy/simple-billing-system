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
        <jsp:include page="_navbar.jsp">
            <jsp:param name="userName" value="${userName}"/>
        </jsp:include>

    	<div class="container">
        <div class="list-table mx-auto">
    		<div class="">
    			<h2>Products (${products.size()})</h2>
    		</div>
            <div>
                <c:if test="${products.size() == 0}">
                    <div class="alert alert-info">No products to display. <a href="">Add products here</a>.</div>
                </c:if>
                <table class="table">
                    <thead>
                        <tr>
                            <td>Product ID</td>
                            <td>Goods/Service</td>
                            <td>SKU</td>
                            <td>Is Service?</td>
                            <td>Price</td>
                            <td>Date Added</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${products}" var="p" varStatus="loop">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td>${p.sku}</td>
                            <td>${p.isService}</td>
                            <td>${p.price}</td>
                            <td>${p.slashedCreatedAt}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    	</div>

    	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
