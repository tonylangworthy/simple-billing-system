<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
    <head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
    <c:url value="/css/main.css" var="jstlCss" />
    <link type="text/css" href="${jstlCss}" rel="stylesheet" />
    </head>
    <body>
    	<div class="container">

    		<div class="starter-template">
    			<h1>Simple Billing System</h1>
    		</div>

    	</div>

    	<script type="text/javascript" src="webjars/jquery/3.0.0/jquery.min.js"></script>
    	<script type="text/javascript" src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    </body>
</html>
