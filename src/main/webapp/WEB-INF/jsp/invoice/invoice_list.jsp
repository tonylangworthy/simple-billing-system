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
    			<h2>Invoices (${invoices.size()})</h2>
    		</div>
            <div>
                <c:if test="${products.size() == 0}">
                    <div class="alert alert-info">No invoices to display.</div>
                </c:if>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <td>Name</td>
                            <td>Number</td>
                            <td>Customer</td>
                            <td>Created Date</td>
                            <td>Created By</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${invoices}" var="i" varStatus="loop">
                        <tr class="clickable-row" data-href="/invoices/${i.invoiceId}">
                            <td>${i.invoiceName}</td>
                            <td>${i.invoiceNumber}</td>
                            <td>${i.customerName}</td>
                            <td>${i.createdAt}</td>
                            <td>${i.userName}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    	</div>

    	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script>
            $('.clickable-row').on('click', function(e) {
                console.log($(this).data('href'));
            });
        </script>

    </body>
</html>
