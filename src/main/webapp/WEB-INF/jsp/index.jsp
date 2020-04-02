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
    <link href="${jstlCss}" rel="stylesheet" />
    </head>
    <body>

    	<nav class="navbar navbar-inverse">
    		<div class="container">
    			<div class="navbar-header">
    				<a class="navbar-brand" href="#">Spring Boot</a>
    			</div>
    			<div id="navbar" class="collapse navbar-collapse">
    				<ul class="nav navbar-nav">
    					<li class="active"><a href="#">Home</a></li>
    					<li><a href="#about">About</a></li>
    				</ul>
    			</div>
    		</div>
    	</nav>

    	<div class="container">

    		<div class="starter-template">
    			<h1>Simple Billing System</h1>
    		</div>

    	</div>

    	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </body>
</html>
