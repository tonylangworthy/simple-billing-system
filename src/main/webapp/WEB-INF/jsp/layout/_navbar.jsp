<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Simple Billing</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse pull-right" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="">Dashboard <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="billing-dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Billing
        </a>
        <div class="dropdown-menu" aria-labelledby="billing-dropdown">
          <a class="dropdown-item" href="/invoices/create">Create Invoice</a>
          <a class="dropdown-item" href="">Create Subscription</a>
          <a class="dropdown-item" href="/invoices">Invoices</a>
          <a class="dropdown-item" href="">Payments</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link dropdown-toggle" href="#" id="manage-dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Manage
        </a>
        <div class="dropdown-menu" aria-labelledby="manage-dropdown">
          <a class="dropdown-item" href="">Logout</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <c:out value="${param.userName}"/>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="/logout">Logout</a>
        </div>
      </li>
    </ul>
  </div>
</nav>