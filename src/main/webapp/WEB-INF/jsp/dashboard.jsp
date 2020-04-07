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
            <div class="card-deck mt-4">
              <div class="card border-light mb-3" style="max-width: 18rem;">
                <div class="card-header">Invoice</div>
                <div class="card-body">
                  <h5 class="card-title">Create an Invoice</h5>
                  <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                  <div class="btn-group mr-2" role="group">
                      <div class="btn btn-link"><a href="/invoices/create">Create</a></div>
                  </div>
                  <div class="btn-group mr-2" role="group">
                      <div class="btn btn-link"><a href="">View</a></div>
                  </div>
                </div>
              </div>
              <div class="card border-light mb-3" style="max-width: 18rem;">
              <div class="card-header">Customers</div>
              <div class="card-body">
                <h5 class="card-title">Manage customers</h5>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                  <div class="btn-group mr-2" role="group">
                      <div class="btn btn-link"><a href="/customers/create">Create</a></div>
                  </div>
                  <div class="btn-group mr-2" role="group">
                      <div class="btn btn-link"><a href="">View</a></div>
                  </div>
              </div>
            </div>
            <div class="card border-light mb-3" style="max-width: 18rem;">
            <div class="card-header">Products / Services</div>
            <div class="card-body">
              <h5 class="card-title">Light card title</h5>
              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                  <div class="btn-group mr-2" role="group">
                      <div class="btn btn-link"><a href="/products/create">Create</a></div>
                  </div>
                  <div class="btn-group mr-2" role="group">
                      <div class="btn btn-link"><a href="/products">View</a></div>
                  </div>
            </div>
          </div>

        </div>

    	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>